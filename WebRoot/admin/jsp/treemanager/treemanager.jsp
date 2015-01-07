<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Seacher</title>
    <%@include file="/admin/jsp/base/basePath.jsp" %> 
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript">
  	</script>
  </head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div region="center" data-options="fit:true,border:false">
	    <table class="easyui-treegrid"
			data-options="
				url:'<%=basePath %>admin/tree/menuTree.htm?operator=treeManagerQuery&moduleId=0',
				idField: 'moduleId',
				treeField: 'name',
				toolbar:'#toolbar',
				fit:true,border:false
			">
			<thead>
				<tr>
					<th data-options="field:'name',align:'left'" width="220">模块列表</th>
					<th data-options="field:'moduleId',align:'center'" width="50">模块Id</th>
					<th data-options="field:'parentId',align:'center'" width="50">父模块</th>
					<th data-options="field:'state',align:'center'" width="50">状态</th>
					<th data-options="field:'url',align:'center'" width="300">路径</th>
					<th data-options="field:'icons',align:'center'" width="60">图标</th>
					<th data-options="field:'leaf',align:'center'" width="50">是否叶子</th>
				</tr>
			</thead>
		</table>
    </div>
		<div id="toolbar" style="display: none;">
			<table>
				<tr>
					<td>
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="findFun();">添加</a>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
