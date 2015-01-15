package com.freedom.search.admin.services.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.RoleDAO;
import com.freedom.search.admin.dao.RoleModuleDAO;
import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.admin.entity.LzRoleModule;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.util.StringUtil;


@Transactional
@Service("roleService")
public class RoleServiceImp implements RoleService {
	
	@Resource
	private RoleDAO roleDAO;
	@Resource
	private RoleModuleDAO roleModuleDAO;
	

	public void setRoleModuleDAO(RoleModuleDAO roleModuleDAO) {
		this.roleModuleDAO = roleModuleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public boolean addRole(LzRole role, List<LzRoleModule> list) {
		
		try {
			String usercode = (String) roleDAO.save(role);
			if(!StringUtil.isStrEmpty(usercode)){
				for(LzRoleModule rm:list){
					roleModuleDAO.save(rm);
				}
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
}
