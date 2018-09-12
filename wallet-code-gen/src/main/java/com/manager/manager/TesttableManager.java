/*
 * Powered By code-generator
 */
package com.manager.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.manager.dao.TesttableDao;
import com.manager.manager.TesttableManager;
import com.manager.pojo.Testtable;
import com.manager.pojo.TesttablePage;
import com.manager.pojo.TesttableQuery;
import com.rkylin.wallet.paging.Pagenation;

@Component("testtableManager")
public class TesttableManager {

	@Autowired
	@Qualifier("testtableDao")
	private TesttableDao testtableDao;

	public TesttableDao getDao() {
		return this.testtableDao;
	}

	public int save(Testtable testtable) {
		int cnt = 0;
		if (testtable.getId() == null) {
			cnt = testtableDao.insertSelective(testtable);
		} else {
			cnt = testtableDao.updateByPrimaryKeySelective(testtable);
		}
		return cnt;
	}
	
	public int update(Testtable testtable) {
		return testtableDao.updateByPrimaryKey(testtable);
	}
	
	public int updateSelective(Testtable testtable) {
		return testtableDao.updateByPrimaryKeySelective(testtable);
	}
	
	public int insert(Testtable testtable) {
		return testtableDao.insert(testtable);
	}
	
	public int insertSelective(Testtable testtable) {
		return testtableDao.insertSelective(testtable);
	}
	
	public int batchInsert(List<Testtable> list){
		return testtableDao.batchInsert(list);
	}

	public Testtable findBy_id(String id) {
		return testtableDao.selectByPrimaryKey(id);
	}
	
	public Testtable findByQueryContion(TesttableQuery query) {
		List<Testtable> list = testtableDao.selectByExample(query);
		if(null!=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<Testtable> queryList(TesttableQuery query) {
		return testtableDao.selectByExample(query);
	}

	public int deleteBy_id(String id) {
		return testtableDao.deleteByPrimaryKey(id);
	}

	public int delete(TesttableQuery query) {
		return testtableDao.deleteByExample(query);
	}

	public TesttablePage queryPageList(TesttableQuery query) {
		TesttablePage testtablePage = new TesttablePage();
		Integer itemCount = testtableDao.countByExample(query);
		query.setItemCount(itemCount);

		if (itemCount == 0) {
			testtablePage.setValues(null);
		} else {
			testtablePage.setValues(testtableDao.selectPageByExample(query));
		}

		testtablePage.setPagenation(new Pagenation(query.getPageNo(), query.getPageSize(), query.getItemCount()));
		return testtablePage;
	}

}
