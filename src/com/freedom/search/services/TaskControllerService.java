package com.freedom.search.services;

import com.freedom.search.hibernate.entity.TaskController;


/**
 * @类功能说明：文章业务类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 上午09:45:35
 * @版本：V1.0
 */
public interface TaskControllerService {
	
	/**
	 * 添加任务
	 */
	public void addTaskController(TaskController pTaskController);
	
	/**
	 * 查询任务
	 */
	public TaskController searchTaskController(String taskClass,String taskMethod);
	
	/**
	 * 重置定时任务
	 */
	public void resetTask();
}
