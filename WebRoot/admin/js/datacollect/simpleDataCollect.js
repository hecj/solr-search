
var SimpleDataCollect = {
    /*
     * 添加字段
     */
	addField:function(){
		var defaultField = jQuery("#ID_fieldContent #ID_defaultField").html();
		jQuery("#ID_fieldContent ul").append("<li>"+defaultField+"</li>")
	},
	/*
	 * 提交数据爬取
	 */
	submitDataCollect:function(){
		
		jQuery.get("admin/data/dataCollect.htm?operator=submitDataCollect",{}, function(data){
			
			
		},"json");
		
	}
};

(function(){
	jQuery(document).ready(function(){
		
	});
})();
