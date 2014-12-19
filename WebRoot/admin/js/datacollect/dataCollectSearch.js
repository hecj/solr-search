
/**
 * admin/jsp/datacollect/dataCollectSearch.jsp
 */

var DataCollectSearch = {
	/*
	 * 初始化表格
	 */
	initGrid : function() {
		var datagrid = jQuery('#ID_dataCollectSearch').datagrid( {
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
			loadMsg : MessageUtil.loadDataGridMsg
		});

		/*
		 * 事件处理
		 */
		datagrid.datagrid( {
			// 双击行事件
				onDblClickRow : function(index, row) {
					alert(row.id);
				}
			});

		/*
		 * 页码处理
		 */
		datagrid.datagrid('getPager').pagination( {
			beforePageText : '第',
			afterPageText : '页    共 {pages} 页',
			displayMsg : '当前 {from} - {to} 条&nbsp;&nbsp;共 {total} 条'
		});
	}
}

