<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/base/basePath.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>名人名言</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/home.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/util/StringUtils.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/main/index.js"></script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>
		<div id="main">
			<div id="position">
				当前位置：首页->名人名言
			</div>
			
		</div>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
	</body>
</html>