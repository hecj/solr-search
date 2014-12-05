package com.hecj.search.web.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.hibernate.util.UUIDUtil;
import com.hecj.search.services.ArticleService;
import com.hecj.search.util.DateFormatUtil;

@Controller
@RequestMapping("article/article.htm")
public class ArticleAction{
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ArticleService articleService = null ;

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping(params="operator=toAddArticle")
	public String toAddArticle(){
		return "/WEB-INF/jsp/article/article_add.jsp" ;
	}
	
	@RequestMapping(params="operator=submitAddArticle")
	public String submitAddArticle(Article article){
		
		article.setArticleNo(UUIDUtil.autoUUID());
		article.setAuther("匿名");
		article.setCreateDate(DateFormatUtil.getCurrDate());
		article.setUpdateDate(DateFormatUtil.getCurrDate());
		articleService.addArticle(article);
		
		return "/article/article.htm?operator=toAddArticle" ;
	}
}
