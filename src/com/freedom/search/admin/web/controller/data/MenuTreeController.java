package com.freedom.search.admin.web.controller.data;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.entity.MenuTree;
import com.freedom.search.admin.services.MenuTreeService;
import com.freedom.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/tree/menuTree.htm")
public class MenuTreeController extends BaseController {
	
	@Resource
	private MenuTreeService menuTreeService;

	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}
	
	@RequestMapping(params="operator=initTree")
	public void initTree(Integer moduleId,HttpServletResponse response){

		MenuTree voTree = menuTreeService.searchMenuTree(moduleId,getBasePath());
		write(response, voTree.toJSON());
	}
	
}
