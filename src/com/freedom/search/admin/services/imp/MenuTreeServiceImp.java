package com.freedom.search.admin.services.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.ModuleDAO;
import com.freedom.search.admin.entity.LzModule;
import com.freedom.search.admin.senum.EnumAdminUtils;
import com.freedom.search.admin.services.MenuTreeService;
import com.freedom.search.admin.vo.MenuTree;
import com.freedom.search.admin.vo.VoModule;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.StringUtil;
@Transactional
@Service("menuTreeService")
public class MenuTreeServiceImp implements MenuTreeService{
	
	@Resource
	private ModuleDAO moduleDAO;

	public ModuleDAO getModuleDAO() {
		return moduleDAO;
	}
	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}
	@Override
	public MenuTree searchMenuTree(String moduleId) {
		
		List<LzModule> list = moduleDAO.queryListByParams("select m from LzModule m where m.id=?", new Object[]{moduleId});
		if(list.size() == 0){
			return null;
		}
		LzModule module = list.get(0);
		MenuTree menuTree = new MenuTree();
		menuTree.setId(module.getModuleId());
		menuTree.setText(module.getName());
		menuTree.setState(module.getState());
		return searchMenuTree(menuTree,new HashSet<LzModule>());
	}
	/* 
	 * 递归遍历菜单,加入递归死循环容错处理.
	 */
	private MenuTree searchMenuTree(MenuTree voTree,Set<LzModule> set) {
		String hql = "select m from LzModule m where m.parentId=?";
		List<LzModule> modules = (List<LzModule>) moduleDAO.queryListByParams(hql,new Object[]{voTree.getId()});
		if(modules.size() == 0){
			return voTree;
		}else{
			List<MenuTree> chiledTree = new ArrayList<MenuTree>();
			for(LzModule m : modules){
				MenuTree t = new MenuTree();
				t.setId(m.getModuleId());
				t.setText(m.getName());
				t.setState(m.getState());
				if(m.getLeaf().equals(EnumAdminUtils.Leaf.TRUE.code)){
					t.setIconCls(m.getIcons());
				}
				Map<String,String> attrMap = new HashMap<String,String>();
				//属性在数据库用,分隔，如:url=http://localhost , name=hecj
				if(!StringUtil.isStrEmpty(m.getUrl())){
					attrMap.put("url", m.getUrl());
					t.setAttributes(attrMap);
				}
				if(m.getLeaf().equals(EnumAdminUtils.Leaf.FALSE.code)){
					if(!set.add(m)){
						Log4jUtil.error("出现了递归死循环！Module："+m.getModuleId());
						return voTree;
					}
					chiledTree.add(searchMenuTree(t,set));
				}else{
					chiledTree.add(t);
				}
			}
			voTree.setChildren(chiledTree);
			return voTree ; 
		}
	}
	
	@Override
	public VoModule treeManagerSearch(String moduleId) {
		
		List<LzModule> modules = moduleDAO.queryListByParams("select m from LzModule m where m.moduleId=?", new Object[]{moduleId});
		if(modules.size()>0){
			LzModule module = modules.get(0);
			VoModule voModule = new VoModule();
			voModule.setModuleId(module.getModuleId());
			voModule.setName(module.getName());
			voModule.setParentId(module.getParentId());
			voModule.setState(module.getState());
			voModule.setLeaf(module.getLeaf());
			if(module.getLeaf().equals(EnumAdminUtils.Leaf.TRUE.code)){
				voModule.setIconCls(module.getIcons());
			}
			return treeManagerSearch(voModule,new HashSet<LzModule>());
		}else{
			return null;
		}
	}
	/* 
	 * 递归遍历菜单,加入递归死循环容错处理.
	 */
	private VoModule treeManagerSearch(VoModule voTree,Set<LzModule> set) {
		String hql = "select m from LzModule m where m.parentId=?";
		List<LzModule> modules = (List<LzModule>) moduleDAO.queryListByParams(hql,new Object[]{voTree.getModuleId()});
		if(modules.size() == 0){
			return voTree;
		}else{
			List<VoModule> chiledTree = new ArrayList<VoModule>();
			for(LzModule m : modules){
				VoModule voModule = new VoModule();
				voModule.setModuleId(m.getModuleId());
				voModule.setName(m.getName());
				if(m.getLeaf().equals(EnumAdminUtils.Leaf.TRUE.code)){
					voModule.setIconCls(m.getIcons());
				}
				voModule.setLeaf(m.getLeaf());
				voModule.setState(m.getState());
				voModule.setParentId(m.getParentId());
				//属性在数据库用,分隔，如:urlEQhttp://localhost,nameEQhecj
				if(!StringUtil.isStrEmpty(m.getUrl())){
					voModule.setUrl(m.getUrl());
				}
				if(m.getLeaf().equals(EnumAdminUtils.Leaf.FALSE.code)){
					if(!set.add(m)){
						Log4jUtil.error("出现了递归死循环！Module："+m.getModuleId());
						return voTree;
					}
					chiledTree.add(treeManagerSearch(voModule,set));
				}else{
					chiledTree.add(voModule);
				}
			}
			voTree.setChildren(chiledTree);
			return voTree ; 
		}
	}

	@Override
	public LzModule searchModuleById(String id) {
		return moduleDAO.findById(id);
	}
	@Override
	public boolean addBrotherNode(LzModule module) {
		try{
			Log4jUtil.log("添加兄弟节点:"+module.getName());
			String qHql = "select m from LzModule m where m.parentId=? order by m.moduleId asc";
			List<LzModule> list = moduleDAO.queryListByParams(qHql, new Object[]{module.getParentId()});
			if(list.size()>0){
				//拼接Id组成新的Id
				module.setModuleId(module.getParentId()+getNewModuleId(list));
				moduleDAO.save(module);
				return true;
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	/*
	 * 遍历获取新的兄弟节点
	 */
	private String getNewModuleId(List<LzModule> list){
		//截图Id最后3位
		String newModuleId = list.get(0).getModuleId();
		int last3IndexId = Integer.parseInt(newModuleId.substring(newModuleId.length()-3));
		for(LzModule m : list){
			//判断最后3位Id是否相等
			int tempId = Integer.parseInt(m.getModuleId().substring(m.getModuleId().length()-3));
			if(tempId == last3IndexId){
				last3IndexId ++;
			}else{
				break;
			}
		}
		//不足3位时前面补0
		String last3IndexIdStr = String.valueOf(last3IndexId);
		int length = last3IndexIdStr.length();
		for(int i=0;i<3-length;i++){
			last3IndexIdStr = "0"+last3IndexIdStr;
		}
		return last3IndexIdStr;
	}
	
	@Override
	public boolean addChildNode(LzModule module) {
		try{
			String qHql = "select m from LzModule m where m.parentId = ? order by m.moduleId asc";
			List<LzModule> list = moduleDAO.queryListByParams(qHql, new Object[]{module.getParentId()});
			String newModuleId ;
			if(list.size()>0){
				newModuleId = module.getParentId()+ getNewModuleId(list);
			}else{
				newModuleId = module.getParentId()+ "001";
			}
			module.setModuleId(newModuleId);
			LzModule parentModule = moduleDAO.findById(module.getParentId());
			//更新父节点为枝干
			if(parentModule.getLeaf().equals(EnumAdminUtils.Leaf.TRUE.code)){
				parentModule.setLeaf(EnumAdminUtils.Leaf.FALSE.code);
				moduleDAO.update(parentModule);
			}
			moduleDAO.save(module);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteNode(String moduleId){
		LzModule module = moduleDAO.findById(moduleId);
		if(!StringUtil.isObjectEmpty(module)){
			//如何是枝干，则递归查询子节点Id
			if(module.getLeaf().equals(EnumAdminUtils.Leaf.FALSE.code)){
				//递归删除
				String ids = searchIds("",moduleId,new HashSet<String>());
				ids = ids.replaceAll(",,", ",").replaceAll(",,,", ",");
				if(ids.startsWith(",")){
					ids = ids.replaceFirst(",", "");
				}
				if(ids.endsWith(",")){
					ids = ids.substring(0, ids.length()-1);
				}
				if(!StringUtil.isStrEmpty(ids)){
					String dHql = "delete Module m where m.moduleId in ("+ids+")";
					moduleDAO.executeHQL(dHql);
				}
			}
			moduleDAO.delete(module);
			//判断是否改变父节点为叶子
			String qHql = "select m from LzModule m where m.parentId=?";
			List<LzModule> list = moduleDAO.queryListByParams(qHql, new Object[]{module.getParentId()});
			if(list.size() == 0){
				LzModule m = moduleDAO.findById(module.getParentId());
				m.setLeaf(EnumAdminUtils.Leaf.TRUE.code);
				moduleDAO.merge(m);
			}
			return true;
		}
		return false;
	}
	
	/* 
	 * 递归遍历菜单,加入递归死循环容错处理.
	 */
	private String searchIds(String ids,String id,Set<String> set) {
		String hql = "select m from LzModule m where m.parentId=?";
		List<LzModule> modules = (List<LzModule>) moduleDAO.queryListByParams(hql,new Object[]{id});
		if(modules.size() == 0){
			return ids;
		}else{
			for(LzModule m : modules){
				if(m.getLeaf().equals(EnumAdminUtils.Leaf.FALSE.code)){
					if(!set.add(m.getModuleId())){
						Log4jUtil.error("出现了递归死循环！Module："+m.getModuleId());
						return ids;
					}
					ids = "'"+m.getModuleId()+"',"+searchIds(ids,m.getModuleId(),set);
				}else{
					ids = ids+",'"+m.getModuleId()+"'";
				}
			}
			return ids ; 
		}
	}
	@Override
	public boolean updateNode(LzModule module) {
		try {
			moduleDAO.merge(module);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
