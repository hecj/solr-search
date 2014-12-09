package com.hecj.search.solr.task;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hecj.search.hibernate.entity.TaskController;
import com.hecj.search.senum.EnumUtils;
import com.hecj.search.services.TaskControllerService;
import com.hecj.search.services.TempIndexService;
import com.hecj.search.util.StringUtil;

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
		
		TaskController mTaskControlle = taskControllerService.searchTaskController(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		if(!StringUtil.isObjectEmpty(mTaskControlle) && mTaskControlle.getTaskRunStatus().equals(EnumUtils.TaskRunStatus.ON.name())){
			System.out.println("com.hecj.search.solr.task.TempIndexTask.commitTempIndex() start ... ");
			tempIndexService.commitTempIndexSerivice();
			System.out.println("com.hecj.search.solr.task.TempIndexTask.commitTempIndex() end ... ");
		}else{
			System.out.println("com.hecj.search.solr.task.TempIndexTask.commitTempIndex() 定时任务停止 ");
		}
	}
	
	/**
	 * 重构索引
	 */
	public void refactorIndex(){
		
		TaskController mTaskControlle = taskControllerService.searchTaskController(this.getClass().getSimpleName(),Thread.currentThread().getStackTrace()[1].getMethodName());
		if(!StringUtil.isObjectEmpty(mTaskControlle) && mTaskControlle.getTaskRunStatus().equals(EnumUtils.TaskRunStatus.ON.name())){
			System.out.println("com.hecj.search.solr.task.TempIndexTask.refactorIndex() start ...");
			tempIndexService.refactorIndexService();
			System.out.println("com.hecj.search.solr.task.TempIndexTask.refactorIndex() end ...");
		}else{
			System.out.println("com.hecj.search.solr.task.TempIndexTask.commitTempIndex() 定时任务停止 ");
		}
	}
	
	/**
	 * 恢复服务器重启前未提交的索引
	 */
	public void recoverTempIndex(){
		
		System.out.println("com.hecj.search.solr.task.TempIndexTask.recoverTempIndex() start ...");
		tempIndexService.recoverTempIndexService();
		System.out.println("com.hecj.search.solr.task.TempIndexTask.recoverTempIndex() end ...");
	}
	
	
}
