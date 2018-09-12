/*
 * Powered By code-generator
 */
package com.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.manager.dao.QDateBaseTableDao;
import com.manager.pojo.GenTable;
import com.rkylin.wallet.wps.dao.BaseDao;

@Repository("qDateBaseTableDao")
public class QDateBaseTableDao extends BaseDao {

	public List<GenTable> queryAllTables(Map<String, Object> map) {
		return super.getSqlSession().selectList("QDateBaseTableMapper.queryAllTables", map);
	}
	
	public long queryCount(Map<String, Object> map) {
		return super.getSqlSession().selectOne("QDateBaseTableMapper.queryCount", map);
	}
}
