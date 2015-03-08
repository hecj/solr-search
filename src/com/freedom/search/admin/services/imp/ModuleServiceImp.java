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

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.admin.dao.ModuleDAO;
import com.freedom.search.admin.dao.RoleModuleDAO;
import com.freedom.search.admin.dao.UserDAO;
import com.freedom.search.admin.entity.LzModule;
import com.freedom.search.admin.entity.LzRoleModule;
import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.admin.vo.Tree;
import com.freedom.search.admin.vo.VoModule;
import com.freedom.search.admin.vo.VoRadio;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.ResultSupport;
import com.freedom.search.util.StringUtil;
@Transactional
@Service("moduleService")
public class ModuleServiceImp implements ModuleService{
	
	@Resource
	private ModuleDAO moduleDAO;
	@Resource
	private RoleModuleDAO roleModuleDAO;
	
	@Resource
	private UserDAO userDAO;
	
	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}
	
	public void setRoleModuleDAO(RoleModuleDAO roleModuleDAO) {
		this.roleModuleDAO = roleModuleDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Tree searchMenuTree(String moduleId) {
		
		List<LzModule> list = moduleDAO.queryListByParams("select m from LzModule m where m.id=?", new Object[]{moduleId});
		if(list.size() == 0){
			return null;
		}
		LzModule module = list.get(0);
		Tree menuTree = new Tree();
		menuTree.setId(module.getModuleId());
		menuTree.setText(module.getName());
		menuTree.setState(module.getState());
		return searchMenuTree(menuTree,new HashSet<LzModule>());
	}
	/* 
	 * 递归遍历菜单,加入递归死循环容错处理.
	 */
	private Tree searchMenuTree(Tree voTree,Set<LzModule> set) {
		String hql = "select m from LzModule m where m.parentId=? and m.type=?";
		List<LzModule> modules = (List<LzModule>) moduleDAO.queryListByParams(hql,new Object[]{voTree.getId(),EnumAdminUtils.ModuleType.Menu.code});
		if(modules.size() == 0){
			return voTree;
		}else{
			List<Tree> chiledTree = new ArrayList<Tree>();
			for(LzModule m : modules){
				Tree t = new Tree();
				t.setId(m.getModuleId());
				t.setText(m.getName());
				t.setState(m.getState());
				if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.True.code)){
					t.setIconCls(m.getIcons());
				}
				Map<String,String> attrMap = new HashMap<String,String>();
				//属性在数据库用,分隔，如:url=http://localhost , name=hecj
				if(!StringUtil.isStrEmpty(m.getUrl())){
					attrMap.put("url", m.getUrl());
					t.setAttributes(attrMap);
				}
				if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.False.code)){
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
			if(module.getLeaf().equals(EnumAdminUtils.Tree.Leaf.True.code)){
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
		String hql = "select m from LzModule m where m.parentId=? and m.type=?";
		List<LzModule> modules = (List<LzModule>) moduleDAO.queryListByParams(hql,new Object[]{voTree.getModuleId(),EnumAdminUtils.ModuleType.Menu.code});
		if(modules.size() == 0){
			return voTree;
		}else{
			List<VoModule> chiledTree = new ArrayList<VoModule>();
			for(LzModule m : modules){
				VoModule voModule = new VoModule();
				voModule.setModuleId(m.getModuleId());
				voModule.setName(m.getName());
				if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.True.code)){
					voModule.setIconCls(m.getIcons());
				}
				voModule.setLeaf(m.getLeaf());
				voModule.setState(m.getState());
				voModule.setParentId(m.getParentId());
				//属性在数据库用,分隔，如:urlEQhttp://localhost,nameEQhecj
				if(!StringUtil.isStrEmpty(m.getUrl())){
					voModule.setUrl(m.getUrl());
				}
				if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.False.code)){
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
			String qHql = "select m from LzModule m where m.parentId=? and m.type=? order by m.moduleId asc";
			List<LzModule> list = moduleDAO.queryListByParams(qHql, new Object[]{module.getParentId(),EnumAdminUtils.ModuleType.Menu.code});
			if(list.size()>0){
				//拼接Id组成新的Id
				module.setModuleId(module.getParentId()+getNewModuleId(list));
				moduleDAO.save(module);
				return true;
			}
		
		}catch(RuntimeException ex){
			ex.printStackTrace();
			throw ex;
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
			String qHql = "select m from LzModule m where m.parentId = ? and m.type=? order by m.moduleId asc";
			List<LzModule> list = moduleDAO.queryListByParams(qHql, new Object[]{module.getParentId(),EnumAdminUtils.ModuleType.Menu.code});
			String newModuleId ;
			if(list.size()>0){
				newModuleId = module.getParentId()+ getNewModuleId(list);
			}else{
				newModuleId = module.getParentId()+ "001";
			}
			module.setModuleId(newModuleId);
			LzModule parentModule = moduleDAO.findById(module.getParentId());
			//更新父节点为枝干
			if(parentModule.getLeaf().equals(EnumAdminUtils.Tree.Leaf.True.code)){
				parentModule.setLeaf(EnumAdminUtils.Tree.Leaf.False.code);
				moduleDAO.update(parentModule);
			}
			moduleDAO.save(module);
			return true;
		}catch(RuntimeException ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@Override
	public boolean deleteNode(String moduleId) {
		
		LzModule module = moduleDAO.findById(moduleId);
		if(!StringUtil.isObjectEmpty(module)){
			//如何是枝干，则递归查询子节点Id
			String ids = "";
			if(module.getLeaf().equals(EnumAdminUtils.Tree.Leaf.False.code)){
				//递归删除
				ids = searchIds("",moduleId,new HashSet<String>());
				ids = ids.replaceAll(",,", ",").replaceAll(",,,", ",");
				if(ids.startsWith(",")){
					ids = ids.replaceFirst(",", "");
				}
				if(ids.endsWith(",")){
					ids = ids.substring(0, ids.length()-1);
				}
			}
			
			//删除模块及子模块
			String dHql;
			if(!StringUtil.isStrEmpty(ids)){
				dHql = "delete LzModule m where m.moduleId in ('"+module.getModuleId()+"',"+ids+") and m.type="+EnumAdminUtils.ModuleType.Menu.code+"";
			}else{
				dHql = "delete LzModule m where m.moduleId in ('"+module.getModuleId()+"') and m.type="+EnumAdminUtils.ModuleType.Menu.code+"";
			}
			moduleDAO.executeHQL(dHql);
			
			String qRMHql ;
			if(!StringUtil.isStrEmpty(ids)){
				qRMHql = "select count(m) from LzRoleModule m where m.moduleId in ('"+module.getModuleId()+"',"+ids+")";
			}else{
				qRMHql = "select count(m) from LzRoleModule m where m.moduleId in ('"+module.getModuleId()+"')";
			}
			//如何模块已被授权，则禁止删除模块
			Long count = (Long) roleModuleDAO.queryUniqueResultByHQL(qRMHql);
			if(count > 0){
				throw new RuntimeException("模块("+module.getModuleId()+")已被授权"+count+"次,不可删除!");
			}
			//判断是否改变父节点为叶子
			String qHql = "select m from LzModule m where m.parentId=?";
			List<LzModule> list = moduleDAO.queryListByParams(qHql, new Object[]{module.getParentId()});
			if(list.size() == 0){
				LzModule m = moduleDAO.findById(module.getParentId());
				m.setLeaf(EnumAdminUtils.Tree.Leaf.True.code);
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
		String hql = "select m from LzModule m where m.parentId=? and m.type=?";
		List<LzModule> modules = (List<LzModule>) moduleDAO.queryListByParams(hql,new Object[]{id,EnumAdminUtils.ModuleType.Menu.code});
		if(modules.size() == 0){
			return ids;
		}else{
			for(LzModule m : modules){
				if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.False.code)){
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
		}catch(RuntimeException ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public List<LzModule> searchChildModules(String rolecode,String id) {
		
		String query = "select m from LzModule m where m.parentId=? and m.moduleId in " +
				"(select rm.moduleId from LzRoleModule rm where rm.rolecode=?) and m.type=?";
		return moduleDAO.queryListByParams(query, new Object[]{id,rolecode,EnumAdminUtils.ModuleType.Menu.code});
	}
	
	@Override
	public List<LzModule> searchChildModules(String id) {
		
		String query = "select m from LzModule m where m.parentId=? and m.type=?";
		return moduleDAO.queryListByParams(query, new Object[]{id,EnumAdminUtils.ModuleType.Menu.code});
	}

	@Override
	public List<Tree> searchChildTree(String usercode,String id) {
		//查询用户角色代码
		String rolecode = userDAO.findById(usercode).getRole().getRolecode();
		List<Tree> trees = new ArrayList<Tree>();
		List<LzModule> modules = this.searchChildModules(rolecode,id);
		//模块转vo类
		for(LzModule m:modules){
			Tree tree = new Tree();
			tree.setId(m.getModuleId());
			tree.setText(m.getName());
			Map<String,String> map = new HashMap<String,String>();
			map.put(EnumAdminUtils.Tree.Attributes.URL.code, m.getUrl());
			tree.setAttributes(map);
			if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.False.code)){
				tree.setState(EnumAdminUtils.Tree.State.Closed.code);
			}else{
				tree.setIconCls(m.getIcons());
			}
			trees.add(tree);
		}
		return trees;
	}

	@Override
	public boolean addRadio(LzModule module) {
		moduleDAO.save(module);
		return true;
	}

	@Override
	public boolean delRadio(String moduleId) {
		
		String query = "select rm from LzRoleModule rm where rm.moduleId=?";
		List<LzRoleModule> list = roleModuleDAO.queryListByParams(query, new Object[]{moduleId});
		if(list.size()>0){
			throw new RuntimeException("按钮已被授权"+list.size()+"次，不可删除！");
		}
		moduleDAO.delete(moduleDAO.findById(moduleId));
		return true;
	}

	@Override
	public boolean editRadio(LzModule module) {
		moduleDAO.merge(module);
		return true;
	}

	@Override
	public Result searchRadioList(Map<String, Object> map) {
		Result result = new ResultSupport();
		try{
			
			Pagination pagination = (Pagination) map.get("pagination");
			String mQueryHQL = "select u from LzModule u where u.type=?";
			String mContHQL = "select count(u) from LzModule u where u.type="+EnumAdminUtils.ModuleType.Radio.code+"";
			
			List<LzModule> moduleList = moduleDAO.queryListByParamsAndPagination(mQueryHQL, pagination.startCursor().intValue(), pagination.getPageSize(),
					new Object[]{EnumAdminUtils.ModuleType.Radio.code});
			long count = Long.parseLong(moduleDAO.queryUniqueResultByHQL(mContHQL).toString());
			pagination.setCountSize(count);
			
			List<VoRadio> radioList = new ArrayList<VoRadio>();
			for(LzModule m : moduleList){
				VoRadio radio = new VoRadio();
				radio.setRadiocode(m.getModuleId());
				radio.setRadioname(m.getName());
				radio.setUrl(m.getUrl());
				radio.setIcon(m.getIcons());
				radioList.add(radio);
			}
			result.setData(radioList);
			result.setPagination(pagination);
			result.setResult(true);
		}catch(Exception ex){
			
			result.setResult(false);
			Log4jUtil.log(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<VoRadio> searchPermissionRadios(String rolecode) {
		
		String queryRoleRadio = "select m from LzModule m where m.type=? and m.moduleId in " +
				"(select rm.moduleId from LzRoleModule rm where rm.rolecode=?)";
		List<LzModule> roleModules = moduleDAO.queryListByParams(queryRoleRadio, new Object[]{EnumAdminUtils.ModuleType.Radio.code,rolecode});
		String queryAllRadio = "select m from LzModule m where m.type=? and m.moduleId not in (select rm.moduleId from LzRoleModule rm where rm.rolecode=?)";
		List<LzModule> allModules = moduleDAO.queryListByParams(queryAllRadio, new Object[]{EnumAdminUtils.ModuleType.Radio.code,rolecode});
		
		List<VoRadio> radios = new ArrayList<VoRadio>();
		for(LzModule m:roleModules){
			VoRadio r = new VoRadio();
			r.setRadiocode(m.getModuleId());
			r.setRadioname(m.getName());
			r.setIcon(m.getIcons());
			r.setUrl(m.getUrl());
			r.setCheck(true);
			radios.add(r);
		}
		for(LzModule m:allModules){
			VoRadio r = new VoRadio();
			r.setRadiocode(m.getModuleId());
			r.setRadioname(m.getName());
			r.setIcon(m.getIcons());
			r.setUrl(m.getUrl());
			radios.add(r);
		}
		return radios;
	}

	@Override
	public List<VoRadio> searchExistPermissionRadios(String rolecode) {
		String queryRoleRadio = "select m from LzModule m where m.type=? and m.moduleId in " +
		"(select rm.moduleId from LzRoleModule rm where rm.rolecode=?)";
		List<LzModule> roleModules = moduleDAO.queryListByParams(queryRoleRadio, new Object[]{EnumAdminUtils.ModuleType.Radio.code,rolecode});
		
		List<VoRadio> radios = new ArrayList<VoRadio>();
		for(LzModule m:roleModules){
			VoRadio r = new VoRadio();
			r.setRadiocode(m.getModuleId());
			r.setRadioname(m.getName());
			r.setIcon(m.getIcons());
			r.setUrl(m.getUrl());
			r.setCheck(true);
			radios.add(r);
		}
		return radios;
	}

}
