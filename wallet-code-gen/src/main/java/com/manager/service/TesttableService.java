/*
 * Powered By code-generator
 */
package com.manager.service;

import java.util.List;

import com.manager.pojo.Testtable;
import com.manager.pojo.TesttablePage;
import com.manager.pojo.TesttableQuery;

public interface TesttableService {

	/**
	 * 保存 Testtable
	 * @param testtable
	 * @return
	 */
	public boolean saveTesttable(Testtable testtable);

	/**
	 * 修改 Testtable
	 * @param testtable
	 * @return
	 */
	public boolean updateTesttable(Testtable testtable);
	
	/**
	 * 修改 TesttableSelective(只修改不为null的数据)
	 * @param testtable
	 * @return
	 */
	public boolean updateTesttableSelective(Testtable testtable);

	/**
	 * 新增 Testtable
	 * @param testtable
	 * @return
	 */
	public boolean insertTesttable(Testtable testtable);
	
	/**
	 * 新增 TesttableSelective（只插入不为null的字段，不影响有默认值的字段）
	 * @param testtable
	 * @return
	 */
	public boolean insertTesttableSelective(Testtable testtable);
	
	/**
	 * 批量 新增Testtable
	 * @param testtable
	 * @return
	 */
	public boolean insertTesttableBatch(List<Testtable> list);

	/**
	 * 根据ID获取 Testtable
	 * @param id
	 * @return
	 */
	public Testtable findTesttableById(String id);
	
	/**
	 * 根据条件获取 Testtable
	 * @param query
	 * @return
	 */
	public Testtable findTesttableByQueryContion(TesttableQuery query);

	/**
	 * 根据条件获取 TesttableList
	 * @param query
	 * @return
	 */
	public List<Testtable> queryTesttableList(TesttableQuery query);

	/**
	 * 根据ID获取 Testtable
	 * @param id
	 * @return
	 */
	public boolean deleteTesttableById(String id);

	/**
	 * 根据条件删除 Testtable
	 * @param query
	 * @return
	 */
	public boolean deleteTesttable(TesttableQuery query);

	/**
	 * 根据条件分页获取 TesttableList
	 * @param query
	 * @return
	 */
	public TesttablePage queryTesttablePageList(TesttableQuery query);

}
