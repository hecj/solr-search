package com.freedom.search.webapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="lb_comment")
public class LbComment implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;

	/*
	 * 主键
	 */
	private String id ;
	
	private String content ;
	
	private Date createDate ;
	
	private String essayId ;
	
	private String usercode ;
	
	public LbComment() {
		
	}
	
	@Id
	@Column(name="id",length=32,nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getEssayId() {
		return essayId;
	}

	public void setEssayId(String essayId) {
		this.essayId = essayId;
	}

	public String getContent() {
		return content;
	}
	
	@Column(length=5000,nullable=false)
	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(length=32)
	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	
}
