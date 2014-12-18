<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div style="height: 100%">
	<ul id="tree_id" class="easyui-tree">  
	    <li>  
	        <span>系统菜单</span>  
	        <ul>  
	            <li>  
	                <span>数据搜集</span>  
	                <ul>  
	                    <li>  
	                        <label style="width: 160px" onclick="openTab('admin/jsp/datacollect/simpleDataCollect.jsp','数据搜集配置')">数据搜集配置</label>
	                    </li>  
	                    <li>  
	                        <label style="width: 160px" onclick="openTab('admin/jsp/datacollect/simpleDataCollect.jsp','数据搜集查询')">数据搜集查询</label>
	                    </li>  
	                </ul>  
	            </li>  
	        </ul>  
	    </li>  
	</ul>
</div>