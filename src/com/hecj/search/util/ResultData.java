package com.hecj.search.util;

import java.util.List;
/**
 * @类功能说明：简单封装分页结果数据集
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-18 下午10:48:41
 * @版本：V1.0
 */
public class ResultData {
	/*
	 * 分页数据集
	 */
	private List<?> data;
	/*
	 * 分页器
	 */
	private Pagination pagination;
	/*
	 * 成功/失败
	 */
	private Boolean success = false;

	public ResultData() {

	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> dataCollectParamsList) {
		this.data = dataCollectParamsList;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Boolean getSuccess() {
		return success;
	}
	
	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

}
