package com.hecj.search.solr.util;

import java.net.MalformedURLException;

import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;

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

	private static CommonsHttpSolrServer server = null;
	static {
		try {
			server = new CommonsHttpSolrServer(PropertiesUtil.getProperties().getProperty("SOLR_URL"));

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public static CommonsHttpSolrServer getServer() {
		return server;
	}
}
