package test.freedom.search.admin.test;

import java.io.File;
import java.util.regex.Pattern;

import org.junit.Test;

import com.alibaba.druid.util.PatternMatcher;

public class TestMain {
	
	@Test
	public void test01(){
		
		String ids = ",,10,,01,";
		ids = ids.replaceAll(",,", ",").replaceAll(",,,", ",");
		if(ids.startsWith(",")){
			ids = ids.replaceFirst(",", "");
		}
		if(ids.endsWith(",")){
			ids = ids.substring(0, ids.length()-1);
		}
		System.out.println(ids);
	}
	
	@Test
	public void test02(){
		File f = new File("D:/workspace/xinhua/apache-tomcat-6.0.33/webapps/solr-search/uplpad/imageHead/14231505427374136232.jpg");
		System.out.println(f.isFile());
		System.out.println(f.delete());
	}
	
	@Test
	public void test03(){
		
		
		System.out.println(Pattern.matches
				(".*\\.htm\\?operator=login.*",
				"/solr-search/admin/tree/tree.htm?operator=initTree"));
	}
}
