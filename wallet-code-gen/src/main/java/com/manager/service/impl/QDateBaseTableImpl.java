/*
 * Powered By code-generator
 */
package com.manager.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.GenCodeConst;
import com.manager.controller.freemark.utils.DBUtil;
import com.manager.manager.QDateBaseTableManager;
import com.manager.service.QDateBaseTableI;
import com.manager.pojo.GenTable;

@Service("qDateBaseTableI")
public class QDateBaseTableImpl implements QDateBaseTableI{

	@Autowired
	private QDateBaseTableManager qDateBaseTableManager;

	@Override
	public List<GenTable> queryMcrTransCodeList(int startRow, int endRow, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		if (tableName != null && !"".equals(tableName)) {
			map.put("tableName", "%"+tableName+"%");
		}
		return this.qDateBaseTableManager.queryAllTables(map);
	}
	
	public long queryCount(String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (tableName != null && !"".equals(tableName)) {
			map.put("tableName", "%"+tableName+"%");
		}
		return this.qDateBaseTableManager.queryCount(map);
	}

	@Override
	public List<GenTable> refreshQueryMcrTransCodeList(int startRow, int endRow, String tableName, String jdbc_url,
			String jdbc_driver, String jdbc_username, String jdbc_password, String jdbcDriver, String jdbc_schema) {
		Connection conn = null;// 表示数据库连接
		Statement stmt = null;// 表示数据库的更新
		ResultSet result = null;// 查询数据库
		List<GenTable> genTableList = new ArrayList<GenTable>();
		try {
			Class.forName(jdbc_driver);// 使用class类来加载程序
			conn = DriverManager.getConnection(jdbc_url, jdbc_username, jdbc_password);// 连接数据库
			// Statement接口要通过connection接口来进行实例化操作
			stmt = conn.createStatement();
			// 执行SQL语句来查询数据库
			StringBuffer sbSql = new StringBuffer();
			
			if (GenCodeConst.ORACLE.equals(jdbcDriver)) {
				sbSql.append("select * from(select t.*,rownum rn from(");
				sbSql.append("SELECT tb.TABLE_NAME as name, ");
				sbSql.append("c.COMMENTS as comments ");
				sbSql.append("FROM ");
				sbSql.append("user_tables tb,");
				sbSql.append("user_tab_comments c ");
				sbSql.append("where 1 = 1 ");
				sbSql.append("and tb.TABLE_NAME = c.TABLE_NAME ");
				if (tableName != null && !"".equals(tableName)) {
					tableName = "'%" + tableName + "%'";
					sbSql.append("and tb.TABLE_NAME like " + tableName);
				}
				sbSql.append(") t where rownum <= " + endRow + " ) where rn > " + startRow);
			} else if (GenCodeConst.MYSQL.equals(jdbcDriver)) {
				sbSql.append("SELECT TABLE_NAME as name, ");
				sbSql.append("TABLE_COMMENT as comments ");
				sbSql.append("FROM ");
				sbSql.append("information_schema.TABLES ");
				sbSql.append("WHERE ");
				sbSql.append("1 = 1 ");
				sbSql.append("and table_type = 'BASE TABLE' ");
				sbSql.append("and table_schema = '" + jdbc_schema + "' ");
				if (tableName != null && !"".equals(tableName)) {
					tableName = "'%" + tableName + "%'";
					sbSql.append("and TABLE_NAME like " + tableName);
				}
				sbSql.append(" LIMIT " + startRow + ", " + endRow);
			}
			System.out.println(sbSql.toString());
			result = stmt.executeQuery(sbSql.toString());


			while (result.next()) {// 判断有没有下一行
				GenTable genTable = new GenTable();
				String name = result.getString("NAME");
				String comments = result.getString("COMMENTS");
				genTable.setName(name);
				genTable.setComments(comments);
				genTableList.add(genTable);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				DBUtil.colseConnection(conn, stmt, result);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return genTableList;
	}

	@Override
	public long refreshQueryCount(String tableName, String jdbc_url, String jdbc_driver, String jdbc_username,
			String jdbc_password, String jdbcDriver, String jdbc_schema) throws SQLException, ClassNotFoundException {
		Connection conn = null;// 表示数据库连接
		Statement stmt = null;// 表示数据库的更新
		ResultSet result = null;// 查询数据库
		int DEPTID = 0;
		try {
			Class.forName(jdbc_driver);// 使用class类来加载程序
			conn = DriverManager.getConnection(jdbc_url, jdbc_username, jdbc_password);// 连接数据库
			// Statement接口要通过connection接口来进行实例化操作
			stmt = conn.createStatement();
			// 执行SQL语句来查询数据库
			StringBuffer sbSql = new StringBuffer();
			if (GenCodeConst.ORACLE.equals(jdbcDriver)) {
				sbSql.append("SELECT COUNT(tb.TABLE_NAME) ");
				sbSql.append("FROM ");
				sbSql.append("user_tables tb, user_tab_comments c ");
				sbSql.append("WHERE ");
				sbSql.append("1 = 1 ");
				sbSql.append("and tb.TABLE_NAME = c.TABLE_NAME ");
				if (tableName != null && !"".equals(tableName)) {
					tableName = "'%" + tableName + "%'";
					sbSql.append("and tb.TABLE_NAME like " + tableName);
				}
			} else if (GenCodeConst.MYSQL.equals(jdbcDriver)) {
				sbSql.append("SELECT COUNT(TABLE_NAME) ");
				sbSql.append("FROM ");
				sbSql.append("information_schema.TABLES ");
				sbSql.append("WHERE ");
				sbSql.append("1 = 1 ");
				sbSql.append("and table_type = 'BASE TABLE' ");
				sbSql.append("and table_schema = '" + jdbc_schema + "' ");
				if (tableName != null && !"".equals(tableName)) {
					tableName = "'%" + tableName + "%'";
					sbSql.append("and TABLE_NAME like " + tableName);
				}
			}
			
			result = stmt.executeQuery(sbSql.toString());
			while (result.next()) {// 判断有没有下一行
				DEPTID = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} finally{
			try {
				DBUtil.colseConnection(conn, stmt, result);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return DEPTID;
	}
}
