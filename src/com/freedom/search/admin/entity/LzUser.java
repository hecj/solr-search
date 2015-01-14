package com.freedom.search.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @类功能说明：后端User表
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-15 下午11:16:42
 * @版本：V1.0
 */
@Entity
@Table(name = "lz_user")
public class LzUser implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */

	private static final long serialVersionUID = 1L;
	/*
	 * 用户名
	 */
	private String usercode;
	/*
	 * 名称
	 */
	private String username;
	/*
	 * 密码
	 */
	private String password;
	/*
	 * 手机号
	 */
	private String telPhone;
	/*
	 * 邮箱
	 */
	private String email;
	/*
	 * 角色
	 */
	private String roleId;
	/*
	 * 头像
	 */
	private String imageHead;
	/*
	 * 创建时间
	 */
	private Date createDate;
	/*
	 * 修改时间
	 */
	private Date updateDate;

	public LzUser() {

	}

	@Id
	@Column(name = "usercode", length = 32, nullable = false)
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getImageHead() {
		return imageHead;
	}

	public void setImageHead(String imageHead) {
		this.imageHead = imageHead;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
