package test.freedom.search.admin.md5;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.freedom.search.util.MD5;

public class MD5Test {
	
	@Test
	public void test01(){
		System.out.println(MD5.md5crypt("hecj"));
	}
	
	@Test
	public void test02(){
		int endDay = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		if(((year+1)%4==0&&(year+1)%100!=0)||((year+1)%400==0)){//起保期下一年若为润年
			//获取月份
			int month = calendar.get(Calendar.MONTH)+1;
			if(month>=3){
				endDay +=1;
			}
		}else if((year%4==0&&year%100!=0)||(year%400==0)){//起保期当年若为润年
			//获取月份和日期
			int month = calendar.get(Calendar.MONTH)+1;
			if(month<=2){
				endDay +=1;
			} 
		}
		
		
	}
}
