package com.freedom.search.admin.entity;

import java.util.List;

public class MenuList {

	public MenuTree menuTree;
	public List<MenuTree> menuTrees;

	public MenuTree getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(MenuTree menuTree) {
		this.menuTree = menuTree;
	}

	public List<MenuTree> getMenuTrees() {
		return menuTrees;
	}

	public void setMenuTrees(List<MenuTree> menuTrees) {
		this.menuTrees = menuTrees;
	}

}
