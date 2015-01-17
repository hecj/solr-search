package com.freedom.search.admin.services;

import java.util.List;
import java.util.Map;

import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.admin.vo.VoTree;
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
	 * 查询所有角色
	 */
	public List<LzRole> searchRoleList();
	
	/**
	 * 删除角色
	 */
	public boolean deleteRole(String rolecode);
	
	/**
	 * 查询角色
	 */
	public LzRole searchRole(String rolecode);
	
	/**
	 * 根据角色代码查询子节点权限
	 */
	public List<VoTree> searchTreeByRolecode(String rolecode,String id);
	
	/**
	 * 根据角色代码查询子节点权限
	 * 有权限的模块打勾
	 */
	public List<VoTree> searchEdutTreeByRolecode(String rolecode,String id);

	/**
	 * 修改角色
	 */
	public boolean editRole(LzRole role, String[] moduleIds);
	
}
