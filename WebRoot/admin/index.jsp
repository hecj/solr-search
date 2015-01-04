<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/admin/jsp/base/basePath.jsp" %>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Seacher</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript" src="<%=basePath%>admin/js/common/InitSystem.js"></script>
  </head>
<body class="easyui-layout">  
    <div region="north" split="false" style="height:100px;"></div>  
    <div region="south" split="false" style="height:30px;">
    	<jsp:include page="./jsp/common/footer.jsp"/>
    </div>  
    <div id="west" region="west" split="true" title="系统工具" style="width:160px;"></div>  
    <div id="center" region="center" class="easyui-tabs" style="padding:0px;background:#eee;height: 100%" fit="false"></div>  
</body>
</html>
