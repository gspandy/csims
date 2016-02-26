package com.gtm.csims.business.datacollection.submit.config;

import com.gtm.csims.business.datacollection.submit.base.BaseReportConfig;

/**
 * 行政执法检查统计表配置信息
 * 
 * @author Sweet
 * 
 */
public class AEinspectionAnlConfig extends BaseReportConfig {

    public AEinspectionAnlConfig() {
        this.beginRow = 4;
        this.endRow = 10000;

        this.beginColumn = 0;
        this.endColumn = 16;
    }

    public String getReportName() {
        return "行政执法检查统计表";
    }
}
