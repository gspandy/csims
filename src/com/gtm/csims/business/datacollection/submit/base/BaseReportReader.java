package com.gtm.csims.business.datacollection.submit.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.utils.ExcelUtil;

/**
 * 报表上传基类
 * 
 * @author Sweet
 * 
 */
public abstract class BaseReportReader {

    // 上报报表时传递参数名常量
    public static final String PARAM_SUBMIT_NO = "SubmitNo";
    public static final String PARAM_BANKNO = "BankNo";
    public static final String PARAM_BANKNAME = "BankName";
    public static final String PARAM_DURING = "During";
    public static final String PARAM_BANKAREA = "BankArea";
    public static final String PARAM_BANKTYPE_NAME = "BankTypeName";
    public static final String PARAM_BANKTYPE_NO = "BankTypeNo";
    public static final int MAX_ARRAY_INDEX = 2000;
    public static final int FIND_EXCEL_LAST_ROW_RANGE = 5000;

    protected BaseReportConfig config;
    protected BaseReportValidator validator;
    protected ExcelProgressListener listener;

    @Transactional(readOnly = false)
    public List<String> read(InputStream is, Map<String, String> reportParam)
            throws ReportSubmitException {
        HSSFWorkbook wb = null;
        try {
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ReportSubmitException("[" + reportParam.get(PARAM_DURING) + "]"
                    + config.getReportName() + "报送失败，原因为上传文件时发生系统错误" + e.getMessage());
        }
        List<String> valdtRst = null;
        // 验证报表格式正确性
        try {
            valdtRst = validator.validateFormat(wb);
            // 如果验证信息为空,则验证通过,否则返回验证信息，不继续读取报表
            if (valdtRst != null && valdtRst.size() > 0) {
                return valdtRst;
            }
        } catch (ReportSubmitException e) {
            e.printStackTrace();
            throw new ReportSubmitException("[" + reportParam.get(PARAM_DURING) + "]"
                    + config.getReportName() + "报送失败，原因为校验文件格式时发生系统错误" + e.getMessage());
        }

        // 验证报表业务正确性
        try {
            valdtRst = validator.validate(wb, reportParam);
        } catch (ReportSubmitException e) {
            e.printStackTrace();
            throw new ReportSubmitException("[" + reportParam.get(PARAM_DURING) + "]"
                    + config.getReportName() + "报送失败，原因为校验文件业务逻辑时发生系统错误" + e.getMessage());
        }
        // 如果验证信息为空,则验证通过,否则返回验证信息，不继续读取报表
        try {
            // 保存数据库
            this.read(wb, reportParam);
        } catch (ReportSubmitException e) {
            e.printStackTrace();
            throw new ReportSubmitException("[" + reportParam.get(PARAM_DURING) + "]"
                    + config.getReportName() + "报送失败，原因为读取报表文件时发生系统错误" + e.getMessage());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // messagesRst.add("[" + reportParam.get(PARAM_DURING) + "]"
        // + config.getReportName() + "报送成功");

        if (valdtRst != null && valdtRst.size() > 0) {
            return valdtRst;
        } else {
            return null;
        }

    }

    /**
     * 验证报表格式
     * 
     * @param is
     * @param reportParam
     * @return
     */
    public List<String> validateFormat(InputStream is, Map<String, String> reportParam) {
        HSSFWorkbook wb = null;
        List<String> messagesRst = new ArrayList<String>();
        try {
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (Exception e) {
            e.printStackTrace();
            messagesRst.add("[" + reportParam.get(PARAM_DURING) + "]" + config.getReportName()
                    + "报送失败，原因为上传文件时发生系统错误" + e.getMessage());
            return messagesRst;
        }
        // 验证报表格式正确性
        try {
            List<String> valdtRst = validator.validateFormat(wb);
            // 如果验证信息为空,则验证通过,否则返回验证信息，不继续读取报表
            if (valdtRst != null && valdtRst.size() > 0) {
                return valdtRst;
            }
        } catch (ReportSubmitException e) {
            e.printStackTrace();
            messagesRst.add("[" + reportParam.get(PARAM_DURING) + "]" + config.getReportName()
                    + "报送失败，原因为校验文件格式时发生系统错误" + e.getMessage());
            return messagesRst;
        }
        return null;
    }

    /**
     * 保存报表数据
     * 
     * @param wb
     * @param reportParam
     * @throws ReportSubmitException
     */
    protected abstract void read(HSSFWorkbook wb, Map<String, String> reportParam)
            throws ReportSubmitException;

    /**
     * 删除已经存在该所属期数据
     * 
     * @param reportParam
     * @throws ReportSubmitException
     */
    protected abstract void deleteExsitData(Map<String, String> reportParam)
            throws ReportSubmitException;

    /**
     * 获取当前指定列中为指定值的行数，用于返回有效行行数
     * 
     * @param wb
     * @param columnIndex
     * @param lastCellValue
     * @return
     */
    protected int getLastRow(HSSFWorkbook wb, int columnIndex, String lastCellValue) {
        HSSFSheet sheet = wb.getSheetAt(0);
        String cellValue = null;
        for (int i = 0; i <= FIND_EXCEL_LAST_ROW_RANGE; i++) {
            try {
                cellValue = ExcelUtil.getValue(sheet, i, columnIndex);
                if (lastCellValue.equals(cellValue)) {
                    return i;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    /**
     * 获取当前值在数组中的索引值
     * 
     * @param value
     * @param array
     * @return
     */
    protected int getArrayIndex(String value, String[] array, Boolean isTotalAtFirst) {
        for (int i = 0; i < array.length; i++) {
            if (value.equals(array[i])) {
                return i + 1;
            }
        }
        if (isTotalAtFirst == null || isTotalAtFirst) {
            return 0;
        } else {
            return MAX_ARRAY_INDEX;
        }
    }

    public void setConfig(BaseReportConfig config) {
        this.config = config;
    }

    public void setValidator(BaseReportValidator validator) {
        this.validator = validator;
    }

    public void setListener(ExcelProgressListener listener) {
        this.listener = listener;
    }

}
