package com.freedom.search.services;

import java.util.List;
import java.util.Map;

import com.freedom.search.hibernate.entity.LaArticle;

/**
 * @类功能说明：文章业务类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 上午09:45:35
 * @版本：V1.0
 */
public interface ArticleService {
	
	/**
	 * @函数功能说明 根据文章ID查询
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-2
	 * @修改内容
	 * @参数： @param id
	 * @参数： @return    
	 * @return Article   
	 * @throws
	 */
	public LaArticle searchArticleById(String pArticleNo);
	
	/**
	 * @函数功能说明 添加
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-3
	 * @修改内容
	 * @参数： @param article    
	 * @return void   
	 * @throws
	 */
	public void addArticle(LaArticle pArticle);
	
	/**
	 * @函数功能说明 添加
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-3
	 * @修改内容
	 * @参数： @param article    
	 * @return void   
	 * @throws
	 */
	public void addArticle(List<LaArticle> pArticles);
	
	/**
	 * @函数功能说明 根据文章Id删除
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-5
	 * @修改内容
	 * @参数： @param pArticleNo    
	 * @return void   
	 * throws
	 */
	public void deleteArticle(String pArticleNo);
	
	/**
	 * @函数功能说明 分页查询文章集合
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-5
	 * @修改内容
	 * @参数： @param pParams{pagination:Pagination}<br>
	 * @参数： @return{rArticleList:List<Article/>,pPagination:Pagination}    
	 * @return Map<String,Object>   
	 * throws
	 */
	public Map<String,Object> searchArticleList(Map<String,Object> pParams);
	
	/**
	 * @函数功能说明 从solr中查询文章集合
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-8
	 * @修改内容
	 * @参数： @param pParams
	 * @参数： @return    
	 * @return Map<String,Object>   
	 * throws
	 */
	public Map<String,Object> searchArticleListBySolr(Map<String,Object> pParams);
}
