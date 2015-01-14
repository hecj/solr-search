package com.freedom.search.admin.services.imp;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.UserDAO;
import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.admin.services.UserService;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.Result;
import com.freedom.search.util.StringUtil;

@Transactional
@Service("userService")
public class UserServiceImp implements UserService {
	
	@Resource
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public boolean addUser(LzUser user) {
		try {
			LzUser u = (LzUser) userDAO.save(user);
			if(!StringUtil.isObjectNull(u)){
				return true;
			}
		} catch (Exception e) {
			Log4jUtil.error("usercode:"+user.getUsercode());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(String usercode) {
		try {
			LzUser user = userDAO.findById(usercode);
			if(!StringUtil.isObjectNull(user)){
				return userDAO.delete(user);
			}
		} catch (Exception e) {
			Log4jUtil.error("usercode:"+usercode);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean editUser(LzUser user) {
		try {
			userDAO.merge(user);
			return true;
		} catch (Exception e) {
			Log4jUtil.error("usercode:"+user.getUsercode());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public LzUser searchUserByCode(String usercode) {
		try {
			return userDAO.findById(usercode);
		} catch (Exception e) {
			Log4jUtil.error("usercode:"+usercode);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Result searchUserByPagination(Map<String, Object> pParams) {

		
		return null;
	}
	
	
}
