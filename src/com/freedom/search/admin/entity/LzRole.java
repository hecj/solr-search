package com.freedom.search.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @类功能说明：后端角色表
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-15 下午11:16:42
 * @版本：V1.0
 */
@Entity
@Table(name="lz_role")
public class LzRole implements Serializable{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	/*
	 * 角色代码
	 */
	private String roleCode;
	/*
	 * 名称
	 */
	private String rolename;
	/*
	 * 创建时间
	 */
	private Date createDate;
	/*
	 * 修改时间
	 */
	private Date udpateDate;
	
	
	public LzRole() {

	}
	
	@Id
	@Column(name="rolecode",length=32,nullable=false)
	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUdpateDate() {
		return udpateDate;
	}

	public void setUdpateDate(Date udpateDate) {
		this.udpateDate = udpateDate;
	}

}