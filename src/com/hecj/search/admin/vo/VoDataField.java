package com.hecj.search.admin.vo;

import java.io.Serializable;

/**
 * @类功能说明：数据字段Vo类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-15 下午11:16:42
 * @版本：V1.0
 */
public class VoDataField implements Serializable{
	
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

	public VoDataField() {

	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getSelectMethod() {
		return selectMethod;
	}

	public void setSelectMethod(String selectMethod) {
		this.selectMethod = selectMethod;
	}
	public String getTargetAttr() {
		return targetAttr;
	}

	public void setTargetAttr(String targetAttr) {
		this.targetAttr = targetAttr;
	}
	public String getFieldSelect() {
		return fieldSelect;
	}

	public void setFieldSelect(String fieldSelect) {
		this.fieldSelect = fieldSelect;
	}
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getOldPlace() {
		return oldPlace;
	}

	public void setOldPlace(String oldPlace) {
		this.oldPlace = oldPlace;
	}
	public String getNewPlace() {
		return newPlace;
	}

	public void setNewPlace(String newPlace) {
		this.newPlace = newPlace;
	}
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public Integer getFieldLenth() {
		return fieldLenth;
	}

	public void setFieldLenth(Integer fieldLenth) {
		this.fieldLenth = fieldLenth;
	}
	

}
