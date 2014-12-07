package com.hecj.search.solr.bean;

import org.apache.solr.client.solrj.beans.Field;
/**
 * @类功能说明:Solr对应的文章Bean
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 上午01:15:03
 * @版本：V1.0
 */
public class ArticleBean {

	public String articleNo;
	public String title;
	public String content;

	public ArticleBean() {
		super();
	}

	public String getArticleNo() {
		return articleNo;
	}

	@Field("id")
	public void setArticleNo(String articleNo) {
		this.articleNo = articleNo;
	}


	public String getTitle() {
		return title;
	}

	@Field("article_title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	@Field("article_content")
	public void setContent(String content) {
		this.content = content;
	}

}
