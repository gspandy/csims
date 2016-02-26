package com.gtm.csims.job.backup;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 备份.
 * 
 * @author yangyongzhi
 *
 */
public class BackupDbJob implements ApplicationContextAware {

	/**
	 * 调试日志.
	 */
	protected static Logger LOGGER = LoggerFactory.getLogger(BackupDbJob.class);

	/**
	 * 初始化系统所有表名.
	 */
	private static String[] TABLE_NAMES = new String[] { "BS_ADMENFCANLY",
			"BS_ADMENFORCE", "BS_ADMPUNISH", "BS_ADMPUNISHCONS",
			"BS_AECONCLUSION", "BS_AEINSPECTION", "BS_AEINSPECTION_ANL",
			"BS_AEPEOPLE", "BS_AERECTIFICATION", "BS_AREA", "BS_ATTCACHMENTS",
			"BS_BANK", "BS_BUSIEVAL", "BS_BUSIEVALINFO", "BS_BUSIEVALSTA",
			"BS_DEPT", "BS_DICTIONARY", "BS_EVALPROPORTION", "BS_EXAMSCORE",
			"BS_FACTBOOK", "BS_LOG", "BS_MESSAGE", "BS_NOGENERATE",
			"BS_OFFSITECHECK", "BS_ORG", "BS_ORG_FOR_MINIEXAM",
			"BS_ORGCOMINFO", "BS_ORGRELEVANTINFO", "BS_ORGTYPE", "BS_PCB",
			"BS_PHOTO", "BS_TEST", "BS_TRAINING", "BS_USERAPPLYINFO",
			"BS_USERCOMINFO", "BS_USERINFOOFJG", "BS_USERINFOOFZX",
			"BS_USERRELEVANTINFO", "BS_USERREPORTINFO", "BS_WORKBASIS",
			"BS_WORKCHECK", "BS_WORKCOMING", "BS_WORKGOAWAY",
			"BS_WORKTALKSUMMARY", "BS_ZXORGDOC", "BS_ZXPERSONALDOC",
			"BS_ZXUSERAPPLYINFO", "JG_APP_PRINCIPAL", "JG_CREDENTIAL",
			"JG_DOMAIN", "JG_PERMISSION", "JG_PRINCIPAL",
			"JG_PRINCIPAL_DOMAIN", "JG_PRINCIPAL_HIERARCHY",
			"JG_PRINCIPAL_PERMISSION", "JG_ST_CREDENTIAL", "JG_ST_PRINCIPAL",
			"JG_ST_USER", "JG_USER", "JG_USER_PRINCIPAL" };

	public static Map<String, String> TABLE_ID_FIELD_NAME = new HashMap<String, String>();
	static {
		TABLE_ID_FIELD_NAME.put("BS_ADMENFCANLY", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ADMENFORCE", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ADMPUNISH", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ADMPUNISHCONS", "ID");
		TABLE_ID_FIELD_NAME.put("BS_AECONCLUSION", "ID");
		TABLE_ID_FIELD_NAME.put("BS_AEINSPECTION", "ID");
		TABLE_ID_FIELD_NAME.put("BS_AEINSPECTION_ANL", "ID");
		TABLE_ID_FIELD_NAME.put("BS_AEPEOPLE", "ID");
		TABLE_ID_FIELD_NAME.put("BS_AERECTIFICATION", "ID");
		TABLE_ID_FIELD_NAME.put("BS_AREA", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ATTCACHMENTS", "ID");
		TABLE_ID_FIELD_NAME.put("BS_BACKUP", "ID");
		TABLE_ID_FIELD_NAME.put("BS_BANK", "NO");
		TABLE_ID_FIELD_NAME.put("BS_BUSIEVAL", "ID");
		TABLE_ID_FIELD_NAME.put("BS_BUSIEVALINFO", "ID");
		TABLE_ID_FIELD_NAME.put("BS_BUSIEVALSTA", "ID");
		TABLE_ID_FIELD_NAME.put("BS_DEPT", "ID");
		TABLE_ID_FIELD_NAME.put("BS_DICTIONARY", "ID");
		TABLE_ID_FIELD_NAME.put("BS_EVALPROPORTION", "ID");
		TABLE_ID_FIELD_NAME.put("BS_EXAMSCORE", "ID");
		TABLE_ID_FIELD_NAME.put("BS_FACTBOOK", "ID");
		TABLE_ID_FIELD_NAME.put("BS_LOG", "ID");
		TABLE_ID_FIELD_NAME.put("BS_MESSAGE", "ID");
		TABLE_ID_FIELD_NAME.put("BS_NOGENERATE", "ID");
		TABLE_ID_FIELD_NAME.put("BS_OFFSITECHECK", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ORG", "NO");
		TABLE_ID_FIELD_NAME.put("BS_ORG_FOR_MINIEXAM", "NO");
		TABLE_ID_FIELD_NAME.put("BS_ORGCOMINFO", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ORGRELEVANTINFO", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ORGTYPE", "NO");
		TABLE_ID_FIELD_NAME.put("BS_PCB", "NO");
		TABLE_ID_FIELD_NAME.put("BS_PHOTO", "ID");
		TABLE_ID_FIELD_NAME.put("BS_TEST", "ID");
		TABLE_ID_FIELD_NAME.put("BS_TRAINING", "ID");
		TABLE_ID_FIELD_NAME.put("BS_USERAPPLYINFO", "ID");
		TABLE_ID_FIELD_NAME.put("BS_USERCOMINFO", "ID");
		TABLE_ID_FIELD_NAME.put("BS_USERINFOOFJG", "ID");
		TABLE_ID_FIELD_NAME.put("BS_USERINFOOFZX", "ID");
		TABLE_ID_FIELD_NAME.put("BS_USERRELEVANTINFO", "ID");
		TABLE_ID_FIELD_NAME.put("BS_USERREPORTINFO", "ID");
		TABLE_ID_FIELD_NAME.put("BS_WORKBASIS", "ID");
		TABLE_ID_FIELD_NAME.put("BS_WORKCHECK", "ID");
		TABLE_ID_FIELD_NAME.put("BS_WORKCOMING", "ID");
		TABLE_ID_FIELD_NAME.put("BS_WORKGOAWAY", "ID");
		TABLE_ID_FIELD_NAME.put("BS_WORKTALKSUMMARY", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ZXORGDOC", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ZXPERSONALDOC", "ID");
		TABLE_ID_FIELD_NAME.put("BS_ZXUSERAPPLYINFO", "ID");
		TABLE_ID_FIELD_NAME.put("JG_APP_PRINCIPAL", "ID");
		TABLE_ID_FIELD_NAME.put("JG_CREDENTIAL", "ID");
		TABLE_ID_FIELD_NAME.put("JG_DOMAIN", "ID");
		TABLE_ID_FIELD_NAME.put("JG_PERMISSION", "ID");
		TABLE_ID_FIELD_NAME.put("JG_PRINCIPAL", "ID");
		TABLE_ID_FIELD_NAME.put("JG_PRINCIPAL_DOMAIN", "DOMAIN_ID");
		TABLE_ID_FIELD_NAME.put("JG_PRINCIPAL_HIERARCHY", "PRINCIPAL_ASC_ID");
		TABLE_ID_FIELD_NAME.put("JG_PRINCIPAL_PERMISSION", "PERMISSION_ID");
		TABLE_ID_FIELD_NAME.put("JG_ST_CREDENTIAL", "ID");
		TABLE_ID_FIELD_NAME.put("JG_ST_PRINCIPAL", "ID");
		TABLE_ID_FIELD_NAME.put("JG_ST_USER", "ID");
		TABLE_ID_FIELD_NAME.put("JG_USER", "ID");
		TABLE_ID_FIELD_NAME.put("JG_USER_PRINCIPAL", "USER_ID");
	}
	/**
	 * Spring IOC上下文.
	 */
	protected ApplicationContext applicationContext;

	/**
	 * 处理线程池.
	 */
	protected ThreadPoolTaskExecutor backupExecutor;

	private BackupDbService backupDbService;

	private FileService fileService;

	/**
	 * 启动多线程获取数据库数据，写入临时数据文件.
	 */
	public void doQueryDbAndWriteTmpFile() {
		for (int i = 0; i < TABLE_NAMES.length; i++) {
			GenerateTempFileThread generateTempFileThread = (GenerateTempFileThread) this.applicationContext
					.getBean("generateTempFileThread");
			generateTempFileThread.setTableName(TABLE_NAMES[i]);
			this.backupExecutor.execute(generateTempFileThread);
		}
		GenerateSqlTempFileThread generateSqlTempFileThread = (GenerateSqlTempFileThread) this.applicationContext
				.getBean("generateSqlTempFileThread");
		generateSqlTempFileThread.setType(1);
		this.backupExecutor.execute(generateSqlTempFileThread);
	}

	/**
	 * 压缩临时文件为最终备份文件，并记录备份文件路径到数据库.
	 */
	public void doZipAndRecordPath() {
		String zipFileName = null;
		try {
			zipFileName = fileService.doZip();
		} catch (Exception e) {
			LOGGER.error("生成压缩文件失败", e);
		}
		if (StringUtils.isNotBlank(zipFileName)) {
			backupDbService.insertBackupRecord(zipFileName);
		}
		fileService.clean();

	}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.applicationContext = arg0;
	}

	public void setBackupExecutor(ThreadPoolTaskExecutor backupExecutor) {
		this.backupExecutor = backupExecutor;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setBackupDbService(BackupDbService backupDbService) {
		this.backupDbService = backupDbService;
	}

}
