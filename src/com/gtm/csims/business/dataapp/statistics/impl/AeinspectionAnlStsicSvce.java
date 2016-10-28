package com.gtm.csims.business.dataapp.statistics.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.dataapp.statistics.BaseStatisticsService;
import com.gtm.csims.business.dataapp.statistics.StatisticsService;
import com.gtm.csims.utils.Constants;

/**
 * 政执法检查情况统计表
 * 
 * @author Sweet
 * 
 */
public class AeinspectionAnlStsicSvce extends BaseStatisticsService implements StatisticsService {
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
		StringBuffer querySql = new StringBuffer("SELECT A1,B1,C1,D1,E1,F1,G1,H1,I1,J1,K1,L1,M1,N1,P1 ")
		        .append(" FROM ").append("BS_AEINSPECTION_ANL").append(" WHERE 1=1 ");
		if (paramsMap.get("FROM_DATE") != null && !paramsMap.get("FROM_DATE").toString().trim().equals("")) {
			querySql.append(" AND ANLDATE >= '").append(paramsMap.get("FROM_DATE").toString()).append(" 00:00:00' ");
			// querySql.append(" AND ANLDATE >=  ? ");
		}
		if (paramsMap.get("TO_DATE") != null && !paramsMap.get("TO_DATE").toString().trim().equals("")) {
			querySql.append(" AND  ANLDATE <= '").append(paramsMap.get("TO_DATE").toString()).append(" 23:59:59' ");
			// querySql.append(" AND ANLDATE <= ? ");
		}
		if (paramsMap.get("A1") != null && !paramsMap.get("A1").toString().trim().equals("")) {
			querySql.append(" AND A1 Like '%").append(paramsMap.get("A1").toString()).append("%' ");
		}
		if (paramsMap.get("B1") != null && !paramsMap.get("B1").toString().trim().equals("")) {
			querySql.append(" AND B1 Like '%").append(paramsMap.get("B1").toString()).append("%' ");
		}
		if (paramsMap.get("ORGNO") != null && !paramsMap.get("ORGNO").toString().trim().equals("")
		        && !paramsMap.get("ORGNO").toString().trim().equals(Constants.PCB_SC_ORG_NO)) {
			String childrenNoStr = super.getChildOrgByParentNoStr(paramsMap.get("ORGNO").toString().trim());
			if (StringUtils.isNotBlank(childrenNoStr)) {
				querySql.append(" AND aeorgno IN (").append(childrenNoStr).append(") ");
			}
		}
		querySql.append(" ORDER BY A1,B1");
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
				for (i = 1; i <= this.getxCount(); i++) {// 17列(A1-Q1)
					resultMap.put(j + "-" + i, "0");
				}
				continue;
			}
			for (i = 1; i <= this.getxCount(); i++) {// 17列(A1-Q1)
				try {
					resultMap.put(j + "-" + i, row.getValue(i - 1) == null ? "0" : row.getValue(i - 1).toString());
					// System.out.println("set map" + j + "-" + i + ":"
					// + row.getValue(i - 1));
				} catch (Exception e) {// 如果返回的数据不能按照坐标取到数据（有可能返回数据少于预计列）,则用0补位
					resultMap.put(j + "-" + i, "0");
					// System.out.println("set map" + j + "-" + i + ":" + "0");
				}
			}
		}
		// 计算合计
		StringBuffer querySql2 = new StringBuffer(
		        "SELECT SUM(decimal(C1,13,0)),SUM(decimal(D1,13,0)),SUM(decimal(E1,13,0)),SUM(decimal(F1,13,0)),SUM(decimal(G1,13,0)) ")
		        .append(",SUM(decimal(H1,13,0)),SUM(decimal(I1,13,0)),SUM(decimal(J1,13,0)),SUM(decimal(K1,13,0))")
		        .append(",SUM(decimal(L1,13,0)),SUM(decimal(M1,13,0)),SUM(decimal(P1,13,2))").append(" FROM ")
		        .append("BS_AEINSPECTION_ANL WHERE 1=1 ");
		if (paramsMap.get("FROM_DATE") != null && !paramsMap.get("FROM_DATE").toString().trim().equals(""))
			querySql2.append(" AND ANLDATE >= '").append(paramsMap.get("FROM_DATE").toString()).append("' ");
		if (paramsMap.get("TO_DATE") != null && !paramsMap.get("TO_DATE").toString().trim().equals(""))
			querySql2.append(" AND  ANLDATE <= '").append(paramsMap.get("TO_DATE").toString()).append("' ");
		if (paramsMap.get("A1") != null && !paramsMap.get("A1").toString().trim().equals(""))
			querySql2.append(" AND A1 Like '%").append(paramsMap.get("A1").toString()).append("%' ");
		if (paramsMap.get("B1") != null && !paramsMap.get("B1").toString().trim().equals(""))
			querySql2.append(" AND B1 Like '%").append(paramsMap.get("B1").toString()).append("%' ");
		// System.out.println(querySql2.toString());
		result = jdbcTemplate.queryForList(querySql2.toString());
		if (result != null && result.size() > 0) {
			row = (ListOrderedMap) result.get(0);
			// 1-4列为汉字，无法统计，统一为"-"
			resultMap.put(j + "-1", "-");
			resultMap.put(j + "-2", "-");
			resultMap.put(j + "-3", row.getValue(0) == null ? "0" : row.getValue(0).toString());
			resultMap.put(j + "-4", row.getValue(1) == null ? "0" : row.getValue(1).toString());
			resultMap.put(j + "-5", row.getValue(2) == null ? "0" : row.getValue(2).toString());
			resultMap.put(j + "-6", row.getValue(3) == null ? "0" : row.getValue(3).toString());
			resultMap.put(j + "-7", row.getValue(4) == null ? "0" : row.getValue(4).toString());
			resultMap.put(j + "-8", row.getValue(5) == null ? "0" : row.getValue(5).toString());
			resultMap.put(j + "-9", row.getValue(6) == null ? "0" : row.getValue(6).toString());
			resultMap.put(j + "-10", row.getValue(7) == null ? "0" : row.getValue(7).toString());
			resultMap.put(j + "-11", row.getValue(8) == null ? "0" : row.getValue(8).toString());
			resultMap.put(j + "-12", row.getValue(9) == null ? "0" : row.getValue(9).toString());
			resultMap.put(j + "-13", row.getValue(10) == null ? "0" : row.getValue(10).toString());
			resultMap.put(j + "-14", "-");
			resultMap.put(j + "-15", row.getValue(11) == null ? "0" : row.getValue(11).toString());
		}
		return resultMap;
	}
}
