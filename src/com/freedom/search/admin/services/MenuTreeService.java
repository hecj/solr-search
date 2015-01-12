package com.freedom.search.admin.services;

import com.freedom.search.admin.entity.Module;
import com.freedom.search.admin.vo.MenuTree;
import com.freedom.search.admin.vo.VoModule;

public interface MenuTreeService {
	
	public MenuTree searchMenuTree(Integer moduleId,String basePath);
	
	public VoModule treeManagerSearch(Integer moduleId);
	
	public Module searchModuleById(Integer id);
	
	/*
	 * 添加子节点
	 */
	public boolean addChildNode(Module module);
}
