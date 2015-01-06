package com.freedom.search.admin.services.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.MenuTreeDAO;
import com.freedom.search.admin.entity.Module;
import com.freedom.search.admin.services.MenuTreeService;
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
	public List<Module> searchMenuTree() {

		return (List<Module>) menuTreeDAO.queryListByParams("select m from Module m");
		
		
		
		
		
		
	}
	
}
