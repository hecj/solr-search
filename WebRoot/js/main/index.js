var AppIndex = {
		/*
		 * 动态绑定menu高亮
		 */
		bindMenuHl:function(index){
			jQuery("#menu .menu ul li:eq("+index+") a").attr("class","menu_default_hover");;
		}
		
}
