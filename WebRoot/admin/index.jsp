<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/admin/jsp/base/basePath.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>UI框架</title>
    <link rel="stylesheet" type="text/css" href="admin/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/easyui/themes/icon.css">
    <script type="text/javascript" src="admin/js/jquery/jquery-1.8.1.js"></script>
    <script type="text/javascript" src="admin/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="admin/js/common/tree.js"></script>
</head>
<body class="easyui-layout">  
    <div region="north" title="North Title" split="true" style="height:100px;"></div>  
    <div region="south" title="South Title" split="true" style="height:100px;"></div>  
    <div region="east" iconCls="icon-reload" title="East" split="true" style="width:100px;"></div>  
    <div id="west" region="west" split="true" title="West" style="width:160px;"></div>  
    <div id="center" region="center" class="easyui-tabs" style="padding:5px;background:#eee;"></div>  
</body> 
</html>
<script type="text/javascript">
	function openTab(plugin, title) {
		if ($('#center').tabs('exists', title)) {
			$('#center').tabs('select', title);
		} else {
			$('#center').tabs('add', {
				title : title,
				href : plugin,
				closable : true
			});
		}
	}
</script>
