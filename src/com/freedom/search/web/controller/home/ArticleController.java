package com.freedom.search.web.controller.home;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.freedom.search.hibernate.entity.LaArticle;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.services.ArticleService;
import com.freedom.search.util.DateFormatUtil;
import com.freedom.search.web.controller.base.BaseController;

/**
 * @类功能说明：文章操作控制器
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-6 上午01:11:28
 * @版本：V1.0
 */
@Controller
@RequestMapping("home/article.htm")
public class ArticleController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Resource
	private ArticleService articleService = null;

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping(params = "operator=toAddArticle")
	public String toAddArticle() {
		return "WEB-INF/jsp/article/article_add";
	}

	/**
	 * @ModelAttribute() //加上才能将request的内容带过来
	 * return "redirect:/indexPage/indexPage.htm?operator=initIndexPage";
	 * return "redirect:http://localhost:8080/search";
	 * return "redirect:/servlet/initIndexPage.do";
	 */
	@RequestMapping(params="operator=submitAddArticle")
	public String submitAddArticle(LaArticle article) {

		try {
			
			article.setArticleNo(UUIDUtil.autoUUID());
			article.setAuther("匿名");
			article.setCreateDate(DateFormatUtil.getCurrDate());
			article.setUpdateDate(DateFormatUtil.getCurrDate());
			articleService.addArticle(article);
		} catch (Exception mException) {
			mException.printStackTrace();
		} finally {

		}
		return "redirect:"+getBasePath();
	}

}
