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

	var userMenu;
	$(function(){
		userMenu = $('#userMenu').menu({});
	});

	/*显示用户菜单*/
	var showUserMenuFun = function(e){
		p = common.mouse(e);
		userMenu.menu('show', {
	        left: p.x,
	        top: p.y
	    });
	}
	
	/*修改密码*/
	var editPwdFun = function(){
		var dialog = parent.app.dialogModel({
			title: '修改密码',
			width: 350,
			height: 230,
			closable:false,
			url : app.basePath+'admin/jsp/common/editPwd.jsp',
			buttons:[{
				text:'提交',
				handler:function(){
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog);
				}
			},{
				text:'关闭',
				handler:function(){
					dialog.dialog('close');
				}
			}]
		});
	}
	
	/*退出系统*/
	var logoutFun = function(){
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
										用户名:<a onclick="showUserMenuFun(event)" href="javascript:void(0);">${context.user.usercode }</a>
									</c:if>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	
</table>

<div id="userMenu">
	<div onclick="editPwdFun()" data-options="iconCls:'icon-edit'">
		修改密码
	</div>
	<div class="menu-sep"></div>
	<div onclick="logoutFun()" data-options="iconCls:'icon-close'">
		退出系统
	</div>
</div>
