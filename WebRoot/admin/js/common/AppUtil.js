/**
 * 系统工具函数
 */
var AppUtil = {
		isObjEmpty:function(obj){
			return obj == undefined || obj == 'undefined' || obj.length==0;
		},
		createDiv:function(parentDivId,newDivId){
			jQuery('#'+parentDivId).append('<div id="'+newDivId+'"></div>');
		}
		
}