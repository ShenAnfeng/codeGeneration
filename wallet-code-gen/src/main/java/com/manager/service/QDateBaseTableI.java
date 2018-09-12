/*
 * Powered By code-generator
 */
package com.manager.service;

import java.sql.SQLException;
import java.util.List;

import com.manager.pojo.GenTable;

public interface QDateBaseTableI {

	/**
	 * 根据条件获取 QDateBaseTableList
	 * 
	 * @param query
	 * @return
	 */
	public List<GenTable> queryMcrTransCodeList(int startRow, int endRow, String tableName);

	/**
	 * 根据条件获取 QDateBaseTableList数量
	 * 
	 * @param tableName
	 * @return
	 */
	public long queryCount(String tableName);

	/**
	 * 根据条件获取 QDateBaseTableList（动态数据库）
	 * 
	 * @param refreshQuery
	 * @return
	 */
	public List<GenTable> refreshQueryMcrTransCodeList(int startRow, int endRow, String tableName, String jdbc_url,
			String jdbc_driver, String jdbc_username, String jdbc_password, String jdbcDriver, String jdbc_schema);

	/**
	 * 根据条件获取 QDateBaseTableList数量（动态数据库）
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public long refreshQueryCount(String tableName, String jdbc_url, String jdbc_driver, String jdbc_username,
			String jdbc_password, String jdbcDriver, String jdbc_schema) throws SQLException, ClassNotFoundException;
}
