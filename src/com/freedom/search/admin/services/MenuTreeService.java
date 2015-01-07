package com.freedom.search.admin.services;

import com.freedom.search.admin.entity.MenuTree;

public interface MenuTreeService {
	
	public MenuTree searchMenuTree(Integer moduleId,String basePath);
}
