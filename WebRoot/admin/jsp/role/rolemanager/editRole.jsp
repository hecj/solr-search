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
		var rolecode = $('input[name=rolecode]').val();
		tree = $('#tree').tree({
			url: app.basePath+'admin/role/role.htm?operator=initEditModule&rolecode='+rolecode+'',
			border:false,
			checkbox:true,
			onLoadSuccess:function(row, data){
				$(this).tree('expandAll',$(this).tree('getRoot').target)
				$.messager.progress('close');
			},
			onBeforeLoad:function(row, param){
				$.messager.progress({
					text : '数据加载中....'
				});
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
	    	url : app.basePath+'admin/role/role.htm?operator=editRoleSub&ids='+ids,
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
	    margin-top: 15px;
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
		     <div>
		     	<input type="hidden" name="rolecode" value="${role.rolecode }"/>
			    <label for="name" >角色名称:</label>
				<input name="rolename" value="${role.rolename }" size="25" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'" />
		     </div>
		</form>
		<div class="easyui-panel" title="权限" border="false" style="padding:5px;height:218px;width:385px">
	    		<table id="tree"></table>
	    </div>
   	 </div>
</body>
</html>
