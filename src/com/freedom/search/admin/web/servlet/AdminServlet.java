package com.freedom.search.admin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @类功能说明：通过此Servlet进入后台管理页面
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-6 上午12:01:31
 * @版本：V1.0
 */
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// private ArticleService articleService = null ;

	public AdminServlet() {
		super();
	}

	public void init() throws ServletException {


		// WebApplicationContext wac =
		// WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		// articleService = (ArticleService) wac.getBean("articleService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("admin/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void destroy() {
		super.destroy();
	}
}
