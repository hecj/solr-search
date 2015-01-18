package com.freedom.search.admin.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.admin.vo.Tree;
import com.freedom.search.admin.vo.UserContext;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.StringUtil;
import com.freedom.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/tree/tree.htm")
public class TreeController extends BaseController {
	
	@Resource
	private ModuleService moduleService;
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@RequestMapping(params="operator=initTree")
	public void initTree(String moduleId,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			Tree voTree = moduleService.searchMenuTree(moduleId);
			if(voTree != null){
				writeToJSON(response, voTree.getChildren());
			}
		}else{
			Log4jUtil.log("moduleId is null!");
		}
	}
	
	@RequestMapping(params="operator=init")
	public void init(HttpServletRequest request,HttpServletResponse response){
		
		try {
			//根节点Id
			String rootId = request.getParameter("rootId");
			//展开时枝干节点Id
			String id = request.getParameter("id");
			if(StringUtil.isStrEmpty(id)){
				id = rootId;
			}
			//获取当前登陆的用户名
			UserContext context = (UserContext) request.getSession().getAttribute("context");
			String usercode = context.getUser().getUsercode();
			//查询子节点
			List<Tree> trees = moduleService.searchChildTree(usercode,id);
			writeToJSON(response, trees);
		} catch (Exception e) {
			writeToJSON(response, new Tree());
			e.printStackTrace();
		}
	}
	
}
