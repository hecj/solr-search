<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.divUser{
		margin-top: 60px;
		margin-right: 20px;
		text-align: right;
		color: white;
	}
	
	.divUser a{
		color: white;
	}
	
</style>
<table width="100%" >
		<tr >
			<td width="80%">
			</td>
			<td width="20%">
				<table width="100%">
					<tr>
						<td height="100%">
							<div class="divUser">
									<c:if test="${not empty context}">
										用户名:<a href="#">${context.user.usercode }</a>
									</c:if>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	
</table>
