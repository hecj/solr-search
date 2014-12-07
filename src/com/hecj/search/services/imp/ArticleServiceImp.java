package com.hecj.search.services.imp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hecj.search.hibernate.dao.ArticleDAO;
import com.hecj.search.hibernate.dao.AttachmentDAO;
import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.services.ArticleService;
import com.hecj.search.solr.bean.ArticleBean;
import com.hecj.search.solr.services.SolrArticleService;
import com.hecj.search.solr.util.ConvertUtil;
import com.hecj.search.util.Pagination;
/**
 * @类功能说明：文章业务实现类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 上午09:46:00
 * @版本：V1.0
 */
@Service("articleService")
@Transactional
public class ArticleServiceImp implements ArticleService{
	
	@Resource
	private ArticleDAO articleDAO ;
	
	@Resource
	private AttachmentDAO attachmentDAO ;
	
	@Resource
	private SolrArticleService solrArticleService ;
	
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public void setSolrArticleService(SolrArticleService solrArticleService) {
		this.solrArticleService = solrArticleService;
	}

	@Override
	public Article searchArticleById(String articleNo) {
		return articleDAO.findById(articleNo);
	}
	@Override
	public void addArticle(Article article) {
		
		articleDAO.save(article);
		solrArticleService.addArticleBeanIndex(ConvertUtil.articleToArticleBean(article));
	}
	
	@Override
	public void addArticle(List<Article> pArticles) {
		
		for(Article mArticle : pArticles){
			articleDAO.save(mArticle);
		}
	}

	@Override
	public void deleteArticle(String pArticleNo) {
		
		try{
			
			Article article = articleDAO.findById(pArticleNo);
			articleDAO.delete(article);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> searchArticleList(Map<String, Object> pParams) {
		Map<String, Object> mMap = new HashMap<String, Object>();

		try {
			Pagination pPagination = (Pagination) pParams.get("pagination");

			String mQueryHql = "from Article a";
			String mCountHql = "select count(a) from Article a ";
			
			List<Article> rArticleList = articleDAO.queryListByPagination(mQueryHql,(int) pPagination.startCursor(), pPagination.getPageSize());
			long mCountSize = Long.parseLong( articleDAO.queryUniqueResultByHQL(mCountHql).toString());
			
			pPagination.setCountSize(mCountSize);
			
			mMap.put("rArticleList", rArticleList);
			mMap.put("pPagination", pPagination);
		} catch (Exception mException) {
			mException.printStackTrace();
		}
		return mMap;
	}

	@Override
	public Map<String, Object> searchArticleListBySolr(Map<String, Object> pParams) {
		
		Map<String, Object> mMap = new HashMap<String, Object>();
		List<Article> maArticels = new ArrayList<Article>();
		try {
			Pagination pPagination = (Pagination) pParams.get("pagination");
			String queryString = (String) pParams.get("queryString");

			List<Object> rList = solrArticleService.queryArticleBeanList(queryString, (int)pPagination.startCursor(), pPagination.getPageSize());
			
			List<ArticleBean> mArticleBeans = (List<ArticleBean>) rList.get(0);
			for(ArticleBean mArticleBean : mArticleBeans){
				maArticels.add(ConvertUtil.articleBeanToArticle(mArticleBean));
			}
			
			long countSize = (Long) rList.get(1);
			pPagination.setCountSize(countSize);

			mMap.put("rArticleList", maArticels);
			mMap.put("pPagination", pPagination);
		} catch (Exception mException) {
			mException.printStackTrace();
		}
		return mMap;
	}


}
