<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<jsp:include page="../base/extlib.jsp"/>
  <script type="text/javascript">
  Ext.onReady(function(){

	  Ext.create('Ext.data.Store', {
		    storeId:'userStore',
		    autoLoad: true,
		    fields:['usercode', 'username', 'telPhone' , 'email', 'role', 'imageHead', 'createDate'],
		    proxy: {
		        type: 'ajax',
		        url : '../../../admin/user/user.htm?operator=searchUser',
		        reader: {
		            type: 'json',
		            root: 'rows',
		            totalProperty : 'total'
		        }
		    }
		});
	
		Ext.create('Ext.grid.Panel', {
			id : 'userGrid',
			region : 'center',
		    store: Ext.data.StoreManager.lookup('userStore'),
		    disableSelection: true,
		    loadMask: true,
		    autoHeight : true,
		    autoScroll : true,
		    columns: [
		        { text: '用户名',  dataIndex: 'usercode' , flex:1},
		        { text: '姓名', dataIndex: 'username' ,flex:1},
		        { text: '手机号', dataIndex: 'telPhone' ,flex:1},
		        { text: '邮箱', dataIndex: 'email'  ,flex:1},
		        { text: '角色代码', dataIndex: 'role' ,flex:1},
		        { text: '头像', dataIndex: 'imageHead' ,flex:1},
		        { text: '创建时间', dataIndex: 'createDate',flex:1}
		    ],
		    dockedItems: [{
		        xtype: 'pagingtoolbar',
		        store: Ext.data.StoreManager.lookup('userStore'),
		        displayMsg: '显示{0}到{1}, 共{2}记录',
		        beforePageText : '第',
		        afterPageText : '共{0}页',
		        dock: 'bottom',
		        displayInfo: true
		    }]
		});

		/**
		 * Viewport
		 */
		Ext.create('Ext.container.Viewport', {
			layout : 'border',
			renderTo : Ext.getBody(),
			items : [ 
			         Ext.getCmp('userGrid'),
			]
		});
		
	});
  
  </script>
  </head>
  <body>
  </body>
</html>
