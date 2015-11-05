<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="refresh" content="4;url=user1.jsp">
<title>XIAZHI PHOTOGRAPHER</title>
<link rel="shortcut icon" href="img/title.jpg" type="image/x-icon" />
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
  	var img=$("#div1");
	var left=($(document).width()-img.width())/2;
	var top =($(document).height()-img.height())/2;
	img.css({	"left":left,"top":top,"position":"absolute"});
 	$("#div1").fadeIn(2000);
 	$("#div1").fadeToggle(2000);

});

</script>
</head>
<body>
	<img style="width:600px; height:400px;display:none" src="img/1.png" id="div1" />     
</body>

</html>

