/**
 * 常用工具类
 */
var common = common || {};
/*
 * 获取鼠标的坐标
 * 参数[event]
 * return {x:x,y:y}
 */
common.mouse = function(e) {
	e = e || window.event;
	if (e.pageX || e.pageY) {
		return {x : e.pageX,y : e.pageY};
	}
	return {
		x : e.clientX + document.body.scrollLeft- document.body.clientLeft,
		y : e.clientY + document.body.scrollTop - document.body.clientTop
	};
}

/*
 * 获取系统时间
 */
common.getSysTime = function(){
	var obj = new Date();
	var showDate = obj.getFullYear()+'-'+
				   (obj.getMonth()+1)+'-'+
				   obj.getDate()+' '+
				   obj.getHours()+':'+
				   obj.getMinutes()+':'+
				   obj.getSeconds()+' '+this.getDays(obj);
	return showDate;
}

/*
 * 获取星期几 
 */
common.getDays = function(date){
	var d = date.getDay();
	switch(d){
	case 1:
		return '星期一';
	case 2:
		return '星期二';
	case 3:
		return '星期三';
	case 4:
		return '星期四';
	case 5:
		return '星期五';
	case 6:
		return '星期六';
	case 0:
		return '星期天';
	}
}


