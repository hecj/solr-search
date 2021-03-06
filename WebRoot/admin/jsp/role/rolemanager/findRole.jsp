<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<script type="text/javascript">
	var tree ;
	var grid ;
	$(function(){
		initFun();
	});
	
	var initFun = function(){
		var rolecode = $('input[name=rolecode]').val();
		tree = $('#tree').tree({
			url: app.contextPath+'admin/role/role.htm?operator=initModule&rolecode='+rolecode,
			border:false,
			onLoadSuccess:function(row, data){
				$(this).tree('expandAll',$(this).tree('getRoot').target);
				$.messager.progress('close');
			},
			onBeforeLoad:function(row, param){
				$.messager.progress({
					text : '数据加载中....'
				});
			}
		});

		$('#tree').panel({
			title:'菜单权限',
			border: false
		});

		grid = $('#grid').datagrid({
			url: app.contextPath+'admin/module/module.htm?operator=existPermissionRadioList&rolecode='+rolecode,
			fitColumns: true,
			pageSize:600,
			idField: 'radiocode',
			pageList : [ 15, 30, 45, 600 ],
			columns:[[
				{title:'按钮名称',field:'radioname',align:'left', width:80},
				{title:'按钮代码',field:'radiocode',align:'left', width:80}
			]],
			onLoadSuccess:function(row, data){
				$.messager.progress('close');
			},
			onBeforeLoad:function(row, param){
				$.messager.progress({
					text : '数据加载中....'
				});
			}
		});

		$('#gridPanel').panel({
			title:'按钮权限',
			border: false
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
				<input size="25" value="${role.rolename }" readonly="readonly" />
		     </div>
		</form>
		<div class="easyui-panel" border="true" style="padding:2px;margin:5px;height:200px;width:360px">
	    		<div id="tree"></div>
	    		<div id="gridPanel">
	    			<div id="grid"></div>
	    		</div>
	    </div>
   	 </div>
</body>
</html>
