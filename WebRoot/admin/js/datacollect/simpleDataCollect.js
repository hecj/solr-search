



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
		var baseURL = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=baseURL]").val();
		var baseSelect = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=baseSelect]").val();
		var dataBaseType = jQuery(".ID_mainContent .CLS_mainContentDivHegiht input[name=dataBaseType]").val();

		
		
		jQuery.get("admin/data/dataCollect.htm?operator=submitDataCollect",{}, function(data){
			
			
		},"json");
		
	}
};

(function(){
	jQuery(document).ready(function(){
		
	});
})();
