package com.freedom.search.web.controller.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.freedom.search.solr.util.PropertiesUtil;
import com.freedom.search.util.FastjsonFilter;
import com.freedom.search.util.StringUtil;



/**
 * @类功能说明：base Controller
 * @类功能说明：
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-6 上午03:26:14
 * @版本：V1.0
 */
public abstract class BaseController implements Controller{
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		return null;
	}
	@Resource
	private HttpServletRequest request;
	
	@Resource
	private HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	/**
	 * @函数功能说明 生产环境basePath从配置文件中取
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-6
	 * @修改内容
	 * @参数： @return    
	 * @return String   
	 * throws
	 */
	protected String getBasePath(){
		
		String basePath = PropertiesUtil.getProperties().getProperty("basePath");
		if(StringUtil.isStrEmpty(basePath)){
			String path = request.getContextPath();
			basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		}
		return basePath ;
	}
	
	protected void writeToJSON(HttpServletResponse response,Object object) {
		writeToJSON(response, object, null);
	}
	
	protected void writeToJSON(HttpServletResponse response,Object object,String[] excludesProperties){
		writeToJSON(response,object,null,excludesProperties);
	}
	
	/**
	 * ajax返回信息
	 */
	protected void writeToJSON(HttpServletResponse response,Object object, String[] includesProperties, String[] excludesProperties){
		PrintWriter out = null ;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.write(writeJsonByFilter(object,includesProperties,excludesProperties));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	protected void write(HttpServletResponse response,String message){
		PrintWriter out = null ;
		response.setContentType("text/html;charset=UTF-8");
		try {
			out = response.getWriter();
			out.write(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(out != null){
				out.close();
			}
		}
	}
	
	protected String writeJsonByFilter(Object object, String[] includesProperties, String[] excludesProperties) {
		FastjsonFilter filter = new FastjsonFilter();// excludes优先于includes
		if (excludesProperties != null && excludesProperties.length > 0) {
			filter.getExcludes().addAll(Arrays.<String> asList(excludesProperties));
		}
		if (includesProperties != null && includesProperties.length > 0) {
			filter.getIncludes().addAll(Arrays.<String> asList(includesProperties));
		}
		String json;
		String User_Agent = getRequest().getHeader("User-Agent");
		if (StringUtils.indexOfIgnoreCase(User_Agent, "MSIE 6") > -1) {
			// 使用SerializerFeature.BrowserCompatible特性会把所有的中文都会序列化为\\uXXXX这种格式，字节数会多一些，但是能兼容IE6
			json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.BrowserCompatible);
		} else {
			// 使用SerializerFeature.WriteDateUseDateFormat特性来序列化日期格式的类型为yyyy-MM-dd hh24:mi:ss
			// 使用SerializerFeature.DisableCircularReferenceDetect特性关闭引用检测和生成
			json = JSON.toJSONString(object, filter, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
		}
		return json;
	}
	
}
