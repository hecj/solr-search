


<!DOCTYPE html>
<html>
<head>
<title>SSHE</title>





<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">







<script type="text/javascript">
var sy = sy || {};
sy.contextPath = '';
sy.basePath = 'http://sshe.jeasyuicn.com:80';
sy.version = '20131115';
sy.pixel_0 = '/style/images/pixel_0.gif';//0像素的背景，一般用于占位
</script>


<script type="text/javascript" src="/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>


<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = '/jslib/ueditor1_2_6_1-utf8-jsp/';</script>
<script src="/jslib/ueditor1_2_6_1-utf8-jsp/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="/jslib/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js" type="text/javascript" charset="utf-8"></script>


<script src='/jslib/jquery-2.0.3.js' type='text/javascript' charset='utf-8'></script>


<script src="/jslib/syExtJquery.js?version=20131115" type="text/javascript" charset="utf-8"></script>


<script src="/jslib/Highcharts-3.0.6/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
<script src="/jslib/Highcharts-3.0.6/js/modules/exporting.js" type="text/javascript" charset="utf-8"></script>

<script src="/jslib/syExtHighcharts.js?version=20131115" type="text/javascript" charset="utf-8"></script>


<script type="text/javascript" src="/jslib/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/jslib/plupload-2.0.0/js/i18n/zh_CN.js"></script>


<link id="easyuiTheme" rel="stylesheet" href="/jslib/jquery-easyui-1.3.4/themes/bootstrap/easyui.css" type="text/css">
<!-- <link rel="stylesheet" href="/jslib/jquery-easyui-1.3.4/themes/icon.css" type="text/css"> -->
<script type="text/javascript" src="/jslib/jquery-easyui-1.3.4/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="/jslib/jquery-easyui-1.3.4/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>

<link rel="stylesheet" href="/jslib/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="/jslib/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>

<script src="/jslib/syExtEasyUI.js?version=20131115" type="text/javascript" charset="utf-8"></script>


<link rel="stylesheet" href="/style/syExtIcon.css?version=20131115" type="text/css">


<link rel="stylesheet" href="/style/syExtCss.css?version=20131115" type="text/css">


<script src="/jslib/syExtJavascript.js?version=20131115" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	var mainMenu;
	var mainTabs;

	$(function() {

		var loginFun = function() {
			if ($('#loginDialog form').form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(sy.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_login.sy', $('#loginDialog form').serialize(), function(result) {
					if (result.success) {
						$('#loginDialog').dialog('close');
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							$('#loginDialog form :input:eq(1)').focus();
						});
					}
					$('#loginBtn').linkbutton('enable');
				}, 'json');
			}
		};
		$('#loginDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('#loginDialog form :input[name="data.pwd"]').val('');
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		}).dialog('close');

		$('#passwordDialog').show().dialog({
			modal : true,
			closable : true,
			iconCls : 'ext-icon-lock_edit',
			buttons : [ {
				text : '修改',
				handler : function() {
					if ($('#passwordDialog form').form('validate')) {
						$.post(sy.contextPath + '/base/syuser!doNotNeedSecurity_updateCurrentPwd.sy', {
							'data.pwd' : $('#pwd').val()
						}, function(result) {
							if (result.success) {
								$.messager.alert('提示', '密码修改成功！', 'info');
								$('#passwordDialog').dialog('close');
							}
						}, 'json');
					}
				}
			} ],
			onOpen : function() {
				$('#passwordDialog form :input').val('');
			}
		}).dialog('close');

		mainMenu = $('#mainMenu').tree({
			url : sy.contextPath + '/base/syresource!doNotNeedSecurity_getMainMenu.sy',
			parentField : 'pid',
			onClick : function(node) {
				if (node.attributes.url) {
					var src = sy.contextPath + node.attributes.url;
					if (!sy.startWith(node.attributes.url, '/')) {
						src = node.attributes.url;
					}
					if (node.attributes.target && node.attributes.target.length > 0) {
						window.open(src, node.attributes.target);
					} else {
						var tabs = $('#mainTabs');
						var opts = {
							title : node.text,
							closable : true,
							iconCls : node.iconCls,
							content : sy.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
							border : false,
							fit : true
						};
						if (tabs.tabs('exists', opts.title)) {
							tabs.tabs('select', opts.title);
						} else {
							tabs.tabs('add', opts);
						}
					}
				}
			}
		});

		$('#mainLayout').layout('panel', 'center').panel({
			onResize : function(width, height) {
				sy.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
			}
		});

		mainTabs = $('#mainTabs').tabs({
			fit : true,
			border : false,
			tools : [ {
				iconCls : 'ext-icon-arrow_up',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'top'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_left',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'left'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_down',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'bottom'
					});
				}
			}, {
				iconCls : 'ext-icon-arrow_right',
				handler : function() {
					mainTabs.tabs({
						tabPosition : 'right'
					});
				}
			}, {
				text : '刷新',
				iconCls : 'ext-icon-arrow_refresh',
				handler : function() {
					var panel = mainTabs.tabs('getSelected').panel('panel');
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
			}, {
				text : '关闭',
				iconCls : 'ext-icon-cross',
				handler : function() {
					var index = mainTabs.tabs('getTabIndex', mainTabs.tabs('getSelected'));
					var tab = mainTabs.tabs('getTab', index);
					if (tab.panel('options').closable) {
						mainTabs.tabs('close', index);
					} else {
						$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
					}
				}
			} ]
		});

	});
</script>
</head>
<body id="mainLayout" class="easyui-layout">
<div style="position: absolute;z-index:10;right: 0;top: 19px;"><script type="text/javascript">
					/*pan共享*/
					var cpro_id = "u1377538";
					</script>
					<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script> </div>
	<div data-options="region:'north',href:'/securityJsp/north.jsp'" style="height: 70px; overflow: hidden;" class="logo"></div>
	<div data-options="region:'west',href:'',split:true" title="导航" style="width: 200px; padding: 10px;">
		<ul id="mainMenu"></ul>
	</div>
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="mainTabs">
			<div title="关于SSHE" data-options="iconCls:'ext-icon-heart'">
				<iframe src="/welcome.jsp" allowTransparency="true" style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	<div data-options="region:'south',href:'/securityJsp/south.jsp',border:false" style="height: 30px; overflow: hidden;"></div>

	<div id="loginDialog" title="解锁登录" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th width="50">登录名</th>
					<td>孙宇<input name="data.loginname" readonly="readonly" type="hidden" value="孙宇" /></td>
				</tr>
				<tr>
					<th>密码</th>
					<td><input name="data.pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="passwordDialog" title="修改密码" style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th>新密码</th>
					<td><input id="pwd" name="data.pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>重复密码</th>
					<td><input type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>