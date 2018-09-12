/*
 * Powered By code-generator
 */
package com.rkylin.wallet.web;

import java.io.Serializable;

public class ReturnObject implements Serializable {
	private static final long serialVersionUID = 1L;

	private String retCode;
	private String retMsg;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public ReturnObject(String retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

}