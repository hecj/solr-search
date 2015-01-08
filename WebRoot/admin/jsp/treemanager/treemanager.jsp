<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>Seacher</title>
    <%@include file="/admin/jsp/base/basePath.jsp" %> 
    <jsp:include page="/admin/jsp/base/easyUI.jsp"/>
  	<script type="text/javascript">
		var treegrid ;
		$(function(){
			treegrid = $('#treegrid').treegrid({

				
			});
		    $('#rightClick').menu('show', {
		        left: 200,
		        top: 100
		    });
		});

		var rightClick = function(){
			var row = treegrid.treegrid('getSelected');
			alert(row);
		}

  	</script>
  </head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div region="center" data-options="fit:true,border:false">
    	    
	    <table id="treegrid"
			data-options="
				url:'<%=basePath %>admin/tree/menuTree.htm?operator=treeManagerQuery&moduleId=0',
				idField: 'moduleId',
				treeField: 'name',
				toolbar:'#toolbar',
				fit:true,
				border:false,
				fitColumns: true
			">
			<thead>
				<tr>
					<th data-options="field:'name',align:'left'" width="220">模块列表</th>
					<th data-options="field:'moduleId',align:'center'" width="50">模块Id</th>
					<th data-options="field:'parentId',align:'center'" width="50">父模块</th>
					<th data-options="field:'state',align:'center'" width="50">状态</th>
					<th data-options="field:'url',align:'center'" width="300">路径</th>
					<th data-options="field:'icons',align:'center'" width="60">图标</th>
					<th data-options="field:'leaf',align:'center'" width="50">是否叶子</th>
				</tr>
			</thead>
		</table>
    </div>
    	<!-- treegrid toolbar -->
		<div id="toolbar" style="display: none;">
			<table>
				<tr>
					<td>
						<a href="javascript:void(0);" onclick="rightClick();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="findFun();">添加</a>
					</td>
				</tr>
			</table>
		</div>
		
		<!-- right click  -->
		<div id="rightClick" class="easyui-menu" style="width:120px;">
		    <div>
				<span>添加</span>
				<div>
					<div>父节点</div>
					<div>同辈节点</div>
					<div>子节点</div>
				</div>
		    </div>
		    <div>删除</div>
		    <div>编辑</div>
		</div>
		 
	</body>
</html>
