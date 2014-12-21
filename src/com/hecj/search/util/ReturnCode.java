package com.hecj.search.util;
/**
 * @类功能说明：Ajax返回信息
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-21 上午09:09:07
 * @版本：V1.0
 */
public class ReturnCode {

	String code;
	String message;

	public ReturnCode() {
		super();
	}

	public ReturnCode(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
