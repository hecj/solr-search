(function(){
	jQuery(document).ready(function(){
		/*第一种方式加载树*/
		/*
		jQuery('#west').panel({  
		    href:'admin/jsp/common/tree.jsp',
		    onLoad:function(){
		    }  
		});
		*/
		/*第二种方式加载树*/
		jQuery('#west').tree( {
			url : 'admin/js/common/tree.json',
			onClick : function(node) {
				var url = node.attributes.url;
				var title = node.text;
				if (url != undefined && url != "undefined") {
					SystemApp.openTab(url, title);
				}
			}
		});
	});
})();

/**
 * 系统应用处理函数 
 */
var SystemApp = {
	openTab : function(url, title) {
		if ($('#center').tabs('exists', title)) {
			$('#center').tabs('select', title);
			/* 重新打开tab */
			var tab = $('#center').tabs('getSelected');
			$('#center').tabs('update', {
				tab : tab,
				options : {
					title : title,
					href : url
				}
			});
		} else {
			$('#center').tabs('add', {
				title : title,
				href : url,
				closable : true,
				loadingMessage : MessageUtil.loadingPageMessage
			});
		}
	}
}
