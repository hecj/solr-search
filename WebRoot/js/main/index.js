(function(){
	
	/*文档加载完毕后*/
	jQuery(document).ready(function(){
		
		
	});
	
	
	
})();

/*
 * 验证表单
 */
function check_qForm(){
	
	if(StringUtils.trims(jQuery("form[id=q_form] input[name=q]").val()).length==0){
		alert('请输入内容');
		return false;
	}
}