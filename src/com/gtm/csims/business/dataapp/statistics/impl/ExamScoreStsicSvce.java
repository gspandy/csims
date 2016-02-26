package com.gtm.csims.business.dataapp.statistics.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.dataapp.statistics.BaseStatisticsService;
import com.gtm.csims.business.dataapp.statistics.StatisticsService;
import com.gtm.csims.utils.Constants;

/**
 * 考试成绩明细表
 * 
 * @author Sweet
 * 
 */
public class ExamScoreStsicSvce extends BaseStatisticsService implements StatisticsService {
    /**
     * 当前报表需要重新复制行
     * 
     * @return
     */
    public Boolean isOnlyFill() {
        return false;
    }

    /**
     * 返回报表的横向单元格数量,从第A列（0）开始-AA列
     */
    public Integer getxCount() {
        return 15;
    }

    /**
     * 统计流程
     */
    @Transactional(readOnly = false)
    public Map<String, Object> doStatistics(Map<String, String> paramsMap) {
        String uuid = this.insertTempTable(paramsMap);
        this.calculateTempData(uuid);
        return this.getResultData(uuid, paramsMap);
    }

    /**
     * 根据统计参数插入临时表数据
     * 
     * @param paramsMap
     */
    private String insertTempTable(Map<String, String> paramsMap) {
        // 针对每一次统计生成唯一ID，以便后期统计最后结果
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    /**
     * 根据临时表中的初始数据进行合计等计算
     * 
     * @param uuid
     */
    private void calculateTempData(String uuid) {
    }

    /**
     * 查询统计临时表,将返回数据组装为显示表格格式,作为报表结果数据
     * 
     * @param uuid
     * @return
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> getResultData(String uuid, Map<String, String> paramsMap) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        StringBuffer querySql = new StringBuffer(
                "SELECT PEONM,PEOID,CERTTYPE,PEOTYPE,ORGNAME,ORGNO,CITY,COUNTRY,TELE,EXSTDT,EXTMDT,EXTYPE,FULLMARK,PASS,SCORE ")
                .append(" FROM ").append("BS_EXAMSCORE").append(" WHERE 1=1 ");
        if (paramsMap.get("FROM_DATE") != null
                && !paramsMap.get("FROM_DATE").toString().trim().equals(""))
            querySql.append(" AND EXSTDT >= '").append(paramsMap.get("FROM_DATE").toString())
                    .append("' ");
        if (paramsMap.get("TO_DATE") != null
                && !paramsMap.get("TO_DATE").toString().trim().equals(""))
            querySql.append(" AND  EXTMDT <= '").append(paramsMap.get("TO_DATE").toString())
                    .append("' ");
        if (paramsMap.get("PEOID") != null && !paramsMap.get("PEOID").toString().trim().equals(""))
            querySql.append(" AND PEOID Like '%").append(paramsMap.get("PEOID").toString())
                    .append("%' ");
        if (paramsMap.get("PEONM") != null && !paramsMap.get("PEONM").toString().trim().equals(""))
            querySql.append(" AND PEONM Like '%").append(paramsMap.get("PEONM").toString())
                    .append("%' ");
        if (paramsMap.get("PEOTYPE") != null
                && !paramsMap.get("PEOTYPE").toString().trim().equals(""))
            querySql.append(" AND PEOTYPE Like '%").append(paramsMap.get("PEOTYPE").toString())
                    .append("%' ");
        String childrenNoStr = "";
        if (paramsMap.get("LOGIN_ORGNO") != null
                && !paramsMap.get("LOGIN_ORGNO").toString().trim().equals("")
                && paramsMap.get("LOGIN_IS_PCB") != null
                && !paramsMap.get("LOGIN_IS_PCB").toString().trim().equals("")) {
            if (paramsMap.get("LOGIN_IS_PCB") != null
                    && paramsMap.get("LOGIN_IS_PCB").trim().equalsIgnoreCase("YES")) {// 如果当前登陆用户为人民银行用户
                if (!paramsMap.get("LOGIN_ORGNO").equals(Constants.PCB_SC_ORG_NO)) {
                    childrenNoStr = this.getChildOrgByParentNoStr(paramsMap.get("LOGIN_ORGNO"),
                            true);
                    querySql.append(" AND pcbno in (").append(childrenNoStr).append(") ");
                }
            } else {// 如果为金融机构用户
                querySql.append(" AND orgtpno = '").append(paramsMap.get("LOGIN_BANK_TYPE"))
                        .append("' ");
            }
        }
        querySql.append(" ORDER BY PEOTYPE,PEONM");
        // System.out.println(querySql.toString());
        List<Map> result = jdbcTemplate.queryForList(querySql.toString());
        // System.out.println(result.size());
        ListOrderedMap row = null;
        int j = 1;
        int i = 1;
        for (; j <= result.size(); j++) {// 返回数据条数
            // 循环每条数据
            try {
                row = (ListOrderedMap) result.get(j - 1);
            } catch (Exception e) {
                // 如果查询出错
                for (i = 1; i <= this.getxCount(); i++) {// 15列(A1-O1)
                    resultMap.put(j + "-" + i, "");
                }
                continue;
            }
            for (i = 1; i <= this.getxCount(); i++) {// 15列(A1-O1)
                try {
                    if (i == 1) {
                        resultMap.put(j + "-" + i,
                                row.getValue(i - 1) == null ? "" : row.getValue(i - 1).toString()
                                        + "");
                    } else {
                        resultMap.put(j + "-" + i,
                                row.getValue(i - 1) == null ? "" : row.getValue(i - 1).toString());
                    }
                    // System.out.println("set map" + j + "-" + i + ":"
                    // + row.getValue(i - 1));
                } catch (Exception e) {// 如果返回的数据不能按照坐标取到数据（有可能返回数据少于预计列）,则用0补位
                    resultMap.put(j + "-" + i, "");
                    // System.out.println("set map" + j + "-" + i + ":" + "0");
                }
            }
        }
        // 计算合计
        StringBuffer querySql2 = new StringBuffer("SELECT AVG(SCORE) ").append(" FROM ").append(
                "BS_EXAMSCORE WHERE 1=1 ");
        if (paramsMap.get("FROM_DATE") != null
                && !paramsMap.get("FROM_DATE").toString().trim().equals(""))
            querySql2.append(" AND EXSTDT >= '").append(paramsMap.get("FROM_DATE").toString())
                    .append("' ");
        if (paramsMap.get("TO_DATE") != null
                && !paramsMap.get("TO_DATE").toString().trim().equals(""))
            querySql2.append(" AND  EXTMDT <= '").append(paramsMap.get("TO_DATE").toString())
                    .append("' ");
        if (paramsMap.get("PEOID") != null && !paramsMap.get("PEOID").toString().trim().equals(""))
            querySql2.append(" AND PEOID Like '%").append(paramsMap.get("PEOID").toString())
                    .append("%' ");
        if (paramsMap.get("PEONM") != null && !paramsMap.get("PEONM").toString().trim().equals(""))
            querySql2.append(" AND PEONM Like '%").append(paramsMap.get("PEONM").toString())
                    .append("%' ");
        if (paramsMap.get("PEOTYPE") != null
                && !paramsMap.get("PEOTYPE").toString().trim().equals(""))
            querySql2.append(" AND PEOTYPE Like '%").append(paramsMap.get("PEOTYPE").toString())
                    .append("%' ");
        if (paramsMap.get("LOGIN_ORGNO") != null
                && !paramsMap.get("LOGIN_ORGNO").toString().trim().equals("")
                && paramsMap.get("LOGIN_IS_PCB") != null
                && !paramsMap.get("LOGIN_IS_PCB").toString().trim().equals("")) {
            if (paramsMap.get("LOGIN_IS_PCB") != null
                    && paramsMap.get("LOGIN_IS_PCB").trim().equalsIgnoreCase("YES")) {// 如果当前登陆用户为人民银行用户
                if (!paramsMap.get("LOGIN_ORGNO").equals(Constants.PCB_SC_ORG_NO)) {
                    querySql2.append(" AND pcbno in (").append(childrenNoStr).append(") ");
                }
            } else {// 如果为金融机构用户
                querySql2.append(" AND orgtpno = '").append(paramsMap.get("LOGIN_BANK_TYPE"))
                        .append("' ");
            }
        }
        // System.out.println(querySql2.toString());
        result = jdbcTemplate.queryForList(querySql2.toString());
        if (result != null && result.size() > 0) {
            row = (ListOrderedMap) result.get(0);
            // 1-4列为汉字，无法统计，统一为"-"
            resultMap.put(j + "-1", "-");
            resultMap.put(j + "-2", "-");
            resultMap.put(j + "-3", "-");
            resultMap.put(j + "-4", "-");
            resultMap.put(j + "-5", "-");
            resultMap.put(j + "-6", "-");
            resultMap.put(j + "-7", "-");
            resultMap.put(j + "-8", "-");
            resultMap.put(j + "-9", "-");
            resultMap.put(j + "-10", "-");
            resultMap.put(j + "-11", "-");
            resultMap.put(j + "-12", "-");
            resultMap.put(j + "-13", "-");
            resultMap.put(j + "-14", "平均分");
            resultMap.put(j + "-15", row.getValue(0) == null ? "0" : row.getValue(0).toString());
        }
        return resultMap;
    }

    /**
     * 根据人民银行机构编号递归所有子机构
     * 
     * @param pcbParentNo
     *            人民银行父机构编号
     * @param isContainSelf
     *            结果是否包含自己
     */
    @SuppressWarnings("unchecked")
    private String getChildOrgByParentNoStr(String pcbParentNo, Boolean isContainSelf) {
        StringBuffer sqlSb = new StringBuffer("WITH resultOrg (NO,parentNo,name) AS ")
                .append("(SELECT No,parentNo,name FROM BS_ORG WHERE parentNo = '")
                .append(pcbParentNo)
                .append("' UNION ALL SELECT child.No,child.parentNo,child.name FROM resultOrg parent, BS_ORG child ")
                .append(" WHERE parent.No = child.parentNo )").append(" SELECT NO from resultOrg ");
        // System.out.println(sqlSb.toString());
        StringBuffer result = new StringBuffer();
        if (isContainSelf) {
            result.append("'").append(pcbParentNo).append("'");
        }
        List<Map<String, Object>> childrenlt = jdbcTemplate.queryForList(sqlSb.toString());
        if (childrenlt != null && childrenlt.size() > 0) {
            result.append(",");
            for (int i = 0; i < childrenlt.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) childrenlt.get(i);
                result.append("'").append(map.get("NO").toString()).append("'");
                if (i != childrenlt.size() - 1)
                    result.append(",");
            }
            return result.toString();
        } else {
            return null;
        }
    }
}
