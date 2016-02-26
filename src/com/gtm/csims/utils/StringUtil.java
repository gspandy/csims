package com.gtm.csims.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串处理工具类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class StringUtil {

    private static final Map<String, String> REPLACE_MAP = new HashMap<String, String>();
    static {
        REPLACE_MAP.put("，", ",");
        REPLACE_MAP.put("。", ".");
        REPLACE_MAP.put("〈", "<");
        REPLACE_MAP.put("〉", ">");
        REPLACE_MAP.put("‖", "|");
        REPLACE_MAP.put("《", "<");
        REPLACE_MAP.put("》", ">");
        REPLACE_MAP.put("〔", "[");
        REPLACE_MAP.put("〕", "]");
        REPLACE_MAP.put("﹖", "?");
        REPLACE_MAP.put("？", "?");
        REPLACE_MAP.put("“", "\"");
        REPLACE_MAP.put("”", "\"");
        REPLACE_MAP.put("：", ":");
        REPLACE_MAP.put("、", ",");
        REPLACE_MAP.put("（", "(");
        REPLACE_MAP.put("）", ")");
        REPLACE_MAP.put("【", "[");
        REPLACE_MAP.put("】", "]");
        REPLACE_MAP.put("｛", "{");
        REPLACE_MAP.put("｝", "}");
        REPLACE_MAP.put("—", "-");
        REPLACE_MAP.put("～", "~");
        REPLACE_MAP.put("！", "!");
        REPLACE_MAP.put("‵", "'");
        REPLACE_MAP.put("①", "1");
        REPLACE_MAP.put("②", "2");
        REPLACE_MAP.put("③", "3");
        REPLACE_MAP.put("④", "4");
        REPLACE_MAP.put("⑤", "5");
        REPLACE_MAP.put("⑥", "6");
        REPLACE_MAP.put("⑦", "7");
        REPLACE_MAP.put("⑧", "8");
        REPLACE_MAP.put("⑨", "9");
        REPLACE_MAP.put("１", "1");
        REPLACE_MAP.put("２", "2");
        REPLACE_MAP.put("３", "3");
        REPLACE_MAP.put("４", "4");
        REPLACE_MAP.put("５", "5");
        REPLACE_MAP.put("６", "6");
        REPLACE_MAP.put("７", "7");
        REPLACE_MAP.put("８", "8");
        REPLACE_MAP.put("９", "9");
        REPLACE_MAP.put("０", "0");
    }

    /**
     * 转化字符串为时间
     * 
     * @param dateStr
     * @return
     */
    public static Date convert(String dateStr) {// 2009-06-11 11:36:19
        // SimpleDateFormat format = new
        // SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            dateStr = dateStr + " 0:00:00";
            // System.out.println(format.parse(dateStr));
            // System.out.println(Timestamp.valueOf(dateStr));
            // return format.parse(dateStr);
            return Timestamp.valueOf(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转化字符串为时间
     * 
     * @param dateStr
     * @param isLastTimeOfADay
     *            是否需要转化为当天最后1秒
     * @return
     */
    public static Date convert(String dateStr, Boolean isLastTimeOfADay) {// 2009-06-11
        // 11:36:19
        // SimpleDateFormat format = new
        // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (isLastTimeOfADay) {
                dateStr = dateStr + " 23:59:59";
            } else {
                dateStr = dateStr + " 0:00:00";
            }
            // System.out.println(format.parse(dateStr));
            // System.out.println(Timestamp.valueOf(dateStr));
            // return format.parse(dateStr);
            return Timestamp.valueOf(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字符串第一个字符小写
     * 
     * @param s
     * @return
     */
    public static String getFirstLetterLowCase(String s) {
        s = Character.toLowerCase(s.charAt(0)) + s.substring(1);
        return s;
    }

    /**
     * 将字符串第一个字符大写
     * 
     * @param s
     * @return
     */
    public static String getFirstLetterUpperCase(String s) {
        s = Character.toUpperCase(s.charAt(0)) + s.substring(1);
        return s;
    }

    /**
     * 将字符串数组连接成字符串
     * 
     * @param array
     * @param prefix
     *            字符串前缀
     * @param postfix
     *            字符串后缀
     * @param separator
     *            字符串分隔符
     * @return
     */
    public static String join(Object[] array, String prefix, String postfix,
            String separator) {
        if (array == null || array.length == 0) {
            return prefix.toString() + postfix.toString();
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            String s = array[i].toString();
            sb.append(prefix);
            sb.append(s);
            sb.append(postfix);
            if (i != array.length - 1) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 返回count个字符数的字符，如果s小于指定长度，则在字符串前以‘0’补充
     * 
     * @param s
     * @param count
     * @return
     */
    public static String addZero(String s, int count) {
        StringBuffer sb = new StringBuffer();
        int length = s.length();
        if (length > count) {
            return s;
        }
        int sub = count - length;
        for (int i = 0; i < sub; i++) {
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }

    /**
     * 返回count个字符数的字符，如果s小于指定长度，则在字符串尾以‘0’补充
     * 
     * @param s
     * @param count
     * @return
     */
    public static String addZeroLast(String s, int count) {
        StringBuffer sb = new StringBuffer();
        int length = s.length();
        if (length > count) {
            return s;
        }
        int sub = count - length;
        sb.append(s);
        for (int i = 0; i < sub; i++) {
            sb.append("0");
        }
        return sb.toString();
    }

    /**
     * 将原字符串按照oldEncode取得字节流，并以newEncode重新编码字符串
     * 
     * @param a
     * @param oldEncode
     * @param newEncode
     * @return
     */
    public static String convertEncode(String a, String oldEncode,
            String newEncode) {
        try {
            return new String(a.getBytes(oldEncode), newEncode);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取源字符串中的len字节数的子字符串，并以symbol结尾
     * 
     * @param str
     *            原字符串
     * @param len
     *            字符串字节数(汉字为2个字节)
     * @param symbol
     *            填充的字符
     * @return
     */
    public static String getLimitLengthString(String str, int len, String symbol) {
        int counterOfDoubleByte = 0;
        byte[] b;
        try {
            b = str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            b = str.getBytes();
            e.printStackTrace();
        }
        if (b.length <= len) {
            return str;
        }
        for (int i = 0; i < len; i++) {
            if (b[i] < 0) {
                counterOfDoubleByte++;
            }
        }
        try {
            if (counterOfDoubleByte % 2 == 0) {
                return new String(b, 0, len, "GBK") + symbol;
            } else {
                return new String(b, 0, len - 1, "GBK") + symbol;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new String(b) + symbol;
        }
    }

    public static String replaceChineseChatactor(String line) {

        int length = line.length();
        for (int i = 0; i < length; i++) {
            String charat = line.substring(i, i + 1);
            if (REPLACE_MAP.containsKey(charat)) {
                line = line.replace(charat, REPLACE_MAP.get(charat));
            }
        }
        return line;
    }

    /**
     * 合并两个字符串数组
     * 
     * @param a
     * @param b
     * @return
     */
    public static String[] combineArray(String[] a, String[] b) {
        String[] c = new String[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    /**
     * 去除字符串中word、html格式信息
     * 
     * @param srcStr
     * @return
     */
    public static String escapeFormatInfo(String srcStr) {
        if (StringUtils.isNotBlank(srcStr)) {
            return srcStr.replaceAll("</?[^>]+>", "").replaceAll(
                    "<a>\\s*|\t|\r|\n</a>", "").replace("&nbsp;", "");// 去除字符串中的空格,回车,换行符,制表符
        } else {
            return "";
        }
    }

}
