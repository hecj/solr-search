<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>用户登陆</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
    <style type="text/css">
    	label{
    		display:inline-block;
    		width: 70px;
    		text-align: right;
    	}
    	div{
    		height: 35px;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
	    	document.onkeydown = function(e){
	    	    var ev = document.all ? window.event : e;
	    	    if(ev.keyCode==13) {
	    	    	submitForm();
	    	    }
	    	}
    	}); 
    
	    var submitForm = function(){
			//validate
			var isValid = $("form").form('validate');
			if (!isValid){
				return;
			}
			//sumbit
		    $('form').form('submit', {
		    	url : app.contextPath+'admin/user/user.htm?operator=login',
		        success: function(data){
			        var data = eval('(' + data + ')');
			        if (data.code == '0'){
			        	parent.MessageUtil.messageShow(data.message);
			        	parent.location.reload();
			        }else{
			        	parent.MessageUtil.errorShow(data.message);
				    }
		        }
		    });
		}
    </script>
  </head>
  <body>
  	<form method="post">
  		<br/>
  		<div>
  			<label for="name">用户名:</label>
			<input type="text" name="usercode" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'">
  		</div>
  		<div>
  			<label for="name">密码:</label>
			<input type="password" name="password" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
  		</div>
  	</form>
  </body>
</html>
