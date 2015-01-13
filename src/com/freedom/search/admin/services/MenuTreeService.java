package com.freedom.search.admin.services;

import com.freedom.search.admin.entity.Module;
import com.freedom.search.admin.vo.MenuTree;
import com.freedom.search.admin.vo.VoModule;

public interface MenuTreeService {
	
	/**
	 * 根据节点Id，递归查询菜单树
	 */
	public MenuTree searchMenuTree(String moduleId);
	/**
	 * 根据节点Id,递归查询节点树
	 */
	public VoModule treeManagerSearch(String moduleId);
	/**
	 * 根据节点Id查询节点
	 */
	public Module searchModuleById(String id);
	/**
	 * 添加子节点
	 */
	public boolean addChildNode(Module module);
	/**
	 * 添加兄弟节点
	 */
	public boolean addBrotherNode(Module module);
	/**
	 * 递归删除节点，包括子节点
	 */
	public boolean deleteNode(String moduleId);
	
	/**
	 * 编辑节点
	 */
	public boolean updateNode(Module module);
	
}
