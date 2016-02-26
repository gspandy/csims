package com.gtm.csims.business.datacollection.submit.reader;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.business.datacollection.submit.base.BaseReportReader;
import com.gtm.csims.dao.BsAdmenforceDAO;
import com.gtm.csims.model.BsAdmenforce;
import com.gtm.csims.utils.ExcelUtil;

/**
 * 取值保存子类
 * 
 * @author Sweet
 * 
 */
public class AEnforcReader extends BaseReportReader {

    private static final Log LOG = LogFactory.getLog(AEnforcReader.class);

    private BsAdmenforceDAO bsAdmenforceDao;

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
        BsAdmenforce ae = null;
        for (int row = super.config.getBeginRow(); row <= lastRowIndex - 1; row++) {
            ae = new BsAdmenforce();
            ae.setAeno(ExcelUtil.getValue(sheet, row, 0));
            ae.setAeorgnm(ExcelUtil.getValue(sheet, row, 1));
            ae.setAeorgno(ExcelUtil.getValue(sheet, row, 2));
            ae.setAeedorgnm(ExcelUtil.getValue(sheet, row, 3));
            ae.setAeedorgno(ExcelUtil.getValue(sheet, row, 4));
            ae.setAeplanstdate(new Date());
            ae.setAeplantmdate(new Date());
            ae.setPrjnm(ExcelUtil.getValue(sheet, row, 9));
            ae.setStep(ExcelUtil.getValue(sheet, row, 9));
            ae.setPrjbasis(ExcelUtil.getValue(sheet, row, 9));
            ae.setAebasis(ExcelUtil.getValue(sheet, row, 9));
            ae.setAecontent(ExcelUtil.getValue(sheet, row, 9));
            ae.setAelimt(ExcelUtil.getValue(sheet, row, 9));
            ae.setAeway(ExcelUtil.getValue(sheet, row, 9));
            ae.setAepeople(ExcelUtil.getValue(sheet, row, 9));
            ae.setAeplan(ExcelUtil.getValue(sheet, row, 9));
            ae.setDptopnion(ExcelUtil.getValue(sheet, row, 9));
            ae.setDptpeople(ExcelUtil.getValue(sheet, row, 9));
            ae.setDptpeopleorg(ExcelUtil.getValue(sheet, row, 9));
            ae.setDptpeopleorgno(ExcelUtil.getValue(sheet, row, 9));
            ae.setChairopnion(ExcelUtil.getValue(sheet, row, 9));
            ae.setChairpeople(ExcelUtil.getValue(sheet, row, 9));
            ae.setChairpeopleorg(ExcelUtil.getValue(sheet, row, 9));
            ae.setChairpeopleorgno(ExcelUtil.getValue(sheet, row, 9));
            ae.setAestat(ExcelUtil.getValue(sheet, row, 9));
            ae.setRegpep(ExcelUtil.getValue(sheet, row, 9));
            ae.setRegorgno(ExcelUtil.getValue(sheet, row, 9));
            ae.setRegorgnm(ExcelUtil.getValue(sheet, row, 9));
            ae.setRegdt(new Date());
            ae.setStat(ExcelUtil.getValue(sheet, row, 9));
            ae.setFlag(ExcelUtil.getValue(sheet, row, 9));
            ae.setMaxino(i);
            ae.setCreatedate(new Date());
            ae.setUpdatedate(new Date());
            ae.setAeheadman(ExcelUtil.getValue(sheet, row, 9));
            ae.setAeother(ExcelUtil.getValue(sheet, row, 9));
            ae.setAemaster(ExcelUtil.getValue(sheet, row, 9));
            ae.setChairman(ExcelUtil.getValue(sheet, row, 9));
            ae.setDeptman(ExcelUtil.getValue(sheet, row, 9));
            ae.setDeptauditdate(new Date());
            ae.setChairauditdate(new Date());
            try {
                bsAdmenforceDao.save(ae);
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

    public void setBsAdmenforceDao(BsAdmenforceDAO bsAdmenforceDao) {
        this.bsAdmenforceDao = bsAdmenforceDao;
    }
}
