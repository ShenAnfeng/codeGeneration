package com.manager.controller.freemark;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.GenCodeConst;
import com.manager.controller.freemark.utils.DBUtil;
import com.manager.controller.freemark.utils.DateUtil;
import com.manager.controller.freemark.utils.GencodeUtils;
import com.manager.controller.freemark.utils.ZipCompressorByAnt;
import com.manager.pojo.GenTable;
import com.manager.service.QDateBaseTableI;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

/**
 * 
 * QDateBaseTableController.java
 * @author shuai.hao
 * @date 2017-6-29 14:56:48
 * @version 1.0v
 */

@RequestMapping("/QDateBaseTable/")
@Controller
public class QDateBaseTableController {
	protected Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private QDateBaseTableI qDateBaseTableI;

	/**
	 * 查询物理表列表页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("queryPage")
	public String queryPage(Model model, HttpServletRequest request) {
		/*List<GenTable> genTableList = new ArrayList<GenTable>();
		try {
			System.out.println("----------------------查询表列表开始--------------------------------");
			System.out.println(request.getServletContext().getRealPath("/"));
			HashMap paramMap = new HashMap();
			genTableList = this.qDateBaseTableI.queryMcrTransCodeList(paramMap);
			for(GenTable genTable: genTableList){
				System.out.println(genTable.getName());
			}
			request.setAttribute("genTableList", genTableList);
			System.out.println("----------------------查询表列表结束--------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return "/queryTable/queryPage";
	}
	
	/**
	 * 查询默认web数据库物理表列表
	 * @param page
	 * @param rows
	 * @param menuName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("query")
	public Map<String, Object> query(Integer page, Integer rows, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer startRow = 0;
			Integer endRow = 0;
			if (page != null && rows != null) {
				page = page < 1 ? 1 : page;
				rows = rows < 1 ? 10 : rows;
				endRow = page * rows;
				
				page = (page - 1) * rows;
				startRow = page; 
			}

			int count = (int) this.qDateBaseTableI.queryCount(tableName);
			List<GenTable> genTableList = this.qDateBaseTableI.queryMcrTransCodeList(startRow, endRow, tableName);
			JSONArray jsonArray = new JSONArray();
			for (GenTable table : genTableList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("tableName", table.getName());
				jsonObject.put("comments", table.getComments());
				jsonArray.add(jsonObject);
			}
			map.put("total", count);
			map.put("rows", jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 查询动态数据库物理表列表
	 * @param page
	 * @param rows
	 * @param menuName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("refreshQuery")
	public Map<String, Object> refreshQuery(Integer page, Integer rows, String refreshTableName, String jdbc_url,
			String jdbc_driver, String jdbc_username, String jdbc_password,String jdbc_schema) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Integer startRow = 0;
			Integer endRow = 0;
			if (page != null && rows != null) {
				if (GenCodeConst.ORACLE.equals(jdbc_driver)) {
					page = page < 1 ? 1 : page;
					rows = rows < 1 ? 10 : rows;

					endRow = page * rows;
					startRow = (page - 1) * rows;

				} else if (GenCodeConst.MYSQL.equals(jdbc_driver)) {
					page = page < 1 ? 1 : page;
					rows = rows < 1 ? 10 : rows;
					endRow = rows;

					page = (page - 1) * rows;
					startRow = page;
				}
			}
			int count = 0;
			List<GenTable> genTableList = new ArrayList<GenTable>();
			String jdbcDriver = jdbc_driver;
			if (GenCodeConst.ORACLE.equals(jdbc_driver)) {
				jdbc_url = GenCodeConst.ORACLE_JDBC_URL_PREFIX + jdbc_url;
				jdbc_driver = GenCodeConst.ORACLE_DRIVER;
			} else if (GenCodeConst.MYSQL.equals(jdbc_driver)){
				jdbc_url = GenCodeConst.MYSQL_JDBC_URL_PREFIX + jdbc_url;
				jdbc_driver = GenCodeConst.MYSQL_DRIVER;
			}
			
			count = (int) this.qDateBaseTableI.refreshQueryCount(refreshTableName, jdbc_url, jdbc_driver, jdbc_username,
					jdbc_password,jdbcDriver,jdbc_schema);
			
			genTableList = this.qDateBaseTableI.refreshQueryMcrTransCodeList(startRow, endRow, refreshTableName,
					jdbc_url, jdbc_driver, jdbc_username, jdbc_password,jdbcDriver,jdbc_schema);
			JSONArray jsonArray = new JSONArray();
			for (GenTable table : genTableList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("tableName", table.getName());
				jsonObject.put("comments", table.getComments());
				jsonArray.add(jsonObject);
			}
			map.put("total", count);
			map.put("rows", jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 生成代码操作
	 * @param packageName
	 * @param tableNames
	 * @param jdbc_url
	 * @param jdbc_driver
	 * @param jdbc_username
	 * @param jdbc_password
	 * @param jdbc_schema
	 * @return
	 */
	@ResponseBody
	@RequestMapping("genCode")
	public HashMap<String,Object> genCode(String packageName, String tableNames, String jdbc_url, String jdbc_driver,
			String jdbc_username, String jdbc_password, String jdbc_schema, HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		String currentTime;
		try {
			log.info("----------------------生成开始--------------------------------");
			/** 1.参数校验 */
			if (GencodeUtils.isNull(packageName) || GencodeUtils.isNull(tableNames) || GencodeUtils.isNull(jdbc_url)
					|| GencodeUtils.isNull(jdbc_driver) || GencodeUtils.isNull(jdbc_username)
					|| GencodeUtils.isNull(jdbc_password) || GencodeUtils.isNull(jdbc_schema)) {
				returnMap.put("returnMsg", "参数有空值");
				returnMap.put("returnCode", "fail");
				return returnMap;
			}
			jdbc_schema = jdbc_schema.toUpperCase();
			String datesourceType = jdbc_driver;
			//模板目录路径
			String templateRoot = request.getServletContext().getRealPath("/") + "template" + "/oracle";
			if (GenCodeConst.ORACLE.equals(jdbc_driver)) {
				jdbc_url = GenCodeConst.ORACLE_JDBC_URL_PREFIX + jdbc_url;
				jdbc_driver = GenCodeConst.ORACLE_DRIVER;
			} else if (GenCodeConst.MYSQL.equals(jdbc_driver)) {
				jdbc_url = GenCodeConst.MYSQL_JDBC_URL_PREFIX + jdbc_url;
				jdbc_driver = GenCodeConst.MYSQL_DRIVER;
				templateRoot = request.getServletContext().getRealPath("/") + "template" + "/mysql";
			}
			/** 2.属性赋值 */
			GencodeUtils.RewriteProperties(packageName, jdbc_url, jdbc_driver, jdbc_username, jdbc_password,
					jdbc_schema,datesourceType);

			/** 3.组装输出文件夹（加时间戳） */
			String basePath = GeneratorProperties.getProperty("outRoot");
			/** 3.1获取今天几月几日 */
			Date currentDate = DateUtil.getCurrentDate();
			String Md = DateUtil.date2Str(currentDate, DateUtil.DEFAULT_DATE_FORMAT);
			String targetPath = basePath + "/" + Md;
			/** 3.2生成目标输出文件夹 */
			File targetFile = new File(targetPath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			System.out.println(targetPath);
			/** 3.3删除不是今天创建的文件夹 */
			/** 3.3.1获取basePath路径下的所有文件夹名称 */
			File basePathFile = new File(basePath);
			/** 3.3.2删除basePath文件夹下不是今天创建的文件夹 */
			String basePathFileName[] = basePathFile.list();
			for (int i = 0; i < basePathFileName.length; i++) {
				if (basePathFileName[i].equals(Md)) {
					continue;
				} else {
					GencodeUtils.deleteFile(new File(basePathFile + "/" + basePathFileName[i]));
				}
			}
			currentTime = DateUtil.getCurrentTime(currentDate);
			String codeDownLoadPath = targetPath + "/" + currentTime + GenCodeConst.CODE_FILE_SUFFIX;
			/** 3.4创建临时目录 */
			File codeDwnPathFile = new File(codeDownLoadPath);
			if (!codeDwnPathFile.exists() && !codeDwnPathFile.isDirectory()) {
				codeDwnPathFile.mkdirs();
			}

			/** 4.生成输出代码 */
			GeneratorFacade g = new GeneratorFacade();
			String[] tableNameStr = tableNames.split(",");
			// 初始化临时输出目录
			g.initOutRoot(codeDownLoadPath);

			log.info("路径地址：" + targetFile.getAbsolutePath());
			// 初始化模板目录
			g.initTemplateRoot(templateRoot);
			g.generateByTable(tableNameStr);

			/** 5.压缩文件夹 */
			File tempCodeDwnPathFile = new File(codeDownLoadPath);
			// 判断要压缩的路径文件是否存在
			if (tempCodeDwnPathFile.exists()) {
				// 压缩文件夹
				ZipCompressorByAnt zca = new ZipCompressorByAnt(codeDownLoadPath+".zip");
				zca.compressExe(codeDownLoadPath);
			} else {
				returnMap.put("returnMsg", "生成的代码文件不存在");
				returnMap.put("returnCode", "fail");
				return returnMap;
			}
			System.out.println("----------------------生成结束--------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
			returnMap.put("returnMsg", e.getMessage());
			returnMap.put("returnCode", "fail");
			return returnMap;
		}
		returnMap.put("returnMsg",currentTime );
		returnMap.put("returnCode", "success");
		return returnMap;
	}
	
	/**
	 * 连接测试
	 * @param jdbc_url
	 * @param jdbc_driver
	 * @param jdbc_username
	 * @param jdbc_password
	 * @param jdbc_schema
	 * @return
	 */
	@ResponseBody
	@RequestMapping("testConnection")
	public String testConnection(String jdbc_url, String jdbc_driver, String jdbc_username, String jdbc_password,
			String jdbc_schema) {
		try {
			/** 1.参数重写 */
			jdbc_schema = jdbc_schema.toUpperCase();
			if (GenCodeConst.ORACLE.equals(jdbc_driver)) {
				jdbc_url = GenCodeConst.ORACLE_JDBC_URL_PREFIX + jdbc_url;
				jdbc_driver = GenCodeConst.ORACLE_DRIVER;
			} else if (GenCodeConst.MYSQL.equals(jdbc_driver)){
				jdbc_url = GenCodeConst.MYSQL_JDBC_URL_PREFIX + jdbc_url;
				jdbc_driver = GenCodeConst.MYSQL_DRIVER;
			}
			DBUtil dbUtil = new DBUtil(jdbc_url, jdbc_driver, jdbc_username, jdbc_password);
			if (!dbUtil.isGetConnection()) {
				return "连接失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "连接失败："+e.getMessage();
		}
		return "连接成功";
	}
	
	@RequestMapping("downFile") 
	public void downFile(String timeTip, HttpServletResponse response) throws IOException {
		String basePath = GeneratorProperties.getProperty("outRoot");
		Date currentDate = DateUtil.getCurrentDate();
		String Md = DateUtil.date2Str(currentDate, DateUtil.DEFAULT_DATE_FORMAT);
		String targetPath = basePath + "/" + Md;
		String codeDownLoadPath = targetPath + "/" + timeTip + GenCodeConst.CODE_FILE_SUFFIX;
		String uploadfilePath = codeDownLoadPath + ".zip";
		InputStream fis = new BufferedInputStream(new FileInputStream(uploadfilePath));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(uploadfilePath, "utf-8"));
		OutputStream out = response.getOutputStream();
		while ((len = fis.read(buf)) > 0)
			out.write(buf, 0, len);
		out.flush();
		out.close();
		fis.close();
	}
	
	public static void main(String[] args) {
	}
	
}
