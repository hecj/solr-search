package com.freedom.search.admin.web.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.entity.LzModule;
import com.freedom.search.admin.senum.EnumAdminUtils;
import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.admin.vo.VoModule;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.StringUtil;
import com.freedom.search.util.http.HtmlUtils;
import com.freedom.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/module/module.htm")
public class ModuleController extends BaseController {
	
	@Resource
	private ModuleService moduleService;

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@RequestMapping(params="operator=searchModule")
	public void searchModule(String moduleId,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			VoModule voTree = moduleService.treeManagerSearch(moduleId);
			if(!StringUtil.isObjectEmpty(voTree)){
				write(response, voTree.toJSON());
				return;
			}
		}
		Log4jUtil.error("加载数失败："+moduleId);
	}
	
	@RequestMapping(params="operator=addFatherNode")
	public String addFatherNode(String moduleId,HttpServletRequest request,HttpServletResponse response){
		
		if(moduleId.equals(EnumAdminUtils.Tree.Root.code)){
			return "admin/jsp/module/modulemanager/addFatherNode";
		}
		
		if(!StringUtil.isStrEmpty(moduleId)){
			LzModule module = moduleService.searchModuleById(moduleId);
			request.setAttribute("module", module);
		}
		return "admin/jsp/module/modulemanager/addFatherNode";
	}
	
	@RequestMapping(params="operator=addChildNode")
	public String addChildNode(String moduleId,HttpServletRequest request,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			LzModule module = moduleService.searchModuleById(moduleId);
			request.setAttribute("module", module);
		}
		return "admin/jsp/module/modulemanager/addChildNode";
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
			LzModule module = new LzModule();
			module.setIcons(icons);
			module.setLeaf(leaf);
			module.setName(name);
			module.setState(state);
			module.setUrl(url);
			module.setParentId(parentId);
			
			if(moduleService.addChildNode(module)){
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
			LzModule module = moduleService.searchModuleById(moduleId);
			request.setAttribute("module", module);
		}
		return "admin/jsp/module/modulemanager/addBrotherNode";
	}
	
	@RequestMapping(params="operator=addBrotherNodeSumbit")
	public void addBrotherNodeSumbit(HttpServletRequest request,HttpServletResponse response){
		
		String parentId = request.getParameter("parentId");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String state = request.getParameter("state");
		String icons = request.getParameter("icons");
		String leaf = request.getParameter("leaf");
		
		LzModule module = new LzModule();
		module.setIcons(icons);
		module.setName(name);
		module.setLeaf(leaf);
		module.setParentId(parentId);
		module.setState(state);
		module.setUrl(url);
		
		if(moduleService.addBrotherNode(module)){
			write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!").toJSON());
			return ;
		}else{
			write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
		}
	}
	
	@RequestMapping(params="operator=deleteNode")
	public void deleteNode(String moduleId,HttpServletRequest request,HttpServletResponse response){
		
		try {
			
			if(moduleId.equals(EnumAdminUtils.Tree.Root.code)){
				write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "根节点不可删除!").toJSON());
				return;
			}
			
			if(!StringUtil.isStrEmpty(moduleId)){
				if(moduleService.deleteNode(moduleId)){
					write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!").toJSON());
					return ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
	}
	
	@RequestMapping(params="operator=testURL")
	public void testURL(String url,HttpServletRequest request,HttpServletResponse response){

		try {
			if(!StringUtil.isStrEmpty(url)){
				if(!url.startsWith("http://")){
					url = getBasePath()+url;
				}
				if(HtmlUtils.testURLConnection(url)){
					write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "验证成功!").toJSON());
					return ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "验证失败!").toJSON());
	}
	
	@RequestMapping(params="operator=editNode")
	public String editNode(String moduleId,HttpServletRequest request,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			LzModule module = moduleService.searchModuleById(moduleId);
			request.setAttribute("module", module);
		}
		return "admin/jsp/module/modulemanager/editNode";
	}
	
	@RequestMapping(params="operator=editNodeSumbit")
	public void editNodeSumbit(HttpServletRequest request,HttpServletResponse response){
		
		String moduleId = request.getParameter("moduleId");
		String parentId = request.getParameter("parentId");
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String state = request.getParameter("state");
		String icons = request.getParameter("icons");
		String leaf = request.getParameter("leaf");
		
		LzModule module = new LzModule();
		module.setModuleId(moduleId);
		module.setIcons(icons);
		module.setName(name);
		module.setLeaf(leaf);
		module.setParentId(parentId);
		module.setState(state);
		module.setUrl(url);
		
		if(moduleService.updateNode(module)){
			write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!").toJSON());
			return ;
		}else{
			write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
		}
	}
}
