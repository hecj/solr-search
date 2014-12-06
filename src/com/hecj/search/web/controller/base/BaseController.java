package com.hecj.search.web.controller.base;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class BaseController {
	
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
	 * @函数功能说明 网站地址
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-6
	 * @修改内容
	 * @参数： @return    
	 * @return String   
	 * throws
	 */
	protected String getBasePath(){
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath ;
	}

}
