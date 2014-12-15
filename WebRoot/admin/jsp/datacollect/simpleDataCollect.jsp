<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="admin/css/datacollect/simpleDataCollect.css">
<script type="text/javascript" src="admin/js/datacollect/simpleDataCollect.js"></script>
<script type="text/javascript" src="admin/js/jquery/jquery.json-2.2.js"></script>
<div class="ID_mainContent">
	 <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">base网站:</label>
        <input class="easyui-validatebox" type="text" name="baseURL" data-options="required:true" />
     	<label for="name" style="width: 50px">分页参数:</label>
        <input class="easyui-validatebox" style="width: 50px" name="pageParams"/>
     	<label for="name" style="width: 50px">开始:</label>
        <input class="easyui-validatebox" style="width: 50px" name="start"/>
     	<label for="name" style="width: 50px">结束:</label>
        <input class="easyui-validatebox" style="width: 50px" name="end"/>
     	<label for="name" style="width: 50px">步长:</label>
        <input class="easyui-validatebox" style="width: 50px" name="step"/>
     </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">base选择器:</label>
        <input class="easyui-validatebox" type="text" name="baseSelect"/>
     </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">数据库:</label>
        <select id="cc" name="dataBaseType" style="width:150px;">
		    <option value="MySQL">MySQL</option>
		</select>
     </div>
     <div id="ID_fieldContent">
     	<ul>
     		<li>
     			<label>字段选择器:</label>
				<input type="text" name="fieldSelect"/>
				<label>正则:</label>
				<input type="text" name="pattern"/>
				<label>替换:</label>
				<input type="text" class="input_width" name="oldPlace"/>->
				<input type="text" class="input_width" name="newPlace"/>
				<label>字段名:</label>
				<input type="text" width="20px" class="input_width" name="fieldName"/>
				<label>字段类型:</label>
				<select class="input_width" name="fieldType">
				    <option value="int">int</option>
				    <option value="int">string</option>
				    <option value="int">date</option>
				</select>
				<label>字段长度:</label>
				<input type="text" class="input_width" name="fieldLenth"/>
		     </li>
		</ul>
		<div id="ID_defaultField" style="visibility: hidden;height: 0px;">
				<label>字段选择器:</label>
				<input type="text" name="fieldSelect"/>
				<label>正则:</label>
				<input type="text" name="pattern"/>
				<label>替换:</label>
				<input type="text" class="input_width" name="oldPlace"/>->
				<input type="text" class="input_width" name="newPlace"/>
				<label>字段名:</label>
				<input type="text" width="20px" class="input_width" name="fieldName"/>
				<label>字段类型:</label>
				<select class="input_width" name="fieldType">
				    <option value="int">int</option>
				    <option value="int">string</option>
				    <option value="int">date</option>
				</select>
				<label>字段长度:</label>
				<input type="text" class="input_width" name="fieldLenth"/>r
		</div>
		<a onclick="SimpleDataCollect.addField()" class="easyui-linkbutton">添加过滤字段</a>
		<a onclick="SimpleDataCollect.submitDataCollect()" class="easyui-linkbutton">爬取数据</a>
     </div>
</div>
