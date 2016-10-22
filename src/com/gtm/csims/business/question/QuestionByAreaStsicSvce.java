package com.gtm.csims.business.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.dataapp.statistics.BaseStatisticsService;
import com.gtm.csims.business.dataapp.statistics.StatisticsService;

/**
 * 辖区内问题概要情况统计
 * 
 * @author Sweet
 * 
 */
public class QuestionByAreaStsicSvce extends BaseStatisticsService implements StatisticsService {

	private static Log LOGGER = LogFactory.getLog(QuestionByAreaStsicSvce.class);

	private int xCount = 4;

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
	public int[] getMergedColumns() {
		return new int[] { 0, 1 };
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, Object> getResultData(String uuid, Map<String, String> paramsMap) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		StringBuffer query_sql = new StringBuffer();
		List<Map> results = null;
		// 根据字典表查询所有问题概况条款
		query_sql
		        .append("select (select qqtitle from BS_QUESTION where id = BSQUESTION)  AS qqtitle , ANSWERRESULT, Max(ararea) as area, ")
		        .append("dec(")
		        .append("dec(COUNT(ANSWERRESULT) * 100,17,2)")
		        .append(" /  ")
		        .append("(SELECT dec(COUNT(BSQUESTION),17,2) FROM BS_ANSWERRESULT WHERE BSQUESTION = ans.BSQUESTION AND BSQUESTIONAIRE = '")
		        .append(paramsMap.get("qid")).append("')").append(",17,2) AS P ")
		        .append("from BS_ANSWERRESULT as ans where BSQUESTIONAIRE = '").append(paramsMap.get("qid"))
		        .append("' group by BSQUESTION, ANSWERRESULT, arareaNo order by BSQUESTION, ANSWERRESULT");
		results = jdbcTemplate.queryForList(query_sql.toString());
		if (!CollectionUtils.isEmpty(results)) {
			int count = 0;
			for (int i = 0; i < results.size(); i++) {
				Map eachMap = results.get(i);
				// 从第二行开始填充数据
				resultMap.put((i + 1) + "-1", eachMap.get("qqtitle"));
				resultMap.put((i + 1) + "-2", eachMap.get("ANSWERRESULT"));
				resultMap.put((i + 1) + "-3", eachMap.get("area"));
				resultMap.put((i + 1) + "-4", eachMap.get("P"));
				count = i;
			}
			resultMap.put((count + 2) + "-1", StringUtils.EMPTY);
			resultMap.put((count + 2) + "-2", StringUtils.EMPTY);
			resultMap.put((count + 2) + "-3", StringUtils.EMPTY);
			resultMap.put((count + 2) + "-4", StringUtils.EMPTY);
		}

		return resultMap;
	}
}
