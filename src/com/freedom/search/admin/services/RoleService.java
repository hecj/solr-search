package com.freedom.search.admin.services;

import java.util.List;

import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.admin.entity.LzRoleModule;

public interface RoleService {
	
	public boolean addRole(LzRole role,List<LzRoleModule> list);
}
