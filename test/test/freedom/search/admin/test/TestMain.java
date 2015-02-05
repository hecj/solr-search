package test.freedom.search.admin.test;

import java.io.File;

import org.junit.Test;

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
}
