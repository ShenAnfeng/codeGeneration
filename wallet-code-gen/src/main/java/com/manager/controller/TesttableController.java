/*
 * Powered By code-generator
 */
package com.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.manager.service.TesttableService;
import com.manager.pojo.Testtable;
import com.manager.pojo.TesttableQuery;
import com.manager.pojo.TesttablePage;

import com.rkylin.wallet.paging.PageView;
import com.rkylin.wallet.paging.Pagenation;
import com.rkylin.wallet.web.BaseController;
import com.rkylin.wallet.web.ReturnObject;

@Controller
@RequestMapping("/testtableController")
public class TesttableController extends BaseController {
	public static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(TesttableController.class);
	
	@Autowired
	private TesttableService testtableService;

	/**
	 * 业务查询功能--页面跳转
	 */
	@RequestMapping(value = "/testtableQuery")
	public ModelAndView testtableQuery() { 
 
		return new ModelAndView("table/testtable/testtableList");
	}
	
	/**
	 * 业务查询功能--查询数据
	 */
	@RequestMapping(value = "/testtableList")
	@ResponseBody
	public PageView<Testtable> testtableList(TesttableQuery testtableQuery, Integer page, Integer rows) throws Exception {
		
		PageView<Testtable> pageView = null;
		try {
			testtableQuery.setPageNo(page==null?1:page);
			testtableQuery.setPageSize(rows==null?10:rows);
	  		TesttablePage rs = testtableService.queryTesttablePageList(testtableQuery);
			Pagenation pg = rs.getPagenation();
			List<Testtable> listTesttable = rs.getValues(); 
			pageView = new PageView<Testtable>(listTesttable, pg);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
		return pageView;
	}

	@RequestMapping(value = "/testtableView")
	public ModelAndView testtableView(String id, HttpSession session) throws Exception {
		
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
		Testtable testtable = null;
		try {
			// 开始查询
			testtable = testtableService.findTesttableById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
		return new ModelAndView("table/testtable/testtableView", "model", testtable);
	}
	
	@RequestMapping(value = "/testtableAdd")
	public ModelAndView testtableAdd(HttpSession session) {
		
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
		
		return new ModelAndView("table/testtable/testtableAdd");
	}

	@RequestMapping(value = "/testtableAddSubmit")
	@ResponseBody
	public ReturnObject testtableAddSubmit(TesttableQuery testtableQuery, HttpSession session) {
		
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
		try {
			// 开始查询
			Testtable testtable = new Testtable();

			testtable.setName(testtableQuery.getName());
			testtable.setLength(testtableQuery.getLength());
			
			testtableService.saveTesttable(testtable);
			return new ReturnObject("0","操作成功");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ReturnObject("1",e.getMessage());
		}
	}
	
	@RequestMapping(value = "/testtableEdit")
	public ModelAndView testtableEdit(String id, HttpSession session) throws Exception {
		
		//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
		Testtable testtable = null;
		try {
			// 开始查询
			testtable = testtableService.findTesttableById(id);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw e;
		}
		
		return new ModelAndView("table/testtable/testtableEdit", "model", testtable);
	}
	
	@RequestMapping(value = "/testtableEditSubmit")
	@ResponseBody
	public ReturnObject testtableEditSubmit(String id, TesttableQuery testtableQuery, HttpSession session) {
		
		try {
			//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
			// 开始查询
			Testtable testtable = testtableService.findTesttableById(id);

			testtable.setName(testtableQuery.getName());
			testtable.setLength(testtableQuery.getLength());
			
			testtableService.updateTesttableSelective(testtable);
			
			return new ReturnObject("0","操作成功");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ReturnObject("1",e.getMessage());
		}
	}

	@RequestMapping(value = "/testtableDel")
	@ResponseBody
	public ReturnObject testtableDel(String id, HttpSession session) {
		
		try {
			//LoginInfo loginInfo = (LoginInfo)session.getAttribute(LoginUser.KEY_NAME_LOGINUSER);
			// 开始查询
			testtableService.deleteTesttableById(id);
			return new ReturnObject("0","操作成功");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return new ReturnObject("1",e.getMessage());
		}
	}
	
}