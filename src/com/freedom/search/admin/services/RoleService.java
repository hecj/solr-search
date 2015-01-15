package com.freedom.search.admin.services;

import java.util.Map;

import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.util.Result;

public interface RoleService {
	
	/**
	 * 添加角色
	 */
	public boolean addRole(LzRole role,String[] moduleIds);
	
	/**
	 * 分页查询角色
	 */
	public Result searchRoleByPagination(Map<String, Object> map);
	
	/**
	 * 删除角色
	 */
	public boolean deleteRole(String roleCode);
}
