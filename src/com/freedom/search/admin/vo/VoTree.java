package com.freedom.search.admin.vo;

import java.io.Serializable;
import java.util.List;
/**
 * @类功能说明：Tree的链表
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2015-1-6 下午08:08:31
 * @版本：V1.0
 */
public class VoTree implements Serializable {
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	private Integer moduleId;
	private String name;//模块名称
	private String attributes;//节点链接地址或按钮属性
	private String icons;//图标
	private List<VoTree> chileds;
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getIcons() {
		return icons;
	}
	public void setIcons(String icons) {
		this.icons = icons;
	}
	
	public List<VoTree> getChileds() {
		return chileds;
	}
	public void setChileds(List<VoTree> chileds) {
		this.chileds = chileds;
	}
	@Override
	public String toString() {
		
		
		
		return null ;
	}
}
