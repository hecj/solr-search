package com.freedom.search.admin.services;

import java.util.Map;

import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.util.Result;

public interface UserService {
	
	/**
	 * 查询用户
	 */
	public LzUser searchUserByCode(String usercode);
	
	/**
	 * 修改用户
	 */
	public boolean editUser(LzUser user);
	
	/**
	 * 添加用户
	 */
	public boolean addUser(LzUser user);
	
	/**
	 * 删除用户
	 */
	public boolean deleteUser(String usercode);
	
	/**
	 * 分页查询
	 */
	public Result searchUserByPagination(Map<String,Object> pParams);
	
	
}
