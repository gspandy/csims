package com.gtm.csims.business.datacollection.submit.reader;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.business.datacollection.submit.base.CSVFileReader;
import com.gtm.csims.dao.BsExamscoreDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.model.BsExamscore;
import com.gtm.csims.model.BsOrg;

/**
 * 考试成绩CSV格式读取实现类.<br>
 * 
 * 用于导入考试系统的考试成绩文件
 * 
 * @author Sweet
 * 
 */
public class ExamScoreCsvReader extends CSVFileReader {

	/**
	 * 日志调试.
	 */
	private static final Logger LOGGER = Logger.getLogger(ExamScoreCsvReader.class);

	/**
     * 
     */
	private static final String[] DATE_FORMATS = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	private BsExamscoreDAO bsExamscoreDao;

	private BsOrgDAO bsOrgDao;

	private JdbcTemplate jdbcTemplate;

	@Override
	@Transactional(readOnly = false)
	protected void read(ArrayList<String[]> csvList, Map<String, String> reportParam) throws ReportSubmitException {
		if (CollectionUtils.isEmpty(csvList)) {
			return;
		}
		Date anlDate = new Date();
		int lastRowIndex = csvList.size();
		LOGGER.debug(String.format("[%s]需要导入考试成绩数量 :%d", DateFormatUtils.format(anlDate, DATE_FORMATS[0]), lastRowIndex));
		int successCount = 0;
		int failureCount = 0;
		int i = 1;
		String importNo = UUID.randomUUID().toString();
		BsExamscore es = null;

		for (int row = 0; row < lastRowIndex; row++) {
			if (ArrayUtils.isEmpty(csvList.get(row))) {
				LOGGER.error(String.format("考试成绩第%d行为空", row));
				failureCount++;
				i++;
				continue;
			}
			try {
				es = this.queryBsExamscore(csvList.get(row));
				if (es == null) {
					es = new BsExamscore();
				}
				es.setImportno(importNo);
				// 姓名
				es.setPeonm(StringUtils.trimToEmpty(csvList.get(row)[0]));
				// 准考证号
				es.setPeoid(StringUtils.trimToEmpty(csvList.get(row)[1]));
				// 证件类型
				es.setCerttype(StringUtils.trimToEmpty(csvList.get(row)[2]));
				// 用户类型
				es.setPeotype(StringUtils.trimToEmpty(csvList.get(row)[3]));
				// 金融机构
				es.setOrgname(StringUtils.trimToEmpty(csvList.get(row)[4]));
				// 金融机构编码
				es.setOrgno(StringUtils.trimToEmpty(csvList.get(row)[5]));
				// 同时查询该用户所属机构的机构类型以及机构所属人民银行保存到成绩表
				// 供金融机构查询时使用
				BsOrg org = bsOrgDao.get(StringUtils.trimToEmpty(csvList.get(row)[5]));
				if (org != null) {
					if (StringUtils.equals(org.getF(), "3")) {
						// 如果当前被检查机构为“农村信用合作社（含联社）”
						es.setOrgtpno(org.getF());
						es.setOrgtpnm(org.getI());
					} else {
						es.setOrgtpno(org.getH());
						es.setOrgtpnm(org.getI());
					}
					es.setPcbno(org.getPcbno());
					es.setPcbnm(org.getPcbname());
				}
				// 城市编码
				es.setCity(StringUtils.trimToEmpty(csvList.get(row)[6]));
				// 地区编码
				es.setCountry(StringUtils.trimToEmpty(csvList.get(row)[7]));
				// 联系电话
				es.setTele(StringUtils.trimToEmpty(csvList.get(row)[8]));
				// 考试开始时间
				es.setExstdt(DateUtils.parseDate(StringUtils.trimToEmpty(csvList.get(row)[9]), DATE_FORMATS));
				// 考试结束时间
				es.setExtmdt(DateUtils.parseDate(StringUtils.trimToEmpty(csvList.get(row)[10]), DATE_FORMATS));
				// 试卷
				es.setExtype(StringUtils.trimToEmpty(csvList.get(row)[11]));
				// 成绩
				es.setScore(new BigDecimal(StringUtils.trimToEmpty(csvList.get(row)[12])));
				// 及格分数
				es.setPass(new BigDecimal(StringUtils.trimToEmpty(csvList.get(row)[13])));
				// 总分
				es.setFullmark(new BigDecimal("100"));

				es.setCrtor(StringUtils.trimToEmpty(reportParam.get("PARAMS_REPORTER")));
				es.setCrtdt(anlDate);

				es.setStat(StringUtils.EMPTY);
				es.setFlag(StringUtils.EMPTY);
				es.setCreatedate(new Date());
				es.setUpdateate(new Date());

				bsExamscoreDao.save(es);
				
				// Update the newest score of a person.
				try {
					jdbcTemplate.update("update BS_USERINFOOFJG set SCORE = '" + es.getScore().toPlainString()
					        + "' where LOGINID = '" + es.getPeoid() + "' and USERSTATUS='启用' ");
				} catch (Exception e) {
					LOGGER.error(e);
				}
				
				successCount++;
				i++;
			} catch (Exception e) {
				LOGGER.error("导入考试成绩失败", e);
				failureCount++;
				i++;
				continue;
			}
			if (listener != null) {
				try {
					listener.update(i, lastRowIndex, 0);
					LOGGER.debug(String.format("正在处理第%d条", i));
				} catch (Exception e) {
					LOGGER.error(String.format("[%s]导入考试成绩时更新进度失败", DateFormatUtils.format(anlDate, DATE_FORMATS[0])),
					        e);
				}
			}

		}
		LOGGER.debug(String.format("[%s]导入考试成绩 :成功%d，失败%d", DateFormatUtils.format(anlDate, DATE_FORMATS[0]),
		        successCount, failureCount));
	}

	/**
	 * 判断当前用户在此次考试中是否已经存在成绩.
	 * 
	 * @param csvRow
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private BsExamscore queryBsExamscore(String[] csvRow) {
		List existExamScores = null;
		try {
			existExamScores = bsExamscoreDao.find("from BsExamscore where Peoid = ? and Exstdt = ? and Extmdt = ? ",
			        csvRow[1], DateUtils.parseDate(StringUtils.trimToEmpty(csvRow[9]), DATE_FORMATS),
			        DateUtils.parseDate(StringUtils.trimToEmpty(csvRow[10]), DATE_FORMATS));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		if (CollectionUtils.isEmpty(existExamScores)) {
			return null;
		}
		return (BsExamscore) existExamScores.get(0);
	}

	public void setBsOrgDao(BsOrgDAO bsOrgDao) {
		this.bsOrgDao = bsOrgDao;
	}

	public void setBsExamscoreDao(BsExamscoreDAO bsExamscoreDao) {
		this.bsExamscoreDao = bsExamscoreDao;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
