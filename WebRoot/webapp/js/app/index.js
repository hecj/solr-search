	var app = app || {};
	app.basePath = 'http://localhost:8080/solr-search/';

	
/**
 * ---------------------------注册页面---------------------------------------
 */	
	$(document).on('pageinit', '#page_reg', function() {
		$('#register').bind('click', app.reg);
	});
	app.reg = function() {
		$.ajax( {
			type : 'POST',
			url : app.basePath + 'webapp/user/user.htm?operator=reg',
			data : $('#formReg').serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.code == '0') {
					$.mobile.changePage('login.html', {
					     transition : "slide",
					     reverse : true,
					     changeHash : true
					});
				} else {
					$('#regMessage').text(data.message);
					$('#regMessage').popup('open');
				}
			},
			beforeSend : function(XMLHttpRequest) {
				common.showLoader();
			},
			complete : function(XMLHttpRequest) {
				common.hideLoader();
			}
		});
	}	
	
/**
 * ---------------------------登陆页面---------------------------------------
 */	
	$(document).on('pageinit', '#page_login', function() {
		$('#login').bind('click', app.login);
		$('#reset').bind('click', app.reset);

	});
	app.login = function() {
		$.ajax( {
			type : 'POST',
			url : app.basePath + 'webapp/user/user.htm?operator=login',
			data : $('#formLogin').serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.code == '0') {
					app.user = data.message;
					var userStr = JSON.stringify(app.user);
					$.cookie("user", userStr, {
						expires : 1
					});
					$.mobile.changePage('index.html', {
					     transition : "slide",
					     reverse : false,
					     changeHash : true
					});
				} else {
					$('#loginMessage').text(data.message);
					$('#loginMessage').popup('open');
				}
			},
			beforeSend : function(XMLHttpRequest) {
				common.showLoader();
			},
			complete : function(XMLHttpRequest) {
				common.hideLoader();
			}
		});
	}
	app.reset = function() {
		$('#formLogin')[0].reset();
	}
