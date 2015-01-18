package com.freedom.search.admin.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import com.freedom.search.util.ObjectToJson;
/**
 * @类功能说明：Tree的链表
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2015-1-6 下午08:08:31
 * @版本：V1.0
 */
public class VoModule implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String moduleId;
	private String name;//模块名称
	private Integer type;//模块类型1是菜单，2是按钮
	private String iconCls;
	private String parentId;//父Id
	private String url;
	private List<VoModule> children;
	private String state;
	private String leaf;
	
	@Id
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<VoModule> getChildren() {
		return children;
	}
	public void setChildren(List<VoModule> children) {
		this.children = children;
	}
	
	public List<VoModule> parentList() {
		List<VoModule> list = new ArrayList<VoModule>();
		list.add(this);
		return list ;
	}
}
