var SimpleDataCollect = {
	addField:function(){
		//var defaultField = jQuery("#field_content #addfield").html();
		alert(1);
	},
};


(function(){
	jQuery(document).ready(function(){
		//绑定Click事件
		jQuery("#idd_addField").click(SimpleDataCollect.addField());
	});
})();

