/**
 * jQuery AJAX全局处理
 */
$.ajaxSetup({  
    contentType : "application/x-www-form-urlencoded;charset=utf-8",  
    complete : function(XMLHttpRequest, textStatus) {  
        if (XMLHttpRequest.status == 999) {  
        	top.location.href='http://localhost:8080/solr-search/admin/index.html';
            return;  
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
