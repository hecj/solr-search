package com.hecj.search.admin.vo;

import java.util.List;
/**
 * @类功能说明：网络爬虫参数
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-15 下午11:17:38
 * @版本：V1.0
 */
public class DataCollectParams {

	private String IP;
	private Integer PORT;
	private String baseURL;
	private String pageParams;
	private Integer start;
	private Integer end;
	private Integer step;
	private String baseSelect;
	private String encode;
	private String dataBaseType;
	private String tableName;
	private List<DataField> dataFields;

	public DataCollectParams() {

	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Integer getPORT() {
		return PORT;
	}

	public void setPORT(Integer pORT) {
		PORT = pORT;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getPageParams() {
		return pageParams;
	}

	public void setPageParams(String pageParams) {
		this.pageParams = pageParams;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	public String getBaseSelect() {
		return baseSelect;
	}

	public void setBaseSelect(String baseSelect) {
		this.baseSelect = baseSelect;
	}

	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<DataField> getDataFields() {
		return dataFields;
	}

	public void setDataFields(List<DataField> dataFields) {
		this.dataFields = dataFields;
	}

}