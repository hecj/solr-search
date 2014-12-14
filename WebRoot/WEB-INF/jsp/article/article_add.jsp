<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/basePath.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加文章</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/home.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
	<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>
	<form action="<%=basePath %>article/article.htm?operator=submitAddArticle" method="POST">
		<table align="center" border="1px" style="margin-top: 50px">
			<tr>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td>标题</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td></td>
				<td>   
					<textarea name="content" rows="3" cols="50"></textarea>
				</td>
			</tr>
		     <tr>
		     	<td>
		     		<input type="submit" value="提交"/>
		     	</td>
		     	
		     </tr>
		     
		</table>
	</form>	
	<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
</body>
</html>