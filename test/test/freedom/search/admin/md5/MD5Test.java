package test.freedom.search.admin.md5;

import org.junit.Test;

import com.freedom.search.util.MD5;

public class MD5Test {
	
	@Test
	public void test01(){
		System.out.println(MD5.md5crypt("hecj"));
	}
}
