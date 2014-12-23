package com.freedom.search.util;

import java.io.UnsupportedEncodingException;

/**
 * @类功能说明：转码工具类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 下午08:49:38
 * @版本：V1.0
 */
public class CodeConvertUtil {
	
	/*
	 * 转码
	 */
	public static String encode(String str) {
		try {
			return java.net.URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 解码
	 */
	public static String decode(String str) {
		try {
			return java.net.URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
