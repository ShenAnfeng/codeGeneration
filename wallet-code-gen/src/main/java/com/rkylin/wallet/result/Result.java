package com.rkylin.wallet.result;

import java.io.Serializable;

public abstract class Result implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String SUCCESS = "000000";
	
	/**
	 * @return 返回码
	 */
	private String code;
	
	/**
	 * @return 返回码描述
	 */
	private String desc;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		if(desc == null)
			return "";
		
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public abstract boolean isError();
}
