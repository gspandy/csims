package com.gtm.csims.business.datacollection.submit.config;

import com.gtm.csims.business.datacollection.submit.base.BaseReportConfig;

/**
 * 考试成绩导入表配置信息
 * 
 * @author Sweet
 * 
 */
public class ExamScoreConfig extends BaseReportConfig {

    public ExamScoreConfig() {
        this.beginRow = 1;
        this.endRow = 10000;

        this.beginColumn = 0;
        this.endColumn = 14;
    }

    public String getReportName() {
        return "考试成绩导入表";
    }
}
