package com.freedom.search.services.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.hibernate.dao.ArticleDAO;
import com.freedom.search.hibernate.dao.AttachmentDAO;
import com.freedom.search.hibernate.dao.TempIndexDAO;
import com.freedom.search.hibernate.entity.LaArticle;
import com.freedom.search.hibernate.entity.LaTempIndex;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.senum.EnumUtils;
import com.freedom.search.services.ArticleService;
import com.freedom.search.solr.bean.ArticleBean;
import com.freedom.search.solr.services.SolrArticleService;
import com.freedom.search.solr.util.ConvertUtil;
import com.freedom.search.util.Pagination;
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
	
	@Resource
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
	public LaArticle searchArticleById(String articleNo) {
		return articleDAO.findById(articleNo);
	}
	@Override
	public void addArticle(LaArticle article) {
		//添加文章对象到数据库
		articleDAO.save(article);
		//添加文章索引到临时索引表
		LaTempIndex mTempIndex = new LaTempIndex();
		mTempIndex.setId(UUIDUtil.autoUUID());
		mTempIndex.setObjectId(article.getArticleNo());
		mTempIndex.setObjectType(EnumUtils.ObjectType.Article.toString());
		mTempIndex.setOperatorType(EnumUtils.OperatorType.ADD.toString());
//		tempIndexDAO.save(mTempIndex);
		//添加文章索引到内存
		solrArticleService.addArticleBeanIndex(ConvertUtil.articleToArticleBean(article));
	}
	
	@Override
	public void addArticle(List<LaArticle> pArticles) {
		
		for(LaArticle mArticle : pArticles){
			articleDAO.save(mArticle);
		}
	}

	@Override
	public void deleteArticle(String pArticleNo) {
		
		try{
			
			LaArticle article = articleDAO.findById(pArticleNo);
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
			
			List<LaArticle> rArticleList = articleDAO.queryListByPagination(mQueryHql,pPagination.startCursor().intValue(), pPagination.getPageSize());
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
		List<LaArticle> maArticels = new ArrayList<LaArticle>();
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
