package com.freedom.search.admin.web.controller;

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
import com.freedom.search.admin.services.ModuleService;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.admin.vo.VoTree;
import com.freedom.search.util.DateFormatUtil;
import com.freedom.search.util.EasyGridData;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.ObjectToJson;
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
				write(response, new EasyGridData(result.getPagination().getCountSize(), result.getData()).toJSON());
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
				write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!").toJSON());
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
	}
	
	@RequestMapping(params="operator=deleteRole")
	public void deleteRole(String roleCode,HttpServletResponse response){
		try {
			if(!StringUtil.isStrEmpty(roleCode)){
				roleService.deleteRole(roleCode);
				write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!").toJSON());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
	}
	
	@RequestMapping(params="operator=findRole")
	public String findRole(String roleCode,HttpServletRequest request,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(roleCode)){
			LzRole role = roleService.searchRole(roleCode);
			request.setAttribute("role", role);
		}
		return "admin/jsp/role/rolemanager/findRole";
	}
	
	@RequestMapping(params="operator=editRole")
	public String editRole(String roleCode,HttpServletRequest request,HttpServletResponse response){
		if(!StringUtil.isStrEmpty(roleCode)){
			LzRole role = roleService.searchRole(roleCode);
			request.setAttribute("role", role);
		}
		return "admin/jsp/role/rolemanager/editRole";
	}
	
	@RequestMapping(params="operator=editRoleSub")
	public void editRoleSub(HttpServletRequest request,HttpServletResponse response){
		
		try {
			//字段
			String roleCode = request.getParameter("roleCode");
			String rolename = request.getParameter("rolename");
			String ids = request.getParameter("ids");
			if(StringUtil.isStrEmpty(roleCode)){
				write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
				return;
			}
			//角色
			LzRole role = new LzRole();
			role.setRoleCode(roleCode);
			role.setRolename(rolename);
			role.setUdpateDate(DateFormatUtil.getCurrDate());
			//模块Ids
			String[] moduleIds = new String[]{};
			if(!StringUtil.isStrEmpty(ids)){
				moduleIds = ids.split(",");
			}
			//修改
			if(roleService.editRole(role, moduleIds)){
				write(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!").toJSON());
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		write(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!").toJSON());
	}
	
	@RequestMapping(params="operator=initTree")
	public void initTree(String moduleId,HttpServletResponse response){
		
		if(!StringUtil.isStrEmpty(moduleId)){
			VoTree voTree = menuTreeService.searchMenuTree(moduleId);
			if(voTree != null){
				write(response, voTree.toString());
			}
		}else{
			Log4jUtil.log("moduleId is null!");
		}
	}
	
	@RequestMapping(params="operator=initModule")
	public void initModule(HttpServletRequest request,HttpServletResponse response){
		String roleCode = request.getParameter("roleCode");
		String id = request.getParameter("id");
		//Id为空时,默认为超级节点
		if(StringUtil.isStrEmpty(id)){
			id = EnumAdminUtils.Tree.RootParent.code ;
		}
		//查询节点树
		if(!StringUtil.isStrEmpty(roleCode)){
			List<VoTree> trees = roleService.searchTreeByRoleCode(roleCode, id);
			if(trees.size()>0){
				write(response, ObjectToJson.object2json(trees));
				return;
			}
		}
		//无权限
		VoTree defaultTree = new VoTree();
		defaultTree.setText("<font color=red>无权限</font>");
		write(response, defaultTree.toString());
		
	}
	
	@RequestMapping(params="operator=initEditModule")
	public void initEditModule(HttpServletRequest request,HttpServletResponse response){
		String roleCode = request.getParameter("roleCode");
		String id = request.getParameter("id");
		//Id为空时,默认为超级节点
		if(StringUtil.isStrEmpty(id)){
			id = EnumAdminUtils.Tree.RootParent.code ;
		}
		//查询所有节点树,并在有权限的节点树上加checked
		if(!StringUtil.isStrEmpty(roleCode)){
			List<VoTree> trees = roleService.searchEdutTreeByRoleCode(roleCode, id);
			if(trees.size()>0){
				write(response, ObjectToJson.object2json(trees));
				return;
			}
		}
		//无权限
		VoTree defaultTree = new VoTree();
		defaultTree.setText("<font color=red>无权限</font>");
		write(response, defaultTree.toString());
		
	}
	
	
}
