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
			height : 55
		});
		
		/**
		 * 右,tab.Panel
		 */
		app.rightPanel = Ext.create('Ext.tab.Panel', {
			region : 'center',
			layout : 'fit',
			tabWidth : 120,
			items : [ {
				title : '首页'
			} ]
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
			width : 230,
			layout : 'accordion',
			collapsible : true
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
		var buildTree = function(json) {
			return Ext.create('Ext.tree.Panel', {
				rootVisible : false,
				border : false,
				store : Ext.create('Ext.data.TreeStore', {
					root : {
						expanded : true,
						children : json.children
					}
				}),
				listeners : {
					itemclick : function(view, record, item, index, e) {
						var id = record.get('id');
						var text = record.get('text');
						var leaf = record.get('leaf');
						if (leaf) {
							alert('id-' + id + ',text-' + text + ',leaf-'
									+ leaf);
						}
					},
					scope : this
				}
			});
		};

		/**
		 * 加载菜单树
		 */
		Ext.Ajax.request( {
			url : '../ext/tree/tree.htm?operator=initTree',
			success : function(response) {
				var json = Ext.JSON.decode(response.responseText)
				Ext.each(json.data, function(el) {
					var panel = Ext.create('Ext.panel.Panel', {
						id : el.id,
						title : el.text,
						layout : 'fit'
					});
					panel.add(buildTree(el));
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
			},
			method : 'post'
		});
		
	}
});


