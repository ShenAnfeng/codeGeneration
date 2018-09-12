/*
 * Powered By code-generator
 */
package com.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manager.dao.TesttableDao;
import com.manager.pojo.Testtable;
import com.manager.pojo.TesttableQuery;
import com.rkylin.wallet.wps.dao.BaseDao;

@Repository("testtableDao")
public class TesttableDao extends BaseDao {

	public int countByExample(TesttableQuery example) {
		return super.getSqlSession().selectOne("TesttableMapper.countByExample", example);
	}

	public int deleteByExample(TesttableQuery example) {
		return super.getSqlSession().delete("TesttableMapper.deleteByExample", example);
	}

	public int deleteByPrimaryKey(String id) {
		return super.getSqlSession().delete("TesttableMapper.deleteByPrimaryKey", id);
	}

	public int insert(Testtable record) {
		return super.getSqlSession().insert("TesttableMapper.insert", record);
	}

	public int insertSelective(Testtable record) {
		return super.getSqlSession().insert("TesttableMapper.insertSelective", record);
	}
	
	public int batchInsert(List<Testtable> list) {
		return super.batchInsert("TesttableMapper.insertSelective", list);
	}

	public List<Testtable> selectByExample(TesttableQuery example) {
		return super.getSqlSession().selectList("TesttableMapper.selectByExample", example);
	}

	public List<Testtable> selectPageByExample(TesttableQuery example) {
		return super.getSqlSession().selectList("TesttableMapper.selectPageByExample", example);
	}

	public Testtable selectByPrimaryKey(String id) {
		return super.getSqlSession().selectOne("TesttableMapper.selectByPrimaryKey", id);
	}

	public int updateByPrimaryKeySelective(Testtable record) {
		return super.getSqlSession().update("TesttableMapper.updateByPrimaryKeySelective", record);
	}

	public int updateByPrimaryKey(Testtable record) {
		return super.getSqlSession().update("TesttableMapper.updateByPrimaryKey", record);
	}

}
