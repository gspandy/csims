package com.gtm.csims.business.datacollection.submit.reader;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.business.datacollection.submit.base.BaseReportReader;
import com.gtm.csims.dao.BsExamscoreDAO;
import com.gtm.csims.dao.BsOrgDAO;
import com.gtm.csims.model.BsExamscore;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.utils.DateUtil;
import com.gtm.csims.utils.ExcelUtil;

/**
 * 取值保存子类
 * 
 * @author Sweet
 * 
 */
public class ExamScoreReader extends BaseReportReader {
    private static final Log LOG = LogFactory.getLog(ExamScoreReader.class);

    private BsExamscoreDAO bsExamscoreDao;

    private BsOrgDAO bsOrgDao;

    public void setBsOrgDao(BsOrgDAO bsOrgDao) {
        this.bsOrgDao = bsOrgDao;
    }

    public void setBsExamscoreDao(BsExamscoreDAO bsExamscoreDao) {
        this.bsExamscoreDao = bsExamscoreDao;
    }

    /**
     * 保存报表数据
     * 
     * @param wb
     * @param reportParam
     * @throws ReportSubmitException
     */
    @Transactional(readOnly = false)
    protected void read(HSSFWorkbook wb, Map<String, String> reportParam) {
        HSSFSheet sheet = wb.getSheetAt(0);
        int lastRowIndex = super.getLastRow(wb, 0, "到此为止");
        LOG.info("lastRowIndex:" + lastRowIndex);
        int i = 1;
        String importNo = UUID.randomUUID().toString();
        BsExamscore es = null;
        Date anlDate = new Date();
        for (int row = super.config.getBeginRow(); row <= lastRowIndex - 1; row++) {
            try {
                es = new BsExamscore();
                es.setImportno(importNo);
                es.setPeonm(ExcelUtil.getValue(sheet, row, 0).trim());
                es.setPeoid(ExcelUtil.getValue(sheet, row, 1).trim());
                es.setCerttype(ExcelUtil.getValue(sheet, row, 2).trim());
                es.setPeotype(ExcelUtil.getValue(sheet, row, 3).trim());
                es.setOrgname(ExcelUtil.getValue(sheet, row, 4).trim());
                es.setOrgno(ExcelUtil.getValue(sheet, row, 5).trim());
                // 同时查询该用户所属机构的机构类型以及机构所属人民银行保存到成绩表
                // 供金融机构查询时使用
                BsOrg org = bsOrgDao.get(ExcelUtil.getValue(sheet, row, 5));
                if (org != null) {
                    es.setOrgtpno(org.getH());
                    es.setOrgtpnm(org.getI());
                    es.setPcbno(org.getPcbno());
                    es.setPcbnm(org.getPcbname());
                }
                es.setCity(ExcelUtil.getValue(sheet, row, 6).trim());
                es.setCountry(ExcelUtil.getValue(sheet, row, 7).trim());
                es.setTele(ExcelUtil.getValue(sheet, row, 8).trim());
                es.setExstdt(DateUtils.parseDate(ExcelUtil.getValue(sheet, row, 9).trim(),
                        DateUtil.DATE_FORMAT_ARRAY));
                es.setExtmdt(DateUtils.parseDate(ExcelUtil.getValue(sheet, row, 10).trim(),
                        DateUtil.DATE_FORMAT_ARRAY));
                es.setExtype(ExcelUtil.getValue(sheet, row, 11).trim());
                es.setScore(new BigDecimal(ExcelUtil.getValue(sheet, row, 12).trim()));
                es.setPass(new BigDecimal(ExcelUtil.getValue(sheet, row, 13).trim()));
                es.setFullmark(new BigDecimal(ExcelUtil.getValue(sheet, row, 14).trim()));
                es.setCrtor(reportParam.get("PARAMS_REPORTER").trim());
                es.setCrtdt(anlDate);

                es.setStat(StringUtils.EMPTY);
                es.setFlag(StringUtils.EMPTY);
                es.setCreatedate(new Date());
                es.setUpdateate(new Date());

                bsExamscoreDao.save(es);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (listener != null) {
                try {
                    listener.update(i, lastRowIndex - super.config.getBeginRow(), 0);
                    LOG.info("i=" + i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }

    /**
     * 删除已经存在该所属期数据
     * 
     * @param reportParam
     * @throws ReportSubmitException
     */
    @Transactional(readOnly = false)
    protected void deleteExsitData(Map<String, String> reportParam) throws ReportSubmitException {
    }
}
