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



