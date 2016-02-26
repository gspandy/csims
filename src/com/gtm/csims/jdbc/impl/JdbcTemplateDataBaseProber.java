package com.gtm.csims.jdbc.impl;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gtm.csims.jdbc.DataBaseProber;

/**
 * 通过jdbcTemplate获取数据库表结构相关信息
 * 
 * @author Sweet
 * 
 */
public class JdbcTemplateDataBaseProber implements DataBaseProber {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 获得当前数据库中该表的该字段的允许长度
	 * 
	 * @param table
	 * @param field
	 * @return
	 */
	public Integer getFieldPrecision(String table, String field) {
		return jdbcTemplate
				.queryForRowSet("SELECT " + field + " FROM " + table)
				.getMetaData().getPrecision(1);
	}

	/**
	 * 获得当前数据库中该表的该字段的保存小数点位数
	 * 
	 * @param table
	 * @param field
	 * @return
	 */
	public Integer getFieldScale(String table, String field) {
		return jdbcTemplate
				.queryForRowSet("SELECT " + field + " FROM " + table)
				.getMetaData().getScale(1);
	}

}
