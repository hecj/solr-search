package com.hecj.search.services.imp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.solr.client.solrj.SolrServerException;

import org.springframework.stereotype.Service;

import com.hecj.search.hibernate.dao.ArticleDAO;
import com.hecj.search.hibernate.dao.TempIndexDAO;
import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.senum.EnumUtils;
import com.hecj.search.services.ArticleService;
import com.hecj.search.services.TempIndexService;
import com.hecj.search.solr.services.SolrArticleService;
import com.hecj.search.solr.util.ConvertUtil;
import com.hecj.search.solr.util.PropertiesUtil;
import com.hecj.search.solr.util.SolrServerUtil;
import com.hecj.search.util.Log4jUtil;
import com.hecj.search.util.Pagination;
import com.hecj.search.util.StringUtil;

@Service("tempIndexService")
@Transactional
public class TempIndexServiceImp implements TempIndexService {
	
	@Resource
	private TempIndexDAO tempIndexDAO ;
	
	@Resource
	private ArticleService articleService;
	
	@Resource
	private ArticleDAO articleDAO;
	
	@Resource
	private SolrArticleService solrArticleService;
	
	public void setTempIndexDAO(TempIndexDAO tempIndexDAO) {
		this.tempIndexDAO = tempIndexDAO;
	}
	
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public void setSolrArticleService(SolrArticleService solrArticleService) {
		this.solrArticleService = solrArticleService;
	}
	

	public void setArticleDAO(ArticleDAO articleDAO) {
		this.articleDAO = articleDAO;
	}

	@Override
	public void commitTempIndexSerivice() {
		
		
		String mtempIndexCount = "select count(t) from TempIndex t";
		try{
			
			long mCountSize = Long.parseLong(tempIndexDAO.queryUniqueResultByHQL(mtempIndexCount).toString());
			/*
			 * 达到多少条上线后提交数据
			 */
			String commitCountStr = PropertiesUtil.getProperties().getProperty("COMMIT_COUNT");
			/*
			 * 若配置文件没有配置,默认10条数据提交
			 */
			int commitCount = 10;
			if(!StringUtil.isStrEmpty(commitCountStr)){
				commitCount = Integer.parseInt(commitCountStr);
			}
			/*
			 * 提交内存索引数据
			 */
			if(mCountSize >= commitCount){
				Log4jUtil.log("start ...");
				SolrServerUtil.getServer().commit();
				int deleteCount = tempIndexDAO.executeHQL("delete from TempIndex t");
				Log4jUtil.log("本次任务共提交索引个数:"+deleteCount);
				Log4jUtil.log("end ...");
			}else{
				
				Log4jUtil.log("本次任务扫描临时索引个数："+mCountSize+",提交峰值为："+commitCount);
			}
			
		}catch(Exception mException){
			
			mException.printStackTrace();
		}
	}

	@Override
	public void refactorIndexService() {
		
		Log4jUtil.log("start ...");

		try {
			Map<String, Object> mArticleMap = new HashMap<String, Object>();
			Pagination mPagination = new Pagination();
			mPagination.setPageSize(1000);
			mArticleMap.put("pagination", mPagination);
			Map<String,Object> rMap = articleService.searchArticleList(mArticleMap);
			SolrServerUtil.getServer().deleteByQuery("*:*");
			
			List<Article> rArticles = (List<Article>) rMap.get("rArticleList");
			for(Article a : rArticles){
				solrArticleService.addArticleBeanIndex(ConvertUtil.articleToArticleBean(a));
			}
			SolrServerUtil.getServer().commit();
			Log4jUtil.log("重构索引个数："+rArticles.size());
			Log4jUtil.log("success ...");

		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void recoverTempIndexService() {
		
		Log4jUtil.log(" 恢复服务器启动前未提交的索引 start ...");
		try{
			/*
			 * 恢复上次的文章索引
			 */
			String mQueryArticleHQL = "select a from Article a where a.articleNo in (select t.objectId from TempIndex t where t.objectType= '"+EnumUtils.ObjectType.Article.toString()+"') ";
			List<Article> rArticleList = articleDAO.queryListByPagination(mQueryArticleHQL,10000);
			for(Article mArticle : rArticleList){
				solrArticleService.addArticleBeanIndex(ConvertUtil.articleToArticleBean(mArticle));
			}
			SolrServerUtil.getServer().commit();
			String mDeleteArticleHQL = "delete from Article a where a.articleNo in (select t.objectId from TempIndex t where t.objectType= '"+EnumUtils.ObjectType.Article.toString()+"') ";
			tempIndexDAO.executeHQL(mDeleteArticleHQL);
			Log4jUtil.log("恢复索引个数:"+rArticleList.size()+" success ...");
		} catch(Exception mException){
			Log4jUtil.error("恢复服务器启动前未提交的索引 fail ...");
			mException.printStackTrace();
		}
		Log4jUtil.log("恢复服务器启动前未提交的索引 end ...");
	}


}
