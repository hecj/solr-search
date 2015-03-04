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
		        title: 'South Region (可调整大小)',
		        region: 'north',     // 所在的位置
		        xtype: 'panel',
		        height: 100,
		        split: true,         // 允许调整大小
		        margins: '0 5 5 5'
		    },{
		        title: 'South Region (可调整大小)',
		        region: 'south',     // 所在的位置
		        xtype: 'panel',
		        height: 100,
		        split: true,         // 允许调整大小
		        margins: '0 5 5 5'
		    }, {
		        title: 'West Region (可折叠/展开)',
		        region: 'west',
		        xtype: 'panel',
		        margins: '5 0 0 5',
		        width: 200,
		        collapsible: true,   // 可折叠/展开
		        id: 'west-region-container',
		        layout: 'fit'
		    }, {
		        title: 'Center Region (必须)',
		        region: 'center',     // 必须指定中间区域
		        xtype: 'panel',
		        layout: 'fit',
		        margins: '5 5 0 0'
		    }]
		});
	}
});