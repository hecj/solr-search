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
			idField: 'radiocode',
			pageList : [ 15, 30, 45, 600 ],
			columns:[[
			    {width:20,field:'check',title:'<input type="checkbox" onclick="selectFun(this)" title="全选/取消全选">',
			    	formatter: function(value,row,index){
	    				if(value){
							grid.datagrid('selectRow',index);
	    					return '<input type="checkbox" id='+index+' name="checkRadio" checked="checked" onclick="selectRadioFun(this)"/>';
				    	}else{
			    			return '<input type="checkbox" id='+index+' name="checkRadio" onclick="selectRadioFun(this)"/>';
				    	}
			    	}
	    		},
				{title:'按钮名称',field:'radioname',align:'left',width:80},
				{title:'按钮代码',field:'radiocode',align:'left', width:80}
			]],
			onLoadSuccess:function(row, data){
				$.messager.progress('close');
			},
			onBeforeLoad:function(row, param){
				$.messager.progress({
					text : '数据加载中....'
				});
			},
			onClickRow:function(index,row){
				var c = $('#'+index).attr('checked');
				if(c){
					$('#'+index).attr('checked',false);
					grid.datagrid('unSelectRow',index);
				}else{
					$('#'+index).attr('checked',true);
					grid.datagrid('SelectRow',index);
				}
			}
		});

		$('#gridPanel').panel({
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
		//选中行
		var rows = grid.datagrid('getSelections');
		for(var i=0;i<rows.length;i++){
			idList.push(rows[i].radiocode);
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
	//全选/取消
	var selectFun = function(obj){
		var c = $(obj).attr('checked');
		if(c){
			$('input[name=checkRadio]').attr('checked',true);
		}else{
			$('input[name=checkRadio]').attr('checked',false);
		}
	}

	var selectRadioFun = function(obj){
		var c = $(obj).attr('checked');
		if(c){
			$(obj).attr('checked',false);
		}else{
			$(obj).attr('checked',true);
		}
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
	    		<div id="gridPanel">
	    			<div id="grid"></div>
	    		</div>
	    </div>
   	 </div>
</body>
</html>
