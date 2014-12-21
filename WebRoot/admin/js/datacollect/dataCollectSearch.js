
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
					}
				}
			},'-',{
				iconCls: 'icon-remove',
				text:'删除',
				handler: function(){
					var row = datacCollectGrid.datagrid('getSelected');
					if(row != null){
						jQuery.ajax({
							url:'admin/data/dataCollect.htm?operator=delete',
							data:{id:row.id},
							async:true,
							dataType:'json',
							timeout:10000,
							type:'GET',
							cache:false,
							success:function(data){
								alert(data.message);
							},
							error:function(data){
								alert(data.message);
							}
						});
						
					}
				}
			},'-',{
				iconCls: 'icon-help',
				text:'帮助',
				handler: function(){
					alert('我要获得帮助');
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
			}]
		});
	}
}

