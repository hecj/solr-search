package com.freedom.search.admin.web.controller;


import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.admin.services.UserService;
import com.freedom.search.admin.vo.UserContext;
import com.freedom.search.util.EasyGridData;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.MD5;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.StringUtil;
import com.freedom.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/user/user.htm")
public class UserController extends BaseController {
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@RequestMapping(params="operator=login")
	public void login(HttpServletRequest request,HttpServletResponse response){
		try {
			String usercode = request.getParameter("usercode");
			Log4jUtil.log("login:"+usercode);
			String password = request.getParameter("password");
			LzUser user = userService.searchUserByCode(usercode);
			if(!StringUtil.isObjectNull(user)){
				if(MD5.md5crypt(password).equals(user.getPassword())){
					//登陆成功
					UserContext context = new UserContext();
					context.setUser(user);
					context.setBasePath(getBasePath());
					request.getSession().setAttribute("context", context);
					Log4jUtil.log("login success:"+usercode);
					writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "登陆成功!"));
					return;
				}else{
					writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "密码不正确!"));
					return;
				}
			}else{
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "用户名不存在!"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "登陆失败!"));
	}
	
	@RequestMapping(params="operator=searchUser")
	public void searchUser(Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try {
			Pagination mPagination = new Pagination(10);
			if(!StringUtil.isObjectNull(page)){
				mPagination.setCurrPage(page.longValue());
			}
			if(!StringUtil.isObjectNull(page)){
				mPagination.setPageSize(rows);
			}
			Map<String,Object> mMap = new HashMap<String,Object>();
			mMap.put("pagination", mPagination);
			
			String usercode = request.getParameter("usercode");
			if(!StringUtil.isStrEmpty(usercode)){
				mMap.put("usercode", usercode);
			}
			Result result = userService.searchUserByPagination(mMap);
			if(result.isSuccess()){
				writeToJSON(response,new EasyGridData(result.getPagination().getCountSize(),result.getData()));
				return;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		writeToJSON(response,new EasyGridData());
	}
	
	@RequestMapping(params="operator=findUser")
	public String findUser(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String usercode = request.getParameter("usercode");
			if(!StringUtil.isStrEmpty(usercode)){
				LzUser user = userService.searchUserByCode(usercode);
				request.setAttribute("user", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/jsp/user/userManager/userMessage";
	}
	
	@RequestMapping(params="operator=addUserSub")
	public void addUserSub(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String usercode = request.getParameter("usercode");
			String password = request.getParameter("password");
			String username = request.getParameter("username");
			String telPhone = request.getParameter("telPhone");
			String email = request.getParameter("email");
			String rolecode = request.getParameter("rolecode");
			if(StringUtil.isStrEmpty(usercode) || StringUtil.isStrEmpty(password)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "用户名或密码为空!"));
				return;
			}
			//判断用户是否存在
			if(!StringUtil.isObjectNull(userService.searchUserByCode(usercode))){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "用户已存在!"));
				return;
			}
			
			LzUser user = new LzUser();
			user.setUsercode(usercode);
			user.setUsername(username);
			user.setPassword(MD5.md5crypt(password));
			user.setRole(roleService.searchRole(rolecode));
			user.setTelPhone(telPhone);
			user.setEmail(email);
			user.setCreateDate(new Date());
			user.setUpdateDate(new Date());
			
			if(userService.addUser(user)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!"));
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}
	
	@RequestMapping(params="operator=editUser")
	public String editUser(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String usercode = request.getParameter("usercode");
			if(!StringUtil.isStrEmpty(usercode)){
				LzUser user = userService.searchUserByCode(usercode);
				request.setAttribute("user", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/jsp/user/userManager/editUser";
	}
	
	@RequestMapping(params="operator=editUserSub")
	public void editUserSub(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String usercode = request.getParameter("usercode");
			String username = request.getParameter("username");
			String telPhone = request.getParameter("telPhone");
			String email = request.getParameter("email");
			String rolecode = request.getParameter("rolecode");
			if(StringUtil.isStrEmpty(usercode)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "用户名或密码为空!"));
				return;
			}
			
			LzUser user = userService.searchUserByCode(usercode);
			user.setUsername(username);
			user.setRole(roleService.searchRole(rolecode));
			user.setTelPhone(telPhone);
			user.setEmail(email);
			user.setUpdateDate(new Date());
			
			if(userService.addUser(user)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}
	
	@RequestMapping(params="operator=logout")
	public void logout(HttpServletRequest request,HttpServletResponse response){
		
		try {
			/*
			 * 注销用户 
			 */
			HttpSession session = request.getSession();
			UserContext context = (UserContext) session.getAttribute("context");
			Log4jUtil.log("logout user:"+context.getUser().getUsercode());
			Enumeration<?> enumeration = session.getAttributeNames();
			while(enumeration.hasMoreElements()){
				String key = (String) enumeration.nextElement();
				session.removeAttribute(key);
			}
			session.invalidate();
			writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "注销成功!"));
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "注销失败!"));
	}
	
	@RequestMapping(params="operator=deleteUser")
	public void deleteUser(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String usercode = request.getParameter("usercode");
			if(!StringUtil.isStrEmpty(usercode)){
				UserContext context = (UserContext) request.getSession().getAttribute("context");
				if(context.getUser().getUsercode().equals(usercode)){
					writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "当前登陆用户不可删除!"));
					return;
				}
				if(userService.deleteUser(usercode)){
					writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!"));
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}
	
	@RequestMapping(params="operator=editPwd")
	public void editPwd(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String password = request.getParameter("password");
			String newpassword = request.getParameter("newpassword");
			String repassword = request.getParameter("repassword");
			//判断密码
			UserContext context = (UserContext) request.getSession().getAttribute("context");
			LzUser user = userService.searchUserByCode(context.getUser().getUsercode());
			if(!user.getPassword().equals(MD5.md5crypt(password))){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "密码不正确!"));
				return ;
			}
			//判断新密码一致
			if(!newpassword.equals(repassword)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "两次密码输入不一致!"));
				return ;
			}
			
			//判断新密码一致
			if(password.equals(newpassword)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "新旧密码不可相同!"));
				return ;
			}
			
			//修改密码
			user.setPassword(MD5.md5crypt(newpassword));
			userService.editUser(user);
			writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!"));
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}
}
