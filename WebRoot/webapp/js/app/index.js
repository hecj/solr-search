//缓存页面
//$.mobile.page.prototype.options.domCache = true;

var app = app || {};
	app.basePath = 'http://localhost:8080/solr-search/';

	
/**
 * ---------------------------注册页面---------------------------------------
 */	
	$(document).on('pageinit', '#page_reg', function() {
		$('#register').bind('click', app.reg);
	});
	// 注册
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
	// 登陆
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
	
/**
 * ------------------------主页-------------------------------------
 */	
	$(document).on('pageinit', '#page_index', function() {
		$('#loadMore').bind('click', app.loadMore);
		$('#home').bind('click', app.openMenu);
	});
 		
	$(document).on('pagebeforeshow', '#page_index', function() {
		$('#listView').html('');
		$('#loadMore span').text('加载更多');
		app.loadListView(1);
	});
	
	// 分页参数
	app.page = 1;
	app.total = 0;
	
	// 打开系统菜单
	app.openMenu = function(){
		$('#sysMenu').html('功能即将实现');
		$('#sysMenu').panel('open');
	}
	
	//加载数据
	app.loadListView = function(p){
		var listView = $('#listView');
		$.ajax( {
			type : 'POST',
			url : app.basePath + 'webapp/essay/essay.htm?operator=searchEssays',
			data : {page:p},
			dataType : 'json',
			success : function(data) {
				if (data) {
					var rows = data.rows;
					app.total = data.total;
					app.page = p;
					$('#total').text('共'+data.total+'篇文章');
					for ( var i = 0; i < rows.length; i++) {
						var row = rows[i];
						var item = $('<li onclick="app.detail(this)"><input type="hidden" name="eId" value="'+row.id+'"/>'+
									'<a href="#">'+
								    '<img src="../imgs/love/psb1.jpg">'+
								    '<h2>'+row.title+'</h2>'+
				        		    '<p>'+row.content+'</p>'+
				        		    '</a></li>');
						listView.append(item).find("li:last").hide();  
						listView.listview('refresh');  
						listView.find("li:last").slideDown(300);  
					}
					var size = $('#listView li').size();
					if (size == app.total){
						$('#loadMore span').text('亲,没有数据了');
					}
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
	
	// 加载更多
	app.loadMore = function(){
		var size = $('#listView li').size();
		if (size < app.total){
			app.loadListView(app.page+1);
		}else{
			$('#loadMore span').text('亲,没有数据了');
		}
	}
	
	// 详细文章
	app.detail = function(obj){
		var id = $(obj).find('input[name=eId]').val();
		$.ajax( {
			type : 'POST',
			url : app.basePath + 'webapp/essay/essay.htm?operator=get',
			data : {id:id},
			dataType : 'json',
			success : function(data) {
				if(data.code == 0){
					$.mobile.changePage('detail.html?data='+$.toJSON(data.message), {
					     transition : "slide",
					     reverse : false,
					     changeHash : true
					});
				}else{
					var indexMes = $('#indexMessage');
					indexMes.text(data.message);
					indexMes.popup('open');
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
 * ------------------------发表新文章-------------------------------------
 */
	$(document).on('pageinit', '#page_addEssay', function() {
		$('#addEssaySub').bind('click', app.addEssaySub);
	});
	
	// 添加新文章
	app.addEssaySub = function(){
		$.ajax({
			type : 'POST',
			url : app.basePath + 'webapp/essay/essay.htm?operator=add',
			data : $('#formAddEssay').serialize(),
			dataType : 'json',
			success : function(data) {
				if (data.code == '0') {
					$('#addEssayMessage').text('发表新文章成功');
					$('#addEssayMessage').popup('open');
				} else {
					$('#addEssayMessage').text(data.message);
					$('#addEssayMessage').popup('open');
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
 * ------------------------detail.html-------------------------------------
 */
	$(document).on('pageinit', '#page_detail', function(e, data) {
		var data = common.getParams(e);
		var obj = $.evalJSON(decodeURIComponent(data));
		$('.detail_title').text(obj.title);
		$('.detail_content').text(obj.content);
		$('.detail_createDate').text(obj.createDate);
	});
	
