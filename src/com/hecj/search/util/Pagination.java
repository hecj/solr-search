package com.hecj.search.util;

/**
 * @类功能说明：简单封装分页器
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-5 上午11:34:07
 * @版本：V1.0
 */
public class Pagination {

	public long currPage = 1;
	public long countPage;
	public int pageSize = 15;
	public long countSize;

	public Pagination() {
		super();
	}

	public long getCurrPage() {
		return currPage;
	}

	public void setCurrPage(long currPage) {
		this.currPage = currPage;
	}

	public long getCountPage() {
		return countPage;
	}

	public void setCountPage(long countPage) {
		this.countPage = countPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCountSize() {
		return countSize;
	}

	public void setCountSize(long countSize) {
		this.countSize = countSize;
	}

	public long startCursor() {
		return (currPage - 1) * pageSize;
	}

}
