(function() {
	jQuery(function() {
		var id = jQuery('#Id_dataCollectParamsEdit').val();
		jQuery('#Id_footGridEdit').datagrid( {
			url: 'admin/data/dataCollect.htm?operator=toEdit&id='+id+"&type=2",
			columns : [ [ {
				field : 'fieldSelect',
				title : '字段选择器 ',
				width : 100
			}, {
				field : 'selectMethod',
				title : '解析方法',
				width : 100
			}, {
				field : 'pattern',
				title : '正则正则',
				width : 100
			}, {
				field : 'oldPlace',
				title : '替换',
				width : 100
			}, {
				field : 'fieldName',
				title : '字段名',
				width : 100
			}, {
				field : 'fieldType',
				title : '字段类型',
				width : 100
			}, {
				field : 'fieldLenth',
				title : '字段长度',
				width : 100
			}, ] ],
			rownumbers : true,
			loadMsg: MessageUtil.loadDataGridMsg,
			singleSelect:true
		});
	});
})();