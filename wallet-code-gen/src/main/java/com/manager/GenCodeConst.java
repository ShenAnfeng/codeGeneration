package com.manager;

public class GenCodeConst {

	/**
	 * 数据库类型
	 */
	// Oracle数据库
	public static final String ORACLE = "Oracle";
	// Mysql数据库
	public static final String MYSQL = "Mysql";

	/**
	 * 数据库驱动类型
	 */
	// Oracle数据库
	public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	// Mysql数据库
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * 生成代码文件后缀标识
	 */
	public static final String CODE_FILE_SUFFIX = "_CODE";

	/**
	 * ORACLE jdbc_url前缀标识
	 */
	public static final String ORACLE_JDBC_URL_PREFIX = "jdbc:oracle:thin:@";

	/**
	 * MYSQL jdbc_url前缀标识
	 */
	public static final String MYSQL_JDBC_URL_PREFIX = "jdbc:mysql://";

}
