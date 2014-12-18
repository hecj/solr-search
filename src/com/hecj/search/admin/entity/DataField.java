package com.hecj.search.admin.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @类功能说明：数据字段
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-15 下午11:16:42
 * @版本：V1.0
 */
@Entity
@Table(name="tb_dataField")
public class DataField implements Serializable{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String fieldSelect;
	private String selectMethod;
	private String targetAttr;
	private String pattern;
	private String oldPlace;
	private String newPlace;
	private String fieldName;
	private String fieldType;
	private Integer fieldLenth;
	private DataCollectParams dataCollectParams;

	public DataField() {

	}
	@Id
	@Column(name="id",length=32,nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Column(name="selectMethod",length=32,nullable=false)
	public String getSelectMethod() {
		return selectMethod;
	}

	public void setSelectMethod(String selectMethod) {
		this.selectMethod = selectMethod;
	}
	@Column(name="targetAttr",length=32)
	public String getTargetAttr() {
		return targetAttr;
	}

	public void setTargetAttr(String targetAttr) {
		this.targetAttr = targetAttr;
	}
	@Column(name="fieldSelect",length=32,nullable=false)
	public String getFieldSelect() {
		return fieldSelect;
	}

	public void setFieldSelect(String fieldSelect) {
		this.fieldSelect = fieldSelect;
	}
	@Column(name="pattern",length=100)
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	@Column(name="oldPlace",length=100)
	public String getOldPlace() {
		return oldPlace;
	}

	public void setOldPlace(String oldPlace) {
		this.oldPlace = oldPlace;
	}
	@Column(name="newPlace",length=100)
	public String getNewPlace() {
		return newPlace;
	}

	public void setNewPlace(String newPlace) {
		this.newPlace = newPlace;
	}
	@Column(name="fieldName",length=50,nullable=false)
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	@Column(name="fieldType",length=10,nullable=false)
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	@Column(name="fieldLenth",length=5,nullable=false)
	public Integer getFieldLenth() {
		return fieldLenth;
	}

	public void setFieldLenth(Integer fieldLenth) {
		this.fieldLenth = fieldLenth;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade={CascadeType.DETACH})
	@JoinColumn(name = "dataCollectParamsId", nullable=false)
	public DataCollectParams getDataCollectParams() {
		return dataCollectParams;
	}

	public void setDataCollectParams(DataCollectParams dataCollectParams) {
		this.dataCollectParams = dataCollectParams;
	}

}
