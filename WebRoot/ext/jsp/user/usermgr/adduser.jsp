<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户添加</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" type="text/css" href="../../../css/icon/icon.css">
	<jsp:include page="../../base/extlib.jsp"/>
  <script type="text/javascript">
  Ext.onReady(function(){
	  
	   Ext.create('Ext.form.Panel', {
		    id: 'adduserform',
		    region: 'center',
		    bodyPadding: 5,
		    url: 'save-form.php',
		    defaultType: 'textfield',
		    items: [{
		        fieldLabel: 'First Name',
		        name: 'first',
		        allowBlank: false
		    },{
		        fieldLabel: 'Last Name',
		        name: 'last',
		        allowBlank: false
		    }],
		    buttons: [{
		        text: 'Reset',
		        handler: function() {
		            this.up('form').getForm().reset();
		        }
		    }, {
		        text: 'Submit',
		        formBind: true, //only enabled once the form is valid
		        disabled: true,
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
