package com.freedom.search.admin.services.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import test.freedom.spring.aop.LogUtil;

import com.freedom.search.admin.dao.MenuTreeDAO;
import com.freedom.search.admin.entity.Module;
import com.freedom.search.admin.services.MenuTreeService;
import com.freedom.search.admin.vo.VoTree;
import com.freedom.search.util.Log4jUtil;
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
	public VoTree searchMenuTree(VoTree voTree) {
		
		return searchMenuTree(voTree,new HashSet<Module>());
	}
	/* 
	 * 递归遍历菜单,加入递归死循环容错处理.
	 */
	private VoTree searchMenuTree(VoTree voTree,Set<Module> set) {
		String hql = "select m from Module m where m.parentId=?";
		List<Module> modules = (List<Module>) menuTreeDAO.queryListByParams(hql,new Object[]{voTree.getModuleId()});
		if(modules.size() == 0){
			return voTree;
		}else{
			List<VoTree> chiledTree = new ArrayList<VoTree>();
			for(Module m : modules){
				VoTree t = new VoTree();
				t.setIcons(m.getIcons());
				t.setModuleId(m.getModuleId());
				t.setName(m.getName());
				t.setAttributes(m.getAttributes());
				if(!m.isLeaf()){
					if(set.add(m)){
						Log4jUtil.error("出现了递归死循环！Module："+m.getModuleId());
						return voTree;
					}
					chiledTree.add(searchMenuTree(t,set));
				}else{
					chiledTree.add(t);
				}
			}
			voTree.setVoTrees(chiledTree);
			return voTree ; 
		}
	}
	
}
