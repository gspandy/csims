package com.gtm.csims.business.dataapp.statistics.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.dataapp.statistics.BaseStatisticsService;
import com.gtm.csims.business.dataapp.statistics.StatisticsService;
import com.gtm.csims.utils.Constants;

/**
 * 辖区内问题概要情况统计
 * 
 * @author Sweet
 * 
 */
public class WtgkStsicSvce extends BaseStatisticsService implements StatisticsService {
	private static Log log = LogFactory.getLog(WtgkStsicSvce.class);
	private int xCount = 0;

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
		return this.xCount;
	}

	/**
	 * 返回当前统计表需要合并单元格处理的列
	 * 
	 * @return
	 */
	// @Override
	// public int[] getMergedColumns() {
	// return new int[] { 0 };
	// }
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
		return UUID.randomUUID().toString();
	}

	/**
	 * 根据临时表中的初始数据进行合计等计算
	 * 
	 * @param uuid
	 */
	private void calculateTempData(String uuid) {
		// TODO anything
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
		// 第一行第一列和第二列默认值，固定不变
		resultMap.put("1-1", "问题概况编号");
		resultMap.put("1-2", "问题概况");
		// 根据字典表查询所有问题概况条款
		StringBuffer queryWTGK_sql = new StringBuffer();
		queryWTGK_sql.append("SELECT id,dvalue FROM BS_DICTIONARY WHERE type = 'WTGK' ORDER BY dkey");
		List<Map> wtgkDics = new ArrayList<Map>();
		// if (CollectionUtils.isEmpty(wtgkDics)) {
		// return resultMap;
		// }

		int index = 0;

		// 一、制度建设
		List<Map> wtgkDics_1 = jdbcTemplate
		        .queryForList("SELECT id,dvalue FROM BS_DICTIONARY WHERE id = '000012' OR stat = '000012'  ORDER BY dkey");
		for (Map dic : wtgkDics_1) {
			// 从第二行开始填充数据
			resultMap.put((index + 2) + "-1", index + 1);
			resultMap.put((index + 2) + "-2", dic.get("dvalue"));
			index++;
		}
		wtgkDics.addAll(wtgkDics_1);

		// 一、制度建设
		List<Map> wtgkDics_2 = jdbcTemplate
		        .queryForList("SELECT id,dvalue FROM BS_DICTIONARY WHERE id = '000018' OR stat = '000018'  ORDER BY dkey");
		for (Map dic : wtgkDics_2) {
			// 从第二行开始填充数据
			resultMap.put((index + 2) + "-1", index + 1);
			resultMap.put((index + 2) + "-2", dic.get("dvalue"));
			index++;
		}
		wtgkDics.addAll(wtgkDics_2);

		// 一、制度建设
		List<Map> wtgkDics_3 = jdbcTemplate
		        .queryForList("SELECT id,dvalue FROM BS_DICTIONARY WHERE id = '000024' OR stat = '000024'  ORDER BY dkey");
		for (Map dic : wtgkDics_3) {
			// 从第二行开始填充数据
			resultMap.put((index + 2) + "-1", index + 1);
			resultMap.put((index + 2) + "-2", dic.get("dvalue"));
			index++;
		}
		wtgkDics.addAll(wtgkDics_3);

		// 一、制度建设
		List<Map> wtgkDics_4 = jdbcTemplate
		        .queryForList("SELECT id,dvalue FROM BS_DICTIONARY WHERE id = '000031' OR stat = '000031'  ORDER BY dkey");
		for (Map dic : wtgkDics_4) {
			// 从第二行开始填充数据
			resultMap.put((index + 2) + "-1", index + 1);
			resultMap.put((index + 2) + "-2", dic.get("dvalue"));
			index++;
		}
		wtgkDics.addAll(wtgkDics_4);

		// 一、制度建设
		List<Map> wtgkDics_5 = jdbcTemplate
		        .queryForList("SELECT id,dvalue FROM BS_DICTIONARY WHERE id = '000036' OR stat = '000036'  ORDER BY dkey");
		for (Map dic : wtgkDics_5) {
			// 从第二行开始填充数据
			resultMap.put((index + 2) + "-1", index + 1);
			resultMap.put((index + 2) + "-2", dic.get("dvalue"));
			index++;
		}
		wtgkDics.addAll(wtgkDics_5);

		// 按照被检查机构分组查询时间段内总的问题概要字符串
		StringBuffer querySql = new StringBuffer();
		querySql.append("WITH s AS ").append(" ( SELECT row_number() OVER(PARTITION BY AEEDORGNM ORDER BY id) id1,")
		        .append("   row_number() OVER(PARTITION BY AEEDORGNM ORDER BY id) id2,")
		        .append("         SELECTEDWTGK, AEEDORGNM").append("       FROM BS_FACTBOOK")
		        .append("       WHERE 1 = 1 ");
		if (paramsMap.get("FROM_DATE") != null && !paramsMap.get("FROM_DATE").toString().trim().equals(""))
			querySql.append(" AND CREATEDATE >= '").append(paramsMap.get("FROM_DATE").toString()).append("' ");
		if (paramsMap.get("TO_DATE") != null && !paramsMap.get("TO_DATE").toString().trim().equals(""))
			querySql.append(" AND  CREATEDATE <= '").append(paramsMap.get("TO_DATE").toString()).append("' ");
		if (paramsMap.get("LOGIN_ORGNO") != null && !paramsMap.get("LOGIN_ORGNO").toString().trim().equals("")
		        && paramsMap.get("LOGIN_IS_PCB") != null && !paramsMap.get("LOGIN_IS_PCB").toString().trim().equals("")) {
			if (paramsMap.get("LOGIN_IS_PCB") != null && paramsMap.get("LOGIN_IS_PCB").trim().equalsIgnoreCase("YES")) {// 如果当前登陆用户为人民银行用户
				if (!paramsMap.get("LOGIN_ORGNO").equals(Constants.PCB_SC_ORG_NO)) {
					String childrenNoStr = super.getChildOrgByParentNoStr(paramsMap.get("LOGIN_ORGNO"));
					querySql.append(" AND PCBNO in (").append(childrenNoStr).append(") ");
				}
			} else {// 如果为金融机构用户
				querySql.append(" AND ORGTPNO = '").append(paramsMap.get("LOGIN_BANK_TYPE")).append("' ");
			}
		}
		querySql.append("   ) ,").append("   t ( AEEDORGNM,id1, id2,SELECTEDWTGK ) AS")
		        .append("   (SELECT AEEDORGNM,id1,id2" + ",CAST(SELECTEDWTGK AS VARCHAR(2000))").append("      FROM s")
		        .append("      WHERE id1 = 1").append("      AND id2 = 1").append("      UNION ALL")
		        .append("        SELECT t.AEEDORGNM, t.id1 + 1, t.id2,")
		        .append("           CAST(s.SELECTEDWTGK||''||t.SELECTEDWTGK AS VARCHAR(2000))")
		        .append("            FROM s, t").append("            WHERE s.id2 = t.id1 + 1")
		        .append("            AND t.AEEDORGNM =  s.AEEDORGNM )").append("  SELECT AEEDORGNM,SELECTEDWTGK")
		        .append("  FROM t").append("  WHERE t.id1 =").append("      (SELECT MAX(id1)")
		        .append("          FROM s").append("          WHERE s.AEEDORGNM = t.AEEDORGNM)");

		System.out.println(querySql.toString());
		List<Map> result = jdbcTemplate.queryForList(querySql.toString());
		if (result != null && result.size() > 0) {
			log.debug(result.size());
			// 根据查询的被检查机构分组记录条数再加上问题概况Id和value两列
			this.xCount = result.size() + 2;
			String wtgkStr = null;
			for (int i = 0; i < result.size(); i++) {
				Map aeedOrgData = result.get(i);
				resultMap.put("1-" + (i + 3), aeedOrgData.get("AEEDORGNM"));
				wtgkStr = aeedOrgData.get("SELECTEDWTGK").toString();
				Set wtgkSet = this.removeDuplicateItem(wtgkStr);
				String tempWtgk = null;
				Map wtgkDicsMap = null;
				for (int j = 0; j < wtgkDics.size(); j++) {
					wtgkDicsMap = wtgkDics.get(j);
					tempWtgk = wtgkDicsMap.get("id").toString().trim();
					if (wtgkSet.contains(tempWtgk)) {
						resultMap.put((j + 2) + "-" + (i + 3), "√");
					} else {
						resultMap.put((j + 2) + "-" + (i + 3), " ");
					}
				}
			}
		} else {
			this.xCount = 2;
			return resultMap;
		}
		return resultMap;
	}

	/**
	 * 根据传入的字符串按照','分隔，去掉重复项再返回
	 * 
	 * @param src
	 * @return
	 */
	private Set<String> removeDuplicateItem(String src) {
		Set<String> result = new HashSet<String>();
		if (src == null || src.length() <= 0)
			return result;
		String[] strArr = src.split(",");
		for (int i = 0; i < strArr.length; i++) {
			result.add(strArr[i].trim());
		}
		return result;
	}
}
