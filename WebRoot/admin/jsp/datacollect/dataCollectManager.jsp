<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<head>
<script type="text/javascript">
	jQuery(function(){
		DataCollectManager.initGrid();
	});
	$(window).resize(function(){
		$('#Id_dataCollectSearch').datagrid('resize', {
			width:function(){return document.body.clientWidth;},
			height:function(){return document.body.clientHeight;},
		});
	}); 
	var DataCollectManager = {
			/*
			 * 初始化表格
			 */
			initGrid : function() {
				var datacCollectGrid = $('#Id_dataCollectSearch').datagrid( {
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
					showHeader : true,
					showFooter : true,
					nowrap: true,
					autoRowHeight: false,
					striped: true,
					collapsible:true,
					pageSize:20,
					loadMsg : MessageUtil.loadDataGridMsg,
					toolbar: [{
						iconCls: 'icon-add',
						text:'添加',
						handler: function(){
							var dialog = parent.app.dialogModel({
								title: '添加信息',
								url : app.basePath+'admin/data/dataCollect.htm?operator=toAdd',
								buttons:[{
									text:'提交',
									handler:function(){
										dialog.find('iframe').get(0).contentWindow.submitForm();
									}
								},{
									text:'关闭',
									handler:function(){
										dialog.dialog('close');
									}
								}]
							});
						}
					},'-',{
						iconCls: 'icon-edit',
						text:'查看',
						handler: function(){
							var row = datacCollectGrid.datagrid('getSelected');
							if(row != null){
								DataCollectManager.openDataCollectDialog(0,row);
							}else{
								parent.MessageUtil.messageShow('<font color=red>请选择一行!</font>');
							}
						}
					},'-',{
						iconCls: 'icon-remove',
						text:'删除',
						handler: function(){
							var row = datacCollectGrid.datagrid('getSelected');
							if(row == null){
								parent.MessageUtil.messageShow('<font color=red>请选择一行!</font>');
								return;
							}
							jQuery.messager.confirm('提示信息','确定要删除吗?',function(r){
								if (r){
									jQuery.ajax({
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
												datacCollectGrid.datagrid('reload');
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
					},'-',{
						iconCls: 'icon-edit',
						text:'编辑',
						handler: function(){
							var row = datacCollectGrid.datagrid('getSelected');
							if(row == null){
								parent.MessageUtil.messageShow("<font color=red>请选择一行!</font>");
								return;
							}
							jQuery('#Id_dataCollectEdit').dialog({
								title: '编辑信息 Id:'+row.id,
								width: 800,
								height: 400,
								cache: false,
								modal: true,
								href: app.basePath+'admin/data/dataCollect.htm?operator=toEdit&id='+row.id+"&type=1",
								buttons:[{
									text:'提交',
									handler:function(){
										DataCollectEdit.onSubmit();
									}
								},{
									text:'关闭',
									handler:function(){
										jQuery('#Id_dataCollectEdit').dialog('close');
									}
								}],
								loadingMessage:MessageUtil.loadingPageMessage
							});
						}
					}]
				});

			

				/*
				 * 事件处理
				 */
				datacCollectGrid.datagrid( {
					// 双击行事件
					onDblClickRow : DataCollectSearch.openDataCollectDialog
				});

				/*
				 * 页码处理
				 */
				datacCollectGrid.datagrid('getPager').pagination( {
					beforePageText : MessageUtil.beforePageText,
					afterPageText : MessageUtil.afterPageText,
					displayMsg : MessageUtil.displayMsg
				});
			},
			openDataCollectDialog:function(index, row) {
				/*jQuery('#Id_dataCollectMessage').dialog({
					title: '详细信息 Id:'+row.id,
					width: 800,
					height: 400,
					cache: false,
					modal: true,
					href: AppUtil.basePath+'admin/data/dataCollect.htm?operator=toDataCollectMessage&id='+row.id,
					buttons:[{
						text:'刷新',
						handler:function(){
							$('#Id_dataCollectMessage').dialog('refresh');
						}
					},{
						text:'关闭',
						handler:function(){
							$('#Id_dataCollectMessage').dialog('close');
						}
					}],
					loadingMessage:MessageUtil.loadingPageMessage
				});*/

				var dialog = parent.app.modalDialog({
					title: '详细信息 Id:'+row.id,
					url : app.basePath+'admin/data/dataCollect.htm?operator=toDataCollectMessage&id='+row.id
				});
			}
		}

</script>
</head>
<body>
<div style="height: 100%；width: 100%; ">
	<div id="Id_dataCollectSearch"></div>
</div>
<div id=Id_dataCollectMessage></div>
<div id=Id_dataCollectEdit></div>
<div id=Id_dataCollectAdd></div>
</body>
</html>
