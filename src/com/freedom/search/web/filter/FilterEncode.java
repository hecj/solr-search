package com.freedom.search.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * @类功能说明：编码过滤器
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 下午03:41:44
 * @版本：V1.0
 */
public class FilterEncode implements Filter {
	
	private String mEncode ;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		mEncode = filterConfig.getInitParameter("encode");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		filterChain.doFilter(requestWrapper((HttpServletRequest)servletRequest,mEncode), servletResponse);
	}

	@Override
	public void destroy() {

	}
	
	private HttpServletRequest requestWrapper(final HttpServletRequest request, final String encode) {
		return new HttpServletRequestWrapper(request) {
			
			@Override
			public String getParameter(String name) {
				return encode(super.getParameter(name));
			}

			@Override
			public String[] getParameterValues(String name) {
				String[] values=super.getParameterValues(name);
				if(values==null) return null;
				
				for (int i = 0; i < values.length; i++) {
					values[i]=encode(values[i]);
				}
				return values;
			}
			
			String encode(String s) {
				try {
					return new String(s.getBytes("ISO-8859-1"), encode);
				} catch (Exception e) {
				}
				return s;
			}
		};
	}
}
