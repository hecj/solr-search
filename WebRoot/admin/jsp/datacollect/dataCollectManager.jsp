<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<!-- --> 
<jsp:include page="/admin/jsp/base/easyUI.jsp"/>
<head>
	<script type="text/javascript" src="admin/js/datacollect/dataCollectManager.js"></script>
	<script type="text/javascript">

	jQuery(function(){
		DataCollectSearch.initGrid();
	});

</script>
</head>
<body>
<div style="height: 100%">
	<div id="Id_dataCollectSearch"></div>
</div>
<div id=Id_dataCollectMessage></div>ddd
<div id=Id_dataCollectEdit></div>
<div id=Id_dataCollectAdd></div>
</body>
</html>