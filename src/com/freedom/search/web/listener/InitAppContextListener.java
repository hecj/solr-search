package com.freedom.search.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.freedom.search.admin.vo.AppContext;

/**
 * @类功能说明：初始化全局变量
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-9 上午09:42:13
 * @版本：V1.0
 */
public class InitAppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent pServletContextEvent) {
		 
	}

	@Override
	public void contextInitialized(ServletContextEvent pServletContextEvent) {
		//项目绝对目录(相对于磁盘)
		String serverPath = pServletContextEvent.getServletContext().getRealPath("/").replace("\\", "/");  
		AppContext.setServerPath(serverPath);
	}


}
