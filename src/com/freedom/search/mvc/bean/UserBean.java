package com.freedom.search.mvc.bean;

public class UserBean {
	
	
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void init(){
		System.out.println(name);
		System.out.println("void com.freedom.search.mvc.bean.UserBean.init()初始化了");
	}
}
