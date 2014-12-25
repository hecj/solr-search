<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="admin/css/datacollect/dataCollect.css">
<!--  
<script type="text/javascript" src="admin/js/datacollect/dataCollectEdit.js"></script>
-->
<div><br/>
	<input type="hidden" value="${dataCollectParams.id }" id="Id_dataCollectParamsEdit"/>
	 <div class="CLS_mainContentDivHegiht">
	     <label for="name" class="data_label_width">代理IP:</label>
	     <input id="Id_dataCollectParamsIP" value="${dataCollectParams.IP }" size="10"/>
	     <label for="name" style="width: 50px">端口:</label>
	     <input id="Id_dataCollectParamsPORT" value="${dataCollectParams.PORT }" size="4"/>
     </div>
	 <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">网址:</label>
        <input id="Id_dataCollectParamsbaseURL" value="${dataCollectParams.baseURL }" size=48/>
     </div>
     <div class="CLS_mainContentDivHegiht">
		<label for="name" class="data_label_width">分页参数:</label>
	    <input id="Id_dataCollectParamspageParams" value="${dataCollectParams.pageParams }" size=6/>
	    <label for="name" style="width: 50px">开始:</label>
	    <input id="Id_dataCollectParamsstart" value="${dataCollectParams.start }" size=6/>
	    <label for="name" style="width: 50px">结束:</label>
	    <input id="Id_dataCollectParamsend" value="${dataCollectParams.end }" size=6/>
	    <label for="name" style="width: 50px">步长:</label>
	    <input id="Id_dataCollectParamsstep" value="${dataCollectParams.step }" size=6/>
    </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">基本选择器:</label>
        <input id="Id_dataCollectParamsbaseSelect" value="${dataCollectParams.baseSelect }" size=49/>
     </div>
      <div class="CLS_mainContentDivHegiht">
        <label  class="data_label_width">编码:</label>
        <select id="Id_dataCollectParamsEncode" class="easyui-combobox"  data-options="panelHeight:45,editable:false">
        	<option value="GBK" <c:if test="${dataCollectParams.encode=='GBK' }">selected="selected"</c:if>>GBK</option>
        	<option value="UTF-8" <c:if test="${dataCollectParams.encode=='UTF-8' }">selected="selected"</c:if>>UTF-8</option>
        </select>
        <label for="name">数据库:</label>
        <select id="Id_dataCollectParamsdataBaseType" class="easyui-combobox" data-options="panelHeight:45,editable:false">
        	<option value="MySQL" <c:if test="${dataCollectParams.dataBaseType=='MySQL' }">selected="selected"</c:if>>MySQL</option>
        	<option value="Oracle" <c:if test="${dataCollectParams.dataBaseType=='Oracle' }">selected="selected"</c:if>>Oracle</option>
        </select>
        <label for="name">表名:</label>
        <input id="Id_dataCollectParamstableName" value="${dataCollectParams.tableName }" size=17/>
     </div>
     <div id="Id_footGridEdit" style="height:162px"></div>
</div>
<script type="text/javascript">
	jQuery(function() {
		DataCollectEdit.init();
	});
</script>