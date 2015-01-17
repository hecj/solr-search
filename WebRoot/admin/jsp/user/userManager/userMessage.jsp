<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/admin/jsp/base/easyUI.jsp" />
<%@include file="/admin/jsp/base/basePath.jsp" %>
<style type="text/css">
	.divHeight{
		height: 35px;
	}
	.labelWidth{
		width: 100px;
		display: inline-block;
		text-align: right;
	}
	.imgHead{
		text-align: center;
		margin-bottom: 10px;
		margin-top: -10px;
	}
</style>
<div><br/>
	 <div>
	     <div class="imgHead">
	     	<img alt="" src="<%=basePath %>${user.imageHead}" width="80" height="80">
		 </div>
	 </div>
	 <div class="divHeight">
	     <label class="labelWidth">用户名:</label>
	     <input  size="20" value="${user.usercode }" readonly="readonly"/>
     </div>
     <div class="divHeight">
	     <label class="labelWidth">昵称:</label>
	     <input size="20" value="${user.username }" readonly="readonly"/>
     </div>
     <div class="divHeight">
	     <label class="labelWidth">手机号:</label>
	     <input  size="20" value="${user.telPhone }" readonly="readonly"/>
     </div>
     <div class="divHeight">
	     <label class="labelWidth">邮箱:</label>
	     <input size="20" value="${user.email }" readonly="readonly"/>
     </div>
     <div class="divHeight">
	     <label class="labelWidth">角色</label>
	     <input  size="20" value="${user.role.rolename }(${user.role.rolecode })" readonly="readonly"/>
     </div>
     <div class="divHeight">
	     <label class="labelWidth">创建时间:</label>
	     <input size="20" value="<fmt:formatDate value="${user.createDate}" type="both"/>" readonly="readonly"/>
     </div>
     <div class="divHeight">
	     <label class="labelWidth">修改时间:</label>
	     <input size="20" value="<fmt:formatDate value="${user.updateDate}" type="both"/>" readonly="readonly"/>
     </div>
</div>
