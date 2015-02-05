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
			url: app.contextPath+'admin/role/role.htm?operator=initTree&moduleId=0',
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
		var nodes = tree.tree('getChecked');
		var nodeList = new Array();
		var idList = new Array();
		for(var i =0;i<nodes.length;i++){
			var node = nodes[i];
			//添加Id
			idList.push(node.id);
			//循环添加枝干节点
			while(true){
				var node = tree.tree('getParent',node.target);
				if(!node){
					break;
				}
				var b = true;
				//判断是否存放过枝干
				for(var j=0;j<nodeList.length;j++){
					if(node.id == nodeList[j]){
						b = false;
						break;
					}
				}
				//集合中没有元素则添加
				if(b){
					nodeList.push(node.id);
				}else{
					break;
				}
			}
		}
		idList = idList.concat(nodeList);
		//sumbit
	    $('form').form('submit', {
	    	url : app.contextPath+'admin/role/role.htm?operator=addRole&ids='+idList.join(','),
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
			    <label for="name" >角色名称:</label>
				<input name="rolename" size="25" class="easyui-validatebox" data-options="required:true,validType:'baseValidator'" />
		     </div>
		</form>
		<div class="easyui-panel" border="false" style="padding:2px;margin:5px;height:200px;width:360px">
	    		<div id="tree"></div>
	    </div>
   	 </div>
</body>
</html>
