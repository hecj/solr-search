package com.hecj.search.services.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hecj.search.hibernate.dao.TaskControllerDAO;
import com.hecj.search.hibernate.entity.TaskController;
import com.hecj.search.hibernate.util.UUIDUtil;
import com.hecj.search.senum.EnumUtils;
import com.hecj.search.senum.EnumUtils.TaskClass;
import com.hecj.search.services.TaskControllerService;
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
	public void addTaskController(TaskController pTaskController) {
		taskControllerDAO.save(pTaskController);
	}

	@Override
	public TaskController searchTaskController(String taskClass,String taskMethod) {
		
		String queryTaskController = "select t from TaskController t where t.taskClass=? and t.taskMethod=?";
		List<TaskController> taskControllers = taskControllerDAO.queryListByParams(queryTaskController, new Object[]{taskClass,taskMethod});
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
		taskControllerDAO.executeHQL("delete from TaskController");
		/*
		 * 添加定时任务
		 */
		for(TaskClass mTaskClass : EnumUtils.TaskClass.values()){
			String taskClass = mTaskClass.name();
			for(String taskMethod : mTaskClass.taskClass){
				TaskController taskController = new TaskController();
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
