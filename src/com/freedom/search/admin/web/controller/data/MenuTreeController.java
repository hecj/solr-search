package com.freedom.search.admin.web.controller.data;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.entity.Module;
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
	
	@RequestMapping(params="operator=init")
	public String initTree(String type){
		
		System.out.println(type);
		
		List<Module> list = menuTreeService.searchMenuTree();
		System.out.println(list.size());
		
		return null;
	}
	
}
