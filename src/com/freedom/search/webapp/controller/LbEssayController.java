package com.freedom.search.webapp.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.util.MessageCode;
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
			return;
		}
		writeToJSON(response, new MessageCode(EnumAdminUtils.MessageCode.FAIL.code, "处理失败!"));
	}	
	
}
