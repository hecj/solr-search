<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<script type="text/javascript">
	var tree ;
	$(function(){
		initFun();
	});
	
	var initFun = function(){
		tree = $('#tree').tree({
			url: app.basePath+'admin/role/role.htm?operator=initTree&moduleId=0',
			border:false,
			checkbox:true,
			cascadeCheck:true,
			onLoadSuccess:function(row, data){
				$.messager.progress('close');
			},
			onBeforeLoad:function(row, param){
				$.messager.progress({
					text : '数据加载中....'
				});
			},
			onCheck:function(node, checked){

			}
		});
	}

	var submitForm = function(dialog,parentGrid){
		//validate
		var isValid = $("form").form('validate');
		if (!isValid){
			return;
		}
		//获取选择的树
		var idsSet = new Set();
		var nodes = tree.tree('getChecked');
		for(var i =0;i<nodes.length;i++){
			var node = nodes[i];
			//添加Id
			idsSet.add(node.id);
			//循环添加父节点Id
			while(true){
				var node = tree.tree('getParent',node.target);
				if(node){
					idsSet.add(node.id);
				}else{
					break;
				}
			}
		}
		//拼接Id
		var ids = '';
		idsSet.forEach(function (item) {
		    ids+=','+item.toString();
		});
		//sumbit
	    $('form').form('submit', {
	    	url : app.basePath+'admin/role/role.htm?operator=addRole&ids='+ids,
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
	    width: 80px;
	}
	form div{
		height: 35px;
	}
</style>
</head>
<body class="easyui-layout" data-options="border:false">
	<div id="content" region="center" data-options="border:false">
		<form method="post">
			 <div style="margin-top: 20px">
			    <label for="name">角色代码:</label>
			    <input name="roleCode" size="25" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
		     </div>
		     <div>
			    <label for="name" >角色名称:</label>
				<input name="rolename" size="25" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'" />
		     </div>
		</form>
		<div class="easyui-panel" title="权限" border="false" style="padding:5px;height:218px;width:385px">
	    		<table id="tree"></table>
	    </div>
   	 </div>
</body>
</html>
