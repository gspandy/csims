package com.gtm.csims.business.dataapp.statistics;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 统计功能接口
 * 
 * @author Sweet
 * 
 */
public interface StatisticsService {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 统计方法
     */
    Map<String, Object> doStatistics(Map<String, String> paramsMap);

    Integer getxCount();

    /**
     * 根据组装后的结果集生成html字符串
     * 
     * @param tableIndex
     * @param data组装后的结果集
     * @param keyValue
     * 
     * @return html字符串
     */
    String getHTMLString(String tableIndex, Map<String, Object> data, Map<String, String> keyValue);

    /**
     * 根据组装后的结果集生成Excel poi对象
     * 
     * @param tableIndex
     * @param data
     * @param keyValue
     * @return
     */
    HSSFWorkbook generateExcel(String tableIndex, Map<String, Object> data, Map<String, String> keyValue);

}
