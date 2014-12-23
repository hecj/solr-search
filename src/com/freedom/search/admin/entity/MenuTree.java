package com.freedom.search.admin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @类功能说明：菜单树
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-22 上午09:41:03
 * @版本：V1.0
 */
@Entity
@Table(name = "tb_menutree")
public class MenuTree implements Serializable {

	private static final long serialVersionUID = 1L;

	public String Id;
	public String name;
	public String url;
	public String upId;
	public Date createDate;
	public Date updateDate;

	public MenuTree() {

	}
	
	@Id
	@Column(nullable=false)
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}
	@Column(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable=false)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(nullable=false)
	public String getUpId() {
		return upId;
	}

	public void setUpId(String upId) {
		this.upId = upId;
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
