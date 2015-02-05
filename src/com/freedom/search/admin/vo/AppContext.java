package com.freedom.search.admin.vo;

import java.io.Serializable;

import com.freedom.search.solr.util.PropertiesUtil;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.StringUtil;

/**
 * @类功能说明：简单实现下全局变量（监听器初始化时注入）
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2015-2-5 下午10:04:02
 * @版本：V1.0
 */
public class AppContext implements Serializable {
	
	private static Object lock = new Object();
	
	private static final long serialVersionUID = 1L;
	
	private static String serverPath ;
	
	private static String parentDir;
	
	private static String imageDir;
	
	private static String imageDirTmp;
	/**
	 * 父文件目录
	 */
	public static String getParentDir() {
		if(StringUtil.isStrEmpty(parentDir)){
			synchronized (lock) {
				parentDir = PropertiesUtil.getProperties().getProperty("parentDir");
				if(StringUtil.isStrEmpty(parentDir)){
					parentDir = getServerPath();
				}
			}
		}
		return parentDir;
	}
	/**
	 * 头像目录(相对于项目)
	 */
	public static String getImageDir() {
		if(StringUtil.isStrEmpty(imageDir)){
			synchronized (lock) {
				imageDir = PropertiesUtil.getProperties().getProperty("imageDir");
			}
		}
		return imageDir;
	}
	/**
	 * 头像临时目录(相对于项目)
	 */
	public static String getImageDirTmp() {
		if(StringUtil.isStrEmpty(imageDirTmp)){
			synchronized (lock) {
				imageDirTmp = PropertiesUtil.getProperties().getProperty("imageDirTmp");
			}
		}
		return imageDirTmp;
	}

	/**
	 * 项目绝对目录
	 */
	public static String getServerPath() {
		return serverPath;
	}

	/**
	 * 只允许注入一次
	 */
	public static void setServerPath(String serverPath) {
		if(StringUtil.isStrEmpty(AppContext.serverPath)){
			synchronized (lock) {
				AppContext.serverPath = serverPath;
				Log4jUtil.log("全局变量serverPath初始化："+AppContext.serverPath);
			}
		}else{
			Log4jUtil.log("全局变量serverPath已被初始化，不可重复注入。"+AppContext.serverPath);
		}
	}

}
