<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
  		var centerTabs ;
  		var customMenu ;
  		var systemTools ;
  		var changeTheme ;
  		
		$(function(){
			changeTheme = $('#changeTheme').menu({});
			customMenu = $('#customMenu').tree( {
				//url : app.contextPath+'admin/js/common/tree3.json',
				//url : app.contextPath+'admin/tree/tree.htm?operator=initTree&moduleId=0001',
				url : app.contextPath+'admin/tree/tree.htm?operator=init&rootId=0',
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
				},
				onBeforeLoad:function(node,param){
					//$.messager.progress({
					//	text : '数据加载中....'
					//});
				},
				onLoadSuccess:function(node, data){
					$(this).tree('expandAll',$(this).tree('getRoot').target);
					//$.messager.progress('close');
				}
			});
			
			systemTools = $('#systemTools').tree( {
				url : app.contextPath+'admin/js/common/systemtools.json',
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
			
			$('#easyUIAPI').tree( {
				url : app.contextPath+'admin/tree/tree.htm?operator=initTree&moduleId=0002',
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
					text : '&nbsp;更换主题',
					iconCls:'hey-ajax',
					handler : function(e) {
						var p = common.mouse(e);
						changeTheme.menu('show', {
					        left: p.x-40,
					        top: p.y+10
					    });
					}
				},{
					 text:'刷新',
					 iconCls:'icon-reload',
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
					iconCls:'icon-close',
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

					/*$('#mainLayout').layout('panel', 'center').panel({
						onResize : function(width, height) {
							app.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
						}
					});*/
				}
			}
		}
	    /*更换主题*/
		var changeThemeFun = function(themeName){
			var $easyUITheme = $('#easyUITheme');
			var url = $easyUITheme.attr('href');
			var newUrl = url.substring(0,url.indexOf('themes'))+'themes/'+themeName+'/easyui.css';
			if(url != newUrl){
				$easyUITheme.attr('href',newUrl);
				var $iframe = $('iframe');
				for(var i=0; i<$iframe.length;i++){
					var ifm = $iframe[i];
					$(ifm).contents().find('#easyUITheme').attr('href',newUrl);
				}
			}
			$.cookie('easyuiThemeName',themeName,{
				expires:7
			});
		}
  	</script>
  <div class="easyui-accordion" data-options="region:'center',fit:true,border:false">
	<div id="customMenu" title="基本菜单" data-options="
				selected:true,
				tools:[{
					iconCls:'icon-reload',
					handler:function(){
						$('#customMenu').tree('reload');
					}
				}]" style="overflow:auto;"></div>
	<div id="systemTools" title="系统工具" data-options="
				selected:true,
				tools:[{
					iconCls:'icon-reload',
					handler:function(){
						$('#systemTools').tree('reload');
					}
				}]" style="overflow:auto;">
	</div>
	
	<div id="easyUIAPI" title="EasyUI文档" data-options="
				selected:true,
				tools:[{
					iconCls:'icon-reload',
					handler:function(){
						$('#easyUIAPI').tree('reload');
					}
				}]" style="overflow:auto;">
	</div>
	
	
	<div id="changeTheme" class="easyui-menu" >
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('default')">
			default
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('gray')">
			gray
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('black')">
			black
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('bootstrap')">
			bootstrap
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('metro')">
			metro
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('metro-blue')">
			metro-blue
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('metro-gray')">
			metro-gray
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('metro-green')">
			metro-green
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('metro-orange')">
			metro-orange
		</div>
		<div class="menu-sep"></div>
		<div data-options="iconCls:'hey-ajax'" onclick="changeThemeFun('metro-red')">
			metro-red
		</div>
    </div>
	
	
	
  </div>
