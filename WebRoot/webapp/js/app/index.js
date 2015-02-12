$(function(){
	
	//绑定事件
	$('#login').bind('click',app.submit);
	$('#reset').bind('click',app.reset);
	$('#formLogin input[name=usercode]').val('hecj');
	$('#formLogin input[name=password]').val('hecj');
	
});
var app = app || {};
app = {
	basePath :'http://localhost:8080/solr-search/',
	submit: function(){
		$.ajax({
			type: 'POST',
			url: app.basePath+'admin/user/user.htm?operator=webappLogin',
			data: $('#formLogin').serialize(),
			dataType: 'json',
			success: function(data){
			     if(data.code == '0'){
			    	 //$.mobile.changePage("#page_main", {reverse:false}); 
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
	}
}
