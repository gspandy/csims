package com.gtm.csims.business.datacollection.submit.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.business.datacollection.submit.base.BaseReportValidator;

/**
 * 四川省各商业银行金融IC卡行业应用拓展情况季报表上报校验子类
 * 
 * @author Sweet
 * 
 */
public class AEnforcValidator extends BaseReportValidator {

    /**
     * 验证报表基本格式规则方法
     * 
     * @param wb
     * @return
     */
    @Override
    protected List<String> validateFormat(HSSFWorkbook wb)
            throws ReportSubmitException {
        List<String> validateMessages = new ArrayList<String>();
        int sheetNo = wb.getNumberOfSheets();
        if (sheetNo <= 0) {
            validateMessages.add("上报的Excel文件格式有误，不能正确获取sheet页");
            return validateMessages;
        }
        int lastRowIndex = super.getLastRow(wb, 0, LAST_ROW_FLAG_VALUE);
        if (lastRowIndex == 0) {
            validateMessages.add("Excel文件中应该指定最后行标志-" + LAST_ROW_FLAG_VALUE);
        }
        // 获得excel sheet对象
        // HSSFSheet sheet = wb.getSheetAt(0);
        // HSSFCell cell = null;
        //
        // // 循环excel第6行到37行
        // int lastRowIndex = super.getLastRow(wb, 0, "到此为止");
        // for (int i = this.config.getBeginRow(); i <= lastRowIndex - 1; i++) {
        // for (int j = this.config.getEndColumn(); j <= this.config
        // .getEndColumn(); j++) {
        // try {
        // cell = sheet.getRow(i).getCell(j);
        // } catch (Exception e) {
        // throw new ReportSubmitException("获取报表第" + (i + 1) + "行，第"
        // + (j + 1) + "列时发生错误,请核对报表格式");
        // }
        // // 如果获取待校验区域单元格对象为空
        // if (cell == null) {
        // validateMessages.add("获取报表第" + (i + 1) + "行，第" + (j + 1)
        // + "列单元格对象为空，应该为数字");
        // }
        // }
        // }
        return validateMessages;
    }

    /**
     * 根据所属期验证报表是否已经存在
     * 
     * @param reportParam
     * @return
     */
    @Override
    protected List<String> validateThisDrIsExsit(Map<String, String> reportParam)
            throws ReportSubmitException {
        List<String> validateMessages = new ArrayList<String>();
        return validateMessages;
    }

    /**
     * 验证报表业务规则方法
     * 
     * 1.与上一期本报表比较或者与上一期月对应季度季报（按地区）比较
     * 
     * 2.本期报表内部校验
     * 
     * @param wb
     * @param reportParam
     * @return
     */
    @Override
    protected List<String> validate(HSSFWorkbook wb,
            Map<String, String> reportParam) throws ReportSubmitException {
        List<String> validateMessages = new ArrayList<String>();
        return validateMessages;
    }

}
