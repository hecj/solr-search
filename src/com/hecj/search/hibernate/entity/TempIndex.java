package com.hecj.search.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @类功能说明：临时索引表，该表中的数据代表内存中未提交的索引集合。 当服务器异常中断后,待重启后查询改表中数据。将上次内存中未提交的数据建立索引。
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 上午11:09:24
 * @版本：V1.0
 */
@Entity
@Table(name = "tb_tempindex")
public class TempIndex {

	/*
	 * 自动生成的Id
	 */
	private String id;
	/*
	 * 对象Id
	 */
	private String objectId;
	/*
	 * 对象类型
	 */
	private String objectType;

	/*
	 * 操作方式
	 */
	private String operatorType;

	public TempIndex() {
		super();
	}

	public TempIndex(String id, String objectId, String objectType,
			String operatorType) {
		super();
		this.id = id;
		this.objectId = objectId;
		this.objectType = objectType;
		this.operatorType = operatorType;
	}

	@Id
	@Column(name = "id", length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "objectid", length = 30)
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	@Column(name = "objecttype", length = 30)
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Column(name = "operatortype", length = 1)
	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

}
