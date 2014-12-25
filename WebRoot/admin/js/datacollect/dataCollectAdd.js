var DataCollectAdd = {
		/*
		 * 初始化页面
		 */
		init:function(){
		
			/*初始化表格*/
			var dataGridEdit = jQuery('#Id_footGridEdit').datagrid( {
				rownumbers : true,
				singleSelect:true,
				loadMsg: MessageUtil.loadDataGridMsg,
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
					align : 'center',
					editor:{
						type:'combobox',
						options:{
							valueField:'value',
							textField:'text',
							data:[
							   {value:'text',text:'text'},
							   {value:'attr',text:'attr'},
							   {value:'html',text:'html'}
							],
							editable:false,
							panelHeight:65
						},
					}
				}, {
					field : 'targetAttr',
					title : '属性',
					align : 'center',
					editor:'text'
				}, {
					field : 'pattern',
					title : '正则',
					align : 'center',
					editor:'text'
				}, {
					field : 'newPlace',
					title : '替换新',
					align : 'center',
					editor:'text'
				}, {
					field : 'oldPlace',
					title : '替换老',
					align : 'center',
					editor:'text'
				}, {
					field : 'fieldName',
					title : '字段名',
					align : 'center',
					editor:'text'
				}, {
					field : 'fieldType',
					title : '字段类型',
					align : 'center',
					editor:{
						type:'combobox',
						options:{
							valueField:'value',
							textField:'text',
							data:[
							   {value:'integer',text:'integer'},
							   {value:'char',text:'char'},
							   {value:'varchar',text:'varchar'},
							   {value:'date',text:'date'},
							   {value:'datetime',text:'datetime'},
							   {value:'blob',text:'blob'}
							],
							editable:false,
							panelHeight:130
						},
					}
				}, {
					field : 'fieldLenth',
					title : '字段长度',
					align : 'center',
					editor:'numberbox'
				} 
				] ],
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){
						var rows = dataGridEdit.datagrid('getRows');
						dataGridEdit.datagrid('insertRow',{
							index: rows.length,
							row: {
								selectMethod:'text',
								fieldType:'varchar',
								fieldLenth:255,
								fieldName:'P'+(rows.length+1)
							}
						});
					}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){
						var row = dataGridEdit.datagrid('getSelected');
						if(row){
							var dIndex = dataGridEdit.datagrid('getRowIndex',row);
							dataGridEdit.datagrid('deleteRow',dIndex);
						}else{
							MessageUtil.messageShow("<font color=red>请选择一行!</font>");
						}
					}
				},'-',{
					text:'保存',
					iconCls: 'icon-save',
					handler: function(){
						var rows = dataGridEdit.datagrid('getRows');
						var b = true;
						//取消编辑行
						for(var i=0;i<rows.length;i+=1){
							row = rows[i];
							if(row.editing){
								dataGridEdit.datagrid('endEdit',i);
								b = false;
								break ;
							}
						}
						if(b){
							MessageUtil.messageShow("<font color=red>当前未更改!</font>");
						}
						
					}
				}],
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
					//当前行正在编辑则返回
					var rows = dataGridEdit.datagrid('getRows');
					if(rows[index].editing){
						return ;
					}
					//取消其他的编辑行
					for(var i=0;i<rows.length;i+=1){
						row = rows[i];
						if(row.editing){
							dataGridEdit.datagrid('endEdit',i);
							break ;
						}
					}
					//打开编辑的行
					jQuery(this).datagrid('beginEdit', index);
					var ed = jQuery(this).datagrid('getEditor', {
						index:index,
						field:field
					});
					jQuery(ed.target).focus();
				}
			});
		},
		updateActions:function(index){
			jQuery('#Id_footGridEdit').datagrid('updateRow',{
				index: index,
				row:{}
			});
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
