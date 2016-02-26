package com.gtm.csims.job.backup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.transaction.annotation.Transactional;

/**
 * 备份过程查询数据库数据并写入文本文件.
 * 
 * @author yangyongzhi
 *
 */
public class BackupDbService {

	/**
	 * 日志调试.
	 */
	private static Logger LOGGER = Logger.getLogger(BackupDbService.class);

	/**
	 * 文本文件临时存放路径.
	 */
	public static final String BACKUP_TEMP_PATH = System
			.getProperty("java.io.tmpdir") + "BackupDbJob_tmp" + File.separator;

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 对单张表生成临时文本数据文件.
	 * 
	 * @param tableName
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void generateSingleTableData(String tableName) {
		if (StringUtils.isBlank(tableName)) {
			throw new IllegalArgumentException("Table name is null or blank");
		}
		File dir = new File(BACKUP_TEMP_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
			LOGGER.debug("创建对单张表生成临时文本数据文件存放文件夹为" + BACKUP_TEMP_PATH);
		}
		FileOutputStream scheme_fos = null;
		BufferedOutputStream scheme_bos = null;
		FileOutputStream data_fos = null;
		BufferedOutputStream data_bos = null;
		try {
			scheme_fos = new FileOutputStream(new File(BACKUP_TEMP_PATH
					+ tableName + "_scheme.txt"));
			scheme_bos = new BufferedOutputStream(scheme_fos);
			int begin = 0, end = 1000;
			String sql = String
					.format("select * from (select b.*,rownumber() over() as rowid from %s b order by %s) a where a.rowid > %d and a.rowid <= %d",
							tableName,
							BackupDbJob.TABLE_ID_FIELD_NAME.get(tableName),
							begin, end);
			RowCountCallbackHandler rcch = new RowCountCallbackHandler();
			jdbcTemplate.query(sql, rcch);
			String[] coloumnName = rcch.getColumnNames();
			int[] coloumnType = rcch.getColumnTypes();
			LOGGER.debug("查询SQL为:" + sql);
			StringBuilder sb = new StringBuilder();
			// 写记录mete信息
			sb.append(StringUtils.join(coloumnName, ","));
			sb.append("\r\n");
			scheme_bos.write(sb.toString().getBytes(CharEncoding.UTF_8));
			scheme_bos.flush();
			// 写入数据
			data_fos = new FileOutputStream(new File(BACKUP_TEMP_PATH
					+ tableName + "_data.txt"));
			data_bos = new BufferedOutputStream(data_fos);
			// 查询第一页
			List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
			while (CollectionUtils.isNotEmpty(results)) {
				results = this.write(results, data_bos, sb, coloumnName,
						coloumnType);
				begin = end;
				end = end + 1000;
				results = jdbcTemplate
						.queryForList(String
								.format("select * from (select b.*,rownumber() over() as rowid from %s b order by %s) a where a.rowid > %d and a.rowid <= %d",
										tableName,
										BackupDbJob.TABLE_ID_FIELD_NAME
												.get(tableName), begin, end));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(scheme_fos);
			IOUtils.closeQuietly(data_fos);
			IOUtils.closeQuietly(scheme_bos);
			IOUtils.closeQuietly(data_bos);
		}
	}

	/**
	 * 将数据库查询记录写入文件流.
	 * 
	 * @param results
	 * @param os
	 * @param sb
	 * @param coloumnName
	 * @param coloumnType
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> write(List<Map<String, Object>> results,
			OutputStream os, StringBuilder sb, String[] coloumnName,
			int[] coloumnType) throws Exception {
		for (Iterator<Map<String, Object>> iterator = results.iterator(); iterator
				.hasNext();) {
			sb.setLength(0);
			Map<String, Object> map = iterator.next();
			int setCount = 0;
			for (int i = 0; i < coloumnName.length; i++) {
				if (map.get(coloumnName[i]) == null) {
					sb.append(StringUtils.EMPTY);
				} else {
					this.getStr(coloumnType[i], map.get(coloumnName[i])
							.toString(), sb);
				}
				if (setCount < coloumnName.length - 1) {
					sb.append(",");
				}
				setCount++;
			}
			sb.append("\r\n");
			os.write(sb.toString().getBytes(CharEncoding.UTF_8));
		}
		os.flush();
		return results;
	}

	/**
	 * 根据数据库字段类型返回数据文件字段的字符串.
	 * 
	 * @param type
	 * @param colValue
	 * @param sb
	 */
	private void getStr(int type, String colValue, StringBuilder sb) {
		switch (type) {
		case Types.BIGINT:
			sb.append(colValue);
			break;
		case Types.DECIMAL:
			sb.append(colValue);
			break;
		case Types.DOUBLE:
			sb.append(colValue);
			break;
		case Types.FLOAT:
			sb.append(colValue);
			break;
		case Types.INTEGER:
			sb.append(colValue);
			break;
		case Types.NUMERIC:
			sb.append(colValue);
			break;
		case Types.SMALLINT:
			sb.append(colValue);
			break;
		case Types.TINYINT:
			sb.append(colValue);
			break;
		case Types.VARCHAR:
			sb.append("\"");
			sb.append(colValue);
			sb.append("\"");
			break;
		case Types.CHAR:
			sb.append("\"");
			sb.append(colValue);
			sb.append("\"");
			break;
		case Types.LONGVARCHAR:
			sb.append("\"");
			sb.append(colValue);
			sb.append("\"");
			break;
		default:
			sb.append("\"");
			sb.append(colValue);
			sb.append("\"");
			break;
		}
	}

	/**
	 * 保存备份最终文件存放路径到数据库.
	 * 
	 * @param backupFileName
	 */
	@Transactional
	public void insertBackupRecord(String backupFileName) {
		if (StringUtils.isBlank(backupFileName)) {
			LOGGER.error("保存备份最终记录到数据库时，文件名称为空");
			return;
		}
		String sql = "insert into BS_BACKUP (id, filename, filepath) values (?,?,?)";
		jdbcTemplate.update(
				sql,
				new Object[] {
						UUID.randomUUID().toString(),
						backupFileName,
						String.format("/%s%s",
								FileService.BACKUP_ZIP_FILE_FOLDER,
								backupFileName) }, new int[] { Types.VARCHAR,
						Types.VARCHAR, Types.VARCHAR });
	}

	/**
	 * 需要重设sequence.
	 */
	private static final String[] NEED_RESET_SEQUENCE_NAMES = new String[] {
			"jg_user_seq", "jg_credential_seq", "jg_permission_seq",
			"jg_domain_seq", "JG_APP_PRINCIPAL_seq", "JG_PRINCIPAL_seq" };
	private static final String[] NEED_RESET_SEQUENCE_TABLE_NAMES = new String[] {
			"JG_USER", "JG_CREDENTIAL", "JG_PERMISSION", "JG_DOMAIN",
			"JG_APP_PRINCIPAL", "JG_PRINCIPAL" };

	/**
	 * 对cpims中需要重设sequence的表导出重设sql.
	 * 
	 * @param tableName
	 * @throws Exception
	 */
	public void generateResetSquence() {
		LOGGER.error("开始生成重设sequence sql");
		File dir = new File(BACKUP_TEMP_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
			LOGGER.debug("创建重设sequence临时文本数据文件存放文件夹为" + BACKUP_TEMP_PATH);
		}
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(new File(BACKUP_TEMP_PATH
					+ "reset_sequence.sql"));
			bos = new BufferedOutputStream(fos);
			StringBuffer content = new StringBuffer();
			for (int i = 0; i < NEED_RESET_SEQUENCE_NAMES.length; i++) {
				content.append("DROP SEQUENCE ")
						.append(NEED_RESET_SEQUENCE_NAMES[i])
						.append(" restrict ").append(";").append("\r\n");
				content.append("create sequence ")
						.append(NEED_RESET_SEQUENCE_NAMES[i]).append(";")
						.append("\r\n");
				bos.write(content.toString().getBytes(CharEncoding.UTF_8));
				content.setLength(0);
			}
			for (int i = 0; i < NEED_RESET_SEQUENCE_TABLE_NAMES.length; i++) {
				String tableName = NEED_RESET_SEQUENCE_TABLE_NAMES[i];
				int maxId = jdbcTemplate.queryForInt(String.format(
						"select max(id) from %s", tableName));
				content.append("ALTER SEQUENCE ")
						.append(NEED_RESET_SEQUENCE_NAMES[i])
						.append(" RESTART WITH ").append(maxId + 1).append(";")
						.append("\r\n");
				bos.write(content.toString().getBytes(CharEncoding.UTF_8));
				content.setLength(0);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fos);
			IOUtils.closeQuietly(bos);
		}
	}
}
