<%@page import="com.freedom.search.solr.util.PropertiesUtil" %>
<%@page import="com.freedom.search.util.StringUtil" %>
<%
String basePath = PropertiesUtil.getProperties().getProperty("basePath");
String contextPath = request.getContextPath()+"/";
if(StringUtil.isStrEmpty(basePath)){
	basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath;
}


String staticPath = PropertiesUtil.getProperties().getProperty("staticPath");
if(StringUtil.isStrEmpty(staticPath)){
	staticPath = basePath;
}

%>