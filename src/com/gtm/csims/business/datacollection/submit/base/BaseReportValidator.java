package com.gtm.csims.business.datacollection.submit.base;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gtm.csims.business.datacollection.submit.ReportSubmitException;
import com.gtm.csims.utils.ExcelUtil;
import com.gtm.csims.utils.StringUtil;

/**
 * 报表上报校验业务基类
 * 
 * @author Sweet
 * 
 */
public abstract class BaseReportValidator {
    // 验证提示信息中坐标和提示内容间的分隔符
    public static final String VALIDATE_MESSAGE_SEPERATOR = "=&=";
    // 系统中数据上报数值类型最大长度（包括小数点和小数位）
    public static final Integer MAX_NUMBER_LENGTH = 16;
    // 系统中上报的所有数值数据都按照0000000.000000的字符串格式保存于数据库
    public static final String NUMBER_TO_STRING_PRECISION = "%.6f";
    // 系统中获取excel数值做比较时统一设置小数为6位再做比较，避免浮点型数字的若干小数位引起的精度问题
    public static final int COMPARE_EQUAL_SCALE = 6;
    // excel文件最后行标志值
    public static final String LAST_ROW_FLAG_VALUE = "到此为止";
    // 验证上报报表中地区顺序（汇总数、21个地市州）
    public static final String[] AREA_INDEX_ARRAY = { "汇总数", "成都", "攀枝花", "自贡",
            "泸州", "乐山", "内江", "德阳", "绵阳", "广元", "遂宁", "南充", "宜宾", "广安", "资阳",
            "眉山", "达州", "巴中", "雅安", "甘孜", "阿坝", "凉山" };

    protected BaseReportConfig config;

    public void setConfig(BaseReportConfig config) {
        this.config = config;
    }

    /**
     * 验证报表基本格式规则方法
     * 
     * @param wb
     * @param reportParam
     * @return
     */
    protected abstract List<String> validateFormat(HSSFWorkbook wb)
            throws ReportSubmitException;

    /**
     * 根据所属期验证报表是否已经存在
     * 
     * @param reportParam
     * @return
     */
    protected abstract List<String> validateThisDrIsExsit(
            Map<String, String> reportParam) throws ReportSubmitException;

    /**
     * 验证报表业务规则方法
     * 
     * @param wb
     * @param reportParam
     * @return
     */
    protected abstract List<String> validate(HSSFWorkbook wb,
            Map<String, String> reportParam) throws ReportSubmitException;

    /**
     * 根据所属期获取对应的上一所属期，如没有返回Null
     * 
     * @param thisDuring
     * @return
     */
    public static String getPrevDuring(String thisDuring) {
        if (thisDuring == null) {
            return null;
        }
        String[] thisDrArr = thisDuring.split("-");
        if (thisDuring != null && thisDrArr != null && thisDrArr.length > 1) {
            if (thisDrArr[0].trim().equalsIgnoreCase("M")
                    && thisDrArr.length == 3) {// 如果所属期为月
                String[] prevDrArr = { "", "", "" };
                prevDrArr[0] = thisDrArr[0];
                Integer month = Integer.valueOf(thisDrArr[2]);
                Integer year = Integer.valueOf(thisDrArr[1]);
                if (month != 1) {
                    prevDrArr[1] = thisDrArr[1];
                    prevDrArr[2] = StringUtil.addZero(
                            String.valueOf(month - 1), 2);
                } else {
                    prevDrArr[1] = String.valueOf(year - 1);
                    prevDrArr[2] = "12";
                }
                return StringUtil.join(prevDrArr, "", "", "-");
            } else if (thisDrArr[0].trim().equalsIgnoreCase("S")
                    && thisDrArr.length == 3) {// 如果所属期为季度
                String[] prevDrArr = { "", "", "" };
                prevDrArr[0] = thisDrArr[0];
                Integer season = Integer.valueOf(thisDrArr[2]);
                Integer year = Integer.valueOf(thisDrArr[1]);
                if (season != 1) {
                    prevDrArr[1] = thisDrArr[1];
                    prevDrArr[2] = StringUtil.addZero(String
                            .valueOf(season - 1), 2);
                } else {
                    prevDrArr[1] = String.valueOf(year - 1);
                    prevDrArr[2] = "04";
                }
                return StringUtil.join(prevDrArr, "", "", "-");
            } else if (thisDrArr[0].trim().equalsIgnoreCase("Y")
                    && thisDrArr.length == 2) {// 如果所属期为年
                String[] prevDrArr = { "", "" };
                prevDrArr[0] = thisDrArr[0];
                prevDrArr[1] = String
                        .valueOf(Integer.valueOf(thisDrArr[1]) - 1);
                return StringUtil.join(prevDrArr, "", "", "-");
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * 根据季度所属期获取对应的上一月所属期，如没有返回Null
     * 
     * @param thisDuring
     * @return
     */
    public static String getPrevMthDuringBySsn(String thisSsn) {
        if (thisSsn == null) {
            return null;
        }
        String[] thisDrArr = thisSsn.split("-");
        if (thisSsn != null && thisDrArr != null && thisDrArr.length > 1) {
            if (thisDrArr[0].trim().equalsIgnoreCase("S")
                    && thisDrArr.length == 3) {// 如果所属期为季度
                String[] prevMthDrArr = { "", "", "" };

                Integer thisSeason = Integer.valueOf(thisDrArr[2]);
                prevMthDrArr[0] = "M";
                prevMthDrArr[1] = thisDrArr[1];
                switch (thisSeason) {
                case 1:
                    prevMthDrArr[1] = thisDrArr[1];
                    prevMthDrArr[2] = "02";
                    break;
                case 2:
                    prevMthDrArr[1] = thisDrArr[1];
                    prevMthDrArr[2] = "05";
                    break;
                case 3:
                    prevMthDrArr[1] = thisDrArr[1];
                    prevMthDrArr[2] = "08";
                    break;
                case 4:
                    prevMthDrArr[1] = thisDrArr[1];
                    prevMthDrArr[2] = "11";
                    break;
                default:
                    return null;
                }
                return StringUtil.join(prevMthDrArr, "", "", "-");
            }
        }
        return null;
    }

    /**
     * 根据季度所属期获取对应的上一年最后季度，如没有返回Null
     * 
     * @param thisDuring
     * @return
     */
    public static String getPrevYearLstSsnDuringBySsn(String thisSsn) {
        if (thisSsn == null) {
            return null;
        }
        String[] thisDrArr = thisSsn.split("-");
        if (thisSsn != null && thisDrArr != null && thisDrArr.length > 1) {
            if (thisDrArr[0].trim().equalsIgnoreCase("S")
                    && thisDrArr.length == 3) {// 如果所属期为季度
                String[] prevYearLstSsnDrArr = { "", "", "" };
                Integer thisYear = Integer.valueOf(thisDrArr[1]);
                prevYearLstSsnDrArr[0] = "S";
                prevYearLstSsnDrArr[1] = String.valueOf(thisYear - 1);
                prevYearLstSsnDrArr[2] = "04";
                return StringUtil.join(prevYearLstSsnDrArr, "", "", "-");
            }
        }
        return null;
    }

    /**
     * 根据季度所属期获取本年此季度以前所有季度所属期，如没有返回Null
     * 
     * @param thisDuring
     * @return
     */
    public static String[] getThsYearAgoSsnsDuringBySsn(String thisSsn) {
        if (thisSsn == null) {
            return null;
        }
        String[] thisDrArr = thisSsn.split("-");
        if (thisSsn != null && thisDrArr != null && thisDrArr.length > 1) {
            if (thisDrArr[0].trim().equalsIgnoreCase("S")
                    && thisDrArr.length == 3) {// 如果所属期为季度
                Integer thisSsnInt = Integer.valueOf(thisDrArr[2]);
                StringBuffer resultStr = new StringBuffer();
                if (thisSsnInt > 1) {
                    for (int i = 1; i < thisSsnInt; i++) {
                        resultStr.append("S-");
                        resultStr.append(thisDrArr[1]);
                        resultStr.append("-0");
                        resultStr.append(i);
                        if (i != thisSsnInt - 1) {
                            resultStr.append("--");
                        }
                    }
                    return resultStr.toString().split("--");
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 根据季度所属期获取本年第一季度所属期，如没有返回Null
     * 
     * @param thisDuring
     * @return
     */
    public static String getThsYearFstSsnsDuringBySsn(String thisSsn) {
        if (thisSsn == null) {
            return null;
        }
        String[] thisDrArr = thisSsn.split("-");
        if (thisSsn != null && thisDrArr != null && thisDrArr.length > 1) {
            if (thisDrArr[0].trim().equalsIgnoreCase("S")
                    && thisDrArr.length == 3) {// 如果所属期为季度
                if (!thisDrArr[2].trim().equals("01")) {
                    String[] thsYearFstSsnDrArr = { "", "", "" };
                    thsYearFstSsnDrArr[0] = "S";
                    thsYearFstSsnDrArr[1] = thisDrArr[1];
                    thsYearFstSsnDrArr[2] = "01";
                    return StringUtil.join(thsYearFstSsnDrArr, "", "", "-");
                } else {
                    return thisSsn;
                }
            }
        }
        return null;
    }

    /**
     * 根据所属期获取对应中文字符串，如没有返回Null
     * 
     * @param thisDuring
     * @return
     */
    public static String getDuringChn(String thisDuring) {
        if (thisDuring == null) {
            return null;
        }
        String[] thisDrArr = thisDuring.split("-");
        if (thisDuring != null && thisDrArr != null && thisDrArr.length > 1) {
            String[] prevDrArr = { "", "" };
            if (thisDrArr[0].trim().equalsIgnoreCase("M")
                    && thisDrArr.length == 3) {// 如果所属期为月
                prevDrArr[0] = thisDrArr[1] + "年";
                Integer month = Integer.valueOf(thisDrArr[2]);
                switch (month) {
                case 1:
                    prevDrArr[1] = "一月";
                    break;
                case 2:
                    prevDrArr[1] = "二月";
                    break;
                case 3:
                    prevDrArr[1] = "三月";
                    break;
                case 4:
                    prevDrArr[1] = "四月";
                    break;
                case 5:
                    prevDrArr[1] = "五月";
                    break;
                case 6:
                    prevDrArr[1] = "六月";
                    break;
                case 7:
                    prevDrArr[1] = "七月";
                    break;
                case 8:
                    prevDrArr[1] = "八月";
                    break;
                case 9:
                    prevDrArr[1] = "九月";
                    break;
                case 10:
                    prevDrArr[1] = "十月";
                    break;
                case 11:
                    prevDrArr[1] = "十一月";
                    break;
                case 12:
                    prevDrArr[1] = "十二月";
                    break;
                default:
                    prevDrArr[1] = "一月";
                }
                return StringUtil.join(prevDrArr, "", "", "");
            } else if (thisDrArr[0].trim().equalsIgnoreCase("S")
                    && thisDrArr.length == 3) {// 如果所属期为季度
                prevDrArr[0] = thisDrArr[1] + "年";
                Integer season = Integer.valueOf(thisDrArr[2]);
                switch (season) {
                case 1:
                    prevDrArr[1] = "第一季度";
                    break;
                case 2:
                    prevDrArr[1] = "第二季度";
                    break;
                case 3:
                    prevDrArr[1] = "第三季度";
                    break;
                case 4:
                    prevDrArr[1] = "第四季度";
                    break;
                default:
                    prevDrArr[1] = "第一季度";
                }
                return StringUtil.join(prevDrArr, "", "", "");
            } else if (thisDrArr[0].trim().equalsIgnoreCase("Y")
                    && thisDrArr.length == 2) {// 如果所属期为年
                prevDrArr[0] = thisDrArr[0];
                prevDrArr[1] = "年";
                return StringUtil.join(prevDrArr, "", "", "");
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * 判断当前所属期是否为季度月份，如没有返回Null
     * 
     * @param thisDuring
     * @return
     */
    public static Boolean isSeasonMonthDuring(String thisDuring) {
        if (thisDuring == null) {
            return false;
        }
        String[] thisDrArr = thisDuring.split("-");
        if (thisDuring != null && thisDrArr != null && thisDrArr.length > 1) {
            if (thisDrArr[0].trim().equalsIgnoreCase("M")
                    && thisDrArr.length == 3) {// 如果所属期为月份
                if (thisDrArr[2].trim().equals("03")
                        || thisDrArr[2].trim().equals("06")
                        || thisDrArr[2].trim().equals("09")
                        || thisDrArr[2].trim().equals("12")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 返回当月对应的季度所属期，如没有返回Null
     * 
     * @param thisDuring
     * @return
     */
    public static String getSeasonDuringByMonth(String thisDuring) {
        if (thisDuring == null) {
            return null;
        }
        String[] thisDrArr = thisDuring.split("-");
        if (thisDuring != null && thisDrArr != null && thisDrArr.length > 1) {
            if (thisDrArr[0].trim().equalsIgnoreCase("M")
                    && thisDrArr.length == 3) {// 如果所属期为月份
                if (thisDrArr[2].trim().equals("03")) {
                    return "S-" + thisDrArr[1] + "-01";
                } else if (thisDrArr[2].trim().equals("06")) {
                    return "S-" + thisDrArr[1] + "-02";
                } else if (thisDrArr[2].trim().equals("09")) {
                    return "S-" + thisDrArr[1] + "-03";
                } else if (thisDrArr[2].trim().equals("12")) {
                    return "S-" + thisDrArr[1] + "-04";
                }
            }
        }
        return null;
    }

    /**
     * 判断当前字符串（00000.000000）是否为小数
     * 
     * @param str
     * @return
     */
    public static boolean isDecimalByString(String str) {
        if (str.indexOf(".000000") == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当前指定列中为指定值的行数，用于返回有效行行数
     * 
     * 目前仅支持2000行excel中查找
     * 
     * 如果超过2000行，则不能正确返回最后行行数
     * 
     * @param wb
     * @param columnIndex
     *            结束标志所在的列数 从0开始计数
     * @param lastCellValue
     *            最后行标志值
     * @return
     */
    protected int getLastRow(HSSFWorkbook wb, int columnIndex,
            String lastCellValue) {
        HSSFSheet sheet = wb.getSheetAt(0);
        String cellValue = null;
        for (int i = 0; i <= BaseReportReader.FIND_EXCEL_LAST_ROW_RANGE; i++) {
            // System.out.println(i);
            try {
                cellValue = ExcelUtil.getValue(sheet, i, columnIndex);
                if (lastCellValue.equals(cellValue)) {
                    return i;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static void main(String[] a) {
        // System.out.println(getSeasonDuringByMonth("M-2013-12"));
        // System.out.println(getSeasonDuringByMonth("M-2014-03"));
        // System.out.println(getSeasonDuringByMonth("S-2014-01"));
        //
        // System.out.println(isSeasonMonthDuring("M-2014-01"));
        // System.out.println(isSeasonMonthDuring("M-2014-03"));
        // System.out.println(isSeasonMonthDuring("S-2014-01"));

        // System.out.println(getPrevDuring("M-2013-01"));

    }
}
