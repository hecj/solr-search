package com.hecj.search.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
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
		HttpClient httpClient = new DefaultHttpClient();
		String content = "";
		try {
			HttpGet httpGet = new HttpGet(URL);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity);
				content = new String(content.getBytes("ISO-8859-1"), encode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return content;
	}
	/**
	 * 得到HTML内容(含tag)
	 */
	public static String getHtmlContentByHttpClient(String URL) {
		HttpClient httpClient = new DefaultHttpClient();
		String content = "";
		try {
			HttpGet httpGet = new HttpGet(URL);
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return content;
	}

	/**
	 * 得到HTML内容(含tag)
	 */
	public String getHtmlContentByConn(String pURL, String encode) {
		URL URL = null;
		HttpURLConnection conn = null;
		InputStream in = null;
		BufferedReader br = null;
		String html = "";
		try {
			URL = new URL(pURL);
			conn = (HttpURLConnection) URL.openConnection();
			conn.setRequestMethod("GET");
			in = conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(in, encode));
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
}
