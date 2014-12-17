package test.hecj.search.jerry;

import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;

import org.junit.Test;

import com.hecj.search.util.PattenUtils;
import com.hecj.search.util.http.HtmlUtils;

public class JerryTest {

	@Test
	public void test01(){
		String url = "http://www.haha365.com/mrmy/";
		String IP = "10.8.2.4";
		int PORT = 80;
		String encode = "GBK";
		Jerry doc = null;
		try {
			String content = HtmlUtils.getHtmlContentByHttpClientProxy(url, IP, PORT,encode);
//			System.out.println(content);
			doc = Jerry.jerry(content);
			// 判断页面是否合法
			if (doc != null && doc.size() > 0 && doc.html() != null
					&& !doc.html().equals("")) {
				doc.$("div#main ul.text_doublelist1 li").each(new JerryFunction() {
					public boolean onNode(Jerry $this,int index) {
//						String str = PattenUtils.pattenUniqueContent($this.text(), "^.+$");
//						System.out.println(str.replaceAll("\\[", "").replaceAll("\\]", ""));
						String href = $this.find("a[target=_blank]").attr("href").toString();
						System.out.println(href);
						return true;
					}
				});
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			doc = null;
			System.gc();
		}
	}
	
	@Test
	public void test02(){
		
		String url = "http://www.haha365.com/mrmy/";
		String IP = "10.8.2.4";
		int PORT = 80;
		String encode = "GBK";
//		String content = HtmlUtils.getHtmlContentByConn(url);
//		String content = HtmlUtils.getHtmlContentByHttpClient(url);
//		String content = HtmlUtils.getHtmlContentByHttpClientProxy(url, IP, PORT,encode);
		String content = HtmlUtils.getHtmlContentByHttpClientProxy(url, IP, PORT,encode);
		
		System.out.println(content);
	}
	
	
}
