package com.freedom.search.admin.services.imp;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.UserDAO;
import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.admin.services.UserService;
import com.freedom.search.admin.vo.AppContext;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.ResultSupport;
import com.freedom.search.util.StringUtil;

@Transactional
@Service("userService")
public class UserServiceImp implements UserService {
	
	@Resource
	private UserDAO userDAO;
	@Resource
	private RoleService roleService;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}



	@Override
	public boolean addUser(LzUser user) {
		try {
			Serializable serializable = userDAO.save(user);
			if(!StringUtil.isObjectNull(serializable)){
				return true;
			}
		} catch (RuntimeException e) {
			Log4jUtil.error("usercode:"+user.getUsercode());
			e.printStackTrace();
			throw e;
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
		} catch (RuntimeException e) {
			Log4jUtil.error("usercode:"+usercode);
			e.printStackTrace();
			throw e;
		}
		return false;
	}

	@Override
	public boolean editUser(LzUser user) {
		try {
			LzUser oldUser = userDAO.findById(user.getUsercode());
			oldUser.setEmail(user.getEmail());
			oldUser.setTelPhone(user.getTelPhone());
			oldUser.setUsername(user.getUsername());
			oldUser.setUpdateDate(new Date());
			//判断更新
			if(!oldUser.getImageHead().equals(user.getImageHead())){
				String fileName = AppContext.getParentDir()+oldUser.getImageHead();
				File file = new File(fileName);
				if(file.isFile()){
					file.delete();
				}
				oldUser.setImageHead(user.getImageHead());
			}
			if(!user.getRole().getRolecode().equals(oldUser.getRole().getRolecode())){
				oldUser.setRole(roleService.searchRole(user.getRole().getRolecode()));
			}
			userDAO.merge(oldUser);
			return true;
		} catch (RuntimeException e) {
			Log4jUtil.error("usercode:"+user.getUsercode());
			e.printStackTrace();
			throw e;
		}
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
		Result result = new ResultSupport();
		try{
			
			String usercode = (String) pParams.get("usercode");
			Pagination pagination = (Pagination) pParams.get("pagination");
			String mQueryHQL = "select u from LzUser u where 1=1";
			String mContHQL = "select count(u) from LzUser u where 1=1";
			
			//动态拼接SQL
			if(!StringUtil.isStrEmpty(usercode)){
				mQueryHQL += " and u.usercode='"+usercode+"'";
				mContHQL += " and u.usercode='"+usercode+"'";
			}
			List<LzUser> userList = userDAO.queryListByParamsAndPagination(mQueryHQL, pagination.startCursor().intValue(), pagination.getPageSize(),new Object[]{});
			long count = Long.parseLong(userDAO.queryUniqueResultByHQL(mContHQL).toString());
			pagination.setCountSize(count);
			
			result.setData(userList);
			result.setPagination(pagination);
			result.setResult(true);
		}catch(Exception ex){
			
			result.setResult(false);
			Log4jUtil.log(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}
	
	
}
