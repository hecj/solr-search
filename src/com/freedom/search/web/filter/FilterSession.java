package com.freedom.search.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.freedom.search.admin.vo.UserContext;

/**
 * @类功能说明：用户过滤器
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 下午03:34:48
 * @版本：V1.0
 */
public class FilterSession implements Filter {

	/**
	 * session超时页面
	 */
	private String sessionPage = null;
	
	/**
	 * 请求方法
	 */
	private String OPERATOR = "operator";

	/**
	 * 不过滤session页面
	 */
	private List<String> noFiltersRegex = new ArrayList<String>();
	
	/**
	 * 过滤session页面
	 */
	private List<String> filtersRegex = new ArrayList<String>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		sessionPage = filterConfig.getInitParameter("sessionPage");
		
		//不过滤
		noFiltersRegex.add(".*user\\.htm\\?operator=login.*");
		noFiltersRegex.add(".*tree\\.htm\\?operator=init.*");
		noFiltersRegex.add(".*login\\.jsp.*");
		noFiltersRegex.add(".*/admin$");

		//过滤
		filtersRegex.add(".*\\.htm\\?operator=.*");
		filtersRegex.add(".*\\.jsp.*");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		//拼接请求的URI
		String URI = request.getRequestURI();
		String operatorName = request.getParameter(OPERATOR);
		if(operatorName != null){
			URI +="?"+OPERATOR+"="+operatorName;
		}
		
		UserContext context = (UserContext) request.getSession().getAttribute(UserContext.SESSION_KEY);
		if(validateSessionTimeOut(URI) && context == null){
			System.out.println("会话超时："+URI+"--"+request.getHeader("x-requested-with"));
			String contextPath=request.getContextPath();
			//AJAX请求
			if(request.getHeader("x-requested-with") !=null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
				//给个状态码
				response.setStatus(999);
				return;
			}else{
				//普通请求，session失效,跳转到首页
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script language='javascript'>top.location.href='"+contextPath+sessionPage+"'</script>");
				out.flush();
				out.close();
				return;
			}
			
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	/**
	 * 判断是否需要校验Session超时
	 * true：校验,false:不校验
	 */
	private boolean validateSessionTimeOut(String URI){
		
		//不过滤Session
		for(String regex : noFiltersRegex){
			if(Pattern.matches(regex, URI)){
				return false;
			}
		}

		//过滤Session
		for(String regex : filtersRegex){
			if(Pattern.matches(regex, URI)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void destroy() {

	}
}
