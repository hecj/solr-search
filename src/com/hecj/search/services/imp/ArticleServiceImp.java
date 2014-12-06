package com.hecj.search.services.imp;


import java.io.File;
import java.io.IOException;
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
	
	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	public void setAttachmentDAO(AttachmentDAO attachmentDAO) {
		this.attachmentDAO = attachmentDAO;
	}

	@Override
	public Article searchArticleById(String articleNo) {
		return articleDAO.findById(articleNo);
	}
	@Override
	public void addArticle(Article article) {
		
		articleDAO.save(article);
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


}
