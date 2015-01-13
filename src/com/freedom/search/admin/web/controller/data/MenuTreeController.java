package com.freedom.search.admin.web.controller.data;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.entity.Module;
import com.freedom.search.admin.senum.EnumAdminUtils;
import com.freedom.search.admin.services.MenuTreeService;
import com.freedom.search.admin.vo.MenuTree;
import com.freedom.search.admin.vo.VoModule;
import com.freedom.search.util.Log4jUtil;
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
	public void initTree(String moduleId,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			MenuTree voTree = menuTreeService.searchMenuTree(moduleId,getBasePath());
			if(voTree != null){
				write(response, voTree.toJSON());
			}
		}else{
			Log4jUtil.log("moduleId is null!");
		}
	}
	
	@RequestMapping(params="operator=treeManagerQuery")
	public void treeManagerQuery(String moduleId,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			VoModule voTree = menuTreeService.treeManagerSearch(moduleId);
			System.out.println(voTree.toJSON());
			write(response, voTree.toJSON());
		}else{
			Log4jUtil.log("moduleId is null!");
		}
	}
	
	@RequestMapping(params="operator=addFatherNode")
	public String addFatherNode(String moduleId,HttpServletRequest request,HttpServletResponse response){
		
		if(moduleId.equals(EnumAdminUtils.Tree.ROOT.code)){
			return "admin/jsp/treemanager/treemanager/addFatherNode";
		}
		
		if(!StringUtil.isStrEmpty(moduleId)){
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
	public String addChildNode(String moduleId,HttpServletRequest request,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
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
			module.setLeaf(leaf);
			module.setName(name);
			module.setState(state);
			if(!StringUtil.isStrEmpty(url)){
				module.setAttributes("url:"+url);
			}
			module.setParentId(parentId);
			
			if(menuTreeService.addChildNode(module)){
				write(response, new MessageCode("0", "处理成功!").toJSON());
				return;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		write(response, new MessageCode("1", "处理失败!").toJSON());
	}
	
	@RequestMapping(params="operator=addBrotherNode")
	public String addBrotherNode(String moduleId,HttpServletRequest request,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			Module module = menuTreeService.searchModuleById(moduleId);
			request.setAttribute("module", module);
		}
		return "admin/jsp/treemanager/treemanager/addBrotherNode";
	}
	
	@RequestMapping(params="operator=addBrotherNodeSumbit")
	public void addBrotherNodeSumbit(HttpServletRequest request,HttpServletResponse response){
		
		String parentId = request.getParameter("parentId");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String state = request.getParameter("state");
		String icons = request.getParameter("icons");
		String leaf = request.getParameter("leaf");
		
		Module module = new Module();
		module.setIcons(icons);
		module.setName(name);
		module.setLeaf(leaf);
		module.setParentId(parentId);
		module.setState(state);
		module.setAttributes("url:"+url);
		
		if(menuTreeService.addBrotherNode(module)){
			write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!").toJSON());
			return ;
		}else{
			write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
		}
	}
	
	@RequestMapping(params="operator=deleteNode")
	public void deleteNode(String moduleId,HttpServletRequest request,HttpServletResponse response){
		
		try {
			
			if(moduleId.equals(EnumAdminUtils.Tree.ROOT.code)){
				write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "根节点不可删除!").toJSON());
				return;
			}
			
			if(!StringUtil.isStrEmpty(moduleId)){
				if(menuTreeService.deleteNode(moduleId)){
					write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!").toJSON());
					return ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
	}
	
	
	
}
