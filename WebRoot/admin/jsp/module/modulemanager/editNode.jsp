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
	    	url : app.contextPath+'admin/module/module.htm?operator=editNodeSumbit',
	        success: function(data){
		        var data = eval('(' + data + ')');
		        if (data.code == '0'){
		        	parent.MessageUtil.messageShow(data.message);
		        	dialog.dialog('close');
		        	parentGrid.treegrid('reload');

		        }else{
		        	parent.MessageUtil.errorShow(data.message);
			    }
	        }
	    });
	}

	var testURL = function(){
		var url = $("input[name=url]").val();
		$.ajax({
			url:app.contextPath+'admin/module/module.htm?operator=testURL',
			data:{url:url},
			dataType:'json',
			timeout:3000,
			type:'GET',
			cache:false,
			success:function(data){
				if(data.code == '0'){
					parent.MessageUtil.messageShow(data.message);
				}else{
					parent.MessageUtil.errorShow(data.message);
				}
			},
			error:function(data){
				parent.MessageUtil.errorShow(data.message);
			}
		});
	}
</script>
<style type="text/css">
	label{  
	    display: inline-block;  
	    padding: 0 0px;  
	    text-align:right;
	    width: 70px;
	}
	form div{
		height: 35px;
	}
</style>
</head>
<body>
	<div>
		<form method="post">
			 <div style="margin-top: 20px">
			 	 <input name="moduleId" type="hidden" value="${module.moduleId }"/>
			     <label for="name">名称:</label>
			     <input name="name" size="30" value="${module.name }" class="easyui-validatebox" data-options="required:true,validType:'baseValidator'"/>
		     </div>
		     <div>
		     	<label for="name" >父模块:</label>
			    <input name="parentId" size="30" value="${module.parentId }" readonly="readonly" />
		     </div>
		     <div>
		     	<label for="name">路径:</label>
			    <input name="url" size="28" value="${module.url }" class="easyui-validatebox"/>
			    <a id="testURL" href="#" class="easyui-linkbutton" onclick="testURL();">验证</a>
		     </div>
			 <div>
			     <label for="name">状态:</label>
			     <select name="state" class="easyui-combobox" data-options="panelHeight:50,editable:false">
					<option value="open" <c:if test="${module.state=='open' }">selected="selected"</c:if>>打开</option>
					<option value="closed" <c:if test="${module.state=='closed' }">selected="selected"</c:if>>关闭</option>
				</select>
		     </div>
			 <div>
			     <label for="name">图标:</label>
			     <input name="icons" value="${module.icons }" value="tree-file" size="30" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
		     </div>
		     <div>
		     	<label for="name">叶子:</label>
			    <select name="leaf" class="easyui-combobox" disabled="disabled" data-options="panelHeight:75,editable:false">
					<option value="1" <c:if test="${module.leaf=='1' }">selected="selected"</c:if>>是</option>
					<option value="0" <c:if test="${module.leaf=='0' }">selected="selected"</c:if>>否</option>
				</select>
		     </div>
		</form>
	</div>
</body>
</html>
