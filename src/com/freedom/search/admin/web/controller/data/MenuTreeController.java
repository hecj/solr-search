package com.freedom.search.admin.web.controller.data;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.freedom.search.admin.services.MenuTreeService;
import com.freedom.search.web.controller.base.BaseController;

@Controller
public class MenuTreeController extends BaseController {
	
	@Resource
	private MenuTreeService menuTreeService;

	public MenuTreeService getMenuTreeService() {
		return menuTreeService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}
	
	
	
}
