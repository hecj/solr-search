package test.freedom.search.admin.test;

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
}
