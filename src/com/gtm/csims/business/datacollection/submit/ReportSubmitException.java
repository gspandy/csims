package com.gtm.csims.business.datacollection.submit;

/**
 * 报表上报出现错误抛出此异常
 * 
 * @author Sweet
 * 
 */
@SuppressWarnings("serial")
public class ReportSubmitException extends Exception {

    public ReportSubmitException() {
        super();
    }

    public ReportSubmitException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ReportSubmitException(String arg0) {
        super(arg0);
    }

    public ReportSubmitException(Throwable arg0) {
        super(arg0);
    }

}
