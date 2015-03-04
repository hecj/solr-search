Ext.onReady(function(){

	var panel = Ext.create("Ext.panel.Panel", {
	    width: 200,
	    defaults: {
	        // 应用到所有子panel
	        bodyStyle: 'padding:15px'
	    },
	    layout: {
	        // 布局配置
	        type: 'accordion',
	        titleCollapse: false,
	        animate: true,
	        activeOnTop: true
	    },
	    items: [{
	        title: 'Panel 1',
	        html: 'Panel content!'
	    }, {
	        title: 'Panel 2',
	        html: 'Panel content!'
	    }, {
	        title: 'Panel 3',
	        html: 'Panel content!'
	    }],
	    renderTo: 'menu-container-body'
	});
});