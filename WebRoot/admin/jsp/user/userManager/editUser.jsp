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
<script type="text/javascript">

	$(function(){
	    $('#roleCode').combobox({
	        url: app.basePath+'admin/role/role.htm?operator=roleList',
	        valueField:'value',
	        textField:'text'
	    });
	});

	var submitForm = function(dialog,parentGrid){
		//validate
		var isValid = $("form").form('validate');
		if (!isValid){
			return;
		}
		//sumbit
	    $('form').form('submit', {
	    	url : app.basePath+'admin/user/user.htm?operator=editUserSub',
	        success: function(data){
		        var data = eval('(' + data + ')');
		        if (data.code == '0'){
		        	parent.MessageUtil.messageShow(data.message);
		        	dialog.dialog('close');
		        	parentGrid.datagrid('reload');
	
		        }else{
		        	parent.MessageUtil.errorShow(data.message);
			    }
	        }
	    });
	}
</script>
<div><br/>
<form method="post">
	 <div>
	     <div class="imgHead">
	     	<img alt="" src="${context.basePath}${user.imageHead}" width="80" height="80">
		 	<a href="javascript:void(0);">浏览</a>
		 </div>
	 </div>
	 <div class="divHeight">
	     <label class="labelWidth">用户名:</label>
	     <input name="usercode"  size="20" value="${user.usercode }" readonly="readonly"/>
     </div>
     <div class="divHeight">
	     <label class="labelWidth">昵称:</label>
	     <input name="username" size="20" value="${user.username }" />
     </div>
     <div class="divHeight">
	     <label class="labelWidth">手机号:</label>
	     <input name="telPhone" size="20" value="${user.telPhone }" />
     </div>
     <div class="divHeight">
	     <label class="labelWidth">邮箱:</label>
	     <input name="email" size="20" value="${user.email }" />
     </div>
     <div class="divHeight">
	     <label class="labelWidth">角色</label>
	     <input id="roleCode" name="roleCode" size="20" value="${user.role.roleCode }" class="easyui-combobox" data-options="required:true,validType:'notEmpty'"/>
     </div>
</form>
</div>
