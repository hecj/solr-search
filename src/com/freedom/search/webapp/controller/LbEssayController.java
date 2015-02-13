package com.freedom.search.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.util.EasyGridData;
import com.freedom.search.util.MessageCode;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.Result;
import com.freedom.search.util.StringUtil;
import com.freedom.search.web.controller.base.BaseController;
import com.freedom.search.webapp.entity.LbEssay;
import com.freedom.search.webapp.services.LbEssayService;


@Controller
@RequestMapping("webapp/essay/essay.htm")
public class LbEssayController extends BaseController {
	
	@Resource
	private LbEssayService lbEssayService;

	public void setEssayService(LbEssayService lbEssayService) {
		this.lbEssayService = lbEssayService;
	}
	
	@RequestMapping(params="operator=add")
	public void add(HttpServletRequest request,HttpServletResponse response){
		try {
			
			String title = request.getParameter("title");
			String image = request.getParameter("image");
			String content = request.getParameter("content");
		
			System.out.println(title);
			System.out.println(image);
			System.out.println(content);
			
			LbEssay essay = new LbEssay();
			essay.setId(UUIDUtil.autoUUID());
			essay.setTitle(title);
			essay.setContent(content);
			essay.setImage(image);
			essay.setCreateDate(new Date());
			essay.setUpdateDate(new Date());

			if(lbEssayService.addLbEssay(essay)){
				writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.SUCCESS.code, "处理成功!"));
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}
	
	@RequestMapping(params="operator=searchEssays")
	public void searchEssays(Integer page,Integer rows,HttpServletRequest request,HttpServletResponse response){
		try {
			System.out.println(page+"===========");
			Pagination p = new Pagination(10);
			if(!StringUtil.isObjectNull(page)){
				p.setCurrPage(page.longValue());
			}
			if(!StringUtil.isObjectNull(rows)){
				p.setPageSize(rows);
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pagination", p);
			
			Result result = lbEssayService.searchEssayList(map);
			if(result.isSuccess()){
				writeToJSON(response,new EasyGridData(result.getPagination().getCountSize(),result.getData()));
				return;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		writeToJSON(response,new EasyGridData());
	}
	
}
