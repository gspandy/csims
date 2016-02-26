package com.gtm.csims.business.datacollection.submit.base;

import java.util.Map;

/**
 * 报表配置信息
 * 
 * @author Sweet
 * 
 */
public abstract class BaseReportConfig {
    // 上报excel文件中有效数据区域行列信息，行列均从0开始计数
    protected int beginRow;// 开始行
    protected int endRow;// 结束行
    protected int beginColumn;// 开始列
    protected int endColumn;// 开始列

    protected Map<Integer, Boolean> cellIsDecimalArrayOfRow;// 为小数的行配置
    protected Map<Integer, Boolean> cellIsDecimalArrayOfCol;// 为小数的列配置

    public Boolean requireIsDecimalRow(int row) {
        return cellIsDecimalArrayOfRow.get(row);
    }

    public Boolean requireIsDecimalColumn(int col) {
        return cellIsDecimalArrayOfCol.get(col);
    }

    public abstract String getReportName();

    public int getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(int beginRow) {
        this.beginRow = beginRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getBeginColumn() {
        return beginColumn;
    }

    public void setBeginColumn(int beginColumn) {
        this.beginColumn = beginColumn;
    }

    public int getEndColumn() {
        return endColumn;
    }

    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }
}
