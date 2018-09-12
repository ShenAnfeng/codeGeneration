package com.rkylin.wallet.result;

import com.rkylin.wallet.paging.Pagenation;

/**
 * 返回集合值分页结果
 */
public class PageListResult<T> extends ListResult<T>{

	private static final long serialVersionUID = 1L;
	
	public static <E> PageListResult<E> newPageListResult() {
		return new PageListResult<E>();
	}
	
	/**
	 * @return 辅助分页查询结果
	 */
	private Pagenation pagenation;

	public Pagenation getPagenation() {
		return pagenation;
	}

	public void setPagenation(Pagenation pagenation) {
		this.pagenation = pagenation;
	}
}
