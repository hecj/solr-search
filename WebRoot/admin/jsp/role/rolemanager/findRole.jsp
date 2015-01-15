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
				<input size="25" value="${role.rolename }" readonly="readonly" />
		     </div>
		</form>
		<div class="easyui-panel" title="权限" border="false" style="padding:5px;height:218px;width:385px">
	    		<table id="tree"></table>
	    </div>
   	 </div>
</body>
</html>
