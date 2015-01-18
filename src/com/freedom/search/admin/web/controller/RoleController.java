package com.freedom.search.admin.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.admin.exception.ModuleRoleExistException;
import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.admin.vo.Combobox;
import com.freedom.search.admin.vo.Tree;
import com.freedom.search.util.DateFormatUtil;
import com.freedom.search.util.EasyGridData;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.StringUtil;
import com.freedom.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/role/role.htm")
public class RoleController extends BaseController {
	
	@Resource
	private RoleService roleService;
	@Resource
	private ModuleService menuTreeService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setMenuTreeService(ModuleService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}

	@RequestMapping(params="operator=searchList")
	public void searchList(Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try {
			//分页参数
			Pagination mPagination = new Pagination(15);
			if(!StringUtil.isObjectNull(page)){
				mPagination.setCurrPage(page.longValue());
			}
			if(!StringUtil.isObjectNull(page)){
				mPagination.setPageSize(rows);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pagination", mPagination);
			//查询结果
			Result result = roleService.searchRoleByPagination(map);
			if(result.isSuccess()){
				writeToJSON(response, new EasyGridData(result.getPagination().getCountSize(), result.getData()));
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params="operator=addRole")
	public void addRole(HttpServletRequest request,HttpServletResponse response){
		try {
			//字段
			String rolename = request.getParameter("rolename");
			String ids = request.getParameter("ids");
			//角色
			LzRole role = new LzRole();
			role.setRolename(rolename);
			role.setCreateDate(DateFormatUtil.getCurrDate());
			role.setUdpateDate(DateFormatUtil.getCurrDate());
			//模块Ids
			String[] moduleIds = new String[]{};
			if(!StringUtil.isStrEmpty(ids)){
				moduleIds = ids.split(",");
			}
			//插入
			if(roleService.addRole(role, moduleIds)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}
	
	@RequestMapping(params="operator=deleteRole")
	public void deleteRole(String rolecode,HttpServletResponse response){
		try {
			if(!StringUtil.isStrEmpty(rolecode)){
				roleService.deleteRole(rolecode);
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!"));
				return;
			}
		} catch(ModuleRoleExistException e){
			writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, e.getMessage()));
			e.printStackTrace();
			return;
		}catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}
	
	@RequestMapping(params="operator=findRole")
	public String findRole(String rolecode,HttpServletRequest request,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(rolecode)){
			LzRole role = roleService.searchRole(rolecode);
			request.setAttribute("role", role);
		}
		return "admin/jsp/role/rolemanager/findRole";
	}
	
	@RequestMapping(params="operator=editRole")
	public String editRole(String rolecode,HttpServletRequest request,HttpServletResponse response){
		if(!StringUtil.isStrEmpty(rolecode)){
			LzRole role = roleService.searchRole(rolecode);
			request.setAttribute("role", role);
		}
		return "admin/jsp/role/rolemanager/editRole";
	}
	
	@RequestMapping(params="operator=editRoleSub")
	public void editRoleSub(HttpServletRequest request,HttpServletResponse response){
		
		try {
			//字段
			String rolecode = request.getParameter("rolecode");
			String rolename = request.getParameter("rolename");
			String ids = request.getParameter("ids");
			if(StringUtil.isStrEmpty(rolecode)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
				return;
			}
			//角色
			LzRole role = new LzRole();
			role.setRolecode(rolecode);
			role.setRolename(rolename);
			role.setUdpateDate(DateFormatUtil.getCurrDate());
			//模块Ids
			String[] moduleIds = new String[]{};
			if(!StringUtil.isStrEmpty(ids)){
				moduleIds = ids.split(",");
			}
			//修改
			if(roleService.editRole(role, moduleIds)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!"));
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}
	
	@RequestMapping(params="operator=initTree")
	public void initTree(String moduleId,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			Tree voTree = menuTreeService.searchMenuTree(moduleId);
			if(voTree != null){
				writeToJSON(response, voTree.parentTree());
			}
		}else{
			Log4jUtil.log("moduleId is null!");
		}
	}
	
	@RequestMapping(params="operator=initModule")
	public void initModule(HttpServletRequest request,HttpServletResponse response){
		String rolecode = request.getParameter("rolecode");
		String id = request.getParameter("id");
		//Id为空时,默认为超级节点
		if(StringUtil.isStrEmpty(id)){
			id = EnumAdminUtils.Tree.RootParent.code ;
		}
		//查询节点树
		if(!StringUtil.isStrEmpty(rolecode)){
			List<Tree> trees = roleService.searchTreeByRolecode(rolecode, id);
			if(trees.size()>0){
				writeToJSON(response, trees);
				return;
			}
		}
		//无权限
		Tree defaultTree = new Tree();
		defaultTree.setText("<font color=red>无权限</font>");
		writeToJSON(response, defaultTree.parentTree());
	}
	
	@RequestMapping(params="operator=initEditModule")
	public void initEditModule(HttpServletRequest request,HttpServletResponse response){
		String rolecode = request.getParameter("rolecode");
		String id = request.getParameter("id");
		//Id为空时,默认为超级节点
		if(StringUtil.isStrEmpty(id)){
			id = EnumAdminUtils.Tree.RootParent.code ;
		}
		//查询所有节点树,并在有权限的节点树上加checked
		if(!StringUtil.isStrEmpty(rolecode)){
			List<Tree> trees = roleService.searchEdutTreeByRolecode(rolecode, id);
			if(trees.size()>0){
				writeToJSON(response, trees);
				return;
			}
		}
		//无权限
		Tree defaultTree = new Tree();
		defaultTree.setText("<font color=red>无权限</font>");
		writeToJSON(response, defaultTree.parentTree());
		
	}
	
	@RequestMapping(params="operator=roleList")
	public void roleList(HttpServletRequest request,HttpServletResponse response){
		try {
			List<LzRole> roleList = roleService.searchRoleList();
			List<Combobox> voList = new ArrayList<Combobox>();
			for (LzRole r:roleList) {
				voList.add(new Combobox(r.getRolecode(),r.getRolename()+"("+r.getRolecode()+")"));
			}
			writeToJSON(response, voList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
