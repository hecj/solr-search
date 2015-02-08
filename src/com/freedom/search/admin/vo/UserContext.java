package com.freedom.search.admin.vo;

import java.io.Serializable;
import java.util.Map;

import com.freedom.search.admin.entity.LzUser;

/**
 * @类功能说明：用户Session作用域存储类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2015-1-14 下午01:56:46
 * @版本：V1.0
 */
public class UserContext implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */

	private static final long serialVersionUID = 1L;
	
	public static final String SESSION_KEY = "context";
	
	/**
	 * 用户对象
	 */
	private LzUser user;
	/**
	 * 网站基本目录
	 */
	private String basePath;
	
	/**
	 * 用户按钮权限
	 */
	private Map<String,VoRadio> radios;

	public LzUser getUser() {
		return user;
	}

	public void setUser(LzUser user) {
		this.user = user;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public Map<String, VoRadio> getRadios() {
		return radios;
	}

	public void setRadios(Map<String, VoRadio> radios) {
		this.radios = radios;
	}

}
