package com.gtm.csims.business.datacollection.submit.base;

/**
 * 操作文件进度监听器
 * 
 * @author Sweet
 * 
 */
public interface ExcelProgressListener {
    /**
     * 根据文件操作进度更新数据
     * 
     * @param hasProgressRow
     * @param totalRow
     * @param itemIndex
     */
    void update(long hasProgressRow, long totalRow, int itemIndex);
}
