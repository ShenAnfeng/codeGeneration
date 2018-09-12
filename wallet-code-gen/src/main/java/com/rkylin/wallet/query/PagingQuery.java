package com.rkylin.wallet.query;

/**
 * 分页查询
 */
public abstract class PagingQuery<T> extends Query<T> {

	private static final long serialVersionUID = 1L;

	private int pageNo;
	private int pageSize;

	public PagingQuery() {
		pageNo = 1;
		pageSize = 10;
	}

	/**
	 * 数据记录总数
	 */
	private int itemCount;
	private int startRow;
	private int endRow;

	public PagingQuery(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {

		if (pageNo <= 0)
			pageNo = 1;
		else {
			int pageCount = (int) Math
					.ceil(((double) itemCount / (double) pageSize));
			pageNo = pageNo > pageCount ? pageCount : pageNo;
		}

		this.itemCount = itemCount;
		startRow = this.getStartIndex() < 1 ? 0 : this.getStartIndex() - 1;
		endRow = this.getEndIndex();
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	private int getStartIndex() {
		return (pageNo - 1) * pageSize + 1;
	}

	private int getEndIndex() {
		int end = pageNo * pageSize;
		if (end > itemCount) {
			end = itemCount;
		}
		return end;
	}

	/**
	 * 计算查询结果总记录数
	 * 
	 * @param t
	 * @return
	 */
	public abstract int calcItemCount(T t);
}
