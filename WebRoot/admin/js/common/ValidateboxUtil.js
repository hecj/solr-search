$.extend($.fn.validatebox.defaults.rules, {
	notEmpty : {
		validator : function(value) {
			if(StringUtils.trims(value).length ==0 ){
				return false
			}
			return true;
		},
		message : '请输入有效字符'
	}
});