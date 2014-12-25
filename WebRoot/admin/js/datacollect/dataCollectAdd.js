var DataCollectAdd = {
		/*
		 * 初始化页面
		 */
		init:function(){
		
			/*初始化表格*/
			var dataGridAdd = jQuery('#Id_Add_footGridAdd').datagrid( {
				rownumbers : true,
				singleSelect:true,
				loadMsg: MessageUtil.loadDataGridMsg,
				columns : [ [ {
					field : 'fieldSelect',
					title : '选择器 ',
					align : 'center',
					editor:'text',
					width:130
				}, {
					field : 'selectMethod',
					title : '方法',
					align : 'center',
					width:60,
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
					width:80,
					editor:'text'
				}, {
					field : 'pattern',
					title : '正则',
					align : 'center',
					width:80,
					editor:'text'
				}, {
					field : 'newPlace',
					title : '替换新',
					align : 'center',
					width:80,
					editor:'text'
				}, {
					field : 'oldPlace',
					title : '替换老',
					align : 'center',
					width:80,
					editor:'text'
				}, {
					field : 'fieldName',
					title : '字段名',
					align : 'center',
					width:80,
					editor:'text'
				}, {
					field : 'fieldType',
					title : '字段类型',
					width:70,
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
					width:70,
					editor:'numberbox'
				} 
				] ],
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){
						var rows = dataGridAdd.datagrid('getRows');
						dataGridAdd.datagrid('insertRow',{
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
						var row = dataGridAdd.datagrid('getSelected');
						if(row){
							var dIndex = dataGridAdd.datagrid('getRowIndex',row);
							dataGridAdd.datagrid('deleteRow',dIndex);
						}else{
							MessageUtil.messageShow("<font color=red>请选择一行!</font>");
						}
					}
				},'-',{
					text:'保存',
					iconCls: 'icon-save',
					handler: function(){
						var rows = dataGridAdd.datagrid('getRows');
						var b = true;
						//取消编辑行
						for(var i=0;i<rows.length;i+=1){
							row = rows[i];
							if(row.editing){
								dataGridAdd.datagrid('endEdit',i);
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
					DataCollectAdd.updateActions(index);
				},
				onAfterEdit:function(index,row){
					row.editing = false;
					DataCollectAdd.updateActions(index);
				},
				onCancelEdit:function(index,row){
					row.editing = false;
					DataCollectAdd.updateActions(index);
				},
				onDblClickCell: function(index,field,value){
					//当前行正在编辑则返回
					var rows = dataGridAdd.datagrid('getRows');
					if(rows[index].editing){
						return ;
					}
					//取消其他的编辑行
					for(var i=0;i<rows.length;i+=1){
						row = rows[i];
						if(row.editing){
							dataGridAdd.datagrid('endEdit',i);
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
			jQuery('#Id_Add_footGridAdd').datagrid('updateRow',{
				index: index,
				row:{}
			});
		},
		/*提交*/
		onSubmit:function(){
			
			//校验表单
			var isValid = jQuery("#Id_dataCollectAdd_form").form('validate');
			if (!isValid){
				return;
			}
			
			//当前行正在编辑则返回
			var rows = jQuery("#Id_Add_footGridAdd").datagrid('getRows');
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
				var IP = jQuery("#Id_Add_dataCollectParamsIP").val();
				var PORT = jQuery("#Id_Add_dataCollectParamsPORT").val();
				var baseURL = jQuery("#Id_Add_dataCollectParamsbaseURL").val();
				var pageParams = jQuery("#Id_Add_dataCollectParamspageParams").val();
				var start = jQuery("#Id_Add_dataCollectParamsstart").val();
				var end = jQuery("#Id_Add_dataCollectParamsend").val();
				var step = jQuery("#Id_Add_dataCollectParamsstep").val();
				var baseSelect = jQuery("#Id_Add_dataCollectParamsbaseSelect").val();
				var encode = jQuery('#Id_Add_dataCollectParamsEncode').combobox('getText');
				var dataBaseType = jQuery("#Id_Add_dataCollectParamsdataBaseType").combobox('getText');
				var tableName = jQuery("#Id_Add_dataCollectParamstableName").val();
				var fieldList = new Array();
				var rows = jQuery('#Id_Add_footGridAdd').datagrid('getRows');
				for ( var i = 0; i < rows.length; i=i+1) {
					row = rows[i];
					var fieldSelect = row.fieldSelect;
					var selectMethod = row.selectMethod;
					var targetAttr = row.targetAttr;
					var pattern = row.pattern;
					var newPlace = row.newPlace;
					var oldPlace = row.oldPlace;
					var fieldName = row.fieldName;
					var fieldType = row.fieldType;
					var fieldLenth = row.fieldLenth;
					var dataField = new AppEntity.DataField('', fieldSelect, selectMethod, targetAttr, pattern, newPlace, oldPlace, fieldName, fieldType, fieldLenth);
					fieldList.push(dataField);
				}
				var dataCollect = new AppEntity.DataCollect('', IP, PORT, baseURL, pageParams, start, end, step, baseSelect, encode, dataBaseType, tableName, fieldList);
				var json = jQuery.toJSON(dataCollect);
				jQuery.ajax({
					url:'admin/data/dataCollect.htm?operator=add',
					data:{data:json},
					dataType:'json',
					timeout:3000,
					type:'GET',
					cache:false,
					success:function(data){
						if(data.code == 'success'){
							MessageUtil.messageShow('<font color=green>'+data.message+'</font>');
							jQuery("#Id_dataCollectAdd").dialog("close");
							datacCollectGrid.datagrid('reload');
						}else{
							MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
						}
					},
					error:function(data){
						MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
					}
				});
			}else{
				MessageUtil.errorShow("操作异常!");
			}
	}
		
}

