/**
 * 信息提示语
 */
var MessageUtil = {
	loadDataGridMsg : '正在加载中,请稍后...',
	loadingPageMessage : '页面加载中,请稍后...',
	beforePageText : '第',
	afterPageText : '页 共<b>{pages}</b>页',
	displayMsg : '当前<b>{from}</b>-<b>{to}</b>条&nbsp;&nbsp;共<b>{total}</b>条',
	messageShow:function(message){
		jQuery.messager.show({
			title:'提示信息',
			msg:'<center><font size=4>'+message+'</font></center>',
			timeout:3000,
			showType:'show'
		})
	}

}