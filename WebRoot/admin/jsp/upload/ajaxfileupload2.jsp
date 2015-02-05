<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/base/basePath.jsp" %>
<html>
	<head>
	<title>文件上传</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
	<link href="<%=basePath %>admin/js/ajaxupload/ajaxfileupload.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<%=basePath %>admin/js/ajaxupload/ajaxfileupload.js"></script>
	<script type="text/javascript">

		//触发选择上传文件事件
		function openBrowse(){
			var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false; 
			if(ie){ 
				document.getElementById("fileToUpload").click(); 
			}else{
				var a=document.createEvent("MouseEvents");
				a.initEvent("click", true, true);  
				document.getElementById("fileToUpload").dispatchEvent(a); 
			} 
		}
	
		function ajaxFileUpload(){

			var size = $('#fileToUpload')[0].files[0].size;
			if(size>3*1024*1024){
				parent.MessageUtil.errorShow('上传文件不可大于3M!');
				return;
			}
			
			$("#loading").ajaxStart(function(){
				$(this).show();
			}).ajaxComplete(function(){
				$(this).hide();
			});

			$.ajaxFileUpload({
				url: app.contextPath+'servlet/imageUploadServlet',
				secureuri:false,
				method:'POST',
				fileElementId:'fileToUpload',
				dataType: 'json',
				data:{name:'logan', id:'id'},
				success: function (data, status){
					if(data && data.code == '0'){
						$('#headImg').attr('src',app.contextPath+data.message);
					}else{
						alert('上传失败!');
					}
				},
				error: function (data, status, e){
					if(data){
						alert(data.message);
					}else{
						alert('上传失败!');
					}
				}
			});
			return false;
		}

	</script>	
	</head>
<body>
	<img id="loading" src="<%=basePath %>admin/js/ajaxupload/loading.gif" style="display: none;">
	<form name="form" enctype="multipart/form-data" style="display: none;">
		<input id="fileToUpload" type="file" name="fileToUpload" onchange="ajaxFileUpload()">
	</form>
	<button class="button" onclick="openBrowse();">上传</button>
	<img id="headImg" alt="" width="100" height="100" src="">
</body>
</html>