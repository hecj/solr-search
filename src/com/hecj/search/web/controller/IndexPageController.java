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
import com.hecj.search.util.CodeConvertUtil;
import com.hecj.search.util.Pagination;
import com.hecj.search.util.StringUtil;
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
	@RequestMapping(params = "m=init")
	public String ininIndexPage(Long n,String q,HttpServletRequest request) {

		try {
			/*
			 * 查询文章集合
			 */
			long currPageNum = 1l;
			if(n != null){
				currPageNum = n;
			}
			/*
			 * 显示页面的内容
			 * showQ
			 */
			String showQ ;
			if(StringUtil.isStrEmpty(q)){
				showQ = "";
				q = "*";
			}else{
				showQ = q ;
			}
			Pagination mPagination = new Pagination();
			mPagination.setCurrPage(currPageNum);
			mPagination.setPathURL(getBasePath()+"indexPage/indexPage.htm?m=init&q="+CodeConvertUtil.encode(showQ)+"&n=");
			
			Map<String, Object> mArticleMap = new HashMap<String, Object>();
			mArticleMap.put("pagination", mPagination);
			mArticleMap.put("queryString", q);
			Map<String, Object> rArticleMap = articleService.searchArticleListBySolr(mArticleMap);

			List<Article> articleList = (List<Article>) rArticleMap.get("rArticleList");
			mPagination = (Pagination) rArticleMap.get("pPagination");
			/*
			 * 放入作用域
			 */
			request.setAttribute("articleList", articleList);
			request.setAttribute("pagination", mPagination);
			request.setAttribute("showQ", showQ);
			
		} catch (Exception mException) {
			mException.printStackTrace();
		} finally {

		}
		return "home";
	}
}
