package com.freedom.search.admin.services;

import com.freedom.search.admin.vo.MenuTree;
import com.freedom.search.admin.vo.VoModule;

public interface MenuTreeService {
	
	public MenuTree searchMenuTree(Integer moduleId,String basePath);
	
	public VoModule treeManagerSearch(Integer moduleId);
}
