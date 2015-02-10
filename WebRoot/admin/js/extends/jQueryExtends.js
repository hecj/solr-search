/**
 * jQuery AJAX全局处理
 */
$.ajaxSetup({  
    contentType : "application/x-www-form-urlencoded;charset=utf-8",  
    complete : function(XMLHttpRequest, textStatus) {  
        if (XMLHttpRequest.status == 999) {  
			window.top.location = app.basePath+'admin';
        }
    }
});

/**
 * 自定义jQuery
 */
jQuery.extend({
	test:function(){
		return '你好，test!';
	}
	
});
