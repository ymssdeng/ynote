package com.ymss.ynote.storage;

public class Paging {
	public static final int DEFAULT_PAGE_SIZE = 10;
	private int pageSize = DEFAULT_PAGE_SIZE;
	private int page = 1;
	private boolean isAscending;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isAscending() {
		return isAscending;
	}

	public void setAscending(boolean isAscending) {
		this.isAscending = isAscending;
	}
}
