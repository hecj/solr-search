package com.freedom.search.mvc.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import test.freedom.search.entity.User;

import com.freedom.search.web.controller.base.BaseController;

@Controller
@RequestMapping("test/test")
public class TestController extends BaseController {
	
	@RequestMapping(params="m=login")
	@ResponseBody
	public String login(HttpServletRequest request,HttpServletResponse response){
		return "login success";
	}
	
	/** 
     * path路径传参
     * eg:http://localhost:8080/solr-search/test/test/porfile/238
     */ 
    @RequestMapping(value = "/porfile/{usercode}", method = RequestMethod.GET)  
    @ResponseBody
    public String porfile(@PathVariable String usercode) {
        return usercode;  
    } 
    
    /** 
     * path路径传参
     */ 
    @RequestMapping(value = "/porfile/{usercode}/{username}", method = RequestMethod.GET)  
    @ResponseBody
    public String porfile2(@PathVariable String usercode,@PathVariable String username) {
    	
        return username;  
    } 
	
    /** 
     * 登录 
     * http://localhost:8080/solr-search/test/test/login/
     * @param Uuser 
     * @return 
     */  
    @RequestMapping(value = "/login/{name}", method = RequestMethod.GET) 
    @ResponseBody
    public String login(@PathVariable String name) {  
    	System.out.println("into..."+name);
    	return name;
    }
	
    public void init(){
    	System.out.println("void com.freedom.search.mvc.controller.TestController.init()我初始化了");
    }
    
    
    
}
