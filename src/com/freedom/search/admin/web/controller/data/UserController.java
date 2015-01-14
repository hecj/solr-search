package com.freedom.search.admin.web.controller.data;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.entity.LzUser;
import com.freedom.search.admin.senum.EnumAdminUtils;
import com.freedom.search.admin.services.UserService;
import com.freedom.search.admin.vo.UserContext;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.StringUtil;
import com.freedom.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/user/user.htm")
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
			String password = request.getParameter("password");
			LzUser user = userService.searchUserByCode(usercode);
			if(!StringUtil.isObjectNull(user)){
				if(password.equals(user.getPassword())){
					//登陆成功
					UserContext context = new UserContext();
					context.setUser(user);
					request.getSession().setAttribute("context", context);
					write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "登陆成功!").toJSON());
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "登陆失败!").toJSON());
	}
}
