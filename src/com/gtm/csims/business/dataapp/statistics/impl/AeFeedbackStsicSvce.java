package com.gtm.csims.business.dataapp.statistics.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.dataapp.statistics.BaseStatisticsService;
import com.gtm.csims.business.dataapp.statistics.StatisticsService;

/**
 * 反馈情况统计表.
 * 
 * @author Sweet
 * 
 */
public class AeFeedbackStsicSvce extends BaseStatisticsService implements StatisticsService {
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
        return 7;
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
        // 获取统计参数
        String year = paramsMap.get("YEAR").toString();

        // 按照报送机构所属银行类型分组加总然后存入临时表
        StringBuffer s1 = new StringBuffer("INSERT INTO BS_AEFEEDBACK_TMP (id,D1,D2,D3,D4,D5")
                .append(",BankTypeNo,BankTypeName)").append(" SELECT '").append(uuid)
                .append("',0,COUNT(ID),0,COUNT(ID),0").append(",TOPLEVELBANKNO,MAX(TOPLEVELBANKNM) ").append(" FROM ")
                .append(" BS_AEFEEDBACK ").append(" WHERE CREATEDATE >= '").append(year).append("-01-01 00:00:00")
                .append("' AND CREATEDATE <='").append(year).append("-12-31 23:59:59").append("'");
        s1.append(" GROUP BY TOPLEVELBANKNO ");
        try {
            jdbcTemplate.execute(s1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        s1.setLength(0);
        s1.append("INSERT INTO BS_AEFEEDBACK_TMP (id,D1,D2,D3,D4,D5").append(",BankTypeNo,BankTypeName)")
                .append(" SELECT '").append(uuid).append("',0,0,COUNT(ID),COUNT(ID),0")
                .append(",TOPLEVELBANKNO,MAX(TOPLEVELBANKNM) ").append(" FROM ").append(" BS_AEPUBNISHFEEDBACK ")
                .append(" WHERE CREATEDATE >= '").append(year).append("-01-01 00:00:00").append("' AND CREATEDATE <='")
                .append(year).append("-12-31 23:59:59").append("'");
        s1.append(" GROUP BY TOPLEVELBANKNO ");
        try {
            jdbcTemplate.execute(s1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        s1.setLength(0);
        s1.append("INSERT INTO BS_AEFEEDBACK_TMP (id,D1,D2,D3,D4,D5").append(",BankTypeNo,BankTypeName)")
                .append(" SELECT '").append(uuid).append("',COUNT(ID),0,0,0,0")
                .append(",TOPLEVELBANKNO,MAX(TOPLEVELBANKNM) ").append(" FROM ").append(" BS_AECONCLUSION ")
                .append(" WHERE CREATEDATE >= '").append(year).append("-01-01 00:00:00").append("' AND CREATEDATE <='")
                .append(year).append("-12-31 23:59:59").append("'");
        s1.append(" AND TOPLEVELBANKNO IS NOT NULL ").append(" GROUP BY TOPLEVELBANKNO ");
        try {
            jdbcTemplate.execute(s1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uuid;
    }

    /**
     * 根据临时表中的初始数据进行合计等计算
     * 
     * @param uuid
     */
    private void calculateTempData(String uuid) {
        // 按照报送机构所属银行类型分组加总然后存入临时表
        StringBuffer s1 = new StringBuffer("INSERT INTO BS_AEFEEDBACK_TMP (id,D1,D2,D3,D4,D5")
                .append(",BankTypeNo,BankTypeName)").append(" SELECT '").append(uuid)
                .append("',0,0,0,0,CASE WHEN SUM(D1) = 0 THEN 0 ELSE decimal(SUM(D4)/SUM(D1),18,6) END ")
                .append(",BankTypeNo,MAX(BankTypeName) ").append(" FROM ").append(" BS_AEFEEDBACK_TMP ")
                .append(" WHERE id = '").append(uuid).append("'");
        s1.append(" GROUP BY BankTypeNo ");
        try {
            jdbcTemplate.execute(s1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                "SELECT MAX(BankTypeName),SUM(decimal(D1,13,0)),SUM(decimal(D2,13,0)),SUM(decimal(D3,13,0)),SUM(decimal(D4,13,0)),SUM(decimal(D5,18,6)) ")
                .append(" FROM ").append("BS_AEFEEDBACK_TMP").append(" WHERE id = '").append(uuid).append("'");
        querySql.append(" GROUP BY BankTypeNo  order by SUM(decimal(D5,13,2)) DESC");
        // System.out.println(querySql.toString());
        List<Map> result = jdbcTemplate.queryForList(querySql.toString());
        // System.out.println(result.size());
        ListOrderedMap row = null;
        for (int j = 1; j <= result.size(); j++) {// 返回数据条数
            // 循环每条数据
            try {
                row = (ListOrderedMap) result.get(j - 1);
            } catch (Exception e) {
                // 如果查询出错
                for (int i = 1; i <= this.getxCount(); i++) {// 17列(A1-Q1)
                    resultMap.put(j + "-" + i, "0");
                }
                continue;
            }
            resultMap.put(j + "-" + 1, j);
            for (int i = 2; i <= this.getxCount(); i++) {// 17列(A1-Q1)
                try {
                    resultMap.put(j + "-" + i, row.getValue(i - 1 - 1) == null ? "0" : row.getValue(i - 1 - 1)
                            .toString());
                    // System.out.println("set map" + j + "-" + i + ":"
                    // + row.getValue(i - 1));
                } catch (Exception e) {// 如果返回的数据不能按照坐标取到数据（有可能返回数据少于预计列）,则用0补位
                    resultMap.put(j + "-" + i, "0");
                    // System.out.println("set map" + j + "-" + i + ":" + "0");
                }
            }
            BigDecimal d3 = new BigDecimal(resultMap.get(j + "-" + 3).toString());
            if (d3.compareTo(BigDecimal.ZERO) != 0) {
                resultMap.put(j + "-" + this.getxCount(),
                        new BigDecimal(resultMap.get(j + "-" + 6).toString()).divide(d3, 2, BigDecimal.ROUND_HALF_UP));
            } else {
                resultMap.put(j + "-" + this.getxCount(), "0.00");
            }
        }
        return resultMap;
    }
}
