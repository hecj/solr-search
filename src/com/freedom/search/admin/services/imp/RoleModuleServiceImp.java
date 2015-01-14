package com.freedom.search.admin.services.imp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.RoleModuleDAO;
import com.freedom.search.admin.services.RoleModuleService;

@Transactional
@Service("roleModuleService")
public class RoleModuleServiceImp implements RoleModuleService {
	
	private RoleModuleDAO roleModuleDAO;

	public void setRoleModuleDAO(RoleModuleDAO roleModuleDAO) {
		this.roleModuleDAO = roleModuleDAO;
	}
	
	
}
