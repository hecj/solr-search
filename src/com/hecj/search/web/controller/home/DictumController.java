package com.hecj.search.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hecj.search.web.controller.base.BaseController;

@Controller
@RequestMapping("home/dictum.htm")
public class DictumController extends BaseController{

	@RequestMapping(params="operator=init")
	public String init(){
		
		return "WEB-INF/jsp/home/dictum";
	}
}
