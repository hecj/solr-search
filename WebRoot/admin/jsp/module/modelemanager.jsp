<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Seacher</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript">
		var treegrid ;
		var rightMenu ;
		var rightMenuRoot ;
		
		$(function(){

			treegrid = $('#treegrid').treegrid({
				url: app.contextPath+'admin/module/module.htm?operator=searchModule&moduleId=0',
				idField: 'moduleId',
				treeField: 'name',
				toolbar:'#toolbar',
				fit:true,
				border:false,
				fitColumns: true,
				onContextMenu:function(e,row){//右键点击事件
					treegrid.treegrid('select',row.moduleId);
					var x = parseInt(e.clientX)-200;
					var y = parseInt(e.clientY)-25;
					if(row.moduleId == '0'){
						rightMenuRoot.menu('show', {
					        left: x,
					        top: y
					    });
					}else{
						rightMenu.menu('show', {
					        left: x,
					        top: y
					    });
					}
				},
				columns:[[
					{title:'名称',field:'name',align:'left', width:220},
					{title:'模块Id',field:'moduleId',align:'left',width:80},
					{title:'父模块Id',field:'parentId',align:'left',width:80},
					{title:'状态',field:'state',align:'center',width:80,
				  		formatter: function(value,row,index){
					  		if(value == 'open'){
						  		return '打开';
						  	}else if(value == 'closed'){
								return '关闭';
							}
		  				}
	  				},
					{title:'路径',field:'url',align:'center',width:210},
					{title:'图标',field:'iconCls',align:'center',width:60,
				  		formatter: function(value,row,index){
			  				return value;
			  			}
			  		},
					{title:'叶子',field:'leaf',align:'center',width:50,
				  		formatter: function(value,row,index){
				  		if(value == '0'){
					  		return '否'
					  	}
		  				return '是';
		  			}}
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
			rightMenu = $('#rightClick').menu({});
			rightMenuRoot = $('#rightMenuRoot').menu({});
		});

		/**
		 * 菜单操作
		 */
		var menuHandler = function(type){
			var row = treegrid.treegrid('getSelected');
			if(!row){
				parent.MessageUtil.errorShow('请选择一行');
				return;
			}
			switch(type){
			case 11:
				if(row.moduleId == '0'){
					parent.MessageUtil.errorShow('根节点不可添加兄弟节点!');
					return;
				}
				var dialog = parent.app.dialogModel({
					title: '添加兄弟节点',
					width: 400,
					height: 330,
					url : app.contextPath+'admin/module/module.htm?operator=addBrotherNode&moduleId='+row.moduleId,
					buttons:[{
						text:'提交',
						handler:function(){
							dialog.find('iframe').get(0).contentWindow.submitForm(dialog,treegrid);
						}
					},{
						text:'关闭',
						handler:function(){
							dialog.dialog('close');
						}
					}]
				});
				break;
			case 12:
				var dialog = parent.app.dialogModel({
					title: '添加子节点',
					width: 400,
					height: 330,
					url : app.contextPath+'admin/module/module.htm?operator=addChildNode&moduleId='+row.moduleId,
					buttons:[{
						text:'提交',
						handler:function(){
							dialog.find('iframe').get(0).contentWindow.submitForm(dialog,treegrid);
						}
					},{
						text:'关闭',
						handler:function(){
							dialog.dialog('close');
						}
					}]
				});
				break;
			case 2:
				if(row.moduleId == '0'){
					parent.MessageUtil.errorShow('根节点不可删除!');
					return;
				}
				parent.$.messager.confirm('提示信息','确定要删除吗?',function(r){
					if (r){
						$.ajax({
							url : app.contextPath+'admin/module/module.htm?operator=deleteNode',
							data:{moduleId:row.moduleId},
							async:true,
							dataType:'json',
							timeout:3000,
							type:'GET',
							cache:false,
							success:function(data){
								if(data.code == '0'){

									parent.MessageUtil.messageShow('<font color=green>'+data.message+'</font>');
									treegrid.treegrid('reload');
									parent.customMenu.tree('reload');
									parent.systemTools.tree('reload');
									var p = parent.customMenu;
									
								}else{
									parent.MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
								}
							},
							error:function(data){
								parent.MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
							}
						});
					}
				});
				break;
			case 3:
				var dialog = parent.app.dialogModel({
					title: '编辑节点 Id:'+row.moduleId,
					width: 400,
					height: 330,
					url : app.contextPath+'admin/module/module.htm?operator=editNode&moduleId='+row.moduleId,
					buttons:[{
						text:'提交',
						handler:function(){
							dialog.find('iframe').get(0).contentWindow.submitForm(dialog,treegrid);
						}
					},{
						text:'关闭',
						handler:function(){
							dialog.dialog('close');
						}
					}]
				});
				break;
	
			}
		} 

  	</script>
  </head>
<body class="easyui-layout" data-options="border:false">
    <div id="content" region="center" data-options="border:false">
	    <table id="treegrid"></table>
    </div>
    	<!-- treegrid toolbar -->
		<div id="toolbar" style="display: none;">
			<table>
				<tr>
					<td colspan="4"><br/></td>
				</tr>
				<tr>
					<c:if test="${!empty context.radios.addBrotherNodeSumbit}">
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="menuHandler(11);">兄弟节点</a>
					</td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					</c:if>
					<c:if test="${!empty context.radios.addChildNodeSumbit}">
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="menuHandler(12);">子节点</a>
					</td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					</c:if>
					<c:if test="${!empty context.radios.deleteNode}">
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="menuHandler(2);">删除</a>
					</td>
					<td>
						<div class="datagrid-btn-separator"></div>
					</td>
					</c:if>
					<c:if test="${!empty context.radios.editNodeSumbit}">
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="menuHandler(3);">编辑</a>
					</td>
					</c:if>
				</tr>
			</table>
		</div>
		
	</body>
</html>
