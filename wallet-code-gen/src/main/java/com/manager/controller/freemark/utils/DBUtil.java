package com.manager.controller.freemark.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private String driverClass = "";// 驱动类
	private String url = "";// url
	private String user = "";// 用户名
	private String pass = "";// 密码
	private Connection conn = null;

	public DBUtil(String jdbc_url, String jdbc_driver, String jdbc_username, String jdbc_password) {
		this.driverClass = jdbc_driver;
		this.url = jdbc_url;
		this.user = jdbc_username;
		this.pass = jdbc_password;
		this.driverClass = jdbc_driver;
	}

	public boolean isGetConnection() throws Exception {
		try {
			this.getConnection();
			if (this.conn == null) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (this.conn != null) {
				this.conn.close();
			}
		}
		return true;
	}
	
	public void getConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName(driverClass);// 注册驱动
			System.out.println("-----------------");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}

		try {
			conn = DriverManager.getConnection(url, user, pass);// 取得连接
			System.out.println("连接成功，其哈希码是：" + conn.hashCode());
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 * @param stmt
	 * @param result
	 * @throws SQLException
	 */
	public static void colseConnection(Connection conn, Statement stmt, ResultSet result) throws SQLException {
		if (result != null) {
			result.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
}



