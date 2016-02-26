package com.gtm.csims.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式处理工具类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class DateUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat FORMAT_NO_LINE = new SimpleDateFormat("yyyyMMddHHmmsssss");
    private static SimpleDateFormat dateFomat = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat dateFomat2 = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat dateSimpleFomat = new SimpleDateFormat("yyyy/MM/dd");
    private static SimpleDateFormat dateZhCNFomat = new SimpleDateFormat("yyyy年MM月dd日");

    /**
     * 时间转换格式.
     */
    public static final String[] DATE_FORMAT_ARRAY = new String[] { "yyyy-MM-dd",
            "yyyy/MM/dd HH:mm" };
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmsssss";
    public static final String DATE_FORMAT_ZH = "yyyy年MM月dd日";

    /**
     * 转化时间为yyyy-MM-dd HH:mm:ss类型的字符串
     * 
     * @param dateStr
     * @return
     */
    public static String convert(Date now) {
        return sdf.format(now);
    }

    /**
     * 转化时间为yyyyMMddHHmmsssss类型的字符串
     * 
     * @param dateStr
     * @return
     */
    public static String convertNoLineStr(Date now) {
        return FORMAT_NO_LINE.format(now);
    }

    /**
     * 转化时间为yyyyMMdd类型的字符串
     * 
     * @param dateStr
     * @return
     */
    public static String convertToStrWithoutSign(Date date) {
        return dateFomat.format(date);
    }

    /**
     * 转化时间为自定义格式的字符串
     * 
     * @param now
     *            时间
     * @param format
     *            自定义格式
     * @return
     */
    public static String convert(Date now, String format) {
        String dateStr = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateStr = sdf.format(now);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    /**
     * 转化时间为yyyy-MM-dd HH:mm:ss类型的时间类型
     * 
     * @param dateStr
     * @return
     */
    public static Date convert(String now) {
        Date date = null;
        try {
            date = sdf.parse(now);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转化时间为yyyy-MM-dd HH:mm:ss类型的时间类型
     * 
     * @param dateStr
     * @return
     */
    public static Date convert(String now, String flag) {
        Date date = null;
        try {
            date = dateSimpleFomat.parse(now);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 转化时间为yyyy-MM-dd HH:mm:ss类型的时间类型
     * 
     * @param dateStr
     * @return
     */
    public static String toStr(Date now) {
        String s = "";
        try {
            s = dateSimpleFomat.format(now);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 转化时间为yyyy年MM月dd日字符串
     * 
     * @param dateStr
     * @return
     */
    public static String toStrZhCN(Date now) {
        String s = "";
        try {
            s = dateZhCNFomat.format(now);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 转化时间为yyyy-MM-dd字符串
     * 
     * @param dateStr
     * @return
     */
    public static String toStr2(Date now) {
        String s = "";
        try {
            s = dateFomat2.format(now);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static void main(String[] s) {
        // System.out.println(DateUtil.convert(new Date()));
        // System.out.println(convert(parseDate("yyyy-MM-dd", "2008-1-1")));
        // Calendar calendar = Calendar.getInstance();
        // calendar.setTime(parseDate("yyyy-MM-dd", "2008-1-1"));
        // int day = calendar.get(Calendar.DAY_OF_YEAR)+ 50;
        // calendar.set(Calendar.DAY_OF_YEAR, day);
        // Date cc = calendar.getTime();
        // System.out.println(convert(cc)+cc.after(new Date()));
    }

    public static Date parseDate(String pattern, String dateString) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(dateString);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String parseDateString(String pattern, String dateString) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.format(df.parse(dateString));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
