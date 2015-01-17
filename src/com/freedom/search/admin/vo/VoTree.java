package com.freedom.search.admin.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	
	private String id;
	private String text;//模块名称
	private String state; //打开、关闭 open、closed
	private String iconCls;
	private String checked;
	private Map<String,String> attributes;//节点链接地址或按钮属性
	private List<VoTree> children;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Map<String,String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String,String> attributes) {
		this.attributes = attributes;
	}

	public List<VoTree> getChildren() {
		return children;
	}

	public void setChildren(List<VoTree> children) {
		this.children = children;
	}

	public List<VoTree> parentTree() {
		List<VoTree> list = new ArrayList<VoTree>();
		list.add(this);
		return list ;
	}
}
