package com.freedom.search.solr.util;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * @类功能说明：简单封装SolrServer
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 上午01:21:40
 * @版本：V1.0
 */
public class SolrServerUtil {

	private static HttpSolrServer server = null;
	static {
		server = new HttpSolrServer(PropertiesUtil.getProperties().getProperty("SOLR_URL"));

	}

	public static HttpSolrServer getServer() {
		return server;
	}
}
