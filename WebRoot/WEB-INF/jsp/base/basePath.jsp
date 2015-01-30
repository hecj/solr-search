<%@page import="com.freedom.search.solr.util.PropertiesUtil" %>
<%@page import="com.freedom.search.util.StringUtil" %>
<%
String basePath = PropertiesUtil.getProperties().getProperty("basePath");
if(StringUtil.isStrEmpty(basePath)){
	String path = request.getContextPath();
	basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
}
%>