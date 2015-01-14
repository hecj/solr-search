package com.freedom.search.services.imp;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.hibernate.dao.TaskControllerDAO;
import com.freedom.search.hibernate.entity.LaTaskController;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.senum.EnumUtils;
import com.freedom.search.senum.EnumUtils.TaskClass;
import com.freedom.search.services.TaskControllerService;
/**
 * @类功能说明：任务业务类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 上午09:46:00
 * @版本：V1.0
 */
@Service("taskControllerService")
@Transactional
public class TaskControllerServiceImp implements TaskControllerService{
	
	@Resource
	private TaskControllerDAO taskControllerDAO;
	
	
	public void setTaskControllerDAO(TaskControllerDAO taskControllerDAO) {
		this.taskControllerDAO = taskControllerDAO;
	}

	@Override
	public void addTaskController(LaTaskController pTaskController) {
		taskControllerDAO.save(pTaskController);
	}

	@Override
	public LaTaskController searchTaskController(String taskClass,String taskMethod) {
		
		String queryTaskController = "select t from LaTaskController t where t.taskClass=? and t.taskMethod=?";
		List<LaTaskController> taskControllers = taskControllerDAO.queryListByParams(queryTaskController, new Object[]{taskClass,taskMethod});
		if(taskControllers.size()>0){
			return taskControllers.get(0);
		}
		return null;
	}

	@Override
	public void resetTask() {
		
		/*
		 * 删除定时任务
		 */
		taskControllerDAO.executeHQL("delete from LaTaskController");
		/*
		 * 添加定时任务
		 */
		for(TaskClass mTaskClass : EnumUtils.TaskClass.values()){
			String taskClass = mTaskClass.name();
			for(String taskMethod : mTaskClass.taskClass){
				LaTaskController taskController = new LaTaskController();
				taskController.setId(UUIDUtil.autoUUID());
				taskController.setTaskClass(taskClass);
				taskController.setTaskMethod(taskMethod);
				taskController.setTaskDesc("定时任务");
				taskController.setTaskRunStatus(EnumUtils.TaskRunStatus.ON.name());
;				taskControllerDAO.save(taskController);
			}
		}
	}
	
	
}
