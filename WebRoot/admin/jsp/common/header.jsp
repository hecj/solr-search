<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.divUser{
		margin-top: 60px;
		margin-right: 20px;
		text-align: right;
		color: white;
	}
	
	.divUser a{
		color: white;
	}
	
</style>
<script type="text/javascript">

	var logout = function(){
		parent.$.messager.confirm('提示信息','确定要注销吗?',function(r){
			if (r){
				$.ajax({
					url:app.basePath+'admin/user/user.htm?operator=logout',
					data:{},
					async:true,
					dataType:'json',
					timeout:3000,
					type:'GET',
					cache:false,
					success:function(data){
						if(data.code == '0'){
							parent.MessageUtil.messageShow(data.message);
							parent.location.reload();
						}else{
							parent.MessageUtil.errorShow(data.message);
						}
					},
					error:function(data){
						parent.MessageUtil.messageShow('<font color=red>'+data.message+'</font>');
					}
				});
			}
		});
	} 

</script>
<table width="100%" >
		<tr >
			<td width="80%">
			</td>
			<td width="20%">
				<table width="100%">
					<tr>
						<td height="100%">
							<div class="divUser">
									<c:if test="${not empty context}">
										用户名:<a href="#">${context.user.usercode }</a>
										<a href="javascript:logout();" style="color: red">注销</a>
									</c:if>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	
</table>
