<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:include page="/admin/jsp/base/easyUI.jsp" />
<%@include file="/WEB-INF/jsp/base/basePath.jsp" %>
<script type="text/javascript" src="<%=basePath %>admin/js/ajaxupload/ajaxfileupload.js"></script>
<style type="text/css">
	.divHeight{
		height: 35px;
	}
	.labelWidth{
		width: 100px;
		display: inline-block;
		text-align: right;
	}
	.imgHead{
		text-align: center;
		margin-bottom: 10px;
		margin-top: -10px;
	}
</style>
<script type="text/javascript">

	$(function(){
		
	    $('#rolecode').combobox({
	        url: app.basePath+'admin/role/role.htm?operator=roleList',
	        valueField:'value',
	        textField:'text'
	    });
	});

	var submitForm = function(dialog,parentGrid){

		//validate
		var isValid = $("form").form('validate');
		if (!isValid){
			return;
		}
		//sumbit
	    $('form').form('submit', {
	    	url : app.basePath+'admin/user/user.htm?operator=addUserSub',
	        success: function(data){
		        var data = eval('(' + data + ')');
		        if (data.code == '0'){
		        	parent.MessageUtil.messageShow(data.message);
		        	dialog.dialog('close');
		        	parentGrid.datagrid('reload');
	
		        }else{
		        	parent.MessageUtil.errorShow(data.message);
			    }
	        }
	    });
	}

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
		
		$("#loading").ajaxStart(function(){
			$(this).show();
		}).ajaxComplete(function(){
			$(this).hide();
		});
		
		$.ajaxFileUpload({
			url: app.basePath+'servlet/imageUploadServlet',
			secureuri:false,
			method:'POST',
			fileElementId:'fileToUpload',
			dataType: 'json',
			data:{name:'logan', id:'id'},
			success: function (data, status){
				if(data && data.code == '0'){
					$('input[name=headImg]').val(data.message);
					$('#headImg').attr('src',app.staticPath+data.message);
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
<form name="form" enctype="multipart/form-data" style="display: none;">
		<input id="fileToUpload" type="file" name="fileToUpload" onchange="ajaxFileUpload()">
</form>
<div><br/>
	<form method="post">
		 <div>
		     <div class="imgHead">
		     	<input name="headImg" type="hidden">
		     	<img id="headImg" alt="" src="" width="80" height="80">
		     	<img id="loading" src="<%=basePath %>admin/js/ajaxupload/loading.gif" style="display: none;">
		     	<a href="javascript:void(0)" onclick="openBrowse();">浏览</a>
			 </div>
		 </div>
		 <div class="divHeight">
		     <label class="labelWidth">用户名:</label>
		     <input name="usercode" size="20" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
	     </div>
		 <div class="divHeight">
		     <label class="labelWidth">密码:</label>
		     <input name="password" type="password" size="20" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
	     </div>
	     <div class="divHeight">
		     <label class="labelWidth">昵称:</label>
		     <input name="username" size="20" />
	     </div>
	     <div class="divHeight">
		     <label class="labelWidth">手机号:</label>
		     <input name="telPhone"/>
	     </div>
	     <div class="divHeight">
		     <label class="labelWidth">邮箱:</label>
		     <input name="email" size="20" />
	     </div>
	     <div class="divHeight">
		     <label class="labelWidth">角色:</label>
		     <input id="rolecode" name="rolecode" size="20" class="easyui-combobox" data-options="required:true,validType:'notEmpty'"/>
	     </div>
     </form>
</div>
