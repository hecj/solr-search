package com.freedom.search.admin.web.controller.data;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.entity.Module;
import com.freedom.search.admin.services.MenuTreeService;
import com.freedom.search.admin.vo.MenuTree;
import com.freedom.search.admin.vo.VoModule;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.StringUtil;
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
		if(voTree != null){
			write(response, voTree.toJSON());
		}
	}
	
	@RequestMapping(params="operator=treeManagerQuery")
	public void treeManagerQuery(Integer moduleId,HttpServletResponse response){

		VoModule voTree = menuTreeService.treeManagerSearch(moduleId);
		write(response, voTree.toJSON());
	}
	
	@RequestMapping(params="operator=addFatherNode")
	public String addFatherNode(Integer moduleId,HttpServletRequest request,HttpServletResponse response){
		if(!StringUtil.isObjectEmpty(moduleId)){
			Module module = menuTreeService.searchModuleById(moduleId);
			request.setAttribute("module", module);
		}
		return "admin/jsp/treemanager/treemanager/addFatherNode";
	}
	
	@RequestMapping(params="operator=addFatherNodeSumbit")
	public void addFatherNodeSumbit(HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("===================");
		
	}
	
	@RequestMapping(params="operator=addChildNode")
	public String addChildNode(Integer moduleId,HttpServletRequest request,HttpServletResponse response){
		if(!StringUtil.isObjectEmpty(moduleId)){
			Module module = menuTreeService.searchModuleById(moduleId);
			request.setAttribute("module", module);
		}
		return "admin/jsp/treemanager/treemanager/addChildNode";
	}
	
	@RequestMapping(params="operator=addChildNodeSumbit")
	public void addChildNodeSumbit(HttpServletRequest request,HttpServletResponse response){
		try{
			
			String name = request.getParameter("name");
			String parentId = request.getParameter("parentId");
			String url = request.getParameter("url");
			String state = request.getParameter("state");
			String icons = request.getParameter("icons");
			String leaf = request.getParameter("leaf");
			Module module = new Module();
			module.setIcons(icons);
			module.setLeaf(leaf.equals("true")?true:false);
			module.setName(name);
			module.setState(state);
			module.setAttributes("url:"+url);
			module.setParentId(Integer.parseInt(parentId));
			
			if(menuTreeService.addChildNode(module)){
				write(response, new MessageCode("0", "处理成功!").toJSON());
				return;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		write(response, new MessageCode("1", "处理失败!").toJSON());
	}
	
}
