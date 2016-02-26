package com.gtm.csims.business.dataapp.statistics;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 生成报表表头实现类
 * 
 * @author Sweet
 * 
 */
public class ReportTitleServiceImpl implements ReportTitleService {

    public HSSFWorkbook fillExcelTitle(Map<String, String> keyValue,
            String tableIndex) {
        String modelName = "excelmodel_" + tableIndex + ".xls";
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(
                    "com/gtm/csims/business/dataapp/statistics/excelmodel/"
                            + modelName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // try {
        // is = new FileInputStream("/Users/yangyongzhi/Desktop/" + modelName);
        // } catch (FileNotFoundException e1) {
        // e1.printStackTrace();
        // }
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        HSSFSheet sheet0 = wb.getSheetAt(0);
        String tableName = sheet0.getRow(0).getCell(0).getRichStringCellValue()
                .getString();
        String tableinfo = sheet0.getRow(1).getCell(0).getRichStringCellValue()
                .getString();
        Set<Map.Entry<String, String>> entrySet = keyValue.entrySet();
        for (Iterator<Map.Entry<String, String>> iterator = entrySet.iterator(); iterator
                .hasNext();) {
            Map.Entry<String, String> entry = iterator.next();
            tableName = tableName.replaceAll("#" + entry.getKey() + "#", entry
                    .getValue());
            tableinfo = tableinfo.replaceAll("#" + entry.getKey() + "#", entry
                    .getValue());
        }
        // System.out.println(tableName);
        // System.out.println(tableinfo);
        sheet0.getRow(0).getCell(0).setCellValue(
                new HSSFRichTextString(tableName));
        sheet0.getRow(1).getCell(0).setCellValue(
                new HSSFRichTextString(tableinfo));
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public String getHtmlTitle(Map<String, String> keyValue, String tableIndex) {
        int index;
        try {
            index = Integer.valueOf(tableIndex);
        } catch (Exception e) {
            return "table title has be number,not String";
        }
        StringBuilder titleText = new StringBuilder(
                "<table width='100%' border='0' cellpadding='0' cellspacing='1' class='tableline01'>");
        switch (index) {
        case 1:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '53' align = 'center'>四川省金融IC卡基本情况月报统计表</td></tr>")
                    .append(
                            "<tr><td  colspan = '53' align = 'left'>#DURING#</td></tr>");
            break;
        case 2:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '53' align = 'center'>辖内机构\"问题概况\"汇总统计表</td></tr>")
                    .append(
                            "<tr><td  colspan = '53' align = 'left'>统计年份：#DURING#&nbsp;nbsp;nbsp;nbsp;统计单位：#ANL_ORG#</td></tr>");
            break;
        case 3:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '53' align = 'center'>辖内机构\"问题概况\"汇总统计表</td></tr>")
                    .append(
                            "<tr><td  colspan = '53' align = 'left'>统计年份：#DURING#&nbsp;&nbsp;&nbsp;&nbsp;")
                    .append(
                            "统计时间：#FROM_DATE#到#TO_DATE#&nbsp;&nbsp;&nbsp;&nbsp;统计单位：#ANL_ORG#</td></tr>");
            break;
        case 4:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '25' align = 'center'>四川省金融IC卡基本情况#DURING#统计表(按地区统计)</td></tr>")
                    .append("<tr>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>发卡机构</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（年初数）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（年初数）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（当期数）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（当期数）	</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（本季新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（本季新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡占比（本季新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（本年度逐季累计新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（本年度逐季累计新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡占比（本年度逐季累计新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>受理环境：间联POS(不含电话POS)</td>")
                    .append(
                            "<td class='tabletext10' colspan = '6' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>受理环境：ATM(含CDM)</td>")
                    .append(
                            "<td class='tabletext10' colspan = '6' align = 'center'>其中</td>")
                    .append("</tr><tr>")
                    .append(
                            "<td class='tabletext10' align = 'center'>受理金融IC卡</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>受理非接触式金融IC卡</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持跨行圈存功能</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")

                    .append(
                            "<td class='tabletext10' align = 'center'>受理金融IC卡</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>受理非接触式金融IC卡</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持跨行圈存功能</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append("</tr>");
            break;
        case 5:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '25' align = 'center'>四川省金融IC卡基本情况#DURING#统计表(按地区统计)</td></tr>")
                    .append("<tr>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>发卡机构</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（年初数）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（年初数）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（当期数）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（当期数）	</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（本季新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（本季新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡占比（本季新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（本年度逐季累计新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（本年度逐季累计新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡占比（本年度逐季累计新增）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>受理环境：间联POS(不含电话POS)</td>")
                    .append(
                            "<td class='tabletext10' colspan = '6' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>受理环境：ATM(含CDM)</td>")
                    .append(
                            "<td class='tabletext10' colspan = '6' align = 'center'>其中</td>")
                    .append("</tr><tr>")
                    .append(
                            "<td class='tabletext10' align = 'center'>受理金融IC卡</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>受理非接触式金融IC卡</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持跨行圈存功能</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")

                    .append(
                            "<td class='tabletext10' align = 'center'>受理金融IC卡</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>受理非接触式金融IC卡</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持跨行圈存功能</td>")
                    .append("<td class='tabletext10' align = 'center'>占比</td>")
                    .append("</tr>");
            break;
        case 6:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '27' align = 'center'>四川省地方性商业银行金融IC卡基本情况季报汇总表</td></tr>")
                    .append(
                            "<tr><td  colspan = '13' align = 'left'>汇总单位：中国人民银行成都分行</td>")
                    .append(
                            "<td  colspan = '14' align = 'right'>#DURING#</td></tr>")
                    .append(
                            "<tr><td  colspan = '27' align = 'right'>填表日期：#CREATEDATE#&nbsp;&nbsp;&nbsp;&nbsp;单位:张、万元、台</td></tr>")
                    .append("<tr>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>发卡机构</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡发卡量（期末累计）</td>")
                    .append(
                            "<td class='tabletext10' colspan = '3' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>金融IC卡发卡量（期末累计）</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>借记IC卡发卡量（期末累计）</td>")
                    .append(
                            "<td class='tabletext10' colspan = '2' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>贷记IC卡发卡量（期末累计）</td>")
                    .append(
                            "<td class='tabletext10' colspan = '2' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>准贷记IC卡发卡量（期末累计）</td>")
                    .append(
                            "<td class='tabletext10' colspan = '2' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡存款余额</td>")
                    .append("<td class='tabletext10' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>银行卡消费交易额</td>")
                    .append(
                            "<td class='tabletext10' colspan = '3' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>受理环境：ATM(含CDM)</td>")
                    .append(
                            "<td class='tabletext10' colspan = '2' align = 'center'>其中</td>")
                    .append(
                            "<td class='tabletext10' rowspan = '2' align = 'center'>受理环境：间联POS(不含电话POS)</td>")
                    .append(
                            "<td class='tabletext10' colspan = '2' align = 'center'>其中</td>")
                    .append("</tr><tr>")

                    .append("<td class='tabletext10' align = 'center'>借记卡</td>")
                    .append("<td class='tabletext10' align = 'center'>贷记卡</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>准贷记卡</td>")

                    .append(
                            "<td class='tabletext10' align = 'center'>带电子现金功能</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持非接</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>带电子现金功能</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持非接</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>带电子现金功能</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持非接</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>金融IC卡存款余额</td>")

                    .append(
                            "<td class='tabletext10' align = 'center'>金融IC卡消费交易额</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>电子现金消费交易额</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>电子现金圈存交易额</td>")

                    .append(
                            "<td class='tabletext10' align = 'center'>受理金融IC卡</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持非接</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>受理金融IC卡</td>")
                    .append(
                            "<td class='tabletext10' align = 'center'>支持非接</td>")
                    .append("</tr>");
            break;
        case 7:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '5' align = 'center'>四川省加载金融功能的社会保障卡发卡情况季报表</td></tr>")
                    .append(
                            "<tr><td  colspan = '3' align = 'left'>填报单位：#BANK_TYPE_NAME#</td>")
                    .append(
                            "<td  colspan = '2' align = 'right'>#DURING#</td></tr>")
                    .append(
                            "<tr><td  colspan = '5' align = 'right'>银统413表&nbsp;&nbsp;&nbsp;&nbsp;单位:张、万元</td></tr>")
                    .append("<tr><td align = 'center'>卡片介质</td>").append(
                            "<td align = 'center'>发卡量</td>").append(
                            "<td align = 'center'>激活量</td>").append(
                            "<td align = 'center'>消费交易额</td>").append(
                            "<td align = 'center'>存款余额</td></tr>");
            break;
        case 8:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '10' align = 'left'>四川省加载金融功能的社会保障卡发卡情况季报表(按地区)</td>")
                    .append(
                            "<td class='tabletext01' colspan = '14' align = 'left'>#DURING#</td></tr>")
                    .append("<tr><td  colspan = '10' align = 'left'></td>")
                    .append(
                            "<td  colspan = '14' align = 'left'>单位:张、万元</td></tr>");
            break;
        case 9:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '10' align = 'left'>四川省加载金融功能的社会保障卡发卡情况季报表(按地区)</td>")
                    .append(
                            "<td class='tabletext01' colspan = '42' align = 'left'>#DURING#</td></tr>")
                    .append("<tr><td  colspan = '10' align = 'left'></td>")
                    .append(
                            "<td  colspan = '42' align = 'left'>单位:张、万元</td></tr>");
            break;
        case 10:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '10' align = 'center'>四川省金融IC卡在公共服务领域中应用业务数据统计表（#DURING#)</td></tr>")
                    .append(
                            "<tr><td  colspan = '10' align = 'right'>统计日期：#CREATEDATE#</td></tr>");
            break;
        case 11:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '8' align = 'center'>四川省各商业银行金融IC卡行业应用季度统计表（#DURING#）</td></tr>")
                    .append(
                            "<tr><td  colspan = '8' align = 'right'>统计日期：#CREATEDATE#  单位：张、笔、元</td></tr>");
            break;
        case 12:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '14' align = 'center'>四川省商圈非接改造基本情况月报统计表（#DURING#）</td></tr>")
                    .append(
                            "<tr><td  colspan = '14' align = 'right'>统计日期: #CREATEDATE#</td></tr>");
            break;
        case 13:
            titleText
                    .append(
                            "<tr><td class='tabletext01' colspan = '5' align = 'center'>四川省金融IC卡在公共服务领域中应用业务数据统计表（#DURING#)</td></tr>")
                    .append(
                            "<tr><td  colspan = '5' align = 'right'>统计日期: #CREATEDATE#</td></tr>");
            break;
        default:
            titleText.append("");
        }

        Set<String> keySet = keyValue.keySet();
        String titleTextString = titleText.toString();
        for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
            String key = iterator.next();
            titleTextString = titleTextString.replaceAll("#" + key + "#",
                    keyValue.get(key));
        }
        return titleTextString;
    }

    /**
     * 
     * @param tableIndex
     * @return
     */
    public Map<String, String> getHtmlVerticalTitleMap(
            Map<String, String> keyValue, String tableIndex) {
        int index;
        try {
            index = Integer.valueOf(tableIndex);
        } catch (Exception e) {
            return null;
        }
        Map<String, String> vtcTtlMap = new HashMap<String, String>();
        switch (index) {
        case 1:// 四川省金融IC卡基本情况月报统计表
            vtcTtlMap
                    .put("1-1",
                            "<td class='tabletext10' align='center' colspan = '3' width='30%'>指标</td>");
            vtcTtlMap
                    .put("2-1",
                            "<td class='tabletext10' align='center' colspan = '2' rowspan='2'>发卡情况</td>");
            vtcTtlMap.put("2-2",
                    "<td class='tabletext10' align='center'>银行卡已发卡数量（张）</td>");
            vtcTtlMap
                    .put("3-2",
                            "<td class='tabletext10' align='center'>金融IC卡已发卡数量（张）</td>");
            vtcTtlMap
                    .put("4-1",
                            "<td class='tabletext10' align='center' rowspan='15'>金融IC卡受理环境改造情况</td>");

            vtcTtlMap
                    .put(
                            "4-2",
                            "<td class='tabletext10' align='center' rowspan='4'>间联POS改造情况（间联POS，不含电话POS）</td>");
            vtcTtlMap.put("4-3",
                    "<td class='tabletext10' align='center'>POS保有量（台）</td>");
            vtcTtlMap
                    .put("5-3",
                            "<td class='tabletext10' align='center'>支持金融IC卡的终端数量（台）</td>");
            vtcTtlMap
                    .put("6-3",
                            "<td class='tabletext10' align='center'>支持非接触IC卡的终端数量（台）</td>");
            vtcTtlMap
                    .put("7-3",
                            "<td class='tabletext10' align='center'>支持跨行圈存功能的终端数量（台）</td>");

            vtcTtlMap
                    .put("8-2",
                            "<td class='tabletext10' align='center' rowspan='4'>ATM改造情况</td>");
            vtcTtlMap.put("8-3",
                    "<td class='tabletext10' align='center'>ATM保有量（台）</td>");
            vtcTtlMap
                    .put("9-3",
                            "<td class='tabletext10' align='center'>支持金融IC卡的终端数量（台）</td>");
            vtcTtlMap
                    .put("10-3",
                            "<td class='tabletext10' align='center'>支持非接触IC卡的终端数量（台）</td>");
            vtcTtlMap
                    .put("11-3",
                            "<td class='tabletext10' align='center'>支持跨行圈存功能的终端数量（台）</td>");

            vtcTtlMap
                    .put("12-2",
                            "<td class='tabletext10' align='center' rowspan='2'>金融IC卡互联网支付终端</td>");
            vtcTtlMap
                    .put("12-3",
                            "<td class='tabletext10' align='center'>是否开通互联网终端支付功能</td>");
            vtcTtlMap.put("13-3",
                    "<td class='tabletext10' align='center'>已发放的终端数量（台）</td>");

            vtcTtlMap
                    .put("14-2",
                            "<td class='tabletext10' align='center' rowspan='4'>网点改造情况（不含自助银行网点）</td>");
            vtcTtlMap.put("14-3",
                    "<td class='tabletext10' align='center'>网点数量（个）</td>");
            vtcTtlMap
                    .put("15-3",
                            "<td class='tabletext10' align='center'>可受理金融IC卡网点数量（个）</td>");
            vtcTtlMap
                    .put("16-3",
                            "<td class='tabletext10' align='center'>可受理非接触IC卡的网点数量（个）</td>");
            vtcTtlMap
                    .put("17-3",
                            "<td class='tabletext10' align='center'>支持圈存功能的网点数量（个）</td>");

            vtcTtlMap
                    .put("18-2",
                            "<td class='tabletext10' align='center'>自助圈存设备投放情况（不含POS、ATM、柜台）</td>");
            vtcTtlMap
                    .put("18-3",
                            "<td class='tabletext10' align='center'>支持圈存功能的自助设备数量（台）</td>");
            break;
        case 2:// 四川省金融IC卡基本情况季报统计表
            vtcTtlMap
                    .put("1-1",
                            "<td class='tabletext10' align='center' colspan = '3' width='30%'>指标</td>");
            vtcTtlMap
                    .put(
                            "2-1",
                            "<td class='tabletext10' align='left' colspan = '3' width='30%'>1.发卡量（截至期末累计量）</td>");
            vtcTtlMap
                    .put("3-1",
                            "<td class='tabletext10' align='left' rowspan='4'>银行卡发卡量</td>");
            vtcTtlMap
                    .put("3-2",
                            "<td class='tabletext10' align='center' colspan = '2'>合计</td>");
            vtcTtlMap
                    .put("4-2",
                            "<td class='tabletext10' align='left'  colspan = '2'>借记卡</td>");
            vtcTtlMap
                    .put("5-2",
                            "<td class='tabletext10' align='left'  colspan = '2'>贷记卡</td>");
            vtcTtlMap
                    .put("6-2",
                            "<td class='tabletext10' align='left'  colspan = '2'>准贷记卡</td>");

            vtcTtlMap
                    .put("7-1",
                            "<td class='tabletext10' align='left' rowspan='10'>金融IC卡发卡量</td>");
            vtcTtlMap
                    .put("7-2",
                            "<td class='tabletext10' align='center' colspan='2'>合计</td>");

            vtcTtlMap
                    .put("8-2",
                            "<td class='tabletext10' align='left' rowspan='3'>借记卡</td>");
            vtcTtlMap
                    .put("8-3", "<td class='tabletext10' align='left'>总量</td>");
            vtcTtlMap.put("9-3",
                    "<td class='tabletext10' align='left'>带电子现金功能</td>");
            vtcTtlMap.put("10-3",
                    "<td class='tabletext10' align='left'>支持非接触</td>");

            vtcTtlMap
                    .put("11-2",
                            "<td class='tabletext10' align='left' rowspan='3'>贷记卡</td>");
            vtcTtlMap.put("11-3",
                    "<td class='tabletext10' align='left'>总量</td>");
            vtcTtlMap.put("12-3",
                    "<td class='tabletext10' align='left'>带电子现金功能</td>");
            vtcTtlMap.put("13-3",
                    "<td class='tabletext10' align='left'>支持非接触</td>");

            vtcTtlMap
                    .put("14-2",
                            "<td class='tabletext10' align='left' rowspan='3'>准贷记卡</td>");
            vtcTtlMap.put("14-3",
                    "<td class='tabletext10' align='left'>总量</td>");
            vtcTtlMap.put("15-3",
                    "<td class='tabletext10' align='left'>带电子现金功能</td>");
            vtcTtlMap.put("16-3",
                    "<td class='tabletext10' align='left'>支持非接触</td>");

            vtcTtlMap
                    .put("17-1",
                            "<td class='tabletext10' align='left' colspan = '3' >2.存款余额（截至期末余额）</td>");
            vtcTtlMap
                    .put("18-1",
                            "<td class='tabletext10' align='left' colspan = '3' >银行卡存款余额</td>");
            vtcTtlMap
                    .put("19-1",
                            "<td class='tabletext10' align='left' colspan = '3' >金融IC卡存款余额</td>");
            vtcTtlMap
                    .put("20-1",
                            "<td class='tabletext10' align='left' colspan = '3' >3.交易额（当期交易额）</td>");
            vtcTtlMap
                    .put("21-1",
                            "<td class='tabletext10' align='left' colspan = '3' >银行卡消费交易额</td>");
            vtcTtlMap
                    .put("22-1",
                            "<td class='tabletext10' align='left' colspan = '3' >金融IC卡消费交易额</td>");
            vtcTtlMap
                    .put("23-1",
                            "<td class='tabletext10' align='left' colspan = '3' >电子现金消费交易额</td>");
            vtcTtlMap
                    .put("24-1",
                            "<td class='tabletext10' align='left' colspan = '3' >电子现金圈存交易额</td>");

            vtcTtlMap
                    .put("25-1",
                            "<td class='tabletext10' align='left' colspan = '3' >4.受理环境（截至期末保有量）</td>");
            vtcTtlMap
                    .put("26-1",
                            "<td class='tabletext10' align='left'  rowspan='3'>ATM(含CDM)</td>");
            vtcTtlMap
                    .put("26-2",
                            "<td class='tabletext10' align='left'  colspan='2'>受理银行卡</td>");
            vtcTtlMap
                    .put("27-2",
                            "<td class='tabletext10' align='left'  colspan='2'>受理金融IC卡</td>");
            vtcTtlMap
                    .put("28-2",
                            "<td class='tabletext10' align='left'  colspan='2'>受理非接触式金融IC卡</td>");

            vtcTtlMap
                    .put("29-1",
                            "<td class='tabletext10' align='left'  rowspan='3'>间联POS（不含电话POS）</td>");
            vtcTtlMap
                    .put("29-2",
                            "<td class='tabletext10' align='left'  colspan='2'>受理银行卡</td>");
            vtcTtlMap
                    .put("30-2",
                            "<td class='tabletext10' align='left'  colspan='2'>受理金融IC卡</td>");
            vtcTtlMap
                    .put("31-2",
                            "<td class='tabletext10' align='left'  colspan='2'>受理非接触式金融IC卡</td>");
            break;
        case 7:// 四川省加载金融功能的社会保障卡发卡情况季报表
            vtcTtlMap
                    .put("1-1",
                            "<td class='tabletext10' align='left' width='30%'>芯片（仅社保应用)+磁条复合卡</td>");
            vtcTtlMap
                    .put("2-1",
                            "<td class='tabletext10' align='left' >芯片（社保＋金融应用)+磁条复合卡</td>");
            vtcTtlMap.put("3-1",
                    "<td class='tabletext10' align='left' >芯片（社保＋金融应用）卡</td>");
            vtcTtlMap.put("4-1",
                    "<td class='tabletext10' align='left' >合计</td>");
            break;
        case 8:// 四川省加载金融功能的社会保障卡发卡情况季报表(按地区)
            vtcTtlMap
                    .put("1-1",
                            "<td class='tabletext10' align='center' width='10%'>卡片介质</td>");
            vtcTtlMap.put("1-2",
                    "<td class='tabletext10' align='center' >报送项目</td>");

            vtcTtlMap
                    .put("2-1",
                            "<td class='tabletext10' align='left' rowspan='4'>芯片（仅社保应用)+磁条复合卡</td>");
            vtcTtlMap.put("2-2",
                    "<td class='tabletext10' align='left'>发卡量</td>");
            vtcTtlMap.put("3-2",
                    "<td class='tabletext10' align='left'>激活量</td>");
            vtcTtlMap.put("4-2",
                    "<td class='tabletext10' align='left'>消费交易额</td>");
            vtcTtlMap.put("5-2",
                    "<td class='tabletext10' align='left'>存款余额</td>");

            vtcTtlMap
                    .put("6-1",
                            "<td class='tabletext10' align='left' rowspan='4'>芯片（社保＋金融应用)+磁条复合卡</td>");
            vtcTtlMap.put("6-2",
                    "<td class='tabletext10' align='left'>发卡量</td>");
            vtcTtlMap.put("7-2",
                    "<td class='tabletext10' align='left'>激活量</td>");
            vtcTtlMap.put("8-2",
                    "<td class='tabletext10' align='left'>消费交易额</td>");
            vtcTtlMap.put("9-2",
                    "<td class='tabletext10' align='left'>存款余额</td>");

            vtcTtlMap
                    .put("10-1",
                            "<td class='tabletext10' align='left' rowspan='4'>芯片（社保＋金融应用）卡</td>");
            vtcTtlMap.put("10-2",
                    "<td class='tabletext10' align='left'>发卡量</td>");
            vtcTtlMap.put("11-2",
                    "<td class='tabletext10' align='left'>激活量</td>");
            vtcTtlMap.put("12-2",
                    "<td class='tabletext10' align='left'>消费交易额</td>");
            vtcTtlMap.put("13-2",
                    "<td class='tabletext10' align='left'>存款余额</td>");
            break;
        case 9:// 四川省加载金融功能的社会保障卡发卡情况季报表(按机构)
            vtcTtlMap
                    .put("1-1",
                            "<td class='tabletext10' align='center' width='10%'>卡片介质</td>");
            vtcTtlMap.put("1-2",
                    "<td class='tabletext10' align='center' >报送项目</td>");

            vtcTtlMap
                    .put("2-1",
                            "<td class='tabletext10' align='left' rowspan='4'>芯片（仅社保应用)+磁条复合卡</td>");
            vtcTtlMap.put("2-2",
                    "<td class='tabletext10' align='left'>发卡量</td>");
            vtcTtlMap.put("3-2",
                    "<td class='tabletext10' align='left'>激活量</td>");
            vtcTtlMap.put("4-2",
                    "<td class='tabletext10' align='left'>消费交易额</td>");
            vtcTtlMap.put("5-2",
                    "<td class='tabletext10' align='left'>存款余额</td>");

            vtcTtlMap
                    .put("6-1",
                            "<td class='tabletext10' align='left' rowspan='4'>芯片（社保＋金融应用)+磁条复合卡</td>");
            vtcTtlMap.put("6-2",
                    "<td class='tabletext10' align='left'>发卡量</td>");
            vtcTtlMap.put("7-2",
                    "<td class='tabletext10' align='left'>激活量</td>");
            vtcTtlMap.put("8-2",
                    "<td class='tabletext10' align='left'>消费交易额</td>");
            vtcTtlMap.put("9-2",
                    "<td class='tabletext10' align='left'>存款余额</td>");

            vtcTtlMap
                    .put("10-1",
                            "<td class='tabletext10' align='left' rowspan='4'>芯片（社保＋金融应用）卡</td>");
            vtcTtlMap.put("10-2",
                    "<td class='tabletext10' align='left'>发卡量</td>");
            vtcTtlMap.put("11-2",
                    "<td class='tabletext10' align='left'>激活量</td>");
            vtcTtlMap.put("12-2",
                    "<td class='tabletext10' align='left'>消费交易额</td>");
            vtcTtlMap.put("13-2",
                    "<td class='tabletext10' align='left'>存款余额</td>");
            break;
        default:
            return vtcTtlMap;
        }

        // Set<String> keySet = keyValue.keySet();
        // String titleTextString = titleText.toString();
        // for (Iterator<String> iterator = keySet.iterator();
        // iterator.hasNext();) {
        // String key = iterator.next();
        // titleTextString = titleTextString.replaceAll("#" + key + "#",
        // keyValue.get(key));
        // }

        return vtcTtlMap;
    }
}
