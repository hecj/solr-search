<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<style type="text/css">
	.divHeight{
		height: 30px;
	}
	
	.labelWidth{
		width: 100px;
		display: inline-block;
		text-align: right;
	}

</style>
<div><br/>
<form id="form">
	<input type="hidden" value="${dataCollectParams.id }" id="id"/>
	 <div class="divHeight">
	     <label for="name" class="labelWidth">代理IP:</label>
	     <input id="ip" value="${dataCollectParams.IP }" size="10"/>
	     <label for="name" style="width: 50px">端口:</label>
	     <input id="port" value="${dataCollectParams.PORT }" size="10"/>
     </div>
	 <div class="divHeight">
        <label for="name" class="labelWidth">网址:</label>
        <input id="baseURL" value="${dataCollectParams.baseURL }" size=48 class="easyui-validatebox" data-options="required:true,validType:'url'"/>
     </div>
     <div class="divHeight">
		<label for="name" class="labelWidth">分页参数:</label>
	    <input id="pageParams" value="${dataCollectParams.pageParams }" size=10/>
	    <label for="name" style="width: 50px">开始:</label>
	    <input id="start" value="${dataCollectParams.start }" size=10/>
	    <label for="name" style="width: 50px">结束:</label>
	    <input id="end" value="${dataCollectParams.end }" size=10/>
	    <label for="name" style="width: 50px">步长:</label>
	    <input id="step" value="${dataCollectParams.step }" size=10/>
    </div>
     <div class="divHeight">
        <label for="name" class="labelWidth">基本选择器:</label>
        <input id="baseSelect" value="${dataCollectParams.baseSelect }" size=49 class="easyui-validatebox" data-options="required:true"/>
     </div>
      <div class="divHeight">
        <label  class="labelWidth">编码:</label>
        <select id="encode" class="easyui-combobox"  data-options="panelHeight:45,editable:false">
        	<option value="GBK" <c:if test="${dataCollectParams.encode=='GBK' }">selected="selected"</c:if>>GBK</option>
        	<option value="UTF-8" <c:if test="${dataCollectParams.encode=='UTF-8' }">selected="selected"</c:if>>UTF-8</option>
        </select>
        <label for="name">数据库:</label>
        <select id="dataBaseType" class="easyui-combobox" data-options="panelHeight:45,editable:false">
        	<option value="MySQL" <c:if test="${dataCollectParams.dataBaseType=='MySQL' }">selected="selected"</c:if>>MySQL</option>
        	<option value="Oracle" <c:if test="${dataCollectParams.dataBaseType=='Oracle' }">selected="selected"</c:if>>Oracle</option>
        </select>
        <label for="name">表名:</label>
        <input id="tableName" value="${dataCollectParams.tableName }" size=17 class="easyui-validatebox" data-options="required:true"/>
     </div>
</form>
     <div id="grid" style="height:162px"></div>
</div>
<script type="text/javascript">

		var grid ;
		
		$(function(){
			
			initFun();
		});

		/*
		 * 初始化页面
		 */
		var initFun = function(){
		
			/*初始化表格*/
			var id = $('#id').val();
			grid = $('#grid').datagrid( {
				url: app.contextPath+'admin/data/dataCollect.htm?operator=toEdit&id='+id+"&type=2",
				rownumbers : true,
				singleSelect:true,
				fitColumns: true,
				//loadMsg: MessageUtil.loadDataGridMsg,
				loadMsg:'',
				columns : [ [ {
					field : 'id',
					title : 'id',
					hidden : true
				},{
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
					align : 'center',
					width:70,
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
				} 
				] ],
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
							MessageUtil.messageShow("<font color=red>请选择一行!</font>");
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
							MessageUtil.messageShow("<font color=red>当前未更改!</font>");
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

		var updateActions = function(index){
			grid.datagrid('updateRow',{
				index: index,
				row:{}
			});
		}
		
		/*提交*/
		var submitForm = function(dialog,parentGrid){
			
			//校验表单
			var isValid = $("#form").form('validate');
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
						MessageUtil.errorShow("第"+(i+1)+"行未保存!");
						return ;
					}
					if(row.fieldSelect == undefined || StringUtils.trims(row.fieldSelect).length == 0){
						MessageUtil.errorShow("第"+(i+1)+"行选择器不可为空!");
						return ;
					}
				}
				var id = $("#id").val();
				var IP = $("#ip").val();
				var PORT = $("#port").val();
				var baseURL = $("#baseURL").val();
				var pageParams = $("#pageParams").val();
				var start = $("#start").val();
				var end = $("#end").val();
				var step = $("#step").val();
				var baseSelect = $("#baseSelect").val();
				var encode = $('#encode').combobox('getText');
				var dataBaseType = $("#dataBaseType").combobox('getText');
				var tableName = $("#tableName").val();
				var fieldList = new Array();
				var rows = grid.datagrid('getRows');
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
					if(StringUtils.isObjEmpty(fid)){
						fid = '';
					}
					var dataField = new AppEntity.DataField(fid, fieldSelect, selectMethod, targetAttr, pattern, newPlace, oldPlace, fieldName, fieldType, fieldLenth);
					fieldList.push(dataField);
				}
				var dataCollect = new AppEntity.DataCollect(id, IP, PORT, baseURL, pageParams, start, end, step, baseSelect, encode, dataBaseType, tableName, fieldList);
				var json = $.toJSON(dataCollect);
				$.ajax({
					url: app.contextPath+'admin/data/dataCollect.htm?operator=edit',
					data:{data:json},
					dataType:'json',
					timeout:3000,
					type:'GET',
					cache:false,
					success:function(data){
						if(data.code == 'success'){
							parent.MessageUtil.messageShow('<font color=green>'+data.message+'</font>');
							dialog.dialog('close');
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