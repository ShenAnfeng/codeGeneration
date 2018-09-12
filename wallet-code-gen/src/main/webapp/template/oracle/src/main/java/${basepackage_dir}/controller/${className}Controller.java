<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ${basepackage}.service.${className}Service;
import ${basepackage}.pojo.${className};
import ${basepackage}.pojo.${className}Query;
import ${basepackage}.pojo.${className}Page;

import com.rkylin.wallet.paging.PageView;
import com.rkylin.wallet.paging.Pagenation;
import com.rkylin.wallet.web.BaseController;
import com.rkylin.wallet.web.ReturnObject;

@Controller
@RequestMapping("/${classNameLower}Controller")
public class ${className}Controller extends BaseController {
	public static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(${className}Controller.class);
	
	@Autowired
	private ${className}Service ${classNameLower}Service;

	/**
	 * 业务查询功能--页面跳转
	 */
	@RequestMapping(value = "/${classNameLower}Query")
	public ModelAndView ${classNameLower}Query() { 
 
		return new ModelAndView("table/${classNameLower}/${classNameLower}List");
	}
	
	/**
	 * 业务查询功能--查询数据
	 */
	@RequestMapping(value = "/${classNameLower}List")
	@ResponseBody
	public PageView<${className}> ${classNameLower}List(${className}Query ${classNameLower}Query, Integer page, Integer rows) throws Exception {
		
		PageView<${className}> pageView = null;
		try {
			${classNameLower}Query.setPageNo(page==null?1:page);
			${classNameLower}Query.setPageSize(rows==null?10:rows);
	  		${className}Page rs = ${classNameLower}Service.query${className}PageList(${classNameLower}Query);
			Pagenation pg = rs.getPagenation();
			List<${className}> list${className} = rs.getValues(); 
			pageView = new PageView<${className}>(list${className}, pg);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
		return pageView;
	}

	@RequestMapping(value = "/${classNameLower}View")
	public ModelAndView ${classNameLower}View(String id, HttpSession session) throws Exception {
		
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
		${className} ${classNameLower} = null;
		try {
			// 开始查询
			${classNameLower} = ${classNameLower}Service.find${className}ById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
		return new ModelAndView("table/${classNameLower}/${classNameLower}View", "model", ${classNameLower});
	}
	
	@RequestMapping(value = "/${classNameLower}Add")
	public ModelAndView ${classNameLower}Add(HttpSession session) {
		
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
		
		return new ModelAndView("table/${classNameLower}/${classNameLower}Add");
	}

	@RequestMapping(value = "/${classNameLower}AddSubmit")
	@ResponseBody
	public ReturnObject ${classNameLower}AddSubmit(${className}Query ${classNameLower}Query, HttpSession session) {
		
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
		try {
			// 开始查询
			${className} ${classNameLower} = new ${className}();

			<#list table.columns as column>
			<#if column.columnNameFirstLower!="id">
			<#if column.columnNameFirstLower!="createTime">
			<#if column.columnNameFirstLower!="updateTime">
			${classNameLower}.set${column.columnName}(${classNameLower}Query.get${column.columnName}());
			</#if>
			</#if>
			</#if>
			</#list>
			
			${classNameLower}Service.save${className}(${classNameLower});
			return new ReturnObject("0","操作成功");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ReturnObject("1",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/${classNameLower}Edit")
	public ModelAndView ${classNameLower}Edit(String id, HttpSession session) throws Exception {
		
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
		${className} ${classNameLower} = null;
		try {
			// 开始查询
			${classNameLower} = ${classNameLower}Service.find${className}ById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
		
		return new ModelAndView("table/${classNameLower}/${classNameLower}Edit", "model", ${classNameLower});
	}
	
	@RequestMapping(value = "/${classNameLower}EditSubmit")
	@ResponseBody
	public ReturnObject ${classNameLower}EditSubmit(String id, ${className}Query ${classNameLower}Query, HttpSession session) {
		
		try {
			//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
			// 开始查询
			${className} ${classNameLower} = ${classNameLower}Service.find${className}ById(id);

			<#list table.columns as column>
			<#if column.columnNameFirstLower!="id">
			<#if column.columnNameFirstLower!="createTime">
			<#if column.columnNameFirstLower!="updateTime">
			${classNameLower}.set${column.columnName}(${classNameLower}Query.get${column.columnName}());
			</#if>
			</#if>
			</#if>
			</#list>
			
			${classNameLower}Service.update${className}Selective(${classNameLower});
			
			return new ReturnObject("0","操作成功");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ReturnObject("1",e.getMessage());
		}
	}

	@RequestMapping(value = "/${classNameLower}Del")
	@ResponseBody
	public ReturnObject ${classNameLower}Del(String id, HttpSession session) {
		
		try {
			//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
			// 开始查询
			${classNameLower}Service.delete${className}ById(id);
			return new ReturnObject("0","操作成功");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ReturnObject("1",e.getMessage());
		}
	}
	
}