package com.freedom.search.admin.services.imp;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.UserDAO;
import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.admin.services.UserService;
import com.freedom.search.admin.vo.AppContext;
import com.freedom.search.admin.vo.UserContext;
import com.freedom.search.admin.vo.VoRadio;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.MD5;
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
	@Resource
	private ModuleService moduleService;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@Override
	public boolean addUser(LzUser user) {
		try {
			//将临时图片移动到图片目录
			String tmpFileName = AppContext.getParentDir()+user.getImageHead();
			File tmpFile = new File(tmpFileName);
			if(tmpFile.isFile()){
				File nFile = new File(AppContext.getParentDir()+user.getImageHead().replace(AppContext.getImageDirTmp(), AppContext.getImageDir()));
				tmpFile.renameTo(nFile);
			}
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
			if(oldUser.getImageHead() == null || !oldUser.getImageHead().equals(user.getImageHead())){
				//将临时图片移动到图片目录
				String tmpFileName = AppContext.getParentDir()+user.getImageHead();
				File tmpFile = new File(tmpFileName);
				String newImageHead =user.getImageHead().replace(AppContext.getImageDirTmp(), AppContext.getImageDir());
				if(tmpFile.isFile()){
					File nFile = new File(AppContext.getParentDir()+newImageHead);
					tmpFile.renameTo(nFile);
				}
				
				//删除旧图片
				String oldFileName = AppContext.getParentDir()+oldUser.getImageHead();
				File oFile = new File(oldFileName);
				if(oFile.isFile()){
					oFile.delete();
				}
				oldUser.setImageHead(newImageHead);
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

	@Override
	public UserContext login(String usercode, String pwd) {
		
		UserContext context = new UserContext();
		LzUser user = searchUserByCode(usercode);
		if(!StringUtil.isObjectNull(user)){
			if(MD5.md5crypt(pwd).equals(user.getPassword())){
				context.setUser(user);
				List<VoRadio> radios = moduleService.searchExistPermissionRadios(user.getRole().getRolecode());
				Map<String,VoRadio> map = new HashMap<String,VoRadio>(); 
				for(VoRadio radio:radios){
					map.put(radio.getRadiocode(), radio);
				}
				context.setRadios(map);
			}else{
				throw new RuntimeException("用户登陆密码错误!");
			}
		}else{
			throw new RuntimeException("用户不存在!");
		}
		
		return context;
	}
	
	
}
