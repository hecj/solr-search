<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="admin/css/datacollect/dataCollect.css">
<script type="text/javascript" src="admin/js/datacollect/dataCollectAdd.js"></script>
<div><br/>
	 <div class="CLS_mainContentDivHegiht">
	     <label for="name" class="data_label_width">代理IP:</label>
	     <input id="Id_Add_dataCollectParamsIP" size="10"/>
	     <label for="name" style="width: 50px">端口:</label>
	     <input id="Id_Add_dataCollectParamsPORT" size="4"/>
     </div>
	 <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">网址:</label>
        <input id="Id_Add_dataCollectParamsbaseURL" size=48/>
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
        <input id="Id_Add_dataCollectParamsbaseSelect" size=49/>
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
        <input id="Id_Add_dataCollectParamstableName" size=17/>
     </div>
     <div id="Id_Add_footGridAdd" style="height:162px"></div>
</div>
<script type="text/javascript">
	jQuery(function() {
		DataCollectAdd.init();
	});
</script>