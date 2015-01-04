<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="admin/css/datacollect/dataCollect.css">
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<div><br/>
	<input type="hidden" id="Id_dataCollectMsg" value="${dataCollectParams.id }"/>
	 <div class="CLS_mainContentDivHegiht">
	     <label for="name" class="data_label_width">代理IP:</label>
	     <input  size="10" value="${dataCollectParams.IP }" readonly="readonly"/>
	     <label for="name" style="width: 50px">端口:</label>
	     <input size="4" value="${dataCollectParams.PORT }" readonly="readonly"/>
     </div>
	 <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">网址:</label>
        <input size=48 value="${dataCollectParams.baseURL }" readonly="readonly"/>
     </div>
     <div class="CLS_mainContentDivHegiht">
		<label for="name" class="data_label_width">分页参数:</label>
	    <input  size=6 value="${dataCollectParams.pageParams }" readonly="readonly"/>
	    <label for="name" style="width: 50px">开始:</label>
	    <input  size=6 value="${dataCollectParams.start }" readonly="readonly"/>
	    <label for="name" style="width: 50px">结束:</label>
	    <input size=6 value="${dataCollectParams.end }" readonly="readonly"/>
	    <label for="name" style="width: 50px">步长:</label>
	    <input  size=6 value="${dataCollectParams.step }" readonly="readonly"/>
    </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">基本选择器:</label>
        <input size=49 value="${dataCollectParams.baseSelect }" readonly="readonly"/>
     </div>
      <div class="CLS_mainContentDivHegiht">
        <label  class="data_label_width">编码:</label>
        <input value="${dataCollectParams.encode }" size=7 readonly="readonly"/>
        <label for="name">数据库:</label>
        <input value="${dataCollectParams.dataBaseType }" size=7 readonly="readonly"/>
        <label for="name">表名:</label>
        <input  size=17 value="${dataCollectParams.tableName }" readonly="readonly"/>
     </div>
     <div id="Id_Add_footGridMsg" style="height:162px"></div>
</div>
<script type="text/javascript">
	jQuery(function() {
		var id = jQuery("#Id_dataCollectMsg").val();
		var dataGridAdd = jQuery('#Id_Add_footGridMsg').datagrid( {
			url: 'http://localhost:8080/search/admin/data/dataCollect.htm?operator=toEdit&id='+id+"&type=2",
			rownumbers : true,
			singleSelect:true,
			loadMsg: MessageUtil.loadDataGridMsg,
			columns : [ [ {
				field : 'fieldSelect',
				title : '选择器 ',
				align : 'center',
				width:130
			}, {
				field : 'selectMethod',
				title : '方法',
				align : 'center',
				width:60
			}, {
				field : 'targetAttr',
				title : '属性',
				align : 'center',
				width:80
			}, {
				field : 'pattern',
				title : '正则',
				align : 'center',
				width:80
			}, {
				field : 'newPlace',
				title : '替换新',
				align : 'center',
				width:80
			}, {
				field : 'oldPlace',
				title : '替换老',
				align : 'center',
				width:80
			}, {
				field : 'fieldName',
				title : '字段名',
				align : 'center',
				width:80
			}, {
				field : 'fieldType',
				title : '字段类型',
				width:70,
				align : 'center'
			}, {
				field : 'fieldLenth',
				title : '字段长度',
				align : 'center',
				width:70
			} 
			] ]
		});
	});
</script>