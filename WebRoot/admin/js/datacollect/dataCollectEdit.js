var DataCollectEdit = {
		/*
		 * 初始化页面
		 */
		init:function(){
		
			/*初始化表格*/
			var id = jQuery('#Id_dataCollectParamsEdit').val();
			jQuery('#Id_footGridEdit').datagrid( {
				url: 'admin/data/dataCollect.htm?operator=toEdit&id='+id+"&type=2",
				columns : [ [ {
					field : 'id',
					title : 'id ',
					hidden : true
				},{
					field : 'fieldSelect',
					title : '选择器 ',
					align : 'center',
					editor:'text'
				}, {
					field : 'selectMethod',
					title : '方法',
					align : 'center'
				}, {
					field : 'targetAttr',
					title : '属性',
					align : 'center'
				}, {
					field : 'pattern',
					title : '正则',
					align : 'center'
				}, {
					field : 'newPlace',
					title : '替换新',
					align : 'center'
				}, {
					field : 'oldPlace',
					title : '替换老',
					align : 'center'
				}, {
					field : 'fieldName',
					title : '字段名',
					align : 'center'
				}, {
					field : 'fieldType',
					title : '字段类型',
					align : 'center'
				}, {
					field : 'fieldLenth',
					title : '字段长度',
					align : 'center'
				},{
					field:'action',
					title:'Action',
					width:80,
					align:'center',
					formatter:function(value,row,index){
						if (row.editing){
							var s = '<button onclick="DataCollectEdit.saverow(this)">Save</button> ';
							var c = '<button onclick="DataCollectEdit.cancelrow(this)">Cancel</button>';
							return s+c;
						} else {
							var e = '<button onclick="DataCollectEdit.editrow(this)">Edit</button> ';
							var d = '<button onclick="DataCollectEdit.deleterow(this)">Delete</button>';
							return e+d;
						}
					}
				} 
				] ],
				rownumbers : true,
				loadMsg: MessageUtil.loadDataGridMsg,
				singleSelect:true,
				onBeforeEdit:function(index,row){
					row.editing = true;
					DataCollectEdit.updateActions(index);
				},
				onAfterEdit:function(index,row){
					row.editing = false;
					DataCollectEdit.updateActions(index);
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					DataCollectEdit.updateActions(index);
				},
				onDblClickCell: function(index,field,value){
					$(this).datagrid('beginEdit', index);
					var ed = $(this).datagrid('getEditor', {index:index,field:field});
					$(ed.target).focus();
				}
			});
		},
		updateActions:function(index){
			$('#Id_footGridEdit').datagrid('updateRow',{
				index: index,
				row:{}
			});
		},
		getRowIndex:function(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr('datagrid-row-index'));
		},
		editrow:function(target){
			$('#Id_footGridEdit').datagrid('beginEdit', this.getRowIndex(target));
		},
		deleterow:function(target){
			$.messager.confirm('Confirm','Are you sure?',function(r){
				if (r){
					$('#Id_footGridEdit').datagrid('deleteRow', this.getRowIndex(target));
				}
			});
		},
		saverow:function(target){
			$('#Id_footGridEdit').datagrid('endEdit', this.getRowIndex(target));
		},
		cancelrow:function(target){
			$('#Id_footGridEdit').datagrid('cancelEdit', this.getRowIndex(target));
		},
		/*提交*/
		onSubmit:function(){
			
			var id = jQuery("#Id_dataCollectParamsEdit").val();
			var IP = jQuery("#Id_dataCollectParamsIP").val();
			var PORT = jQuery("#Id_dataCollectParamsPORT").val();
			var baseURL = jQuery("#Id_dataCollectParamsbaseURL").val();
			var pageParams = jQuery("#Id_dataCollectParamspageParams").val();
			var start = jQuery("#Id_dataCollectParamsstart").val();
			var end = jQuery("#Id_dataCollectParamsend").val();
			var step = jQuery("#Id_dataCollectParamsstep").val();
			var baseSelect = jQuery("#Id_dataCollectParamsbaseSelect").val();
			var encode = jQuery('#Id_dataCollectParamsEncode').combobox('getText');
			var dataBaseType = jQuery("#Id_dataCollectParamsdataBaseType").combobox('getText');
			var tableName = jQuery("#Id_dataCollectParamstableName").val();
			var fieldList = new Array();
			var rows = jQuery('#Id_footGridEdit').datagrid('getRows');
			for ( var i = 0; i < rows.length; i=i+1) {
				row = rows[i];
				var fid = row.id;
				var fieldSelect = row.fieldSelect;
				var selectMethod = row.selectMethod;
				var targetAttr = row.targetAttr;
				var pattern = row.pattern;
				var newPlace = row.newPlace;
				var oldPlace = row.oldPlace;
				var fieldName = row.fieldName;
				var fieldType = row.fieldType;
				var fieldLenth = row.fieldLenth;
				var dataField = new AppEntity.DataField(fid, fieldSelect, selectMethod, targetAttr, pattern, newPlace, oldPlace, fieldName, fieldType, fieldLenth);
				fieldList.push(dataField);
			}
			var dataCollect = new AppEntity.DataCollect(id, IP, PORT, baseURL, pageParams, start, end, step, baseSelect, encode, dataBaseType, tableName, fieldList);
			var json = jQuery.toJSON(dataCollect);
			jQuery.ajax({
				url:'admin/data/dataCollect.htm?operator=edit',
				data:{data:json},
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
		
}

