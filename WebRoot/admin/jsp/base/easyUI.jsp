<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/WEB-INF/jsp/base/basePath.jsp" %>    
<script type="text/javascript">
	var app = app || {};
	/*根目录*/
	app.contextPath = '<%=contextPath%>';
	app.basePath = '<%=basePath%>';
	app.staticPath = '<%=staticPath%>';
</script>
<meta http-equiv="content-Type" content="text/html;charset=utf-8"/>
<link id="easyUITheme" rel="stylesheet" type="text/css" href="<%=contextPath%>admin/easyui/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>admin/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>admin/css/common/common.css?v=1.0">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>admin/css/common/customIcon.css?v=1.0">
<script type="text/javascript" src="<%=contextPath%>admin/js/jquery/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>admin/easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>admin/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/jquery/jquery.json-2.2.js"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/extends/extendsUI.js?v=1.0"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/common/MessageUtil.js?v=1.0"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/common/StringUtils.js?v=1.0"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/common/ValidateboxUtil.js?v=1.0"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/common/AppEntity.js?v=1.0"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/common/common.js?v=1.0"></script>
<script type="text/javascript" src="<%=contextPath%>admin/js/extends/jQueryExtends.js?v=1.0"></script>
