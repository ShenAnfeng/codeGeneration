package com.rkylin.wallet.paging;

import java.io.Serializable;

/**
 * 辅助分页查询结果
 */
public class Pagenation implements Serializable {

	private static final long serialVersionUID = 1L;

	public Pagenation(int pageNo, int pageSize, int itemCount) {
		this.pageSize = pageSize;
		this.itemCount = itemCount;
		this.pageCount = (int) Math
				.ceil(((double) itemCount / (double) pageSize));
		this.pageNo = pageNo;
		this.pageNo = this.pageNo>this.pageCount?this.pageCount:this.pageNo;
		this.pageNo = this.pageNo>0?this.pageNo:1;
	}

	/**
	 * @return 每页条目数量
	 */
	private int pageSize;

	/**
	 * @return 当前页码
	 */
	private int pageNo;

	/**
	 * @return 条目总数
	 */
	private int itemCount;

	/**
	 * @return 总页数
	 */
	private int pageCount;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public int getPreviousPage() {
		return this.pageNo - 1 > 0 ? this.pageNo - 1 : this.pageNo;
	}

	public int getNextPage() {
		return this.pageNo + 1 <= this.pageCount ? this.pageNo + 1 : this.pageNo;
	}

}
