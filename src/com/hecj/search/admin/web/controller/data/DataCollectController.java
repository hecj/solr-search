package com.hecj.search.admin.web.controller.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hecj.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/data/dataCollect.htm")
public class DataCollectController extends BaseController{
	
	@ResponseBody
	@RequestMapping(params="operator=submitDataCollect")
	public String submitDataCollect(String data){
		System.out.println(data+"======");
		
		return "";
	}
	
}
