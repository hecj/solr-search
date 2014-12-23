package test.freedom.spring.aop;

import org.springframework.stereotype.Repository;

@Repository("myBean")
public class MyBean {
	
	public void init(){
		System.out.println("初始化...");
	}
}
