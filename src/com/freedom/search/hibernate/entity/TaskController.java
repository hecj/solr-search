package com.freedom.search.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @类功能说明：任务控制表
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-9 上午10:28:41
 * @版本：V1.0
 */
@Entity
@Table(name="tb_taskcontroller")
public class TaskController implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO
	 */

	private static final long serialVersionUID = 1L;

	private String id;
	private String taskClass;
	private String taskMethod;
	private String taskDesc;
	private String taskRunStatus;

	@Id
	@Column(name="id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name="taskclass")
	public String getTaskClass() {
		return taskClass;
	}

	public void setTaskClass(String taskClass) {
		this.taskClass = taskClass;
	}
	
	@Column(name="taskmethod")
	public String getTaskMethod() {
		return taskMethod;
	}

	public void setTaskMethod(String taskMethod) {
		this.taskMethod = taskMethod;
	}


	@Column(name="taskdesc")
	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	@Column(name="taskrunstatus")
	public String getTaskRunStatus() {
		return taskRunStatus;
	}

	public void setTaskRunStatus(String taskRunStatus) {
		this.taskRunStatus = taskRunStatus;
	}


}
