<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.hey.com" prefix="hey" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>自定义标签</title>
  </head>
  <body>
  <hey:permission radiocode="radio" usercode="usercode">
  		我不想显示了<br>
    <p>自定义标签</p><br/>
  </hey:permission>
  </body>
</html>
