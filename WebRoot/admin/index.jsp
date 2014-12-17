<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/admin/jsp/base/basePath.jsp" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>UI框架</title>
    <link rel="stylesheet" type="text/css" href="admin/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="admin/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="admin/css/common/common.css">
    <script type="text/javascript" src="admin/js/jquery/jquery-1.8.1.min.js"></script>
    <script type="text/javascript" src="admin/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="admin/js/common/initPanel.js"></script>
    <script type="text/javascript" src="admin/js/jquery/jquery.json-2.2.js"></script>
    <script type="text/javascript" src="admin/js/manager/dataCollect.js"></script>
</head>
<body class="easyui-layout">  
    <div region="north" split="false" style="height:100px;"></div>  
    <div region="south" split="false" style="height:30px;">
    	<jsp:include page="./jsp/common/footer.jsp"/>
    </div>  
    <div id="west" region="west" split="true" title="West" style="width:160px;height: 100%"></div>  
    <div id="center" region="center" class="easyui-tabs" style="padding:0px;background:#eee;width: 100%;height: 100%"></div>  
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
