/**
 * 共用类
 */
var common = common || {};

common.showLoader = function(option) {
	var defaultText = '加载中...';
	if(option){
		if(option.text){
			defaultText = option.text;
		}
	}
    $.mobile.loading('show', {  
        text: defaultText,
        textVisible: true,
        theme: 'a',
        textonly: false,
        html: ""
    });
}  

common.hideLoader = function(){
    $.mobile.loading('hide');  
}

common.getParams = function(e,name){
	if(!name){
		name = 'data';
	}
	var p = '?'+name+'=';
	var baseURI = $.mobile.path.parseUrl(e.target.baseURI);
	var search = baseURI.search ;
	return search.substring(search.indexOf(p)+p.length, search.length);
}