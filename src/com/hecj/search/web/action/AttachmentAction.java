package com.hecj.search.web.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hecj.search.services.ArticleService;

@Controller
@RequestMapping("/attachmen/attachmen.htm")
public class AttachmentAction{
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ArticleService articleService = null ;

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@ResponseBody
	@RequestMapping(params="method=search")
	public String search(){
		
		return "search";
	}
	
	@ResponseBody
	@RequestMapping(params="method=search2")
	public String search2(){
		
		return "fds";
	}
}
