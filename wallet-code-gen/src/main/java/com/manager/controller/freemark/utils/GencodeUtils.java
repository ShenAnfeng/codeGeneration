package com.manager.controller.freemark.utils;

import java.io.File;

import cn.org.rapid_framework.generator.GeneratorConstants;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class GencodeUtils {
	
	/**
	 * 删除文件夹
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}
	
	/**
	 * 属性文件重写
	 * @param packageName
	 * @param jdbc_url
	 * @param jdbc_driver
	 * @param jdbc_username
	 * @param jdbc_password
	 * @param jdbc_schema
	 * @param datesourceType
	 */
	public static void RewriteProperties(String packageName, String jdbc_url, String jdbc_driver, String jdbc_username,
			String jdbc_password, String jdbc_schema, String datesourceType) {
		/** 1.参数重写 */
		GeneratorProperties.setProperty("basepackage", packageName);
		GeneratorProperties.setProperty(GeneratorConstants.JDBC_USERNAME, jdbc_username);
		GeneratorProperties.setProperty(GeneratorConstants.JDBC_PASSWORD, jdbc_password);
		GeneratorProperties.setProperty(GeneratorConstants.JDBC_DRIVER, jdbc_driver);
		GeneratorProperties.setProperty(GeneratorConstants.JDBC_URL, jdbc_url);
		GeneratorProperties.setProperty(GeneratorConstants.JDBC_SCHEMA, jdbc_schema);
		GeneratorProperties.setProperty("datesource_type", datesourceType);
	}
	
	/**
	 * 参数校验
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		} else if (obj.toString().trim().length() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
