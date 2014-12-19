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
	/*
	 * 当前页码
	 */
	private long currPage = 1;
	/*
	 * 每页条数
	 */
	private int pageSize = 15;
	/*
	 * 总条数
	 */
	private long countSize = 0;
	/*
	 * 显示分页器
	 */
	private String showPagination;
	/*
	 * 分页的baseURL
	 */
	private String pathURL ;

	public Pagination() {
		super();
	}
	public Pagination(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCurrPage() {
		return currPage;
	}

	public void setCurrPage(long currPage) {
		this.currPage = currPage;
	}

	public long getCountPage() {
		if(this.getCountSize()%this.getPageSize() == 0){
			return this.getCountSize()/this.getPageSize();
		}else{
			return this.getCountSize()/this.getPageSize() + 1;
		}
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
		return (this.currPage - 1) * this.pageSize;
	}
	
	public void setPathURL(String pathURL) {
		this.pathURL = pathURL;
	}
	
	public String getShowPagination(){
		
		StringBuffer showPage = new StringBuffer();
		
		if(this.getCurrPage() == 1 ){
			showPage.append("首页&nbsp;&nbsp;");
		}else{
			showPage.append("<a href='"+this.pathURL+"1'>首页</a>&nbsp;&nbsp;");
		}
		
		if(this.getCurrPage() > 1 && this.getCountPage() > 1 ){
			showPage.append("<a href='"+this.pathURL+""+(this.getCurrPage()-1)+"'>上一页</a>&nbsp;&nbsp;");
		}else{
			showPage.append("上一页&nbsp;&nbsp;");
		}
		
		if(this.getCurrPage() < this.getCountPage() && this.getCountPage() > 1 ){
			showPage.append("<a href='"+this.pathURL+""+(this.getCurrPage()+1)+"'>下一页</a>&nbsp;&nbsp;");
		}else{
			showPage.append("下一页&nbsp;&nbsp;");
		}
		
		if(this.getCurrPage() == this.getCountPage() || this.getCountPage() == 0 ){
			showPage.append("尾页");
		}else{
			showPage.append("<a href='"+this.pathURL+""+this.getCountPage()+"'>尾页</a>&nbsp;&nbsp;");
		}
		showPagination = showPage.toString();
		return showPagination;
	}
}
