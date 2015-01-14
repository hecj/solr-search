package com.freedom.search.solr.task;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.freedom.search.hibernate.entity.LaTaskController;
import com.freedom.search.senum.EnumUtils;
import com.freedom.search.services.TaskControllerService;
import com.freedom.search.services.TempIndexService;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.StringUtil;

@Service("tempIndexTask")
public class TempIndexTask {

	@Resource
	private TempIndexService tempIndexService;

	@Resource
	private TaskControllerService taskControllerService;
	
	public void setTempIndexService(TempIndexService tempIndexService) {
		this.tempIndexService = tempIndexService;
	}

	public void setTaskControllerService(TaskControllerService taskControllerService) {
		this.taskControllerService = taskControllerService;
	}

	/**
	 * 提交内存中索引任务
	 */
	public void commitTempIndex() {
		
		LaTaskController mTaskControlle = taskControllerService.searchTaskController(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		if(!StringUtil.isObjectEmpty(mTaskControlle) && mTaskControlle.getTaskRunStatus().equals(EnumUtils.TaskRunStatus.ON.name())){
			Log4jUtil.log("start ... ");
			tempIndexService.commitTempIndexSerivice();
			Log4jUtil.log("end ... ");
		}else{
			Log4jUtil.log("定时任务停止 ");
		}
	}
	
	/**
	 * 重构索引
	 */
	public void refactorIndex(){
		
		LaTaskController mTaskControlle = taskControllerService.searchTaskController(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		if(!StringUtil.isObjectEmpty(mTaskControlle) && mTaskControlle.getTaskRunStatus().equals(EnumUtils.TaskRunStatus.ON.name())){
			Log4jUtil.log("start ...");
			tempIndexService.refactorIndexService();
			Log4jUtil.log("end ...");
		}else{
			Log4jUtil.log("定时任务停止 ");
		}
	}
	
	/**
	 * 恢复服务器重启前未提交的索引
	 */
	public void recoverTempIndex(){
		
		Log4jUtil.log("start ...");
		tempIndexService.recoverTempIndexService();
		Log4jUtil.log("end ...");
	}
	
	
}
