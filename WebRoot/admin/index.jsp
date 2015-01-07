<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Seacher</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript">
  		var centerTabs ;
		$(function(){
			$('#customMenu').tree( {
				//url : app.basePath+'admin/js/common/tree2.json',
				url : app.basePath+'admin/tree/menuTree.htm?operator=initTree&moduleId=10',
				onClick : function(node) {
					if(!StringUtils.isObjEmpty(node.state)){
						if(node.state == "open"){
							$(this).tree('collapse',node.target); 
						}else{
							$(this).tree('expand',node.target);  
						}
					}
					if (!StringUtils.isObjEmpty(node.attributes)) {
						addTab(node);
					}
				}
			});
			
			$('#systemTools').tree( {
				url : app.basePath+'admin/js/common/systemtools.json',
				onClick : function(node) {
					if(!StringUtils.isObjEmpty(node.state)){
						if(node.state == "open"){
							$(this).tree('collapse',node.target); 
						}else{
							$(this).tree('expand',node.target);  
						}
					}
					if (!StringUtils.isObjEmpty(node.attributes)) {
						addTab(node);
					}
				}
			});
			
			centerTabs = $('#center_tabs').tabs({
				tools:[{
					 text:'刷新',
					 handler : function() {
							var panel = centerTabs.tabs('getSelected').panel('panel');
							var frame = panel.find('iframe');
							try {
								if (frame.length > 0) {
									for (var i = 0; i < frame.length; i++) {
										frame[i].contentWindow.document.write('');
										frame[i].contentWindow.close();
										frame[i].src = frame[i].src;
									}
									if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
										try {
											CollectGarbage();
										} catch (e) {
										}
									}
								}
							} catch (e) {
							}
						}
				},
				{
					text : '关闭',
					handler : function() {
						var index = centerTabs.tabs('getTabIndex', centerTabs.tabs('getSelected'));
						var tab = centerTabs.tabs('getTab', index);
						if (tab.panel('options').closable) {
							centerTabs.tabs('close', index);
						} else {
							MessageUtil.messageShow('<font color=red>[' + tab.panel('options').title + ']不可以被关闭！</font>');
						}
					}
				}]
			});
		});

		function addTab(node){
			if (centerTabs.tabs('exists', node.text)) {
				centerTabs.tabs('select',node.text);
			}else{
				if(node.attributes.url && node.attributes.url.length>0){
					centerTabs.tabs('add', {
						title : node.text,
						content:'<iframe src="'+node.attributes.url+'" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
						closable : true,
						border : false,
						fit : true,
						loadingMessage : MessageUtil.loadingPageMessage
					});

					$('#mainLayout').layout('panel', 'center').panel({
						onResize : function(width, height) {
							app.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
						}
					});
				}
			}
		}
  	</script>
  </head>
<body class="easyui-layout" id="mainLayout">
    <div region="north" split="false" style="height:100px;"></div>  
    <div region="south" split="false" style="height:30px;">
    	<jsp:include page="./jsp/common/footer.jsp"/>
    </div>  
    <div id="west" region="west" split="true" style="width:160px;">
    	<div class="easyui-accordion" fit="true">
	    	<div id="customMenu" title="常用菜单"></div>
	    	<div id="systemTools" title="系统工具"></div>
    	</div>
    </div>  
    <div region="center"  style="padding:0px;background:#eee;" style="overflow: hidden;">
    	<div id="center_tabs" class="easyui-tabs" fit="true">
    		<div title="欢迎页">
    			<iframe src="admin/jsp/common/webcome.jsp" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>
    		</div>
    	</div>
    </div>  
</body>
</html>
