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
				width:130
			}, {
				field : 'username',
				title : '姓名',
				align : 'center',
				width:250
			}, {
				field : 'telPhone',
				title : '手机号',
				align : 'center',
				width:250
			}, {
				field : 'email',
				title : '邮箱',
				align : 'center',
				width:50
			}, {
				field : 'roleId',
				title : '角色代码',
				align : 'center',
				width:70
			}, {
				field : 'imageHead',
				title : '头像',
				align : 'center',
				width:100
			}, {
				field : 'createDate',
				title : '创建时间',
				align : 'center',
				width:80
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
							<tr><td>
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="findFun();">查看</a>
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
