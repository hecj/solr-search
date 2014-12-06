package com.hecj.search.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.services.ArticleService;
import com.hecj.search.util.Pagination;
import com.hecj.search.web.controller.base.BaseController;

@Controller
@RequestMapping("/indexPage/indexPage.htm")
public class IndexPageController extends BaseController {

	@Resource
	private ArticleService articleService = null;

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}


	/**
	 * 初始化首页
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "operator=initIndexPage")
	public String ininIndexPage(Long currPage,HttpServletRequest request) {

		try {
			/*
			 * 查询文章集合
			 */
			long currPageNum = 1l;
			if(currPage != null){
				currPageNum = currPage;
			}
			Pagination mPagination = new Pagination();
			mPagination.setCurrPage(currPageNum);
			mPagination.setPathURL(getBasePath()+"indexPage/indexPage.htm?operator=initIndexPage&currPage=");
			
			Map<String, Object> mArticleMap = new HashMap<String, Object>();
			mArticleMap.put("pagination", mPagination);
			Map<String, Object> rArticleMap = articleService.searchArticleList(mArticleMap);

			List<Article> articleList = (List<Article>) rArticleMap.get("rArticleList");
			mPagination = (Pagination) rArticleMap.get("pPagination");
			/*
			 * 放入作用域
			 */
			request.setAttribute("articleList", articleList);
			request.setAttribute("pagination", mPagination);
			
		} catch (Exception mException) {
			mException.printStackTrace();
		} finally {

		}
		return "index";
	}
}
