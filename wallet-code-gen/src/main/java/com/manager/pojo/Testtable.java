/*
 * Powered By code-generator
 */
package com.manager.pojo;

import java.io.Serializable;

/**
 * Testtable
 * @author code-generator
 */
public class Testtable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private String id;
	/**  */
	private java.lang.String name;
	/**  */
	private java.lang.Integer length;
	/**  */
	private java.util.Date createTime;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	/**  */
	public void setName(java.lang.String name) {
		this.name = name;
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

	/**  */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**  */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
}