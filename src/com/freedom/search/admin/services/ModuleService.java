package com.freedom.search.admin.services;

import java.util.List;

import com.freedom.search.admin.entity.LzModule;
import com.freedom.search.admin.vo.VoModule;
import com.freedom.search.admin.vo.Tree;

public interface ModuleService {
	
	/**
	 * 根据节点Id，递归查询菜单树
	 */
	public Tree searchMenuTree(String moduleId);
	/**
	 * 根据节点Id,递归查询节点树
	 */
	public VoModule treeManagerSearch(String moduleId);
	/**
	 * 根据节点Id查询节点
	 */
	public LzModule searchModuleById(String id);
	/**
	 * 添加子节点
	 */
	public boolean addChildNode(LzModule module);
	/**
	 * 添加兄弟节点
	 */
	public boolean addBrotherNode(LzModule module);
	/**
	 * 递归删除节点，包括子节点
	 * @throws Exception 
	 */
	public boolean deleteNode(String moduleId) ;
	
	/**
	 * 编辑节点
	 */
	public boolean updateNode(LzModule module);
	
	/**
	 * 根据父模块Id查询子模块集合
	 */
	public List<LzModule> searchChildModules(String id);
	
	/**
	 * 查询子节点Tree
	 */
	public List<Tree> searchChildTree(String id);
}	
