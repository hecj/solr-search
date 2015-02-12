$(function(){
	
	//绑定事件
	//$('#login').bind('click',app.submit);
	//$('#reset').bind('click',app.reset);
	//$('#formLogin input[name=usercode]').val('hecj');
	//$('#formLogin input[name=password]').val('hecj');
	//初始化主页面
	//$(document).on("pageinit","#page_main",app.initPageMain);
	
});

/**
 * 页面加载事件
 */
$(document).on("pagebeforecreate", function(event) {
	
});
$(document).on("pagecreate", function(event) {

});
$(document).on("pageinit", function(event) {

});
/**
 * webApp
 */
var app = app || {};
app = {
	basePath :'http://192.168.1.101:8080/solr-search/',
	user:{},
	submit: function(){
		$.ajax({
			type: 'POST',
			url: app.basePath+'admin/user/user.htm?operator=webappLogin',
			data: $('#formLogin').serialize(),
			dataType: 'json',
			success: function(data){
			     if(data.code == '0'){
			    	 app.user = data.message;
			    	 var userStr = JSON.stringify(app.user);
			    	 $.cookie("user", userStr, {
		                    expires:1
		             });
			    	 $('#login_success').click();
			     }else{
			    	 $('#loginMessage').text(data.message);
			    	 $('#loginMessage').popup('open');
			     }
			},
			beforeSend: function(XMLHttpRequest){
				common.showLoader();
			},
			complete: function(XMLHttpRequest){
				common.hideLoader();
			}
		});
	},
	reset: function(){
		$('#formLogin')[0].reset();
	},
	initPageMain: function(){
		var userStr = $.cookie("user");
		if(userStr){
			var user = JSON.parse(userStr);
			$('#show_usercode').text(user.username);
		}
	}
}
