/*
 * Powered By code-generator
 */
package com.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.manager.TesttableManager;
import com.manager.service.TesttableService;
import com.manager.pojo.Testtable;
import com.manager.pojo.TesttablePage;
import com.manager.pojo.TesttableQuery;

@Service("testtableService")
public class TesttableImpl implements TesttableService{

	@Autowired
	private TesttableManager testtableManager;

	@Override
	public boolean saveTesttable(Testtable testtable) {
		int cnt = testtableManager.save(testtable);
		if(cnt == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean updateTesttable(Testtable testtable) {
		int cnt = testtableManager.update(testtable);
		if(cnt == 0){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updateTesttableSelective(Testtable testtable) {
		int cnt = testtableManager.updateSelective(testtable);
		if(cnt == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean insertTesttable(Testtable testtable) {
		int cnt = testtableManager.insert(testtable);
		if(cnt == 0){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean insertTesttableSelective(Testtable testtable) {
		int cnt = testtableManager.insertSelective(testtable);
		if(cnt == 0){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean insertTesttableBatch(List<Testtable> list) {
		if (list.isEmpty()) {
			return false;
		}
		int cnt = testtableManager.batchInsert(list);

		if (cnt == list.size()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Testtable findTesttableById(String id) {
		return testtableManager.findBy_id(id);
	}
	
	public Testtable findTesttableByQueryContion(TesttableQuery query) {
		return testtableManager.findByQueryContion(query);
	}

	@Override
	public List<Testtable> queryTesttableList(TesttableQuery query) {
		return testtableManager.queryList(query);
	}

	@Override
	public boolean deleteTesttableById(String id) {
		int cnt = testtableManager.deleteBy_id(id);
		if(cnt == 0){
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteTesttable(TesttableQuery query) {
		int cnt = testtableManager.delete(query);
		if(cnt == 0){
			return false;
		}
		return true;
	}

	@Override
	public TesttablePage queryTesttablePageList(TesttableQuery query) {
		return testtableManager.queryPageList(query);
	}

}
