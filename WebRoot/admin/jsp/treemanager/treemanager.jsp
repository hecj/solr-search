<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Seacher</title>
    <%@include file="/admin/jsp/base/basePath.jsp" %> 
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript">
  	</script>
  </head>
<body class="easyui-layout" id="mainLayout">
    <div region="center" style="padding:0px;background:#eee;" style="overflow: hidden;">
	    <table class="easyui-treegrid" fit="true"
			data-options="
				url:'<%=basePath %>admin/tree/menuTree.htm?operator=treeManagerQuery&moduleId=10',
				idField: 'moduleId',
				treeField: 'name'
			">
		<thead>
			<tr>
				<th data-options="field:'name'" width="220">菜单列表</th>
				<th data-options="field:'moduleId'" width="220">模块Id</th>
				<th data-options="field:'parentId'" width="220">父模块</th>
				<th data-options="field:'state'" width="220">状态</th>
				<th data-options="field:'url'" width="220">路径</th>
				<th data-options="field:'icons'" width="220">图标</th>
				<th data-options="field:'leaf'" width="220">是否叶子</th>
			</tr>
		</thead>
	</table>
    </div>  
</body>
</html>
