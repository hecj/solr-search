package com.freedom.search.admin.services.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.RoleDAO;
import com.freedom.search.admin.services.RoleService;

@Transactional
@Service("roleService")
public class RoleServiceImp implements RoleService {
	
	@Resource
	private RoleDAO roleDAO;

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
	
}
