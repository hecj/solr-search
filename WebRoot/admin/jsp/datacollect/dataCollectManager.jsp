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
			url : app.basePath+'admin/data/dataCollect.htm?operator=seacherDataCollect',
			columns : [ [ {
				field : 'id',
				title : '编号',
				align : 'center',
				hidden : false,
				width:130
			}, {
				field : 'baseURL',
				title : '网址',
				align : 'center',
				fitColumns : true,
				width:250
			}, {
				field : 'baseSelect',
				title : '选择器',
				align : 'center',
				fitColumns : true,
				width:250
			}, {
				field : 'encode',
				title : '编码',
				align : 'center',
				fitColumns : true,
				width:50
			}, {
				field : 'dataBaseType',
				title : '数据库',
				align : 'center',
				fitColumns : true,
				width:70
			}, {
				field : 'tableName',
				title : '表名',
				align : 'center',
				fitColumns : true,
				width:100
			}, {
				field : 'pageParams',
				title : '分页参数',
				align : 'center',
				fitColumns : true,
				width:80
			}, {
				field : 'start',
				title : '开始',
				align : 'center',
				fitColumns : true,
				width:80
			}, {
				field : 'end',
				title : '结束',
				align : 'center',
				fitColumns : true,
				width:80
			}, {
				field : 'step',
				title : '步长',
				align : 'center',
				fitColumns : true,
				width:80
			}, {
				field : 'IP',
				title : 'IP',
				align : 'center',
				fitColumns : true,
				width:100
			}, {
				field : 'PORT',
				title : '端口',
				align : 'center',
				fitColumns : true,
				width:80
			}] ],
			pagination : true,
			rownumbers : true,
			pageList : [ 10, 20, 30, 40 ],
			singleSelect : true,
			pageSize:20,
			//loadMsg : MessageUtil.loadDataGridMsg,
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

	var addFun = function(){
		var dialog = parent.app.dialogModel({
			title: '添加信息',
			width: 850,
			height: 450,
			url : app.basePath+'admin/data/dataCollect.htm?operator=toAdd',
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
					url:app.basePath+'admin/data/dataCollect.htm?operator=delete',
					data:{id:row.id},
					async:true,
					dataType:'json',
					timeout:3000,
					type:'GET',
					cache:false,
					success:function(data){
						if(data.code == 'success'){
							parent.MessageUtil.messageShow('<font color=green>'+data.message+'</font>');
							grid.datagrid('reload');
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
	}

	var editFun = function(){
		var row = grid.datagrid('getSelected');
		if(row == null){
			parent.MessageUtil.messageShow("<font color=red>请选择一行!</font>");
			return;
		}
		var dialog = parent.app.dialogModel({
			title: '编辑信息 Id:'+row.id,
			width: 850,
			height: 450,
			url: app.basePath+'admin/data/dataCollect.htm?operator=toEdit&id='+row.id+"&type=1",
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
			}],
			loadingMessage:MessageUtil.loadingPageMessage
		});
	}
	
	var findFun = function(){
		var row = grid.datagrid('getSelected');
		if(row == null){
			parent.MessageUtil.messageShow("<font color=red>请选择一行!</font>");
			return;
		}
		var dialog = parent.app.dialogModel({
			title: '查看信息 Id:'+row.id,
			width: 850,
			height: 450,
			url: app.basePath+'admin/data/dataCollect.htm?operator=toDataCollectMessage&id='+row.id+"",
			buttons:[{
				text:'关闭',
				handler:function(){
					dialog.dialog('close');
				}
			}],
			loadingMessage:MessageUtil.loadingPageMessage
		});
	}

	/**
	 * 查询
	 */
	var queryFun = function(){
		var encode = $('#encode').combobox('getValue');
		grid.datagrid({
			queryParams:{
				encode:encode
			}
		});
		grid.datagrid('reload');
	}
	
</script>
	</head>
	<body class="easyui-layout" data-options="fit:true,border:false">
		<div id="toolbar" style="display: none;">
			<table>
				<tr>
					<td>
						<form id="searchForm">
							<table>
								<tr>
									<td>编码：</td>
									<td><select id="encode" class="easyui-combobox"  data-options="panelHeight:75,editable:false">
								        	<option value="">不限</option>
								        	<option value="GBK">GBK</option>
								        	<option value="UTF-8">UTF-8</option>
								        </select>
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
		<div data-options="region:'center',fit:true,border:false">
			<div id="grid" data-options="fit:true,border:false"></div>
		</div>
	</body>
</html>
