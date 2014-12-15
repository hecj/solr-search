<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="admin/css/datacollect/simpleDataCollect.css">
<script type="text/javascript" src="admin/js/datacollect/simpleDataCollect.js"></script>
<div class="ID_mainContent">
	 <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">base网站:</label>
        <input class="easyui-validatebox" type="text" name="baseURL" data-options="required:true" />
     </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">base选择器:</label>
        <input class="easyui-validatebox" type="text" name="baseSelect"/>
     </div>
     <div class="CLS_mainContentDivHegiht">
        <label for="name" class="data_label_width">数据库:</label>
        <select id="cc" class="easyui-combobox" name="dataBaseType" style="width:150px;">
		    <option value="MySQL">MySQL</option>
		</select>
     </div>
     <div id="ID_fieldContent">
     	<ul>
     		<li>
     			<label>字段选择器:</label>
				<input type="text" name="fieldSelect"/>
				<label>正则:</label>
				<input type="text"/>
				<label>替换:</label>
				<input type="text" class="input_width" name="oldPlace"/>->
				<input type="text" class="input_width" name="newPlace"/>
				<label>字段名:</label>
				<input type="text" width="20px" class="input_width" name="fieldName"/>
				<label>字段类型:</label>
				<select class="easyui-combobox" class="input_width" name="fieldType">
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
				<input type="text"/>
				<label>正则:</label>
				<input type="text"/>
				<label>替换:</label>
				<input type="text" class="input_width"/>->
				<input type="text" class="input_width"/>
				<label>字段名:</label>
				<input type="text" width="20px" class="input_width"/>
				<label>字段类型:</label>
				<select class="easyui-combobox" class="input_width">
				    <option value="int">int</option>
				    <option value="int">string</option>
				    <option value="int">date</option>
				</select>
				<label>字段长度:</label>
				<input type="text" class="input_width"/>
		</div>
		<a onclick="SimpleDataCollect.addField()" class="easyui-linkbutton">添加过滤字段</a>
		<a onclick="SimpleDataCollect.submitDataCollect()" class="easyui-linkbutton">爬取数据</a>
     </div>
</div>
