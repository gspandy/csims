package com.gtm.csims.business.datacollection.submit.reader;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.business.datacollection.submit.base.BaseReportReader;
import com.gtm.csims.dao.BsAeinspectionAnlDAO;
import com.gtm.csims.model.BsAeinspectionAnl;
import com.gtm.csims.utils.ExcelUtil;

/**
 * 取值保存子类
 * 
 * @author Sweet
 * 
 */
public class AEinspectionAnlReader extends BaseReportReader {

    private static final Log LOG = LogFactory.getLog(AEinspectionAnlReader.class);

    private BsAeinspectionAnlDAO bsAeinspectionAnlDao;

    public void setBsAeinspectionAnlDao(
            BsAeinspectionAnlDAO bsAeinspectionAnlDao) {
        this.bsAeinspectionAnlDao = bsAeinspectionAnlDao;
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
        BsAeinspectionAnl ae = null;
        Date anlDate = new Date();
        for (int row = super.config.getBeginRow(); row <= lastRowIndex - 1; row++) {
            ae = new BsAeinspectionAnl();
            ae.setA1(ExcelUtil.getValue(sheet, row, 0));
            ae.setB1(ExcelUtil.getValue(sheet, row, 1));
            ae.setC1(Long.valueOf(ExcelUtil.getValue(sheet, row, 2)));
            ae.setD1(Long.valueOf(ExcelUtil.getValue(sheet, row, 3)));
            ae.setE1(Long.valueOf(ExcelUtil.getValue(sheet, row, 4)));
            ae.setF1(Long.valueOf(ExcelUtil.getValue(sheet, row, 5)));
            ae.setG1(Long.valueOf(ExcelUtil.getValue(sheet, row, 6)));
            ae.setH1(Long.valueOf(ExcelUtil.getValue(sheet, row, 7)));
            ae.setI1(Long.valueOf(ExcelUtil.getValue(sheet, row, 8)));
            ae.setJ1(Long.valueOf(ExcelUtil.getValue(sheet, row, 9)));
            ae.setK1(Long.valueOf(ExcelUtil.getValue(sheet, row, 10)));
            ae.setL1(Long.valueOf(ExcelUtil.getValue(sheet, row, 11)));
            ae.setM1(Long.valueOf(ExcelUtil.getValue(sheet, row, 12)));
            ae.setN1(ExcelUtil.getValue(sheet, row, 13));
            ae.setO1(ExcelUtil.getValue(sheet, row, 14));
            ae.setP1(new BigDecimal(ExcelUtil.getValue(sheet, row, 15)));
            ae.setQ1(new BigDecimal(ExcelUtil.getValue(sheet, row, 16)));
            ae.setAnldate(anlDate);
            ae.setReporter(reportParam.get("PARAMS_REPORTER"));
            ae.setStat("");
            ae.setFlag("");
            ae.setCreatedate(new Date());
            ae.setUpdateate(new Date());
            try {
                bsAeinspectionAnlDao.save(ae);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (listener != null) {
                try {
                    listener.update(i, lastRowIndex
                            - super.config.getBeginRow(), 0);
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
    protected void deleteExsitData(Map<String, String> reportParam)
            throws ReportSubmitException {
    }

}
