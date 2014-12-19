package com.hecj.search.web.controller.home;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hecj.search.services.ArticleService;
import com.hecj.search.web.controller.base.BaseController;

@Controller
@RequestMapping("home/attachmen.htm")
public class AttachmentController extends BaseController{
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ArticleService articleService = null ;

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping(params="operator=add")
	public String add(){
		
		return "WEB-INF/jsp/article/article_add.jsp";
	}
	
	@ResponseBody
	@RequestMapping(params="method=search2")
	public String search2(){
		
		return "fds";
	}
}
