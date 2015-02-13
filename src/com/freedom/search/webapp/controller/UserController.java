package com.freedom.search.webapp.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.admin.services.UserService;
import com.freedom.search.admin.vo.UserContext;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.MD5;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.StringUtil;
import com.freedom.search.web.controller.base.BaseController;

@Controller("webAppUserController")
@RequestMapping("webapp/user/user.htm")
public class UserController extends BaseController {
	
	@Resource
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(params="operator=login")
	public void login(HttpServletRequest request,HttpServletResponse response){
		try {
			String usercode = request.getParameter("usercode");
			Log4jUtil.log("login:"+usercode);
			String password = request.getParameter("password");
			UserContext context = userService.webappLogin(usercode, password);
			context.setBasePath(getBasePath());
			request.getSession().setAttribute("context", context);
			writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, context.getUser()));
		} catch (Exception e) {
			writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, e.getMessage()==null?"登陆失败！":e.getMessage()));
			e.printStackTrace();
		}
	}
	
	/**
	 * 注册
	 */
	@RequestMapping(params="operator=reg")
	public void reg(HttpServletRequest request,HttpServletResponse response){
		
		try {
			String usercode = request.getParameter("usercode");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			
			//判断用户是否存在
			if(!StringUtil.isObjectNull(userService.searchUserByCode(usercode))){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "用户已存在!"));
				return;
			}
			
			if(StringUtil.isStrEmpty(usercode) || StringUtil.isStrEmpty(password)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "用户名或密码为空!"));
				return;
			}
			
			if(!repassword.equals(password)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "确认密码不一致!"));
				return;
			}
			
			LzUser user = new LzUser();
			user.setUsercode(usercode);
			user.setUsername(username);
			user.setPassword(MD5.md5crypt(password));
			user.setImageHead("");
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
}
