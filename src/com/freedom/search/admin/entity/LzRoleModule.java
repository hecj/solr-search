package com.freedom.search.admin.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @类功能说明：后端角色模块表
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-15 下午11:16:42
 * @版本：V1.0
 */
@Entity
@Table(name="lz_role_module")
public class LzRoleModule implements Serializable{
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	/*
	 * 角色代码
	 */
	private String roleCode;
	/*
	 * 模块Id
	 */
	private String moduleId;
	
	public LzRoleModule() {

	}
	@Id
	@Column(name="id",length=32,nullable=false)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

}
