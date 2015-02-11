$(function(){
	
	//绑定事件
	$('#login').bind('click',app.submit);
	$('#reset').bind('click',app.reset);
	$('#formLogin input[name=usercode]').val('hecj');
	$('#formLogin input[name=password]').val('hecj');
	
});
var app = app || {};
app = {
	submit: function(){
		$.ajax({
			type: 'POST',
			url: '../../admin/user/user.htm?operator=login',
			data: $('#formLogin').serialize(),
			dataType: 'json',
			success: function(data){
			     if(data.code == '0'){
			    	 $.mobile.changePage("main.html", "pop"); 
			     }else{
			    	 //alert('登陆失败');
			     }
			},
			beforeSend: function(XMLHttpRequest){
				showLoader();
			},
			complete: function(XMLHttpRequest){
				hideLoader();
			}
		});
	
		
	},
	reset: function(){
		$('#formLogin')[0].reset();
	}
}


function showLoader() {  
    $.mobile.loading('show', {  
        text: '登陆中...',
        textVisible: true,
        theme: 'a',
        textonly: false,
        html: ""
    });  
}  
  
function hideLoader(){  
    $.mobile.loading('hide');  
}  