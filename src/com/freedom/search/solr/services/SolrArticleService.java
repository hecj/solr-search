package com.freedom.search.solr.services;

import java.util.List;

import com.freedom.search.solr.bean.ArticleBean;
/**
 * @类功能说明：文章索引业务类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 上午01:16:21
 * @版本：V1.0
 */
public interface SolrArticleService {
	
	/**
	 * @函数功能说明 添加索引
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-8
	 * @修改内容
	 * @参数： @param pArticleBean    
	 * @return void   
	 * throws
	 */
	public void addArticleBeanIndex(ArticleBean pArticleBean);
	
	/**
	 * @函数功能说明 在索引中分页查询文章
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-8
	 * @修改内容
	 * @参数： @param queryString
	 * @参数： @param start
	 * @参数： @param rows
	 * @参数： @return    
	 * @return List<Object>   
	 * throws
	 */
	public List<Object> queryArticleBeanList(String queryString,int start,int rows);
}
