package com.freedom.search.admin.services.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.UserDAO;
import com.freedom.search.admin.services.UserService;

@Transactional
@Service("userService")
public class UserServiceImp implements UserService {
	
	@Resource
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	
}
