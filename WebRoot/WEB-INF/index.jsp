<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jsp/base/basePath.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文章列表</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/index.css">
<script type="text/javascript" src="<%=basePath %>js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/util/StringUtils.js"></script>
<script type="text/javascript" src="<%=basePath %>js/main/index.js"></script>
</head>
<body>
		<table align="center" width="500" border="1">
			<tr>
				<td><a href="<%=basePath %>article/article.htm?operator=toAddArticle">添加</a></td>
				<td>
					<form action="<%=basePath %>indexPage/indexPage.htm?m=init" method="post" onSubmit="return check_qForm()" id="q_form">
						<input type="text" name="q" value="${showQ }"/>
						<input type="submit" value="查询"/>
					</form>
				</td>
			</tr>
			<tbody>
				<c:if test="${articleList != null}">
				<c:forEach var="mArticle" items="${articleList}" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${mArticle.title }</td>
				</tr>
				<tr>
					<td></td>
					<td>${mArticle.content }</td>
				</tr>
				</c:forEach>	
				</c:if>
			</tbody>
				<tr>
					<td colspan="2">
						${pagination.showPagination }
					</td>
				</tr>
				<tr>
					<td colspan="2">
						当前页${pagination.currPage }&nbsp;&nbsp;
						总页数${pagination.countPage }&nbsp;&nbsp;
						总条数${pagination.countSize }&nbsp;&nbsp;
						页条数${pagination.pageSize}&nbsp;&nbsp;
					</td>
				</tr>

			<tfoot>
				
			</tfoot>
		</table>	
</body>
</html>