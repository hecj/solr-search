package com.freedom.search.admin.entity;

import java.io.Serializable;

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
@Table(name = "lz_module")
public class LzModule implements Serializable {

	private static final long serialVersionUID = 1L;

	private String moduleId;
	private String name;// 模块名称
	private String url;
	private Integer type;// 模块类型1是菜单，2是按钮
	private String parentId;// 父Id
	private String icons;// 图标
	private String state; //状态 打开/关闭
	private String leaf; //是否叶子
	private Integer flag; //有效状态 审核，生效，删除

	@Id
	@Column(name="moduleId",length=32,nullable=false)
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name="name",length=100,nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="url",length=255)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="type",length=1)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name="parentId",length=32)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name="icons",length=100)
	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	@Column(name="leaf",length=1)
	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	@Column(name="state",length=10)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="flag",length=2)
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}
