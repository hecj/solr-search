package com.hecj.search.services.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hecj.search.hibernate.dao.ArticleDAO;
import com.hecj.search.hibernate.dao.AttachmentDAO;
import com.hecj.search.hibernate.dao.TempIndexDAO;
import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.hibernate.entity.TempIndex;
import com.hecj.search.hibernate.util.UUIDUtil;
import com.hecj.search.senum.EnumUtils;
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
	
	@Autowired
	private ArticleDAO articleDAO ;
	
	@Autowired
	private AttachmentDAO attachmentDAO ;
	
	@Autowired
	private SolrArticleService solrArticleService ;
	
	@Autowired
	private TempIndexDAO tempIndexDAO ;
	
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	public void setSolrArticleService(SolrArticleService solrArticleService) {
		this.solrArticleService = solrArticleService;
	}
	
	public void setTempIndexDAO(TempIndexDAO tempIndexDAO) {
		this.tempIndexDAO = tempIndexDAO;
	}

	@Override
	public Article searchArticleById(String articleNo) {
		return articleDAO.findById(articleNo);
	}
	@Override
	public void addArticle(Article article) {
		//添加文章对象到数据库
		articleDAO.save(article);
		//添加文章索引到临时索引表
		TempIndex mTempIndex = new TempIndex();
		mTempIndex.setId(UUIDUtil.autoUUID());
		mTempIndex.setObjectId(article.getArticleNo());
		mTempIndex.setObjectType(EnumUtils.ObjectType.Article.toString());
		mTempIndex.setOperatorType(EnumUtils.OperatorType.ADD.toString());
//		tempIndexDAO.save(mTempIndex);
		//添加文章索引到内存
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
			
			List<Article> rArticleList = articleDAO.queryListByPagination(mQueryHql,pPagination.startCursor().intValue(), pPagination.getPageSize());
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

			List<Object> rList = solrArticleService.queryArticleBeanList(queryString, pPagination.startCursor().intValue(), pPagination.getPageSize());
			
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
