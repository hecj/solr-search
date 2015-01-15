package com.freedom.search.admin.services.imp;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.dao.RoleDAO;
import com.freedom.search.admin.dao.RoleModuleDAO;
import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.admin.entity.LzRoleModule;
import com.freedom.search.admin.services.RoleService;
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
	

	public void setRoleModuleDAO(RoleModuleDAO roleModuleDAO) {
		this.roleModuleDAO = roleModuleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
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
		} catch (Exception e) {
			e.printStackTrace();
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
			//删除权限
			String deleteRoleModele = "delete LzRoleModule t where t.roleCode='"+roleCode+"'";
			roleModuleDAO.executeHQL(deleteRoleModele);
			//删除角色
			roleDAO.delete(roleDAO.findById(roleCode));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
	
}
