<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<script type="text/javascript">

	var submitForm = function(dialog,parentGrid){
		//validate
		var isValid = $("form").form('validate');
		if (!isValid){
			return;
		}
		//sumbit
	    $('form').form('submit', {
	    	url : app.contextPath+'admin/module/module.htm?operator=editRadioSub',
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
<style type="text/css">
	label{  
	    display: inline-block;  
	    padding: 0 0px;  
	    text-align:right;
	    width: 90px;
	}
	form div{
		height: 40px;
	}
</style>
</head>
<body>
	<div>
		<form method="post">
			 <div style="margin-top: 40px">
			     <label for="name">按钮代码:</label>
			     <input name="radiocode" value="${module.moduleId }" readonly="readonly" size="25" class="easyui-validatebox" data-options="required:true,validType:'baseValidator'"/>
		     </div>
		     <div>
		     	<label for="name" >按钮名称:</label>
			    <input name="radioname" size="25" value="${module.name }" class="easyui-validatebox" data-options="required:true,validType:'baseValidator'"/>
		     </div>
		     <div>
		     	<label for="name">路径:</label>
			    <input name="url" size="25" value="${module.url }"/>
		     </div>
		     <div>
		     	<label for="name">图标:</label>
			    <input name="icon" size="25" value="${module.icons }"/>
		     </div>
			 
		</form>
	</div>
</body>
</html>
