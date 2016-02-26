package com.gtm.csims.business.dataapp.statistics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gtm.csims.utils.ExcelUtil;

/**
 * 统计基类
 * 
 * 各个统计报表生成html页面和excel文件均可以继承该类并实现数据获取方法
 * 
 * @author Sweet
 * 
 */
public abstract class BaseStatisticsService {
    // 子类统计表需要配置项
    /**
     * 定义所有报表的统计Excel模板填充区域的起始列，以0开始计
     */
    public static final int[] TABLEDATA_BEGIN_X = { 0, 0, 0, 0, 0, 0, 1, 2, 2,
            0, 0, 0, 0 };// 开始列
    /**
     * 定义所有报表的统计Excel模板填充区域的起始行，以0开始计
     */
    public static final int[] TABLEDATA_BEGIN_Y = { 4, 2, 2, 3, 3, 5, 3, 3, 3,
            5, 3, 6, 5 };// 开始行
    /**
     * 根据报表编号设置是否需要HTML垂直表头
     */
    public static final boolean[] IS_FIRST_ROW_NOT_DISPLAY_ZERO = { false,
            false, false, false, false, false, false, true, true, false, false,
            false, false };

    // 静态报表基础数据
    public static final String[] AREA_INDEX_ARRAY = { "成都", "攀枝花", "自贡", "泸州",
            "乐山", "内江", "德阳", "绵阳", "广元", "遂宁", "南充", "宜宾", "广安", "资阳", "眉山",
            "达州", "巴中", "雅安", "甘孜", "阿坝", "凉山" };// 地区顺序（21个地市州）
    public static final String[] BANKTYPE_INDEX_ARRAY = { "工商银行四川省分行",
            "农业银行四川省分行", "中国银行四川省分行", "建设银行四川省分行", "交通银行四川省分行",
            "中国邮政储蓄银行四川省分行", "四川省农村信用社联合社", "招商银行成都分行", "中信银行成都分行", "光大银行成都分行",
            "上海浦东发展银行成都分行", "华夏银行成都分行", "平安银行成都分行", "民生银行成都分行", "兴业银行成都分行",
            "浙商银行成都分行", "恒丰银行成都分行", "广发银行成都分行", "渤海银行成都分行", "上海银行成都分行",
            "重庆银行成都分行", "浙江民泰商业银行成都分行", "哈尔滨银行成都分行", "大连银行成都分行", "包商银行成都分行",
            "贵阳银行成都分行", "天津银行成都分行", "成都银行", "成都农村商业银行", "乐山商业银行", "德阳银行",
            "泸州商业银行", "攀枝花商业银行", "绵阳商业银行", "南充商业银行", "自贡商业银行", "宜宾商业银行",
            "凉山州商业银行", "遂宁商业银行", "达州商业银行", "雅安商业银行", "华侨银行成都分行", "东亚银行成都分行",
            "汇丰银行成都分行", "渣打银行成都分行", "花旗银行成都分行", "大华银行成都分行", "南洋银行成都分行",
            "友利银行成都分行" };// 机构类型顺序（49个机构类型）
    public static final String[] BANKTYPE_SC_INDEX_ARRAY = { "四川省农村信用社联合社",
            "成都银行", "成都农村商业银行", "达州商业银行", "德阳银行", "乐山商业银行", "凉山州商业银行",
            "泸州商业银行", "绵阳商业银行", "南充商业银行", "攀枝花商业银行", "遂宁商业银行", "雅安商业银行",
            "宜宾商业银行", "自贡商业银行" };// 四川机构类型顺序（15个机构类型）

    // 作为最终统计时使用的报表上报数据存放表名
    public static final String FIN_IC_BASIC_MTH_TABLENAME = "BS_FINANCIALICBASICMTH_FINAL";
    /**
     * 报表中使用的常数100
     */
    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    protected JdbcTemplate jdbcTemplate;

    /**
     * 表示该统计报表是否需要重新复制空白行
     * 
     * 如果报表行数不固定，需要根据实际数据增加行数，则为false
     * 
     * 如果报表行数固定，则为true
     */
    protected boolean isOnlyFill;
    /**
     * 生成报表表头业务
     */
    protected ReportTitleService reportTitleService;
    /**
     * 生成Excel文件的Excel对象
     */
    protected HSSFWorkbook wb;

    /**
     * 返回当前统计报表的列数
     */
    protected abstract Integer getxCount();

    /**
     * 返回当前统计表需要合并单元格处理的列
     * 
     * @return
     */
    public int[] getMergedColumns() {
        return null;
    }

    /**
     * 返回是否仅为填充Excel不需要重新复制行
     * 
     * 如果报表行数不固定，需要根据实际数据增加行数，则为false
     * 
     * 如果报表行数固定，则为true
     * 
     * @return
     */
    public abstract Boolean isOnlyFill();

    /**
     * 根据组装后的结果集生成html字符串
     * 
     * @param tableIndex
     *            统计报表编号
     * @param data
     *            组装后的结果集
     * @param keyValue
     *            生成报表时所需的参数
     * @return html字符串
     */
    @SuppressWarnings("unchecked")
    public String getHTMLString(String tableIndex, Map<String, Object> data,
            Map<String, String> keyValue) {
        if (data == null) {
            return "input map is null";
        }
        StringBuffer verticalTitleSb = new StringBuffer();
        // 获取数据结果集列数
        int x = this.getxCount();
        int y = 0;
        // 计算数据结果集的行数
        try {
            y = data.size() / x;
        } catch (Exception e) {
            return "map.size/x has exception";
        }

        String cellValue = null;
        String verticalTitleHtml = null;
        // 根据报表编号获取水平报表头
        verticalTitleSb.append(reportTitleService.getHtmlTitle(keyValue,
                tableIndex));

        boolean isSum;// 是否为合计行
        boolean isFirstRowNotDisplayZero = false;// 当前统计报表是否第一行空白位置显示0
        if (IS_FIRST_ROW_NOT_DISPLAY_ZERO[Integer.valueOf(tableIndex) - 1]) {
            isFirstRowNotDisplayZero = true;
        }

        // 根据报表编号获取报表垂直报表头
        Map<String, String> verticalTitle = reportTitleService
                .getHtmlVerticalTitleMap(keyValue, tableIndex);

        StringBuffer vtcTtlRowStr = new StringBuffer();// 垂直表头
        StringBuffer dataRowStr = new StringBuffer();// 数据行
        StringBuffer cellHtml = new StringBuffer();// 单元格字符串
        StringBuffer rowHtml = new StringBuffer();// 行字符串

        // 获取是否需要合并单元格
        Map<Integer, Integer[]> rowSpan = this.calculateRowSpans(data,
                this.getMergedColumns());

        // 循环行
        for (int i = 1; i <= y; i++) {
            isSum = false;
            verticalTitleSb.append("<tr>");
            // 循环列
            for (int j = 1; j <= x; j++) {
                // 根据行列信息获取垂直报表头值(仅适用于垂直报表头列数少于报表数据列数)
                verticalTitleHtml = verticalTitle.get(i + "-" + j);
                // 根据行列信息获取报表数据
                cellValue = data.get(i + "-" + j).toString();
                if (cellValue != null
                        && (cellValue.indexOf("总计") != -1
                                || cellValue.indexOf("小计") != -1 || cellValue
                                .indexOf("合计") != -1)) {
                    isSum = false;
                }
                if (isSum) {
                    cellHtml.append("<b>");
                }
                // 如果垂直报表头能获取具体值，则将值放入最后报表字符串中
                if (verticalTitleHtml != null
                        && !verticalTitleHtml.trim().equals("")) {
                    vtcTtlRowStr.append(verticalTitleHtml);
                }
                // 如果当前报表有列需要合并相同值单元格
                if (rowSpan != null && rowSpan.get(j) != null) {
                    if (this.isContain(rowSpan.get(j), i)) {
                        cellHtml.append("<td class='tabletext10' align='center' ");
                        cellHtml.append(" rowspan='")
                                .append(getSpanValue(rowSpan.get(j), i))
                                .append("'");
                        cellHtml.append(">");
                        if (cellValue == null || cellValue.trim().equals("")) {
                            cellHtml.append("&nbsp;");
                        } else {
                            if (i == 1 && isFirstRowNotDisplayZero
                                    && cellValue.equals("0")) {
                                cellHtml.append("&nbsp;");
                            } else {
                                cellHtml.append(cellValue);
                            }
                        }
                        cellHtml.append("</td>");
                    }
                } else {
                    cellHtml.append("<td class='tabletext10' align='center' ");
                    cellHtml.append(">");
                    if (cellValue == null || cellValue.trim().equals("")) {
                        cellHtml.append("&nbsp;");
                    } else {
                        if (i == 1 && isFirstRowNotDisplayZero
                                && cellValue.equals("0")) {
                            cellHtml.append("&nbsp;");
                        } else {
                            cellHtml.append(cellValue);
                        }
                    }
                    cellHtml.append("</td>");
                }

                if (isSum) {
                    cellHtml.append("</b>");
                }
                // 每循环一个单元格html值做相应逻辑处理，默认不做处理
                dataRowStr.append(this.afterLoopDataOneColToHtml(i, j,
                        cellHtml.toString(), data, keyValue));
                cellHtml.setLength(0);
            }
            rowHtml.append(vtcTtlRowStr).append(dataRowStr);
            // 每循环一行单元格html值做相应逻辑处理，默认不做处理
            verticalTitleSb.append(this.afterLoopDataOneRowToHtml(i,
                    rowHtml.toString(), data, keyValue));
            verticalTitleSb.append("</tr>");
            vtcTtlRowStr.setLength(0);
            dataRowStr.setLength(0);
            rowHtml.setLength(0);

        }
        verticalTitleSb.append("</table>");
        // 在最后根据"toHandleCellMap"值Html字符串中的单元格位置和值依次对进行替换
        String verticalTitleStr = verticalTitleSb.toString();
        if (data.get("toHandleCellMap") != null) {
            Map cellMap = (Map) data.get("toHandleCellMap");
            Set<Map.Entry<String, String>> entrySet = cellMap.entrySet();
            Map.Entry<String, String> cellEntry = null;
            for (Iterator<Map.Entry<String, String>> iterator = entrySet
                    .iterator(); iterator.hasNext();) {
                cellEntry = iterator.next();
                // cellValue = cellMap.get(cellPosition).toString();
                verticalTitleStr = verticalTitleStr.replace(
                        "#=" + cellEntry.getKey() + "=#", cellEntry.getValue());
            }
        }
        return verticalTitleStr;
    }

    /**
     * 生成HTML时循环到每一个单元格数据后所需做的操作
     * 
     * @param row
     *            单元格行
     * @param col
     *            单元格列
     * @param value
     *            单元格值字符串
     * @param data
     *            生成HTML数据
     * @param keyValue
     *            生成HTML需替换值
     */
    protected String afterLoopDataOneColToHtml(int row, int col, String value,
            Map<String, Object> data, Map<String, String> keyValue) {
        return value;
    }

    /**
     * 生成HTML时循环到每一行单元格数据后所需做的操作
     * 
     * @param row
     *            单元格行
     * @param value
     *            单元格值字符串
     * @param data
     *            生成HTML数据
     * @param keyValue
     *            生成HTML需替换值
     */
    protected String afterLoopDataOneRowToHtml(int row, String value,
            Map<String, Object> data, Map<String, String> keyValue) {
        return value;
    }

    /**
     * 根据组装后的结果集生成Excel POI 对象
     * 
     * @param tableIndex
     * @param map
     * @param keyValue
     * @return Excel POI 对象
     */
    @SuppressWarnings("unchecked")
    public HSSFWorkbook generateExcel(String tableIndex,
            Map<String, Object> data, Map<String, String> keyValue) {
        int x = this.getxCount();
        int y = 0;
        try {
            y = data.size() / x;
        } catch (Exception e) {
            return null;
        }
        // 根据报表序号获取带有表头的Excel POI 对象
        HSSFWorkbook wb = reportTitleService.fillExcelTitle(keyValue,
                tableIndex);
        HSSFSheet sheet0 = wb.getSheetAt(0);
        int tableIndexInt;
        try {
            tableIndexInt = Integer.valueOf(tableIndex);
        } catch (Exception e) {
            return null;
        }
        // 如果行数超过1行，需要复制空白行供数据填充
        if (!this.isOnlyFill() && y > 1) {
            // 如果该统计类没有设置拷贝行信息则默认逐行复制
            HSSFRow row = sheet0.getRow(TABLEDATA_BEGIN_Y[tableIndexInt - 1]);
            for (int count = 0; count < y - 1; count++) {
                HSSFRow rowNew = sheet0
                        .createRow(TABLEDATA_BEGIN_Y[tableIndexInt - 1] + 1
                                + count);
                if (row != null) {
                    for (int i = 0; i < row.getLastCellNum(); i++) {
                        HSSFCell cellNew = rowNew.createCell(i);
                        HSSFCell cell = row.getCell(i);
                        if (cell != null) {
                            cellNew.setCellType(cell.getCellType());
                            // cellNew.setCellValue(cell.get);
                            cellNew.setCellStyle(cell.getCellStyle());
                        }
                    }
                }
            }
        }
        // 填充单元格数据
        String cellValue = "";

        // 循环数据Map，获取数据设置每个单元格值
        for (int i = 1; i <= y; i++) {
            for (int j = 1; j <= x; j++) {
                if (data.get(i + "-" + j) == null) {
                    cellValue = " ";
                } else {
                    cellValue = data.get(i + "-" + j).toString();
                }
                // System.out.println(i + "-" + j + ":" + cellValue);
                try {
                    HSSFCell cell = sheet0.getRow(
                            TABLEDATA_BEGIN_Y[tableIndexInt - 1] + (i - 1))
                            .getCell(
                                    TABLEDATA_BEGIN_X[tableIndexInt - 1]
                                            + (j - 1));
                    this.setCellValue(cell, cellValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // System.out.println("第"
                // + (TABLEDATA_BEGIN_Y[tableIndexInt - 1] + (i - 1))
                // + "行，第"
                // + (TABLEDATA_BEGIN_X[tableIndexInt - 1] + (j - 1))
                // + "列,值:" + cellValue);
            }
        }
        // 在最后根据"toHandleCellMap"这个数据集合中的单元格位置和值依次对excel中的单元格值进行赋值
        if (data.get("toHandleCellMap") != null) {
            Map cellMap = (Map) data.get("toHandleCellMap");
            Set<Map.Entry> entrySet = cellMap.entrySet();

            for (Iterator<Map.Entry> iterator = entrySet.iterator(); iterator
                    .hasNext();) {
                Map.Entry cellPosition = (Map.Entry) iterator.next();
                String[] cellPositionArr = cellPosition.getKey().toString()
                        .split("-");
                cellValue = cellPosition.getValue().toString();
                HSSFCell cell = sheet0.getRow(
                        Integer.valueOf(cellPositionArr[0])).getCell(
                        Integer.valueOf(cellPositionArr[1]));
                this.setCellValue(cell, cellValue);
            }
        }
        // 根据需要对生成的统计表做合并单元格处理
        if (this.getMergedColumns() != null) {
            int[] array = this.getMergedColumns();
            for (int i = 0; i < array.length; i++) {
                ExcelUtil.mergedRegionWithSameValue(sheet0, array[i],
                        TABLEDATA_BEGIN_Y[tableIndexInt - 1]);
            }
        }
        return wb;
    }

    /**
     * 对指定位置的单元格赋值
     * 
     * @param cell
     * @param cellValue
     */
    protected void setCellValue(HSSFCell cell, String cellValue) {
        if (cell == null) {
            return;
        } else {
            switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC: // 数值型
                try {
                    cell.setCellValue(new Double(cellValue));
                } catch (Exception e) {
                    cell.setCellValue(new HSSFRichTextString(cellValue));
                }
                break;
            case HSSFCell.CELL_TYPE_STRING: // 字符串型
                try {
                    cell.setCellValue(new HSSFRichTextString(cellValue));
                } catch (Exception e) {
                    cell.setCellValue(new HSSFRichTextString(""));
                }
                break;
            case HSSFCell.CELL_TYPE_FORMULA:// 公式型
                try {
                    cell.setCellValue(new HSSFRichTextString(cellValue));
                } catch (Exception e) {
                    cell.setCellValue(new HSSFRichTextString(""));
                }
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔
                try {
                    cell.setCellValue(new HSSFRichTextString(cellValue));
                } catch (Exception e) {
                    cell.setCellValue(new HSSFRichTextString(""));
                }
                break;
            case HSSFCell.CELL_TYPE_BLANK: // 空值
                try {
                    cell.setCellValue(new Double(cellValue));
                } catch (Exception e) {
                    cell.setCellValue(new HSSFRichTextString(cellValue));
                }
                break;
            case HSSFCell.CELL_TYPE_ERROR: // 故障
                cell.setCellValue(new HSSFRichTextString("ERROR"));
                break;
            default:
                cell.setCellValue(new HSSFRichTextString(cellValue));
            }
        }
    }

    /**
     * 用于将最后临时表更新地区字段顺序
     * 
     * @param uuid
     * @param isTotalAtFirst
     *            汇总数据是否在所有地市州之前
     */
    public void executeFinalAreaIndex(String tempTableName, String uuid,
            boolean isTotalAtFirst) {
        String[] sqlArr = new String[AREA_INDEX_ARRAY.length + 1];
        if (isTotalAtFirst) {
            if (uuid == null) {
                sqlArr[0] = "UPDATE "
                        + tempTableName
                        + " SET BankAreaIndex=0 WHERE BankArea='汇总数' AND BankAreaIndex IS NULL ";
            } else {
                sqlArr[0] = "UPDATE " + tempTableName
                        + " SET BankAreaIndex=0 WHERE BankArea='汇总数' AND id='"
                        + uuid + "'";
            }
        } else {
            if (uuid == null) {
                sqlArr[0] = "UPDATE "
                        + tempTableName
                        + " SET BankAreaIndex=100 WHERE BankArea='汇总数' AND BankAreaIndex IS NULL ";
            } else {
                sqlArr[0] = "UPDATE "
                        + tempTableName
                        + " SET BankAreaIndex=100 WHERE BankArea='汇总数' AND id='"
                        + uuid + "'";
            }
        }

        for (int i = 1; i <= AREA_INDEX_ARRAY.length; i++) {
            if (uuid == null) {
                sqlArr[i] = "UPDATE " + tempTableName + " SET BankAreaIndex="
                        + i + " WHERE BankArea='" + AREA_INDEX_ARRAY[i - 1]
                        + "' AND BankAreaIndex IS NULL ";
            } else {
                sqlArr[i] = "UPDATE " + tempTableName + " SET BankAreaIndex="
                        + i + " WHERE BankArea='" + AREA_INDEX_ARRAY[i - 1]
                        + "' AND id='" + uuid + "'";
            }
        }
        jdbcTemplate.batchUpdate(sqlArr);
    }

    /**
     * 用于将最后临时表更新机构类型字段顺序
     * 
     * @param uuid
     */
    public void executeFinalOrgIndex(String tempTableName, String uuid,
            boolean isTotalAtFirst) {
        String[] sqlArr = new String[BANKTYPE_INDEX_ARRAY.length + 1];
        if (isTotalAtFirst) {
            if (uuid == null) {
                sqlArr[0] = "UPDATE "
                        + tempTableName
                        + " SET BankTypeIndex=0 WHERE BankTypeName='合计' AND BankTypeIndex IS NULL ";
            } else {
                sqlArr[0] = "UPDATE "
                        + tempTableName
                        + " SET BankTypeIndex=0 WHERE BankTypeName='合计' AND id='"
                        + uuid + "'";
            }
        } else {
            if (uuid == null) {
                sqlArr[0] = "UPDATE "
                        + tempTableName
                        + " SET BankTypeIndex=1000 WHERE BankTypeName='合计' AND BankTypeIndex IS NULL ";
            } else {
                sqlArr[0] = "UPDATE "
                        + tempTableName
                        + " SET BankTypeIndex=1000 WHERE BankTypeName='合计' AND id='"
                        + uuid + "'";
            }
        }
        for (int i = 1; i <= BANKTYPE_INDEX_ARRAY.length; i++) {
            sqlArr[i] = "UPDATE " + tempTableName + " SET BankTypeIndex=" + i
                    + " WHERE BankTypeName='" + BANKTYPE_INDEX_ARRAY[i - 1]
                    + "' AND id='" + uuid + "'";
        }
        jdbcTemplate.batchUpdate(sqlArr);
    }

    /**
     * 用于将最后临时表更新四川范围机构类型字段顺序
     * 
     * @param uuid
     */
    public void executeFinalSCOrgIndex(String tempTableName, String uuid) {
        String[] sqlArr = new String[BANKTYPE_SC_INDEX_ARRAY.length];
        for (int i = 0; i < BANKTYPE_SC_INDEX_ARRAY.length; i++) {
            sqlArr[i] = "UPDATE " + tempTableName + " SET BankTypeIndex="
                    + (i + 1) + " WHERE BankTypeName='"
                    + BANKTYPE_SC_INDEX_ARRAY[i] + "' AND id='" + uuid + "'";
        }
        jdbcTemplate.batchUpdate(sqlArr);
    }

    /**
     * 根据需要合并相同值单元格配置计算每一次合并的第一行信息
     * 
     * @param data
     * @param mergedColumns
     * @return
     */
    protected Map<Integer, Integer[]> calculateRowSpans(
            Map<String, Object> data, int[] mergedColumns) {
        if (mergedColumns == null || mergedColumns.length <= 0) {
            return null;
        }
        Map<Integer, Integer[]> result = new HashMap<Integer, Integer[]>();
        for (int i = 0; i < mergedColumns.length; i++) {
            // List<Integer> mergedBeginRowIndexs = new ArrayList<Integer>();
            Set<String> keySet = data.keySet();
            int maxi = 0;
            int maxj = 0;
            int tempi = 0;
            int tempj = 0;
            for (String key : keySet) {
                String[] xy = key.split("-");
                tempi = Integer.valueOf(xy[0]);
                if (tempi > maxi) {
                    maxi = tempi;
                }
                tempj = Integer.valueOf(xy[1]);
                if (tempj > maxj) {
                    maxj = tempj;
                }
            }

            String tempCellValue = null;
            String cellValue = null;
            List<Integer> everyColSpans = new ArrayList<Integer>();
            everyColSpans.add(1);// 首先把第一行保存到返回值中
            for (int j = 1; j <= maxi; j++) {
                // 由于定义需要合并单元格的数从0开始，这里从map中取值从1开始，所以+1
                cellValue = data.get(j + "-" + (mergedColumns[i] + 1))
                        .toString();// 获取map中的值做比较
                if (tempCellValue == null) {
                    tempCellValue = cellValue;
                } else {
                    if (cellValue.equals(tempCellValue)) {
                        // do anything
                    } else {
                        everyColSpans.add(j);
                        tempCellValue = cellValue;
                    }
                }
            }
            Integer[] array = new Integer[everyColSpans.size()];
            array = everyColSpans.toArray(array);
            result.put(mergedColumns[i] + 1, array);
        }
        // System.out.println(result.size());
        // Set a = result.keySet();
        // for (Object object : a) {
        // System.out.println(object.toString() + "=============");
        // Integer[] aaa = result.get(object);
        // for (int i = 0; i < aaa.length; i++) {
        // System.out.println(aaa[i]);
        // }
        // }
        return result;
    }

    /**
     * 判断当前整数是否在此整数数组中
     * 
     * @param a
     * @param i
     * @return
     */
    protected boolean isContain(Integer[] a, int i) {
        for (int j = 0; j < a.length; j++) {
            if (a[j] == i) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前整数在此整数数组中与下一个数组元素之差
     * 
     * @param a
     * @param i
     * @return
     */
    protected int getSpanValue(Integer[] a, int i) {
        for (int j = 0; j < a.length; j++) {
            if (a[j] == i) {
                if (j != a.length - 1) {
                    return a[j + 1] - a[j];
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * 根据人民银行机构编号递归所有子机构,结果是否包含自己.
     * 
     * @param pcbParentNo
     *            人民银行父机构编号
     */
    @SuppressWarnings("unchecked")
    protected String getChildOrgByParentNoStr(String pcbParentNo) {
        StringBuffer sqlSb = new StringBuffer(
                "WITH resultOrg (NO,parentNo,name) AS ")
                .append("(SELECT No,parentNo,name FROM BS_ORG WHERE parentNo = '")
                .append(pcbParentNo)
                .append("' UNION ALL SELECT child.No,child.parentNo,child.name FROM resultOrg parent, BS_ORG child ")
                .append(" WHERE parent.No = child.parentNo )")
                .append(" SELECT NO from resultOrg ");
        // System.out.println(sqlSb.toString());
        StringBuffer result = new StringBuffer();
        result.append("'").append(pcbParentNo).append("'");
        List<Map<String, Object>> childrenlt = jdbcTemplate.queryForList(sqlSb
                .toString());
        if (childrenlt != null && childrenlt.size() > 0) {
            result.append(",");
            for (int i = 0; i < childrenlt.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) childrenlt
                        .get(i);
                result.append("'").append(map.get("NO").toString()).append("'");
                if (i != childrenlt.size() - 1)
                    result.append(",");
            }
        }
        return result.toString();
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setReportTitleService(ReportTitleService reportTitleService) {
        this.reportTitleService = reportTitleService;
    }
}
