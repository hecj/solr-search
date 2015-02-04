$.extend($.fn.validatebox.defaults.rules, {
	baseValidator : {
		validator : function(value) {
			var p = /^.{1,50}$/;
			if(p.test(value)){
				return true;
			}
			return false;
		},
		message : '长度超出范围[1-50]！'
	},
	notEmpty : {
		validator : function(value) {
			if(StringUtils.trims(value).length ==0 ){
				return false;
			}
			return true;
		},
		message : '请输入有效字符'
	},
	userValidator : {
		validator : function(value) {
			var p = /^[a-zA-Z]{1}[0-9a-zA-Z_]{2,19}$/;
			if(p.test(value)){
				return true;
			}
			return false;
		},
		message : '用户名只能包含数字、字母、下划线,且以字母开头,3~20位!'
	},
	pwdValidator : {
		validator : function(value) {
			var p = /^[0-9a-zA-Z]{3,20}$/;
			if(p.test(value)){
				return true;
			}
			return false;
		},
		message : '密码只能包含数字、字母,3~20位!'
	},
	phoneValidator : {
		validator : function(value) {
			var p = /^1(\d{6}|\d{10})$/;
			if(p.test(value)){
					return true;
			}
			return false;
		},
		message : '手机号不合法!'
	},
	emailValidator : {
		validator : function(value) {
			var p = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			if(p.test(value)){
				return true;
			}
			return false;
		},
		message : '邮箱不合法!'
	}
});

