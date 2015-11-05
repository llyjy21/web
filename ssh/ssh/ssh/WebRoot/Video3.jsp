<%@ page language="java" import="java.util.*,com.sis.entity.FileInfo" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'rightVideo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/style.css" type="text/css"></link>
	<link rel="stylesheet" href="css/styles.css" type="text/css"></link>
	<link rel="stylesheet" href="css/uploadify.css" type="text/css"></link>
	
	<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.uploadify.min.js"></script>
	
	<link href="css/video-js.css" rel="stylesheet" type="text/css">
    <script src="js/video.js"></script>
    <script>
    videojs.options.flash.swf = "js/video-js.swf";
    </script>
  </head>
  
  <body>
	 <div class="video">
	<% 
		List<FileInfo> list=(List<FileInfo>)request.getAttribute("fileInfoList");  
		int index;          
		if(list!=null){		
			String virtualPath = list.get(0).getFilePath().substring(5);
			System.out.println(virtualPath);
			virtualPath+= "/"+session.getAttribute("efname");
			String virtualName = list.get(0).getFileName();
	%>
	<video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="100%" height="100%" data-setup="{}">
   		<source src="uploads/Video<%=virtualPath%>/<%=virtualName%>" type="video/mp4" />
   		<%
		}%>
		
    </video>
		
		
		<div class="videoRemark"><%=session.getAttribute("remark")%></div>

 	</div>
  </body>
</html>
