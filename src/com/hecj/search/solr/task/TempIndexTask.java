package com.hecj.search.solr.task;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hecj.search.services.TempIndexService;

@Service("tempIndexTask")
public class TempIndexTask {

	@Resource
	private TempIndexService tempIndexService;

	public void setTempIndexService(TempIndexService tempIndexService) {
		this.tempIndexService = tempIndexService;
	}

	/**
	 * 提交内存中索引任务
	 */
	public void commitTempIndex() {

		System.out.println("com.hecj.search.solr.task.TempIndexTask.commitTempIndex() start ... ");
		tempIndexService.commitTempIndexSerivice();
		System.out.println("com.hecj.search.solr.task.TempIndexTask.commitTempIndex() end ... ");
	}
	
	/**
	 * 重构索引
	 */
	public void refactorIndex(){
		System.out.println("com.hecj.search.solr.task.TempIndexTask.refactorIndex() start ...");
		tempIndexService.refactorIndexService();
		System.out.println("com.hecj.search.solr.task.TempIndexTask.refactorIndex() end ...");
	}
}
