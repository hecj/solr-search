<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>HECJ-Pro</title>
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
    <c:if test="${context eq null}">
    	<script type="text/javascript">
    		$(function(){
    			var dialog = parent.app.dialogModel({
    				title: '用户登陆',
    				width: 330,
    				height: 200,
    				closable:false,
    				url : app.contextPath+'admin/jsp/common/login.jsp',
    				buttons:[{
    					text:'登陆',
    					handler:function(){
    						dialog.find('iframe').get(0).contentWindow.submitForm();
    					}
    				}]
    			});
        	});
		</script>
    </c:if>
  </head>
<body class="easyui-layout" >
    <div region="north" split="false" style="height:100px;">
    	<jsp:include page="./jsp/common/header.jsp"/>
    </div>  
    <div region="south" split="false" style="height:30px;">
    	<jsp:include page="./jsp/common/footer.jsp"/>
    </div>  
    <div id="west" region="west" split="true" style="width:160px;">
    	<jsp:include page="./jsp/common/left.jsp"/>
    </div>  
    <div region="center"  style="padding:0px;background:#eee;" style="overflow: hidden;">
    	<div id="center_tabs" class="easyui-tabs" fit="true">
    		<div title="欢迎页">
    			<iframe src="admin/jsp/system/commitLog.html" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>
    		</div>
    	</div>
    </div>  
</body>
</html>
