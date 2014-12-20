<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="admin/css/datacollect/simpleDataCollect.css">
<div><br/>
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
     <div style="position:absolute;width:887px;height:130px;clear:both;">
		<table class="easyui-datagrid" style="width: auto; height: 128px;" data-options="singleSelect:true,collapsible:true,rownumbers : true,">
			<thead>
				<tr>
					<th data-options="field:'fieldSelect'">字段选择器</th>
					<th data-options="field:'selectMethod'">解析方法</th>
					<th data-options="field:'pattern'">正则</th>
					<th data-options="field:'oldPlace'">替换</th>
					<th data-options="field:'fieldName'">字段名</th>
					<th data-options="field:'fieldType'">字段类型</th>
					<th data-options="field:'fieldLenth'">字段长度</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${dataCollectParams.dataFields != null}">
					<c:forEach var="f" items="${dataCollectParams.dataFields}">
						<tr>
							<td>${f.fieldSelect }</td>
							<td>${f.selectMethod }
								<c:if test="${f.targetAttr!=''}">[</c:if>
									${f.targetAttr}
								<c:if test="${f.targetAttr!=''}">]</c:if>
							</td>
							<td>${f.pattern }</td>
							<td>${f.oldPlace }
								<c:if test="${f.oldPlace!=''}">-&gt;</c:if>
								${f.newPlace }
							</td>
							<td>${f.fieldName }</td>
							<td>${f.fieldType }</td>
							<td>${f.fieldLenth }</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>