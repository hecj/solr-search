<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/admin/jsp/base/basePath.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Seacher</title>
    <link rel="stylesheet" type="text/css" href="admin/easyui/themes/black/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="admin/css/common/customIcon.css">
    <link rel="stylesheet" type="text/css" href="admin/css/common/common.css">
    <script type="text/javascript" src="admin/js/jquery/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="admin/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="admin/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="admin/js/jquery/jquery.json-2.2.js"></script>
    <script type="text/javascript" src="admin/js/common/InitSystem.js"></script>
    <script type="text/javascript" src="admin/js/common/MessageUtil.js"></script>
    <script type="text/javascript" src="admin/js/common/AppUtil.js"></script>
    <script type="text/javascript" src="admin/js/common/AppEntity.js"></script>
</head>
<body class="easyui-layout">  
    <div region="north" split="false" style="height:100px;"></div>  
    <div region="south" split="false" style="height:30px;">
    	<jsp:include page="./jsp/common/footer.jsp"/>
    </div>  
    <div id="west" region="west" split="true" title="系统工具" style="width:160px;"></div>  
    <div id="center" region="center" class="easyui-tabs" style="padding:0px;background:#eee;height: 100%" fit="true"></div>  
</body> 
</html>

<%@include file="debug.jsp"%>
