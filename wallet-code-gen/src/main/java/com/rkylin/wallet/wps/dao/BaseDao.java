package com.rkylin.wallet.wps.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Service;
/**
 * 数据库操作基类，使用时需要注入SqlSessionFactory
 */
public class BaseDao extends SqlSessionDaoSupport {
	public static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(BaseDao.class);

	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	private static int BATCH_DEAL_NUM = 200;

	protected static SqlSessionFactory batchSqlSessionFactory;

	/**
	 * 批量插入记录
	 * 默认500条，也可以通过修改BATCH_DEAL_NUM字段改变记录数量
	 * @param statement
	 * @param list
	 * @return
	 */
	public int batchInsert(String statement, List<?> list) {
		SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		try {
			for (int cnt = list.size(); i < cnt; i++) {
				try {
					batchSession.insert(statement, list.get(i));
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
					return cnt;
				}

				if ((i + 1) % BATCH_DEAL_NUM == 0) { //BATCH_DEAL_NUM为批量提交的条数
					batchSession.commit();
				}
			}
			batchSession.commit();
			batchSession.clearCache();
		} catch (Throwable ex) {
			LOG.error(ex.getMessage(), ex);
		} finally {
			batchSession.close();
		}
		return i;
	}

	/**
	 * 批量更新记录
	 * 默认500条，也可以通过修改BATCH_DEAL_NUM字段改变记录数量
	 * @param statement
	 * @param list
	 * @return
	 */
	public int batchUpdate(String statement, List<?> list) {
		SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		try {
			for (int cnt = list.size(); i < cnt; i++) {
				batchSession.update(statement, list.get(i));
				if ((i + 1) % BATCH_DEAL_NUM == 0) {
					batchSession.commit();
				}
			}
			batchSession.commit();
		} catch (Throwable ex) {
			LOG.error(ex.getMessage(), ex);
		} finally {
			batchSession.close();
		}
		return i;
	}

	/**
	 * 批量删除记录
	 * 默认500条，也可以通过修改BATCH_DEAL_NUM字段改变记录数量
	 * @param statement
	 * @param list
	 * @return
	 */
	public int batchDelete(String statement, List<?> list) {
		SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		try {
			for (int cnt = list.size(); i < cnt; i++) {
				batchSession.delete(statement, list.get(i));
				if ((i + 1) % BATCH_DEAL_NUM == 0) {
					batchSession.commit();
				}
			}
			batchSession.commit();
		} catch (Throwable ex) {
			LOG.error(ex.getMessage(), ex);
		} finally {
			batchSession.close();
		}
		return i;
	}

	@Resource
	public void setBatchSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		batchSqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * 手动提交事务
	 */
	public void commitTransaction(){
    	SqlSession batchSession = batchSqlSessionFactory.openSession(true);
    	try{
    		batchSession.flushStatements();
    		batchSession.commit();
    	}catch(Exception e){
    		e.printStackTrace();
    		logger.error("----commit-----the error is:=" + e.getMessage(), e);
    	}finally{
    		if(batchSession!=null){
    			batchSession.close();
    		}
    	}
    }
}