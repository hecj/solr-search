<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<ul id="tree_id" class="easyui-tree">  
    <li>  
        <span>系统菜单</span>  
        <ul>  
            <li>  
                <span>管理</span>  
                <ul>  
                    <li >  
                        <label onclick="openTab('admin/jsp/task/search.jsp','查询')">查询</label>
                    </li>  
                    <li >  
                        <label onclick="openTab('admin/jsp/task/address.json','删除')">删除</label>
                    </li>  
                </ul>  
            </li>  
        </ul>  
    </li>  
</ul>
 