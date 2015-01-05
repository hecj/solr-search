<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Seacher</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript">
  		var centerTabs ;
		$(function(){
			$('#west').tree( {
				url : app.basePath+'admin/js/common/tree.json',
				onClick : function(node) {
					if(!StringUtils.isObjEmpty(node.state)){
						if(node.state == "open"){
							$(this).tree('collapse',node.target); 
						}else{
							$(this).tree('expand',node.target);  
						}
					}
					if (!StringUtils.isObjEmpty(node.attributes.url)) {
						addTab(node);
					}
				}
			});
			centerTabs = $('#center').tabs('add', {
				title : '欢迎页',
				content:'<iframe src="'+app.basePath+'admin/jsp/common/webcome.jsp" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
				closable : true,
				loadingMessage : MessageUtil.loadingPageMessage
			});
		});

		function addTab(node){
			if (centerTabs.tabs('exists', node.text)) {
				centerTabs.tabs('select',node.text);
			}else{
				if(node.attributes.url && node.attributes.url.length>0){
					centerTabs.tabs('add', {
						title : node.text,
						content:'<iframe src="'+node.attributes.url+'" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>',
						closable : true,
						loadingMessage : MessageUtil.loadingPageMessage
					});
				}
			}
		}
  	</script>
  </head>
<body class="easyui-layout">  
    <div region="north" split="false" style="height:100px;"></div>  
    <div region="south" split="false" style="height:30px;">
    	<jsp:include page="./jsp/common/footer.jsp"/>
    </div>  
    <div id="west" region="west" split="true" title="系统工具" style="width:160px;"></div>  
    <div id="center" region="center" class="easyui-tabs" style="padding:0px;background:#eee;height: 100%" fit="false"></div>  
</body>
</html>
