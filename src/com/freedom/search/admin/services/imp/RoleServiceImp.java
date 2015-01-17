package com.freedom.search.admin.services.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.admin.dao.ModuleDAO;
import com.freedom.search.admin.dao.RoleDAO;
import com.freedom.search.admin.dao.RoleModuleDAO;
import com.freedom.search.admin.entity.LzModule;
import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.admin.entity.LzRoleModule;
import com.freedom.search.admin.exception.ModuleRoleExistException;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.admin.vo.VoTree;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.ResultSupport;
import com.freedom.search.util.StringUtil;

@Transactional
@Service("roleService")
public class RoleServiceImp implements RoleService {
	
	@Resource
	private RoleDAO roleDAO;
	@Resource
	private RoleModuleDAO roleModuleDAO;
	@Resource
	private ModuleDAO moduleDAO;
	
	public void setRoleModuleDAO(RoleModuleDAO roleModuleDAO) {
		this.roleModuleDAO = roleModuleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	@Override
	public synchronized boolean addRole(LzRole role,String[] moduleIds) {
		
		try {
			//自动判断生成唯一的Id
			String roleCode = "";
			while(true){
				roleCode = UUIDUtil.autoRoleCode();
				LzRole tempRole = roleDAO.findById(roleCode);
				if(StringUtil.isObjectNull(tempRole)){
					break;
				}
			}
			//插入角色
			role.setRoleCode(roleCode);
			String roleCodeTemp = (String) roleDAO.save(role);
			if(!StringUtil.isStrEmpty(roleCodeTemp)){
				//插入权限
   				for(String id : moduleIds){
					if(!StringUtil.isStrEmpty(id)){
						LzRoleModule rm = new LzRoleModule();
						rm.setId(UUIDUtil.autoUUID());
						rm.setRoleCode(roleCodeTemp);
						rm.setModuleId(id);
						roleModuleDAO.save(rm);
					}
				}
				return true;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
		return false;
	}
	
	@Override
	public Result searchRoleByPagination(Map<String, Object> map) {
		Result result = new ResultSupport();
		try{
			
			Pagination pagination = (Pagination) map.get("pagination");
			String mQueryHQL = "select r from LzRole r where 1=1";
			String mContHQL = "select count(r) from LzRole r where 1=1";
			
			List<LzRole> roleList = roleDAO.queryListByParamsAndPagination(mQueryHQL, pagination.startCursor().intValue(), pagination.getPageSize());
			long count = Long.parseLong(roleDAO.queryUniqueResultByHQL(mContHQL).toString());
			pagination.setCountSize(count);
			
			result.setData(roleList);
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
	public boolean deleteRole(String roleCode) {
		try {
			
			String qHql = "select count(u) from LzUser u where u.role='"+roleCode+"'";
			long count = Long.parseLong(roleDAO.queryUniqueResultByHQL(qHql).toString());
			if(count>0){
				throw new ModuleRoleExistException("角色("+roleCode+")已被授权("+count+"次,不可删除!)");
			}
			//删除权限
			String deleteRoleModele = "delete LzRoleModule t where t.roleCode='"+roleCode+"'";
			roleModuleDAO.executeHQL(deleteRoleModele);
			//删除角色
			roleDAO.delete(roleDAO.findById(roleCode));
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public LzRole searchRole(String roleCode) {
		try {
			return roleDAO.findById(roleCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VoTree> searchTreeByRoleCode(String roleCode, String id) {
		
		List<VoTree> trees = new ArrayList<VoTree>();
		try {
			//查询模块权限
			String qHql = "select m from LzModule m where m.moduleId in (select rm.moduleId from LzRoleModule rm where rm.roleCode=?) and m.parentId=?";
			List<LzModule> modules = moduleDAO.queryListByParams(qHql, new Object[]{roleCode,id});
			//模块转树对象
			for(LzModule m : modules){
				VoTree tree = new VoTree();
				tree.setId(m.getModuleId());
				tree.setText(m.getName());
				if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.False.code)){
					tree.setState(EnumAdminUtils.Tree.State.Closed.code);
				}else{
					tree.setIconCls(m.getIcons());
				}
				trees.add(tree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trees;
	}

	@Override
	public List<VoTree> searchEdutTreeByRoleCode(String roleCode, String id) {
		
		List<VoTree> trees = new ArrayList<VoTree>();
		try {
			//查询模块权限
			String qHql = "select m from LzModule m where m.moduleId in (select rm.moduleId from LzRoleModule rm where rm.roleCode=?) and m.parentId=?";
			List<LzModule> modules = moduleDAO.queryListByParams(qHql, new Object[]{roleCode,id});
			//无权限的模块
			String qNHql = "select m from LzModule m where m.moduleId not in (select rm.moduleId from LzRoleModule rm where rm.roleCode=?) and m.parentId=?";
			List<LzModule> nModules = moduleDAO.queryListByParams(qNHql, new Object[]{roleCode,id});
			
			//权限模块节点设置为checked
			for(LzModule m : modules){
				VoTree tree = new VoTree();
				tree.setId(m.getModuleId());
				tree.setText(m.getName());
				if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.False.code)){
					tree.setState(EnumAdminUtils.Tree.State.Closed.code);
				}else{
					tree.setIconCls(m.getIcons());
				}
				tree.setChecked(EnumAdminUtils.Tree.Checked.Checked.code);
				trees.add(tree);
			}
			//无权限模块设置不设置checked
			for(LzModule m : nModules){
				VoTree tree = new VoTree();
				tree.setId(m.getModuleId());
				tree.setText(m.getName());
				if(m.getLeaf().equals(EnumAdminUtils.Tree.Leaf.False.code)){
					tree.setState(EnumAdminUtils.Tree.State.Closed.code);
				}else{
					tree.setIconCls(m.getIcons());
				}
				trees.add(tree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trees;
	}

	@Override
		
	public boolean editRole(LzRole role, String[] moduleIds) {
		try {
			//查询角色
			LzRole tmpRole = roleDAO.findById(role.getRoleCode());
			if(StringUtil.isObjectNull(tmpRole)){
				return false;
			}
			//修改角色
			tmpRole.setRolename(role.getRolename());
			tmpRole.setUdpateDate(role.getUdpateDate());
			roleDAO.update(tmpRole);
			//删除历史权限
			String delHql = "delete LzRoleModule rm where rm.roleCode = '"+role.getRoleCode()+"'";
			roleModuleDAO.executeHQL(delHql);
			//插入新权限
			for(String id : moduleIds){
				if(!StringUtil.isStrEmpty(id)){
					LzRoleModule rm = new LzRoleModule();
					rm.setId(UUIDUtil.autoUUID());
					rm.setRoleCode(role.getRoleCode());
					rm.setModuleId(id);
					roleModuleDAO.save(rm);
				}
			}
			return true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<LzRole> searchRoleList() {
		return roleDAO.queryListByParams("select r from LzRole r ");
	}

	
	
}
