/*
 * Powered By code-generator
 */
package com.manager.pojo;

import org.apache.commons.lang.StringUtils;
import com.rkylin.wallet.query.PagingQuery;

/**
 * TesttableQuery
 * @author code-generator
 */
public class TesttableQuery extends PagingQuery {
	private static final long serialVersionUID = 1L;

	public TesttableQuery(){
		super(1, 10);
	}

	public TesttableQuery(int pageNo, int pageSize){
		super(pageNo, pageSize);
	}

	/**  */
	private java.lang.String name;
	/**  */
	private java.lang.Integer length;

	/** 计算总记录数 */
	public int calcItemCount(Object t) {
		return 0;
	}

	/**  */
	public void setName(java.lang.String name) {
		if (StringUtils.isNotBlank(name)) {
			this.name = name;
		}
	}
	
	/**  */
	public java.lang.String getName() {
		return this.name;
	}
	/**  */
	public void setLength(java.lang.Integer length) {
		this.length = length;
	}
	
	/**  */
	public java.lang.Integer getLength() {
		return this.length;
	}
	

}