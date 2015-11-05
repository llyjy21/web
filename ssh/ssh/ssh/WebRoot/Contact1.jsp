<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="/struts-tags" prefix="s"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script src="js/jquery-1.11.0.min.js"></script>
<script language="javascript" type="text/javascript" src="js/turn.js"></script>
<script language="javascript" type="text/javascript" src="js/tb.js"></script>
<script language="javascript" type="text/javascript" src="js/edit.js"></script>
<link rel="stylesheet" href="css/style.css" type="text/css"></link>
<link rel="stylesheet" href="css/styles.css" type="text/css"></link>

</head>

<body>
<div class="right">
<div class="contact_logo">
<img src="img/phone.png" alt="" />
<img src="img/email.png" alt="" />
<img src="img/weichat.png" alt="" />
</div>
<p id="contact" class="contact">
<s:iterator value="conList">${engConx}</s:iterator>
</p>

<br/>
<div class="img">
       <a href="http://weibo.com/u/1966132855" target="_new"><img src="img/weibo.png" width="20" height="20" /></a>
       <a href="http://instagram.com/xiazhi_photographer" target="_new"><img src="img/ins.jpg" width="20" height="20"  /></a>
</div>
</div>
</body>
</html>