package com.freedom.search.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	/*
	 * 用户角色
	 */
	private LzRole role ;

	public LzUser() {

	}

	@Id
	@Column(name = "usercode", length = 32, nullable = false,updatable=false)
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	
	@Column(name="username",length=100)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "password", length = 32, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="telPhone",length=20)
	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	@Column(name="email",length=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="imageHead",length=200)
	public String getImageHead() {
		return imageHead;
	}

	public void setImageHead(String imageHead) {
		this.imageHead = imageHead;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="createDate")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updateDate")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	/**
	 * @OneToOne(mappedBy="user",fetch=FetchType.LAZY) mappedBy 对方拥有的
	 * fetch=FetchType.EAGER 表示急加载
	 * fetch=FetchType.LAZY 表示延迟加载
	 * cascade=CascadeType.REMOVE 表示级联删除
	 * cascade=CascadeType.MERGE 表示级联更新
	 */
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	@JoinColumn(name="rolecode")
	public LzRole getRole() {
		return role;
	}

	public void setRole(LzRole role) {
		this.role = role;
	}

}
