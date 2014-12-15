<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="admin/css/datacollect/simpleDataCollect.css">
<script type="text/javascript" src="admin/js/datacollect/simpleDataCollect.js"></script>
<div class="main_content">
	 <div class="main_content_div_hegiht">
        <label for="name" class="data_label_width">base网站:</label>
        <input class="easyui-validatebox" type="text" name="name" data-options="required:true" />
     </div>
     <div class="main_content_div_hegiht">
        <label for="name" class="data_label_width">jq选择器:</label>
        <input class="easyui-validatebox" type="text"/>
     </div>
     <div class="main_content_div_hegiht">
        <label for="name" class="data_label_width">数据库:</label>
        <select id="cc" class="easyui-combobox" name="dept" style="width:150px;">
		    <option value="MySQL">MySQL</option>
		</select>
     </div>
     <div id="field_content">
     	<ul>
     		<li>
     			<label>字段1选择器:</label>
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
		     </li>
		</ul>
		<a id="idd_addField" class="easyui-linkbutton">添加过滤字段</a>
		<a id="btn" class="easyui-linkbutton">爬q数据</a>
     </div>

</div>
