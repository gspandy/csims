package com.gtm.csims.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;

/**
 * Excel POI 工具类
 * 
 * @author Sweet
 * 
 */
@SuppressWarnings("deprecation")
public class ExcelUtil {

    /**
     * 验证单元格坐标是否属于某个合并单元格的坐标范围内
     * 
     * @param regionList
     * @param rowIndex
     * @param cellnum
     * @return
     */
    public static boolean isMergedRegion(List<Region> regionList, int rowIndex,
            int cellnum) {
        if (regionList != null) {
            for (Region region : regionList) {
                // 判断是否是合并单元格
                if ((region.getRowFrom() <= rowIndex
                        && rowIndex <= region.getRowTo()
                        && region.getColumnFrom() <= cellnum && cellnum <= region
                        .getColumnTo())
                        || (region.getRowFrom() <= rowIndex
                                && rowIndex <= region.getRowTo()
                                && region.getColumnFrom() <= cellnum && cellnum <= region
                                .getColumnTo())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 读取单元格并返回跨行数、跨列数、值
     * 
     * @param cell
     * @return
     */
    public static Map<String, Object> readCell(HSSFCell cell) {
        Map<String, Object> map = new HashMap<String, Object>();

        HSSFSheet sheet = cell.getSheet();
        int row = cell.getRowIndex();
        int col = cell.getColumnIndex();
        int mrow = 1; // 默认跨行数
        int mcell = 1; // 默认跨列数

        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            Region region = sheet.getMergedRegionAt(i);
            // 判断是否是合并单元格
            if ((region.getRowFrom() <= row && row <= region.getRowTo()
                    && region.getColumnFrom() < col && col <= region
                    .getColumnTo())
                    || (region.getRowFrom() < row && row <= region.getRowTo()
                            && region.getColumnFrom() <= col && col <= region
                            .getColumnTo())) {

                mrow = region.getRowTo() - region.getRowFrom() + 1; // 跨行数
                mcell = region.getColumnTo() - region.getColumnFrom() + 1; // 跨列数
            }
            sheet.removeMergedRegion(i);
        }

        map.put("line", mrow);
        map.put("column", mcell);
        map.put("value", getValue(cell));

        return map;
    }

    /**
     * 判断单元格是否是合并单元格
     * 
     * @param cell
     * @return
     */
    public static boolean checkIsMerged(HSSFCell cell) {
        boolean flag = true;

        HSSFSheet sheet = cell.getSheet();
        int row = cell.getRowIndex();
        int col = cell.getColumnIndex();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            Region region = sheet.getMergedRegionAt(i);
            if ((region.getRowFrom() <= row && row <= region.getRowTo()
                    && region.getColumnFrom() < col && col <= region
                    .getColumnTo())
                    || (region.getRowFrom() < row && row <= region.getRowTo()
                            && region.getColumnFrom() <= col && col <= region
                            .getColumnTo())) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 判断Excel单元格类型并取值
     * 
     * @param cell
     * @return
     */
    public static String getValue(HSSFCell cell) {
        String value = "";
        try {
            switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    // 如果是date类型则 ，获取该cell的date值
                    Date dateValue = HSSFDateUtil.getJavaDate(cell
                            .getNumericCellValue());
                    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
                    value = sd.format(dateValue);
                } else {// 纯数字
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    value = String.valueOf(bd.setScale(3,
                            BigDecimal.ROUND_HALF_UP));
                    if (value.indexOf(".000") != -1) {
                        value = value.replace(".000", "");
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串型
                value = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:// 公式型
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getRichStringCellValue().toString();
                }
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                value = "GET CELL ERROR";
                break;
            default:
                value = cell.getRichStringCellValue().toString();
            }
        } catch (Exception e) {
            return value;
        }
        return value.trim();
    }

    /**
     * 获取单元格的值并且
     * 
     * @param cell
     * @return
     */
    public static String getNameValue(HSSFCell cell) {
        String value = "";
        try {
            switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
                BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                value = String.valueOf(bd.setScale(0,
                        BigDecimal.ROUND_HALF_DOWN));

                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串型
                value = cell.getRichStringCellValue().toString();
                break;
            default:
                value = cell.getRichStringCellValue().toString();
            }
        } catch (Exception e) {
            return value;
        }
        return value.trim();
    }

    /**
     * 根据行数和列数获取单元格值
     * 
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getValue(HSSFSheet sheet, int row, int column) {
        HSSFCell cell = sheet.getRow(row).getCell(column);
        return getValue(cell);
    }

    /**
     * 根据行数和列数获取数字单元格
     * 
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static BigDecimal getNumericValue(HSSFSheet sheet, int row,
            int column) {
        HSSFCell cell = sheet.getRow(row).getCell(column);
        BigDecimal decimal = new BigDecimal("0");
        try {
            switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return decimal;
                } else {// 纯数字
                    // 返回总长度为11位数值的BigDecimal
                    return new BigDecimal(cell.getNumericCellValue()).setScale(
                            6, BigDecimal.ROUND_HALF_UP);
                }
            case HSSFCell.CELL_TYPE_FORMULA: // 公式型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return decimal;
                } else {// 纯数字
                    // 返回总长度为11位数值的BigDecimal
                    return new BigDecimal(cell.getNumericCellValue()).setScale(
                            6, BigDecimal.ROUND_HALF_UP);
                }
            default:
                return decimal;
            }
        } catch (Exception e) {
            return decimal;
        }
    }

    /**
     * 获取指定行列所在的合并单元格值
     * 
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(HSSFSheet sheet, int row,
            int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return getValue(sheet, firstRow, firstColumn);
                }
            }
        }
        return null;
    }

    /**
     * 能否根据指定的行列信息正确获取单元格
     * 
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static boolean canGetCell(HSSFSheet sheet, int row, int column) {
        try {
            HSSFCell cell = sheet.getRow(row).getCell(column);
            if (cell == null) {
                return false;
            } else if (HSSFCell.CELL_TYPE_BLANK == cell.getCellType()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断当前指定单元格是否为合并单元格
     * 
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static boolean isMergedRegion(HSSFSheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 生成合并单元格
     * 
     * @param sheet
     * @param firstRow(0-based)
     * @param lastRow(0-based)
     * @param firstColumn(0-based)
     * @param lastColumn(0-based)
     */
    public static void createMergedRegion(HSSFSheet sheet, int firstRow,
            int lastRow, int firstColumn, int lastColumn) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow,
                firstColumn, lastColumn));
    }

    /**
     * 对指定位置的单元格判断，如果值相等，则合并单元格
     * 
     * @param sheet
     * @param columnIndex(0-based)
     * @param beginRowIndex(0-based)
     */
    public static void mergedRegionWithSameValue(HSSFSheet sheet,
            int columnIndex, int beginRowIndex) {
        int lastRowNum = sheet.getLastRowNum();
        int tempRow = beginRowIndex;
        String tempCellValue = null;
        String cellValue = null;
        for (int i = beginRowIndex; i <= lastRowNum; i++) {
            cellValue = getValue(sheet, i, columnIndex);
            if (tempCellValue == null) {
                tempCellValue = cellValue;
            } else {
                if (!cellValue.equals(tempCellValue)) {
                    if (tempRow != (i - 1)) {
                        sheet.addMergedRegion(new CellRangeAddress(tempRow,
                                i - 1, columnIndex, columnIndex));
                    }
                    tempCellValue = cellValue;
                    tempRow = i;
                }
            }
        }
    }

}
