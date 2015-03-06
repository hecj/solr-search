package com.freedom.search.ext.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.web.controller.base.BaseController;
@Controller("ExtTreeController")
@RequestMapping("ext/tree/tree.htm")
public class TreeController extends BaseController {
	
	@RequestMapping(params="operator=initTree")
	public void initTree(String moduleId,HttpServletResponse response){
		
		String message = "{\"success\":true,\"data\":[{\"id\":1,\"text\":\"我的办公桌\",\"leaf\":false,\"children\":[{\"id\":3,\"text\":\"二级(1)\",\"leaf\":true},{\"id\":4,\"text\":\"二级(2)\",\"leaf\":true},{\"id\":5,\"text\":\"二级(3)\",\"leaf\":true},{\"id\":6,\"text\":\"二级(4)\",\"leaf\":false,\"children\":[{\"id\":7,\"text\":\"三级(1)\",\"leaf\":true},{\"id\":8,\"text\":\"三级(2)\",\"leaf\":true}]}]},{\"id\":2,\"text\":\"系统管理\",\"leaf\":false,\"children\":[{\"id\":9,\"text\":\"用户管理\",\"leaf\":true}]}]}";
		
		write(response, message);
	}
	
}
