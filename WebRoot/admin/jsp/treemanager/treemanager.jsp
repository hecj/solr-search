<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Seacher</title>
    <%@include file="/admin/jsp/base/basePath.jsp" %> 
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
    <style type="text/css">
    	
    	body{
			background-color: #EEE;
		}
    
    </style>
  	<script type="text/javascript">
  	</script>
  </head>
<body class="easyui-layout" id="mainLayout">
    <div region="center"  style="padding:0px;background:#eee;" style="overflow: hidden;">
	    <table class="easyui-treegrid" style="width:700px;height:250px"
			data-options="
				url:'<%=basePath %>admin/tree/menuTree.htm?operator=initTree&moduleId=10',
				idField: 'id',
				treeField: 'text',
				fit:'true'
			">
		<thead>
			<tr>
				<th data-options="field:'text'" width="220">菜单列表</th>
				<th data-options="field:'id'" width="220">模块Id</th>
				<th data-options="field:'state'" width="220">状态</th>
				<th data-options="field:'attributes'" width="220">属性</th>
			</tr>
		</thead>
	</table>
    </div>  
</body>
</html>
