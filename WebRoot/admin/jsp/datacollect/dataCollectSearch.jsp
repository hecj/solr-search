<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div id="ID_dataCollectSearch" style="width:1000px;"></div>
<script type="text/javascript">
jQuery(document).ready(function(){
	
	var datagrid = jQuery('#ID_dataCollectSearch').datagrid({
	    url:'admin/data/dataCollect.htm?operator=seacherDataCollect',
	    columns:[[
	        {field:'baseURL',title:'base网站',align:'center',fitColumns:true},
	        {field:'baseSelect',title:'base选择器',align:'center',fitColumns:true},
	        {field:'encode',title:'编码',align:'center',fitColumns:true},
	        {field:'dataBaseType',title:'数据库',align:'center',fitColumns:true},
	        {field:'tableName',title:'表名',align:'center',fitColumns:true},
	        {field:'pageParams',title:'分页参数',align:'center',fitColumns:true},
	        {field:'start',title:'开始',align:'center',fitColumns:true},
	        {field:'end',title:'结束',align:'center',fitColumns:true},
	        {field:'step',title:'步长',align:'center',fitColumns:true},
	        {field:'IP',title:'IP',align:'center',fitColumns:true},
	        {field:'PORT',title:'端口',align:'center',fitColumns:true}
	    ]],
	    pagination:true,
	    rownumbers:true,
	    pageList: [10,20,30,40],
	    singleSelect:true,
	    showHeader:true,
	    showFooter:true,
	    loadMsg:MessageUtil.loadDataGridMsg
	});

});

</script>