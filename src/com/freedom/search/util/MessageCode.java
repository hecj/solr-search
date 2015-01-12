package com.freedom.search.util;
/**
 * @类功能说明：Ajax返回信息
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-21 上午09:09:07
 * @版本：V1.0
 */
public class MessageCode {

	String code;
	String message;

	public MessageCode() {
		super();
	}

	public MessageCode(String code, String message) {
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
	
	public String toJSON(){
		return ObjectToJson.object2json(this);
	}

}
