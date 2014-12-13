package com.hecj.search.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hecj.search.services.TaskControllerService;
import com.hecj.search.solr.task.TempIndexTask;
import com.hecj.search.util.Log4jUtil;

/**
 * @类功能说明：恢复索引的监听器
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-9 上午09:42:13
 * @版本：V1.0
 */
public class TempIndexRecoverListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent pServletContextEvent) {
		 
	}

	@Override
	public void contextInitialized(ServletContextEvent pServletContextEvent) {
		
		
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(pServletContextEvent.getServletContext());
		final TempIndexTask tempIndexTask = (TempIndexTask) wac.getBean("tempIndexTask");
		final TaskControllerService taskControllerService = (TaskControllerService) wac.getBean("taskControllerService");
		new Thread(){
			public void run() {
				Log4jUtil.log("监听器启动->任务开始...");
				tempIndexTask.recoverTempIndex();
				Log4jUtil.log("监听器启动->任务结束...");
				/*
				 * 插入数据启动其他任务
				 */
				Log4jUtil.log("添加定时任务 start ...");
				taskControllerService.resetTask();
				Log4jUtil.log("添加定时任务 end ...");
			};
		}.start();
	}


}
