package com.hecj.search.solr.util;

import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.solr.bean.ArticleBean;

/**
 * @类功能说明：简单转换类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 上午01:55:43
 * @版本：V1.0
 */
public class ConvertUtil {
	
	public static ArticleBean articleToArticleBean(Article pArticle){
		
		ArticleBean mArticleBean = new ArticleBean();
		mArticleBean.setArticleNo(pArticle.getArticleNo());
		mArticleBean.setTitle(pArticle.getTitle());
		mArticleBean.setContent(pArticle.getContent());
		return mArticleBean;
	}
	
	public static Article articleBeanToArticle(ArticleBean pArticleBean){
		
		Article mArticle = new Article();
		mArticle.setArticleNo(pArticleBean.getArticleNo());
		mArticle.setTitle(pArticleBean.getTitle());
		mArticle.setContent(pArticleBean.getContent());
		return mArticle;
	}
}
