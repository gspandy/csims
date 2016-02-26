package com.gtm.csims.jdbc;

/**
 * 获取数据库表结构相关信息
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public interface DataBaseProber {

	/**
	 * 获得当前数据库中该表的该字段的允许长度
	 * 
	 * @param table
	 * @param field
	 * @return
	 */
	public Integer getFieldPrecision(String table, String field);

	/**
	 * 获得当前数据库中该表的该字段的保存小数点位数
	 * 
	 * @param table
	 * @param field
	 * @return
	 */
	public Integer getFieldScale(String table, String field);
}
