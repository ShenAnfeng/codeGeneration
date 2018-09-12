/*
 * Powered By code-generator
 */
package com.manager.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.manager.dao.QDateBaseTableDao;
import com.manager.manager.QDateBaseTableManager;
import com.manager.pojo.GenTable;

@Component("qDateBaseTableManager")
public class QDateBaseTableManager {

	@Autowired
	@Qualifier("qDateBaseTableDao")
	private QDateBaseTableDao qDateBaseTableDao;

	public List<GenTable> queryAllTables(Map<String, Object> map) {
		return this.qDateBaseTableDao.queryAllTables(map);
	}

	public long queryCount(Map<String, Object> map) {
		return this.qDateBaseTableDao.queryCount(map);
	}
}
