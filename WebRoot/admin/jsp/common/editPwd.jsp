<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>修改密码</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
    <style type="text/css">
    	label{
    		display:inline-block;
    		width: 90px;
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
    
	    var submitForm = function(dialog){
			//validate
			var isValid = $("form").form('validate');
			if (!isValid){
				return;
			}

			var password = $("input[name=password]").val();
			var newpassword = $("input[name=newpassword]").val();
			var repassword = $("input[name=repassword]").val();
			//判断新密码一致
			if(newpassword != repassword){
				parent.MessageUtil.errorShow('新密码不相同!');
				return;
			}

			//判断新旧密码一致
			if(password == newpassword){
				parent.MessageUtil.errorShow('新旧密码不可相同!');
				return;
			}
			
			//sumbit
		    $('form').form('submit', {
		    	url : app.basePath+'admin/user/user.htm?operator=editPwd',
		        success: function(data){
			        var data = eval('(' + data + ')');
			        if (data.code == '0'){
			        	parent.MessageUtil.messageShow(data.message);
						dialog.dialog('close');
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
  			<label for="name">密码:</label>
			<input type="password" name="password" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'">
  		</div>
  		<div>
  			<label for="name">新密码:</label>
			<input type="password" name="newpassword" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
  		</div>
  		<div>
  			<label for="name">再输一次:</label>
			<input type="password" name="repassword" class="easyui-validatebox" data-options="required:true,validType:'notEmpty'"/>
  		</div>
  	</form>
  </body>
</html>
