
var centerTabs;
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
		/*第二种方式加载树
		jQuery('#west').tree( {
			url : 'admin/js/common/tree.json',
			onClick : function(node) {
				if(!AppUtil.isObjEmpty(node.state)){
					if(node.state == "open"){
						jQuery(this).tree('collapse',node.target); 
					}else{
						jQuery(this).tree('expand',node.target);  
					}
				}
				var url = node.attributes.url;
				var title = node.text;
				if (!AppUtil.isObjEmpty(url)) {
					SystemApp.openTab(url, title);
				}
			}
		});
		*/
		
		$('#west').tree( {
			url : 'admin/js/common/tree.json',
			onClick : function(node) {
				addTab(node);
			}
		});
		
		centerTabs = $('#center').tabs('add', {
			title : '欢迎页2',
//			href : 'admin/jsp/common/webcome.jsp',
			content:'<iframe src="admin/jsp/datacollect/dataCollectManager.jsp" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
			closable : true,
			loadingMessage : MessageUtil.loadingPageMessage
		});
		
		
	});
})();

/**
 * 系统应用处理函数 
 */
var SystemApp = {
	openTab : function(url, title) {
		if (jQuery('#center').tabs('exists', title)) {
			jQuery('#center').tabs('select', title);
			/* 重新打开tab */
			var tab = jQuery('#center').tabs('getSelected');
			jQuery('#center').tabs('update', {
				tab : tab,
				options : {
					title : title,
					href : url
				}
			});
		} else {
			jQuery('#center').tabs('add', {
				title : title,
				href : url,
				closable : true,
				loadingMessage : MessageUtil.loadingPageMessage
			});
		}
	}
}


function addTab(node){
	
	if (centerTabs.tabs('exists', node.text)) {
		centerTabs.tabs('select',node.text);
	}else{
		if(node.attributes.url && node.attributes.url.length>0){
			/*$.message.progress({
				text:'页面加载中...',
				interval:100
			});
			*/
			alert(node.attributes.url);
			centerTabs.tabs('add', {
				title : node.text,
				content:'<iframe src="'+node.attributes.url+'" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
				closable : true,
				loadingMessage : MessageUtil.loadingPageMessage
			});
			
			/*centerTabs.tabs('add', {
				title : node.text,
//				href : 'admin/jsp/common/webcome.jsp',
				content:'<iframe src="'+node.attributes.url+'" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
				closable : true,
				loadingMessage : MessageUtil.loadingPageMessage
			});*/
		}
	}
}