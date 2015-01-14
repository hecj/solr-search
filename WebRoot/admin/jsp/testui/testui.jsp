<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>测试UI</title>
    <%@include file="/admin/jsp/base/basePath.jsp" %> 
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript">
		$(function(){
			$.messager.progress({
				text : '数据加载中....'
			});
		});

  	</script>
  </head>
<body class="easyui-layout" data-options="fit:true,border:false">
	
	

</body>
</html>
