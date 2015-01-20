<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
	<head>
	<title>文件上传</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
	<link href="ajaxfileupload.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="ajaxfileupload.js"></script>
	<script type="text/javascript">
		function ajaxFileUpload(){
			
			$("#loading").ajaxStart(function(){
				$(this).show();
			}).ajaxComplete(function(){
				$(this).hide();
			});
			
			$.ajaxFileUpload({
				url: app.basePath+'servlet/imageUploadServlet',
				secureuri:false,
				fileElementId:'fileToUpload',
				dataType: 'content',
				data:{name:'logan', id:'id'},
				success: function (data, status){
					alert(data);
				},
				error: function (data, status, e){
					alert(data);
				}
			});
			
			return false;
		}
	</script>	
	</head>
<body>
	<img id="loading" src="loading.gif" style="display:none;">
	<form name="form" action="" method="POST" enctype="multipart/form-data">
		<input id="fileToUpload" type="file" size="45" name="fileToUpload" class="input">
		<button class="button" id="buttonUpload" onclick="return ajaxFileUpload();">上传</button>
	</form> 
</body>
</html>