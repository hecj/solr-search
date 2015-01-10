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
import com.freedom.search.admin.entity.Module;
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
	public MenuTree searchMenuTree(Integer moduleId,String basePath) {
		Module module = moduleDAO.queryListByParams("select m from Module m where m.id=?", new Object[]{moduleId}).get(0);
		MenuTree voTree = new MenuTree();
		voTree.setId(module.getModuleId());
		voTree.setText(module.getName());
		return searchMenuTree(voTree,new HashSet<Module>(),basePath);
	}
	/* 
	 * 递归遍历菜单,加入递归死循环容错处理.
	 */
	private MenuTree searchMenuTree(MenuTree voTree,Set<Module> set,String basePath) {
		String hql = "select m from Module m where m.parentId=?";
		List<Module> modules = (List<Module>) moduleDAO.queryListByParams(hql,new Object[]{voTree.getId()});
		if(modules.size() == 0){
			return voTree;
		}else{
			List<MenuTree> chiledTree = new ArrayList<MenuTree>();
			for(Module m : modules){
				MenuTree t = new MenuTree();
				t.setId(m.getModuleId());
				t.setText(m.getName());
				Map<String,String> attrMap = new HashMap<String,String>();
				//属性在数据库用,分隔，如:url=http://localhost , name=hecj
				if(!StringUtil.isStrEmpty(m.getAttributes())){
					String[] attrs = m.getAttributes().split(",");
					for(String attr:attrs){
						String[] str = attr.split("=");
						if(str.length == 2){
							if(str[0].equals("url")){
								attrMap.put(str[0], basePath + str[1]);
							}else{
								attrMap.put(str[0], str[1]);
							}
						}
					}
					t.setAttributes(attrMap);
				}
				if(!m.isLeaf()){
					if(!set.add(m)){
						Log4jUtil.error("出现了递归死循环！Module："+m.getModuleId());
						return voTree;
					}
					chiledTree.add(searchMenuTree(t,set ,basePath));
				}else{
					chiledTree.add(t);
				}
			}
			voTree.setChildren(chiledTree);
			return voTree ; 
		}
	}
	
	@Override
	public VoModule treeManagerSearch(Integer moduleId) {
		Module module = moduleDAO.queryListByParams("select m from Module m where m.id=?", new Object[]{moduleId}).get(0);
		VoModule voModule = new VoModule();
		voModule.setModuleId(module.getModuleId());
		voModule.setName(module.getName());
		return treeManagerSearch(voModule,new HashSet<Module>());
	}
	/* 
	 * 递归遍历菜单,加入递归死循环容错处理.
	 */
	private VoModule treeManagerSearch(VoModule voTree,Set<Module> set) {
		String hql = "select m from Module m where m.parentId=?";
		List<Module> modules = (List<Module>) moduleDAO.queryListByParams(hql,new Object[]{voTree.getModuleId()});
		if(modules.size() == 0){
			return voTree;
		}else{
			List<VoModule> chiledTree = new ArrayList<VoModule>();
			for(Module m : modules){
				VoModule voModule = new VoModule();
				voModule.setModuleId(m.getModuleId());
				voModule.setName(m.getName());
				voModule.setIcons(m.getIcons());
				voModule.setLeaf(m.isLeaf()?"是":"否");
				voModule.setParentId(m.getParentId());
				//属性在数据库用,分隔，如:url=http://localhost , name=hecj
				if(!StringUtil.isStrEmpty(m.getAttributes())){
					String[] attrs = m.getAttributes().split(",");
					for(String attr:attrs){
						String[] str = attr.split("=");
						if(str.length == 2){
							if(str[0].equals("url")){
								voModule.setUrl(str[1]);
							}
						}
					}
				}
				if(!m.isLeaf()){
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
	public Module searchModuleById(Integer id) {
		return moduleDAO.findById(id);
	}
	@Override
	public boolean addBrotherNode(Module module) {
		try{
			Log4jUtil.log("添加兄弟节点:"+module.getName());
			String qHql = "select m from Module m where m.parentId=? order by m.moduleId asc";
			List<Module> list = moduleDAO.queryListByParams(qHql, new Object[]{module.getParentId()});
			if(list.size()>0){
				int newModuleId = list.get(0).getModuleId();
				for(Module m : list){
					if(m.getModuleId() == newModuleId){
						newModuleId ++;
					}else{
						break;
					}
				}
				module.setModuleId(newModuleId);
				moduleDAO.save(module);
				return true;
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean deleteNode(Integer moduleId){
		Module module = moduleDAO.findById(moduleId);
		if(!StringUtil.isObjectEmpty(module)){
			moduleDAO.delete(module);
			return true;
		}
		return false;
	}
}
