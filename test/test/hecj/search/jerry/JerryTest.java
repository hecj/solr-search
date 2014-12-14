package test.hecj.search.jerry;

import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;

import org.junit.Test;

import com.hecj.search.util.HtmlUtils;
import com.hecj.search.util.PattenUtils;

public class JerryTest {

	@Test
	public void test01(){
		String url = "http://www.haha365.com/mrmy/index_51.htm";
		Jerry doc = null;
		try {
			String content = HtmlUtils.getHtmlContentByHttpClient(url, "gbk");
//			System.out.println(content);
			doc = Jerry.jerry(content);
			// 判断页面是否合法
			if (doc != null && doc.size() > 0 && doc.html() != null
					&& !doc.html().equals("")) {
				doc.$("div#main div.left ul.text_doublelist1 a.catname").each(new JerryFunction() {
					public boolean onNode(Jerry $this,int index) {
						String str = PattenUtils.pattenUniqueContent($this.text(), "^.+$");
						System.out.println(str.replaceAll("\\[", "").replaceAll("\\]", ""));
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
}
