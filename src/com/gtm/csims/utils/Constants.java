package com.gtm.csims.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author Sweet
 *
 */
public class Constants {
    public static Date TASK_DUE_DATE;

    public static final String PCB_SC_ORG_NO = "A1000151000028";
    
    public static final String ZONG_BU = "总部";

    static {
        String dueDateStr = "2009-12-31";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            TASK_DUE_DATE = sdf.parse(dueDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            TASK_DUE_DATE = new Date();
        }
    }

    // 中小企业txt报文的报文头各项之间用于分割的符号
    public static final String SEGMENTATION = ";";
    // 判断字符串是否是日期格式yyyy-MM-dd的正则式
    public static final String DATE_REG = "^(?:[0-9]{1,4}(?<!^0?0?0?0))-(?:0?[1-9]|1[0-2])-(?:0?[1-9]|1[0-9]|2[0-8]|(?:(?<=-(?:0?[13578]|1[02])-)(?:29|3[01]))|(?:(?<=-(?:0?[469]|11)-)(?:29|30))|(?:(?<=(?:(?:[0-9]{0,2}(?!0?0)(?:[02468]?(?<![13579])[048]|[13579][26]))|(?:(?:[02468]?[048]|[13579][26])00))-0?2-)(?:29)))$";

    // 所属期类型flag and name
    public static final String YEAR = "1"; // 2008
    public static final String QUARTER = "2"; // 2008.1Q
    public static final String MONTH = "3"; // 2008-1
    public static final String HALF_MONTH = "4"; // 2008-1F 2008-1L
    public static final String TEN_DAY = "5"; // 2008-1.1T
    public static final String WEEK = "6"; // 2008-1.1W
    public static final String DAY = "7"; // 2008-1-1
}
