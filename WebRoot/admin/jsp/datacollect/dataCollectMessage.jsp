<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="admin/css/datacollect/dataCollect.css">
<div><br/>
	 <input type="hidden" value="${dataCollectParams.id }" id="Id_dataCollectParams"/>
	 <div style="height: 38px">
	 	<c:if test="${dataCollectParams.IP != null && dataCollectParams.IP != '' }">
        <label for="name" class="data_label_width">代理IP:</label>
        <label class="easyui-validatebox input" style="width: 100px" >${dataCollectParams.IP }</label>
        <label for="name" style="width: 50px">端口:</label>
        <label class="easyui-validatebox" style="width: 50px;" >${dataCollectParams.PORT }</label>
     	</c:if>
     </div>
	 <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">base网站:</label>
        <label class="easyui-validatebox" style="width: 350px"  >${dataCollectParams.baseURL }</label>
     	<c:if test="${dataCollectParams.pageParams != null && dataCollectParams.pageParams != '' }">
     	<label for="name" style="width: 50px">分页参数:</label>
        <label class="easyui-validatebox" style="width: 50px" >${dataCollectParams.pageParams }</label>
     	<label for="name" style="width: 50px">开始:</label>
        <label class="easyui-validatebox" style="width: 50px" >${dataCollectParams.start }</label>
     	<label for="name" style="width: 50px">结束:</label>
        <label class="easyui-validatebox" style="width: 50px">${dataCollectParams.end }</label>
     	<label for="name" style="width: 50px">步长:</label>
        <label class="easyui-validatebox" style="width: 50px">${dataCollectParams.step }</label>
     	</c:if>
     </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">base选择器:</label>
        <label class="easyui-validatebox" style="width: 450px" >${dataCollectParams.baseSelect }</label>
     </div>
      <div class="CLS_mainContentDivHegiht">
        <label  class="data_label_width">编码:</label>
        <label  style="width:150px;">${dataCollectParams.encode }</label>
     </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">数据库:</label>
        <label id="cc" style="width:150px;">${dataCollectParams.dataBaseType }</label>
     </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">表名:</label>
        <label class="easyui-validatebox" >${dataCollectParams.tableName }</label>
     </div>
     <div id="Id_footGrid"></div>
</div>

<script>

	jQuery(function(){

		var id = jQuery('#Id_dataCollectParams').val();
		jQuery('#Id_footGrid').datagrid( {
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

</script>