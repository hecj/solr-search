<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/jsp/base/basePath.jsp"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>文章列表</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/home.css">
		<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/util/StringUtils.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/main/index.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/main/home.js"></script>
	</head>
	<body>
		<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
		<jsp:include page="/WEB-INF/jsp/common/menu.jsp"/>
		<div id="main">
			<div id="position">
				笑话共 224551&nbsp;篇&nbsp;&nbsp;最后更新：12月14日 18:40&nbsp;&nbsp;今日已更新 36 次 还将更新 12 次
			</div>
			<div id="main_content">
				<table align="center" width="500" border="1">
					<tr>
						<td width="40">
							<a href="<%=basePath%>home/article.htm?operator=toAddArticle">添加</a>
						</td>
						<td>
							<form action="<%=basePath%>home/home.htm?m=init"
								method="post" onSubmit="return check_qForm()" id="q_form">
								<input type="text" name="q" value="${showQ }" />
								<input type="submit" value="查询" />
							</form>
						</td>
					</tr>
					<tbody>
						<c:if test="${articleList != null}">
							<c:forEach var="mArticle" items="${articleList}" varStatus="status">
								<tr>
									<td>
										${status.count }
									</td>
									<td>
										${mArticle.title }
									</td>
								</tr>
								<tr>
									<td></td>
									<td>
										${mArticle.content }
									</td>
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
							当前页${pagination.currPage }&nbsp;&nbsp; 总页数${pagination.countPage
							}&nbsp;&nbsp; 总条数${pagination.countSize }&nbsp;&nbsp;
							页条数${pagination.pageSize}&nbsp;&nbsp;
						</td>
					</tr>
		
					<tfoot>
		
					</tfoot>
				</table>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
	</body>
</html>