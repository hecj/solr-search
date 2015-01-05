var so = so || {};

so.modalDialog = function(options) {
	var opts = $.extend({
		title : '&nbsp;',
		width : 640,
		height : 480,
		modal : true,
		zIndex:10000,
		onClose : function() {
			$(this).dialog('destroy');
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	if (options.url) {
		opts.content = '<iframe id="" src="' + options.url + '" allowTransparency="true" scrolling="auto" width="100%" height="98%" frameBorder="0" name=""></iframe>';
	}
	return $('<div/>').dialog(opts);
};
