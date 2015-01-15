package com.freedom.search.admin.web.controller.data;



import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.entity.LzRole;
import com.freedom.search.admin.entity.LzRoleModule;
import com.freedom.search.admin.senum.EnumAdminUtils;
import com.freedom.search.admin.services.MenuTreeService;
import com.freedom.search.admin.services.RoleService;
import com.freedom.search.admin.vo.MenuTree;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.util.DateFormatUtil;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.StringUtil;
import com.freedom.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/role/role.htm")
public class RoleController extends BaseController {
	
	@Resource
	private RoleService roleService;
	@Resource
	private MenuTreeService menuTreeService;

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setMenuTreeService(MenuTreeService menuTreeService) {
		this.menuTreeService = menuTreeService;
	}

	@RequestMapping(params="operator=searchList")
	public void searchList(HttpServletRequest request,HttpServletResponse response){
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(params="operator=addRole")
	public void addRole(HttpServletRequest request,HttpServletResponse response){
		try {
			
			String roleCode = request.getParameter("roleCode");
			String rolename = request.getParameter("rolename");
			String ids = request.getParameter("ids");
			
			LzRole role = new LzRole();
			role.setRoleCode(roleCode);
			role.setRolename(rolename);
			role.setCreateDate(DateFormatUtil.getCurrDate());
			role.setUdpateDate(DateFormatUtil.getCurrDate());
			
			List<LzRoleModule> list = new ArrayList<LzRoleModule>();
			if(!StringUtil.isStrEmpty(ids)){
				String[] idList = ids.split(",");
				for(String moduleId : idList){
					if(!StringUtil.isStrEmpty(moduleId)){
						LzRoleModule rm = new LzRoleModule();
						rm.setId(UUIDUtil.autoUUID());
						rm.setRoleCode(roleCode);
						rm.setModuleId(moduleId);
						list.add(rm);
					}
				}
			}
			if(roleService.addRole(role, list)){
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
			MenuTree voTree = menuTreeService.searchMenuTree(moduleId);
			if(voTree != null){
				write(response, voTree.toString());
			}
		}else{
			Log4jUtil.log("moduleId is null!");
		}
	}
	
}
