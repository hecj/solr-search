/**
 * ----------------------------common--------------------------------------
 */

	//缓存页面
	//$.mobile.page.prototype.options.domCache = true;

	$(document).bind("mobileinit",function(){    
		$.extend($.mobile ,{
			defaultPageTransition:'none' 
	    });    
	}); 

	var app = app || {};
	app.basePath = 'http://pos-hecj:8080/solr-search/';
//	app.basePath = 'http://121.40.56.87/solr-search/';

	app.user ;
	// 获取用户名
	app.getUserCode = function(){
		if(app.user && app.user != null){
			return app.user.usercode ;
		}
		return '' ; 
	}
	
	$(document).on("pagebeforecreate", function(event) {
		var u = $.cookie('user');
		if (u) {
			app.user = $.evalJSON(u);
		}
	});
	
	$(document).on("pagecreate", function(event) {
		
	});
	
	$(document).on("pageinit", function(event) {
		
	});
	
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
					     transition : "none",
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
					     transition : "none",
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
		$('#goLogin').bind('click', app.goLogin);
		app.initUserMessage();
	});
 		
	$(document).on('pagebeforeshow', '#page_index', function() {
		$('#listView').html('');
		$('#loadMore span').text('加载更多');
		app.loadListView(1);
	});
	
	// 分页参数
	app.page = 1;
	app.total = 0;

	// 初始化用户信息
	app.initUserMessage = function(){
		if ( app.user || app.user != null){
			$('#show_usercode').text(app.user.usercode);
			$('#show_login').text('注销');
		} else {
			$('#show_usercode').text('匿名');
			$('#show_login').text('登录');
		}
	}
	
	// 清除user cookie,转login.html页面
	app.goLogin = function (){
		if(app.user || app.user != null){
			app.user = null;
			$.cookie('user',null);
		}
		$.mobile.changePage('login.html', {});
	}
	
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
					$('#total').text('共'+data.total+'篇');
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
						listView.find("li:last").slideDown(100);  
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
					     transition : "none",
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
		
		$('#addCommentSub').bind('click',app.addCommentSub);
		$('#loadMoreComment').bind('click',app.loadMoreComment);
		
		var data = common.getParams(e);
		var obj = $.evalJSON(decodeURIComponent(data));
		$('#essayId').val(obj.id);
		$('.detail_title').text(obj.title);
		$('.detail_usercode').text(obj.usercode?obj.usercode:'匿名');
		$('.detail_content').text(obj.content);
		$('.detail_createDate').text(obj.createDate);
		
		app.loadCommentList(1);
		
	});
	
	// 分页参数
	app.pageComment = 1;
	app.totalComment = 0;
	
	// 提交评论
	app.addCommentSub = function(){
		var essayId = $('#essayId').val();
		var commentContent = $('#fomAddComment textarea[name=commentContent]').val();
		$.ajax( {
			type : 'POST',
			url : app.basePath + 'webapp/essay/essay.htm?operator=addComment',
			data : {essayId:essayId,commentContent:commentContent,usercode:app.getUserCode()},
			dataType : 'json',
			success : function(data) {
				if(data.code == 0){
					var indexMes = $('#detailMessage');
					indexMes.text('评论成功!');
					indexMes.popup('open');
					$('#commentList').html('');
					$('#fomAddComment textarea[name=commentContent]').val('');
					app.loadCommentList(1);
				}else{
					var indexMes = $('#detailMessage');
					indexMes.text(data.message);
					indexMes.popup('open');
					setTimeout('closePopup('+indexMes+')',1000);
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
	app.loadMoreComment = function(){
		var size = $('#commentList .commentLi').size();
		if (size < app.totalComment){
			app.loadCommentList(app.pageComment+1);
		}else{
			$('#loadMoreComment span').text('亲,没有评论了');
		}
	}
	
	// 加载评论
	app.loadCommentList = function(p){
		
		var essayId = $('#essayId').val();
		var commentList = $('#commentList');
		$.ajax( {
			type : 'POST',
			url : app.basePath + 'webapp/essay/essay.htm?operator=searchComments',
			data : {page:p,essayId:essayId},
			dataType : 'json',
			success : function(data) {
				if (data) {
					var rows = data.rows;
					app.totalComment = data.total;
					app.pageComment = p;
					$('.showCommentTotal').text('共'+data.total+'条');
					var n = 5 * (p-1) ;
					for ( var i = 0; i < rows.length; i++) {
						var row = rows[i];
						var item = ('<div class="commentLi">'+
				        		    '<p>'+row.content+'</p>'+
				        		    '<div style="text-align: right">'+(row.usercode?row.usercode:'匿名')+'</div>'+
				        		    '<div>'+
				        			'<span style="display: inline-block;float: right">'+(n+i+1)+'楼&nbsp;&nbsp;'+row.createDate+'</span>'+
				        			'</div>'+
				        		    '<br/><hr>'+
				        		    '</div>');
						commentList.append(item);  
					}
					
					var size = $('#commentList .commentLi').size();
					if (size == app.totalComment){
						$('#loadMoreComment span').text('亲,没有评论了');
					}else{
						$('#loadMoreComment span').text('加载更多');
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
	
	
	function closePopup(obj){
		obj.popup('closed');
	}
