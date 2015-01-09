<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<script type="text/javascript">
	var grid ;

	$(function() {
		initFun();
	});
	/*
	 *初始化表格
	 */
	var initFun = function(){
		grid = $('#grid').datagrid( {
			rownumbers : true,
			singleSelect:true,
			loadMsg: MessageUtil.loadDataGridMsg,
			fitColumns: true,
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
					}
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
					}
				}
			}, {
				field : 'fieldLenth',
				title : '字段长度',
				align : 'center',
				width:70,
				editor:'numberbox'
			}] ],
			toolbar: [{
				text:'添加',
				iconCls: 'icon-add',
				handler: function(){
					var rows = grid.datagrid('getRows');
					grid.datagrid('insertRow',{
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
					var row = grid.datagrid('getSelected');
					if(row){
						var dIndex = grid.datagrid('getRowIndex',row);
						grid.datagrid('deleteRow',dIndex);
					}else{
						parent.parent.MessageUtil.messageShow("<font color=red>请选择一行!</font>");
					}
				}
			},'-',{
				text:'保存',
				iconCls: 'icon-save',
				handler: function(){
					var rows = grid.datagrid('getRows');
					var b = true;
					//取消编辑行
					for(var i=0;i<rows.length;i+=1){
						row = rows[i];
						if(row.editing){
							grid.datagrid('endEdit',i);
							b = false;
							break ;
						}
					}
					if(b){
						parent.MessageUtil.messageShow("<font color=red>当前未更改!</font>");
					}
				}
			}],
			onBeforeEdit:function(index,row){
				row.editing = true;
				updateActions(index);
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				updateActions(index);
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				updateActions(index);
			},
			onDblClickCell: function(index,field,value){
				//当前行正在编辑则返回
				var rows = grid.datagrid('getRows');
				if(rows[index].editing){
					return ;
				}
				//取消其他的编辑行
				for(var i=0;i<rows.length;i+=1){
					row = rows[i];
					if(row.editing){
						grid.datagrid('endEdit',i);
						break ;
					}
				}
				//打开编辑的行
				$(this).datagrid('beginEdit', index);
				var ed = $(this).datagrid('getEditor', {
					index:index,
					field:field
				});
				$(ed.target).focus();
			}
		});
	}

	/**
	 * 更新行
	 */
	var updateActions = function(index){
		grid.datagrid('updateRow',{
			index: index,
			row:{}
		});
	}

	var submitForm = function(dialog,parentGrid){
		//校验表单
		var isValid = $("#Id_dataCollectAdd_form").form('validate');
		if (!isValid){
			return;
		}
		
		//当前行正在编辑则返回
		var rows = grid.datagrid('getRows');
		if(rows){
			//取消其他的编辑行
			for(var i=0;i<rows.length;i+=1){
				row = rows[i];
				if(row.editing){
					parent.MessageUtil.errorShow("第"+(i+1)+"行未保存!");
					return ;
				}
				if(row.fieldSelect == undefined || StringUtils.trims(row.fieldSelect).length == 0){
					parent.MessageUtil.errorShow("第"+(i+1)+"行选择器不可为空!");
					return ;
				}
			}
			var IP = $("#Id_Add_dataCollectParamsIP").val();
			var PORT = $("#Id_Add_dataCollectParamsPORT").val();
			var baseURL = $("#Id_Add_dataCollectParamsbaseURL").val();
			var pageParams = $("#Id_Add_dataCollectParamspageParams").val();
			var start = $("#Id_Add_dataCollectParamsstart").val();
			var end = $("#Id_Add_dataCollectParamsend").val();
			var step = $("#Id_Add_dataCollectParamsstep").val();
			var baseSelect = $("#Id_Add_dataCollectParamsbaseSelect").val();
			var encode = $('#Id_Add_dataCollectParamsEncode').combobox('getText');
			var dataBaseType = $("#Id_Add_dataCollectParamsdataBaseType").combobox('getText');
			var tableName = $("#Id_Add_dataCollectParamstableName").val();
			var fieldList = new Array();
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
			var json = $.toJSON(dataCollect);
			$.ajax({
				url:app.basePath+'admin/data/dataCollect.htm?operator=add',
				data:{data:json},
				dataType:'json',
				timeout:3000,
				type:'GET',
				cache:false,
				success:function(data){
					if(data.code == 'success'){
						parent.MessageUtil.messageShow('<font color=green>'+data.message+'</font>');
						dialog.dialog("close");
						parentGrid.datagrid('reload');
					}else{
						parent.MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
					}
				},
				error:function(data){
					parent.MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
				}
			});
		}else{
			parent.MessageUtil.errorShow("操作异常!");
		}
	}
	
</script>
</head>
<body>
<div><br/>
<form id="Id_dataCollectAdd_form">
	 <div class="CLS_mainContentDivHegiht">
	     <label for="name" class="data_label_width">代理IP:</label>
	     <input id="Id_Add_dataCollectParamsIP" size="10"/>
	     <label for="name" style="width: 50px">端口:</label>
	     <input id="Id_Add_dataCollectParamsPORT" size="4"/>
     </div>
	 <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">网址:</label>
        <input id="Id_Add_dataCollectParamsbaseURL"  class="easyui-validatebox" size=48 data-options="required:true,validType:'notEmpty'"/>
     </div>
     <div class="CLS_mainContentDivHegiht">
		<label for="name" class="data_label_width">分页参数:</label>
	    <input id="Id_Add_dataCollectParamspageParams" size=6/>
	    <label for="name" style="width: 50px">开始:</label>
	    <input id="Id_Add_dataCollectParamsstart" size=6/>
	    <label for="name" style="width: 50px">结束:</label>
	    <input id="Id_Add_dataCollectParamsend" size=6/>
	    <label for="name" style="width: 50px">步长:</label>
	    <input id="Id_Add_dataCollectParamsstep" size=6/>
    </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">基本选择器:</label>
        <input id="Id_Add_dataCollectParamsbaseSelect" size=49 class="easyui-validatebox" data-options="required:true"/>
     </div>
      <div class="CLS_mainContentDivHegiht">
        <label  class="data_label_width">编码:</label>
        <select id="Id_Add_dataCollectParamsEncode" class="easyui-combobox"  data-options="panelHeight:45,editable:false">
        	<option value="GBK">GBK</option>
        	<option value="UTF-8">UTF-8</option>
        </select>
        <label for="name">数据库:</label>
        <select id="Id_Add_dataCollectParamsdataBaseType" class="easyui-combobox" data-options="panelHeight:45,editable:false">
        	<option value="MySQL">MySQL</option>
        	<option value="Oracle">Oracle</option>
        </select>
        <label for="name">表名:</label>
        <input id="Id_Add_dataCollectParamstableName" size=17  class="easyui-validatebox" data-options="required:true"/>
     </div>
</form>
     <div id="grid"  style="height:162px"></div>
</div>
</body>
</html>
