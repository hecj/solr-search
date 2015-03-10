/**
 * 应用程序入口
 */
Ext.application( {
	requires : [ 'Ext.container.Viewport' ],
	name : 'AM',
	appFolder : 'app',
	launch : function() {

		/**
		 * 上,panel.Panel
		 */
		app.topPanel = Ext.create('Ext.panel.Panel', {
			region : 'north',
			height : 100
		});
		
		/**
		 * 右,tab.Panel
		 */
		app.rightPanel = Ext.create('Ext.tab.Panel', {
			region : 'center',
			layout : 'fit',
			tabWidth : 120,
			frame : true,
			items : [{
				title : '变更记录',
				loader: {
			        url: 'html/log.html',
			        autoLoad: true
			    }
			}]
		});
		
		/**
		 * 下,panel.Panel
		 */
		app.bottomPanel = Ext.create('Ext.panel.Panel', {
			region : 'south',
			height : 25,
			html : '<div style="text-align: center;line-height:20px;height:30px;">CopyRight@&nbsp;Author:HECJ</div>'
		});
		
		/**
		 * 左,panel.Panel
		 */
		app.leftPanel = Ext.create('Ext.panel.Panel', {
			region : 'west',
			title : '导航栏',
			width : 180,
			layout : 'accordion',
			collapsible : true,
			resizable : true,
		});
		
		/**
		 * Viewport
		 */
		Ext.create('Ext.container.Viewport', {
			layout : 'border',
			renderTo : Ext.getBody(),
			items : [ 
			         app.topPanel,
			         app.bottomPanel,
			         app.rightPanel,
			         app.leftPanel
			]
		});
		/**
		 * 组建树
		 */
		var buildTree = function(node) {
			return Ext.create('Ext.tree.Panel', {
				rootVisible : false,
				border : false,
				store : Ext.create('Ext.data.TreeStore', {
					autoLoad : true,
					nodeParam : 'id',
					root : {
						expanded : true,
						id :node.id
					},
					proxy: {
				        type: 'ajax',
				        url : '../ext/tree/tree.htm?operator=initTree',
				        reader: 'json'
				    },
				    fields: ['id', 'expanded', 'text', 'url','leaf']
				}),
				listeners : {
					itemclick : function(view, record, item, index, e) {
						var id = record.get('id');
						var text = record.get('text');
						var leaf = record.get('leaf');
						var url = record.get('url');
						if (leaf) {
							var panel = Ext.create('Ext.panel.Panel', {
							    title: text,
							    closable : true,
							    layout: {
				                    type: 'fit',
				                    align: 'stretch'
				                },
				                html: '<iframe src="'+app.basePath+url+'" width=100% height="100%" frameBorder="0"></iframe>'
							});
							var tabs = app.rightPanel.items.items;
							var isExist = false;
							var tabId ;
							for(var i = 0; i <tabs.length ; i++){
								if(tabs[i].title == text) {
									tabId = tabs[i].id;
									isExist = true;
								}
							}
							if(isExist){
								app.rightPanel.setActiveTab(tabId);
							}else{
								app.rightPanel.add(panel);
								app.rightPanel.setActiveTab(panel);
							}
						}
					},
					scope : this
				}
			});
		}

		/**
		 * 加载菜单树
		 */
		Ext.Ajax.request( {
			method : 'post',
			url : '../ext/tree/tree.htm?operator=initTree&id=0',
			success : function(response) {
				var json = Ext.JSON.decode(response.responseText)
				Ext.each(json, function(node) {
					var panel = Ext.create('Ext.panel.Panel', {
						id : node.id,
						title : node.text,
						layout : 'fit'
					});
					panel.add(buildTree(node));
					app.leftPanel.add(panel);
				});
			},
			failure : function(request) {
				Ext.MessageBox.show( {
					title : '操作提示',
					msg : "连接服务器失败",
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		});
		
	}
});


