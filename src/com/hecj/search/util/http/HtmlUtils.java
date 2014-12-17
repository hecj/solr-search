package com.hecj.search.util.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.hecj.search.util.StringUtil;
/**
 * @类功能说明：HTML简单处理工具类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-14 下午11:07:06
 * @版本：V1.0
 */
public class HtmlUtils {

	/**
	 * 得到HTML内容(含tag)
	 */
	public static String getHtmlContentByHttpClient(String URL, String encode) {
		return getHtmlContentByHttpClientProxy(URL, null, null, encode);
	}
	/**
	 * 得到HTML内容(含tag)
	 */
	public static String getHtmlContentByHttpClient(String URL) {
		return getHtmlContentByHttpClient(URL, null);
	}
	/**
	 * 得到HTML内容(含tag)
	 */
	public static String getHtmlContentByConn(String url) {
		return getHtmlContentByConn(url, null);
	}
	/**
	 * 得到HTML内容(含tag)
	 */
	public static String getHtmlContentByConn(String url, String encode) {
		URL URL = null;
		HttpURLConnection conn = null;
		InputStream in = null;
		BufferedReader br = null;
		String html = "";
		try {
			URL = new URL(url);
			conn = (HttpURLConnection) URL.openConnection();
			conn.setRequestMethod("GET");
			in = conn.getInputStream();
			if(!StringUtil.isStrEmpty(encode)){
				br = new BufferedReader(new InputStreamReader(in, encode));
			}else{
				br = new BufferedReader(new InputStreamReader(in));
			}
			
			String data = "";
			while ((data = br.readLine()) != null) {
				html += data + "\n";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (in != null)
					in.close();
				conn.disconnect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return html;
	}
	
	/**
	 * 代理方式一
	 */
	public static String getHtmlContentByHttpClientProxy(String URL,String IP,Integer PORT) {
		return getHtmlContentByHttpClientProxy(URL,IP,PORT,null);
	}
	/**
	 * 代理方式一
	 */
	public static String getHtmlContentByHttpClientProxy(String URL,String IP,Integer PORT,String encode) {
		
		HttpClient httpClient = new DefaultHttpClient();
		if(!StringUtil.isStrEmpty(IP)){
			/*
			 * 代理服务器
			 */
			HttpHost proxy = new HttpHost(IP, PORT);
			/*
			 * 设置代理
			 */
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		String content = "";
		try {
			HttpGet httpGet = new HttpGet(URL);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity);
				/*
				 * 转码
				 */
				if(!StringUtil.isStrEmpty(encode)){
					content = new String(content.getBytes("ISO-8859-1"), encode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return content;
	}
	/**
	 * 代理方式二
	 */
	public static String getHtmlContentByHttpClientProxy2(String URL,String IP,int PORT) {
		return getHtmlContentByHttpClientProxy2(URL,IP,PORT,null);
	}
	/**
	 * 代理方式二
	 */
	public static String getHtmlContentByHttpClientProxy2(String URL,String IP,int PORT,String encode) {
		/*
		 * 代理服务器
		 */
		HttpHost proxy = new HttpHost(IP, PORT);
		/*
		 * 设置代理
		 */
		HttpParams params = new BasicHttpParams();
		params.setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		HttpClient httpClient = new DefaultHttpClient(params);
		String content = "";
		try {
			HttpGet httpGet = new HttpGet(URL);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity);
				/*
				 * 转码
				 */
				if(!StringUtil.isStrEmpty(encode)){
					content = new String(content.getBytes("ISO-8859-1"), encode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return content;
	}
}
