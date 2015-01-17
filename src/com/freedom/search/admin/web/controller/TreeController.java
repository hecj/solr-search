package com.freedom.search.admin.web.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.admin.vo.VoTree;
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
			VoTree voTree = moduleService.searchMenuTree(moduleId);
			if(voTree != null){
				writeToJSON(response, voTree.getChildren());
			}
		}else{
			Log4jUtil.log("moduleId is null!");
		}
	}
	
}
