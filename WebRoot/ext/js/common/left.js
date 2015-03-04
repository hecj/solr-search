Ext.onReady(function(){
	
	var store = Ext.create('Ext.data.TreeStore', {
	    root: {
	        expanded: true,
	        children: [
	            { text: "detention", leaf: true },
	            { text: "homework", expanded: true, children: [
	                { text: "book report", leaf: true },
	                { text: "algebra", leaf: true}
	            ] },
	            { text: "buy lottery tickets", leaf: true }
	        ]
	    }
	});

	var accordionMenu = Ext.create("Ext.panel.Panel", {
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
	        xtype: 'treepanel',
	        store: store
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