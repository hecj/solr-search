<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Seacher</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript">
		var treegrid ;
		
		$(function(){

			treegrid = $('#treegrid').treegrid({
				url: app.basePath+'admin/role/role.htm?operator=searchList',
				idField: 'moduleId',
				treeField: 'name',
				toolbar:'#toolbar',
				fit:true,
				border:false,
				fitColumns: true,
				columns:[[
					{title:'角色名称',field:'name',align:'left', width:220},
					{title:'角色Id或模块Id',field:'moduleId',align:'left',width:80}
				]],
				onLoadSuccess:function(row, data){
					//$.messager.progress('close');
				},
				onBeforeLoad:function(row, param){
					//$.messager.progress({
					//	text : '数据加载中....'
					//});
				}
			});
		});

		var addFun = function(){
			var dialog = parent.app.dialogModel({
				title: '添加角色',
				width: 400,
				height: 400,
				url : app.basePath+'admin/jsp/role/rolemanager/addRole.jsp',
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
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addFun();">添加角色</a>
					</td>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="editFun();">编辑角色</a>
					</td>
					
				</tr>
			</table>
		</div>
		
	</body>
</html>
