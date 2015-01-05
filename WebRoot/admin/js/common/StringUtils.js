
/**
 * 字面式对象封装
 */
var StringUtils = {
	trims:function(str){
		return str.replace(/(^\s*)|(\s*$)/g,"");
	},
	isObjEmpty:function(obj){
		return obj == undefined || obj == 'undefined' || obj.length==0;
	}
};