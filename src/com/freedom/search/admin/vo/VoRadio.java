package com.freedom.search.admin.vo;

import java.io.Serializable;
/**
 * @类功能说明：按钮权限业务类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2015-2-8 上午12:25:39
 * @版本：V1.0
 */
public class VoRadio implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 按钮代码
	 */
	private String radiocode;
	/**
	 * 按钮名称
	 */
	private String radioname;
	/**
	 * 按钮URL
	 */
	private String url;
	/**
	 * 按钮图标
	 */
	private String icon;
	/**
	 * 是否选择
	 */
	private boolean check;
	
	public VoRadio() {
		super();
	}
	public String getRadiocode() {
		return radiocode;
	}
	public void setRadiocode(String radiocode) {
		this.radiocode = radiocode;
	}
	public String getRadioname() {
		return radioname;
	}
	public void setRadioname(String radioname) {
		this.radioname = radioname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public boolean getCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
}
