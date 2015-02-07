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
			url: app.contextPath+'admin/role/role.htm?operator=initEditModule&rolecode='+rolecode+'',
			border:false,
			checkbox:true,
			onLoadSuccess:function(node, data){
				$(this).tree('expandAll',$(this).tree('getRoot').target);
				$.messager.progress('close');
			},
			onBeforeLoad:function(node, param){
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
			url: app.contextPath+'admin/module/module.htm?operator=permissionRadioList&rolecode='+rolecode,
			fitColumns: true,
			pageSize:600,
			pageList : [ 15, 30, 45, 600 ],
			columns:[[
			    {width:20,field:'check',title:'<input type=\"checkbox\">',
			    	formatter: function(value,row,index){
			    		if(value){
		    				return '<input type="checkbox" checked="checked" />';
			    		}else{
			    			return '<input type="checkbox"/>';
				    	}
			    	}
	    		},
				{title:'按钮代码',field:'radiocode',align:'left'},
				{title:'名称',field:'radioname',align:'left', width:80}
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

		$('#grid').panel({
			title:'按钮权限',
			border: false
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
		//选中的按钮
		var rows = grid.datagrid('getChecked');
		alert(rows.length);
		for(var i=0;i<rows.length;i++){
			var row = rows[i];
			alert(row.checked);
			idList.push(row.radiocode);
		}
		
		//sumbit
	    $('form').form('submit', {
	    	url : app.contextPath+'admin/role/role.htm?operator=editRoleSub&ids='+idList.join(','),
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
				<input name="rolename" value="${role.rolename }" size="25" class="easyui-validatebox" data-options="required:true,validType:'baseValidator'" />
		     </div>
		</form>
		<div class="easyui-panel" border="true" style="padding:2px;margin:5px;height:200px;width:360px">
	    		<div id="tree"></div>
	    		<div id="grid"></div>
	    </div>
   	 </div>
</body>
</html>
