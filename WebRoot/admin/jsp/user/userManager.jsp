<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<jsp:include page="/admin/jsp/base/easyUI.jsp" />
	<head>
<script type="text/javascript">
	$(function(){
		initFun();
	});
	var grid;
	/*
    * 初始化表格
	*/
	var initFun = function() {
		grid = $('#grid').datagrid( {
			url : app.basePath+'admin/user/user.htm?operator=searchUser',
			columns : [ [ {
				field : 'usercode',
				title : '用户名',
				align : 'center',
				width:100
			}, {
				field : 'username',
				title : '姓名',
				align : 'center',
				width:100
			}, {
				field : 'telPhone',
				title : '手机号',
				align : 'center',
				width:80
			}, {
				field : 'email',
				title : '邮箱',
				align : 'center',
				width:150
			}, {
				field : 'role',
				title : '角色代码',
				align : 'center',
				width:120,
				formatter: function(value,row,index){
					if(!value){
						return '';
					}
					return value.rolename+'('+value.rolecode+')';
				}
			}, {
				field : 'imageHead',
				title : '头像',
				align : 'center',
				width:200
			}, {
				field : 'createDate',
				title : '创建时间',
				align : 'center',
				width:100,
				formatter: function(value,row,index){
					return value;
				}
			}] ],
			pagination : true,
			rownumbers : true,
			pageList : [ 10, 20, 30, 40 ],
			singleSelect : true,
			pageSize:20,
			border:false,
			fit:true,
			fitColumns: true,
			loadMsg : '',
			toolbar : '#toolbar',
			onDblClickRow:function(index,row){
				findFun();
			},
			onLoadSuccess:function(data){
				$.messager.progress('close');
			},
			onBeforeLoad:function(param){
				$.messager.progress({
					text : '数据加载中....'
				});
			}
		});
	}

	/**
	 * 查询
	 */
	var queryFun = function(){

		var usercode = $('input[name=usercode]').val();
		grid.datagrid({
			queryParams:{
				usercode:usercode
			}
		});
		grid.datagrid('reload');
	}
	
	var findFun = function(){

		var row = grid.datagrid('getSelected');
		if(row == null){
			parent.MessageUtil.messageShow("<font color=red>请选择一行!</font>");
			return;
		}
		var dialog = parent.app.dialogModel({
			title: '查看信息  '+row.usercode,
			width: 350,
			height: 450,
			url: app.basePath+'admin/user/user.htm?operator=findUser&usercode='+row.usercode,
			buttons:[{
				text:'关闭',
				handler:function(){
					dialog.dialog('close');
				}
			}]
		});
	}

	var addFun = function(){
		var dialog = parent.app.dialogModel({
			title: '添加用户',
			width: 350,
			height: 450,
			url: app.basePath+'admin/jsp/user/userManager/addUser.jsp',
			buttons:[{
				text:'提交',
				handler:function(){
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog,grid);
				}
			},{
				text:'关闭',
				handler:function(){
					dialog.dialog('close');
				}
			}]
		});
	}

	var editFun = function(){
		var row = grid.datagrid('getSelected');
		if(row == null){
			parent.MessageUtil.messageShow("<font color=red>请选择一行!</font>");
			return;
		}
		var dialog = parent.app.dialogModel({
			title: '编辑信息  '+row.usercode,
			width: 350,
			height: 450,
			url: app.basePath+'admin/user/user.htm?operator=editUser&usercode='+row.usercode,
			buttons:[{
				text:'提交',
				handler:function(){
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog,grid);
				}
			},{
				text:'关闭',
				handler:function(){
					dialog.dialog('close');
				}
			}]
		});
	}

	var deleteFun = function(){
		var row = grid.datagrid('getSelected');
		if(row == null){
			parent.MessageUtil.messageShow('<font color=red>请选择一行!</font>');
			return;
		}
		parent.$.messager.confirm('提示信息','确定要删除吗?',function(r){
			if (r){
				$.ajax({
					url: app.basePath+'admin/user/user.htm?operator=deleteUser',
					data:{usercode:row.usercode},
					async:true,
					dataType:'json',
					timeout:3000,
					type:'GET',
					cache:false,
					success:function(data){
						if(data.code == '0'){
							parent.MessageUtil.messageShow(data.message);
							grid.datagrid('reload');
						}else{
							parent.MessageUtil.errorShow(data.message);
						}
					},
					error:function(data){
						parent.MessageUtil.errorShow(data.message);
					}
				});
			}
		});
	}
	
</script>
	</head>
	<body class="easyui-layout" data-options="border:false">
		<div id="toolbar" style="display: none;">
			<table>
				<tr>
					<td>
						<form>
							<table>
								<tr>
									<td>用户名：</td>
									<td>
										<input name="usercode"/>
								     </td>
								     <td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="queryFun();">查询</a>
								</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<table>
							<tr>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addFun();">添加</a>
								</td>
								<td>
									<div class="datagrid-btn-separator"></div>
								</td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="deleteFun();">删除</a>
								</td>
								<td>
									<div class="datagrid-btn-separator"></div>
								</td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="editFun();">编辑</a>
								</td>
								<td>
									<div class="datagrid-btn-separator"></div>
								</td>
								<td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="findFun();">查看</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div region="center" data-options="border:false">
			<div id="grid"></div>
		</div>
	</body>
</html>
