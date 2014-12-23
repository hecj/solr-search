package com.freedom.search.admin.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * @类功能说明：网络爬虫参数
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-15 下午11:17:38
 * @版本：V1.0
 */
@Entity
@Table(name="tb_dataCollectParams")
public class DataCollectParams implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	
	private String id;
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
	private Set<DataField> dataFields;

	public DataCollectParams() {

	}
	
	@Id
	@Column(name="id",length=32,nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name="ip",length=20)
	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}
	@Column(name="port",length=6)
	public Integer getPORT() {
		return PORT;
	}

	public void setPORT(Integer pORT) {
		PORT = pORT;
	}
	@Column(name="end",length=10)
	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}
	@Column(name="step",length=5)
	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}
	@Column(name="pageParams",length=20)
	public String getPageParams() {
		return pageParams;
	}

	public void setPageParams(String pageParams) {
		this.pageParams = pageParams;
	}
	@Column(name="encode",length=10)
	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}
	@Column(name="start",length=10)
	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}
	@Column(name="baseURL",length=255,nullable=false)
	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	@Column(name="baseSelect",length=255,nullable=false)
	public String getBaseSelect() {
		return baseSelect;
	}

	public void setBaseSelect(String baseSelect) {
		this.baseSelect = baseSelect;
	}
	@Column(name="dataBaseType",length=10,nullable=false)
	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}
	
	@Column(name="tableName",length=30,nullable=false)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "dataCollectParams")
	public Set<DataField> getDataFields() {
		return dataFields;
	}
	
	public void setDataFields(Set<DataField> dataFields) {
		this.dataFields = dataFields;
	}

}