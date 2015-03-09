package com.freedom.search.ext.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.entity.LzModule;
import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.ext.vo.ExtTree;
import com.freedom.search.web.controller.base.BaseController;
@Controller("ExtTreeController")
@RequestMapping("ext/tree/tree.htm")
public class TreeController extends BaseController {
	
	@Resource
	private ModuleService moduleService;
	
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	@RequestMapping(params="operator=initTree")
	public void initTree(HttpServletRequest request,HttpServletResponse response){
		
		Enumeration<?> es = request.getParameterNames();
		while(es.hasMoreElements()){
			String s = es.nextElement().toString();
			System.out.println(s+"=="+request.getParameter(s));
		}
		String id = request.getParameter("id");
		List<LzModule>  modules = moduleService.searchChildModules(id);
		List<ExtTree> trees = new ArrayList<ExtTree>();
		for(LzModule m :modules){
			ExtTree t = new ExtTree();
			t.setHref(m.getUrl());
			t.setText(m.getName());
			t.setId(m.getModuleId());
			if(m.getLeaf().equals("0")){
				t.setLeaf(false);
				t.setExpanded(true);
			}else{
				t.setLeaf(true);
			}
			trees.add(t);
		}
		writeToJSON(response, trees);
	}
	
}
