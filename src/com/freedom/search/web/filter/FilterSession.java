package com.freedom.search.web.filter;

import java.io.IOException;
import java.io.PrintWriter;

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

	String sessionPage = null;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		sessionPage = filterConfig.getInitParameter("sessionPage");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		
		String uri = request.getRequestURI();
		String operator = request.getParameter("operator");
		//登陆不限制
		if(uri.contains("user.htm")&&operator.equals("login")){
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}
		
		System.out.println(uri);
		System.out.println(operator);
		UserContext context = (UserContext) request.getSession().getAttribute(UserContext.SESSION_KEY);
		System.out.println("===="+context);
		if(context == null){
			String contextPath=request.getContextPath();
			response.sendRedirect(contextPath+sessionPage);
			return;
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {

	}
}
