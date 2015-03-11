<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="../../../css/icon/icon.css">
	<jsp:include page="../../base/extlib.jsp"/>
	<style type="text/css">
	
	.fieldheight{
		margin: 10 0 15 25
	}
	
	.contentCenter{
		text-align: center;
		padding-left: 100px;
  		width:100%;  
  		vertical-align:center;  
	}
	
	</style>
  <script type="text/javascript">
  Ext.onReady(function(){
	  
	   Ext.create('Ext.form.Panel', {
		    id: 'adduserform',
		    region: 'center',
		    bodyPadding: 5,
		    width: 380,
			height: 450,
		    url: 'save-form.php',
		    bodyCls: 'contentCenter',
		    defaultType: 'textfield',
		    frame: true,
		    defaults: {
		        autoScroll: true,
		        labelAlign :'right',
		        labelWidth :80,
		        labelSeparator :'：',
		        labelPad : 5,
		        //margin : '0 0 50 0',
		        cls:'fieldheight',
		    },
		    items: [{
		    	layout: 'column',
		    	xtype: 'panel',
		        items: [{
		        	xtype:'image',
				    height: 80,
				    width: 80,
			    	src: 'http://www.sencha.com/img/20110215-feat-html5.png',
			    	columnWidth: 0.50
		        },{
		        	xtype:'image',
				    height: 80,
				    width: 80,
			    	src: 'http://www.sencha.com/img/20110215-feat-html5.png',
		            columnWidth: 0.50
		        }]
		    },{
		        fieldLabel: '用户名',
		        name: 'usercode',
		        allowBlank: false
		    },{
		        fieldLabel: '密码',
		        name: 'password',
		        allowBlank: false,
		    },{
		        fieldLabel: '昵称',
		        name: 'username',
		        allowBlank: false
		    },{
		        fieldLabel: '手机号',
		        name: 'telPhone',
		        allowBlank: false
		    },{
		        fieldLabel: '邮箱',
		        name: 'email',
		        allowBlank: false
		    },{
		        fieldLabel: '角色',
		        xtype: 'combobox',
		        name: 'rolecode',
		        allowBlank: false,
		        queryMode: 'local',
		        displayField: 'name',
		        valueField: 'abbr'
		    }],
		    buttons: [{
		        text: '重置',
		        handler: function() {
		            this.up('form').getForm().reset();
		        }
		    }, {
		        text: '提交',
		        //formBind: true,
		        //disabled: true,
		        handler: function() {
		            var form = this.up('form').getForm();
		            if (form.isValid()) {
		                form.submit({
		                    success: function(form, action) {
		                       Ext.Msg.alert('Success', action.result.msg);
		                    },
		                    failure: function(form, action) {
		                        Ext.Msg.alert('Failed', action.result.msg);
		                    }
		                });
		            }
		        }
		    }],
		    renderTo: Ext.getBody()
	  });

	    /*
		 *Viewport
		 */
		Ext.create('Ext.container.Viewport', {
			layout : 'border',
			renderTo : Ext.getBody(),
			items : [
			     Ext.getCmp('adduserform'),
			]
		});
  });
  </script>
  </head>
  <body>
  </body>
</html>
