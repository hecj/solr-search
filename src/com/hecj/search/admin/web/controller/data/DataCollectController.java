package com.hecj.search.admin.web.controller.data;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hecj.search.util.CodeConvertUtil;
import com.hecj.search.util.Log4jUtil;
import com.hecj.search.web.controller.base.BaseController;

@Controller
@RequestMapping("admin/data/dataCollect.htm")
public class DataCollectController extends BaseController{
	
	
	@ResponseBody
	@RequestMapping(params="operator=submitDataCollect")
	public String submitDataCollect(String data){
		data = CodeConvertUtil.decode(data);
		Log4jUtil.log(data);
		JSONObject jsonObj = JSONObject.fromObject(data);
		String baseURL = jsonObj.getString("baseURL");
		String pageParams = jsonObj.getString("pageParams");
		String start = jsonObj.getString("start");
		String end = jsonObj.getString("end");
		String step = jsonObj.getString("step");
		String baseSelect = jsonObj.getString("baseSelect");
		String dataBaseType = jsonObj.getString("dataBaseType");
		System.out.println(baseURL);
		System.out.println(pageParams);
		System.out.println(start);
		System.out.println(end);
		System.out.println(step);
		System.out.println(baseSelect);
		System.out.println(dataBaseType);
		
		//return
		JSONObject reJson = new JSONObject();
		reJson.put("message", "success");
		return reJson.toString();
	}	
}
