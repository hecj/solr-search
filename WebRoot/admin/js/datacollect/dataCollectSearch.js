
/**
 * admin/jsp/datacollect/dataCollectSearch.jsp
 */

var DataCollectSearch = {
	/*
	 * 初始化表格
	 */
	initGrid : function() {
		var datacCollectGrid = jQuery('#Id_dataCollectSearch').datagrid( {
			url : 'admin/data/dataCollect.htm?operator=seacherDataCollect',
			columns : [ [ {
				field : 'id',
				hidden : true
			}, {
				field : 'baseURL',
				title : 'base网站',
				align : 'center',
				fitColumns : true
			}, {
				field : 'baseSelect',
				title : 'base选择器',
				align : 'center',
				fitColumns : true
			}, {
				field : 'encode',
				title : '编码',
				align : 'center',
				fitColumns : true
			}, {
				field : 'dataBaseType',
				title : '数据库',
				align : 'center',
				fitColumns : true
			}, {
				field : 'tableName',
				title : '表名',
				align : 'center',
				fitColumns : true
			}, {
				field : 'pageParams',
				title : '分页参数',
				align : 'center',
				fitColumns : true
			}, {
				field : 'start',
				title : '开始',
				align : 'center',
				fitColumns : true
			}, {
				field : 'end',
				title : '结束',
				align : 'center',
				fitColumns : true
			}, {
				field : 'step',
				title : '步长',
				align : 'center',
				fitColumns : true
			}, {
				field : 'IP',
				title : 'IP',
				align : 'center',
				fitColumns : true
			}, {
				field : 'PORT',
				title : '端口',
				align : 'center',
				fitColumns : true
			}] ],
			pagination : true,
			rownumbers : true,
			pageList : [ 10, 20, 30, 40 ],
			singleSelect : true,
			showHeader : true,
			showFooter : true,
			loadMsg : MessageUtil.loadDataGridMsg,
			toolbar: [{
				iconCls: 'icon-edit',
				text:'查看',
				handler: function(){
					var row = datacCollectGrid.datagrid('getSelected');
					if(row != null){
						DataCollectSearch.openDataCollectDialog(0,row);
					}else{
						MessageUtil.messageShow('<font color=red>请选择一行!</font>');
					}
				}
			},'-',{
				iconCls: 'icon-remove',
				text:'删除',
				handler: function(){
					var row = datacCollectGrid.datagrid('getSelected');
					if(row == null){
						MessageUtil.messageShow('<font color=red>请选择一行!</font>');
						return;
					}
					jQuery.messager.confirm('提示信息','确定要删除吗?',function(r){
						if (r){
							jQuery.ajax({
								url:'admin/data/dataCollect.htm?operator=delete',
								data:{id:row.id},
								async:true,
								dataType:'json',
								timeout:3000,
								type:'GET',
								cache:false,
								success:function(data){
									if(data.code == 'success'){
										MessageUtil.messageShow('<font color=green>'+data.message+'</font>');
										datacCollectGrid.datagrid('reload');
									}else{
										MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
									}
								},
								error:function(data){
									MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
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
						MessageUtil.messageShow("<font color=red>请选择一行!</font>");
						return;
					}
					jQuery('#Id_dataCollectEdit').dialog({
						title: '编辑信息',
						width: 800,
						height: 400,
						cache: false,
						modal: true,
						href: 'admin/data/dataCollect.htm?operator=toEdit&id='+row.id+"&type=1",
						buttons:[{
							text:'提交',
							handler:function(){
								//当前行正在编辑则返回
								var rows = jQuery("#Id_footGridEdit").datagrid('getRows');
								if(rows){
									//取消其他的编辑行
									for(var i=0;i<rows.length;i+=1){
										row = rows[i];
										if(row.editing){
											MessageUtil.errorShow("第"+(i+1)+"行未保存!");
											return ;
										}
										if(row.fieldSelect == undefined || StringUtils.trims(row.fieldSelect).length == 0){
											MessageUtil.errorShow("第"+(i+1)+"行选择器不可为空!");
											return ;
										}
									}
									DataCollectEdit.onSubmit();
									jQuery('#Id_dataCollectEdit').dialog('refresh');
								}else{
									MessageUtil.errorShow("操作异常!");
								}
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
		jQuery('#Id_dataCollectMessage').dialog({
			title: '详细信息',
			width: 900,
			height: 400,
			cache: false,
			modal: true,
			href: 'admin/data/dataCollect.htm?operator=toDataCollectMessage&id='+row.id,
			buttons:[{
				text:'刷新',
				handler:function(){
					jQuery('#Id_dataCollectMessage').dialog('refresh');
				}
			},{
				text:'关闭',
				handler:function(){
					jQuery('#Id_dataCollectMessage').dialog('close');
				}
			}],
			loadingMessage:MessageUtil.loadingPageMessage
		});
	}
}

