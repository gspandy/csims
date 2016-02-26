package com.gtm.csims.business.datacollection.submit.config;

import com.gtm.csims.business.datacollection.submit.base.BaseReportConfig;

/**
 * 行政执法数据表配置信息
 * 
 * @author Sweet
 * 
 */
public class AEnforcConfig extends BaseReportConfig {

    public AEnforcConfig() {
        this.beginRow = 2;
        this.endRow = 10000;

        this.beginColumn = 0;
        this.endColumn = 41;
    }

    public String getReportName() {
        return "行政执法数据表";
    }
}
