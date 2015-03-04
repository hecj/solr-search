/**
 * 应用程序入口
 */
Ext.application( {
	requires : [ 'Ext.container.Viewport' ],
	name : 'AM',
	appFolder : 'app',
	launch : function() {
		Ext.create('Ext.container.Viewport', {
			rederTo: Ext.getBody(),
			layout : 'border',
			items: [{
		        region: 'north',
		        xtype: 'panel',
		        height: 80,
		        split: false,
		        margins: '0 0 0 0'
		    },{
		        region: 'south',
		        xtype: 'panel',
		        height: 30,
		        split: false,
		        margins: '0 0 5 0'
		    },{
		        title: '导航栏',
		        region: 'west',
		        xtype: 'panel',
		        margins: '5 0 0 5',
		        width: 200,
		        collapsible: true,
		        id: 'menu-container',
		        layout: 'fit'
		    },{
		        title: 'Center Region',
		        region: 'center',
		        xtype: 'panel',
		        layout: 'fit',
		        margins: '5 5 0 0'
		    }]
		});
	}
});
