<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<script type="text/javascript">

	$(function(){
		
	});

	var submitForm = function(dialog,parentGrid){
		//validate
		var isValid = $("form").form('validate');
		if (!isValid){
			return;
		}
		//sumbit
	    $('form').form('submit', {
	    	url : app.basePath+'admin/tree/menuTree.htm?operator=addChildNodeSumbit',
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
			url:app.basePath+'admin/tree/menuTree.htm?operator=testURL',
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
	.height{
		height: 35px;
	}
</style>
</head>
<body>
	<div>
		<form method="post">
			 <div class="height" style="margin-top: 20px">
			     <label for="name">名称:</label>
			     <input name="name" size="30" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
		     </div>
		     <div class="height">
		     	<label for="name" >父模块:</label>
			    <input name="parentId" size="30" value="${module.moduleId }" readonly="readonly" />
		     </div>
		     <div class="height">
		     	<label for="name">路径:</label>
			    <input name="url" size="28" class="easyui-validatebox" />
			    <a id="testURL" href="#" class="easyui-linkbutton" onclick="testURL();">测试</a>
		     </div>
			 <div class="height">
			     <label for="name">状态:</label>
			     <select name="state" class="easyui-combobox" data-options="panelHeight:50,editable:false">
					<option value="open">打开</option>
					<option value="closed">关闭</option>
				</select>
		     </div>
			 <div class="height">
			     <label for="name">图标:</label>
			     <input name="icons" value="tree-file" size="30" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
		     </div>
		     <div class="height">
		     	<label for="name">叶子:</label>
			    <select name="leaf" class="easyui-combobox" disabled="disabled" data-options="panelHeight:75,editable:false">
					<option value="1">是</option>
				</select>
		     </div>
		</form>
	</div>
</body>
</html>
