package com.hecj.search.admin.services.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hecj.search.admin.dao.MenuTreeDAO;
import com.hecj.search.admin.entity.MenuTree;
import com.hecj.search.admin.services.MenuTreeService;
@Transactional
@Service("menuTreeService")
public class MenuTreeServiceImp implements MenuTreeService{
	
	@Resource
	private MenuTreeDAO menuTreeDAO;

	public MenuTreeDAO getMenuTreeDAO() {
		return menuTreeDAO;
	}

	public void setMenuTreeDAO(MenuTreeDAO menuTreeDAO) {
		this.menuTreeDAO = menuTreeDAO;
	}

	@Override
	public List<MenuTree> searchMenuTree() {

		return (List<MenuTree>) menuTreeDAO.queryListByParams("select m from MenuTree m");
	}
	
}
