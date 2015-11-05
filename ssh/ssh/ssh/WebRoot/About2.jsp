<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/chistyle.css" type="text/css"></link>
	<link rel="stylesheet" href="css/styles.css" type="text/css"></link>
	<script src="js/jquery-1.11.0.min.js"></script>
	<script language="javascript" type="text/javascript" src="js/turn.js"></script>
	<script language="javascript" type="text/javascript" src="js/tb.js"></script>
	<script type="text/javascript" src="js/chiEdit.js"></script>
  </head>
  
  <body>
  <div class="right">
	<p id="about">
<s:iterator value="aboutList">${chiConx}</s:iterator>
	</p>
  </div>

  </body>
</html>
