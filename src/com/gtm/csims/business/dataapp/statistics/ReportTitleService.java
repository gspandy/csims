package com.gtm.csims.business.dataapp.statistics;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 生成报表表头接口
 * 
 * @author Sweet
 * 
 */
public interface ReportTitleService {

    /**
     * 
     * @param tableIndex
     * @return
     */
    String getHtmlTitle(Map<String, String> keyValue, String tableIndex);

    /**
     * 
     * @param tableIndex
     * @return
     */
    Map<String, String> getHtmlVerticalTitleMap(Map<String, String> keyValue,
            String tableIndex);

    /**
     * 
     * @param excelObject
     * @param tableIndex
     */
    HSSFWorkbook fillExcelTitle(Map<String, String> keyValue, String tableIndex);
}
