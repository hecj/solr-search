package com.hecj.search.solr.services.imp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Repository;

import com.hecj.search.solr.bean.ArticleBean;
import com.hecj.search.solr.services.SolrArticleService;
import com.hecj.search.solr.util.SolrServerUtil;

@Repository("solrArticleService")
public class SolrArticleServiceImp implements SolrArticleService {

	@Override
	public void addArticleBeanIndex(ArticleBean pArticleBean) {
		
		System.out.println("SolrArticleServiceImp.addArticleBeanIndex:"+pArticleBean.getArticleNo());
		try {
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", pArticleBean.getArticleNo());
			document.addField("article_title", pArticleBean.getTitle());
			document.addField("article_content", pArticleBean.getContent());
			SolrServerUtil.getServer().add(document);
			SolrServerUtil.getServer().commit();
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("SolrArticleServiceImp.addArticleBeanIndex:"+pArticleBean.getArticleNo());
		} catch (SolrServerException e) {
			e.printStackTrace();
			System.out.println("SolrArticleServiceImp.addArticleBeanIndex:"+pArticleBean.getArticleNo());
		} catch (IOException e) {
			System.out.println("SolrArticleServiceImp.addArticleBeanIndex:"+pArticleBean.getArticleNo());
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Object> queryArticleBeanList(String queryString, int start,
			int rows) {
		System.out.println("SolrArticleServiceImp.searchArticleBeanList:"+queryString);
		SolrQuery query = new SolrQuery("article_all:"+queryString);
		
		List<Object> mList = new ArrayList<Object>();
		List<ArticleBean> mArticleBeanList = new ArrayList<ArticleBean>();
		
		try {
			//分页查询
			query.setStart(start);//多少条开始
			query.setRows(rows); //每页多少条
			QueryResponse response = SolrServerUtil.getServer().query(query);
			SolrDocumentList documentList = response.getResults();
			long countSize = documentList.getNumFound();
			for(SolrDocument doc :documentList){
				ArticleBean mArticleBean = new ArticleBean();
				mArticleBean.setArticleNo((String)doc.getFieldValue("id"));
				mArticleBean.setTitle((String)doc.getFieldValue("article_title"));
				mArticleBean.setContent((String)doc.getFieldValue("article_content"));
				mArticleBeanList.add(mArticleBean);
			}
			
			mList.add(0,mArticleBeanList);
			mList.add(1,countSize);
			
		} catch (SolrServerException e) {
			e.printStackTrace();
			mList.clear();
			mList.add(0,mArticleBeanList);
			mList.add(1,0l);
		}
		return mList;
	}

}
