<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>XIAZHI PHOTOGRAPHER</title>
	<link rel="shortcut icon" href="img/title.jpg" type="image/x-icon" />
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/chistyle.css" type="text/css"/>
<script src="js/jquery-1.11.0.min.js"></script>
<script language="javascript" type="text/javascript" src="js/turn.js"></script>
<script language="javascript" type="text/javascript" src="js/tb.js"></script>
<script type="text/javascript" src="js/scroll.js"></script>
<script type="text/javascript" src="js/tc.all.js"></script>
<script type="text/javascript" src="js/position.js"></script>
<script type="text/javascript">
 
 function fs(x)
 {
 	document.getElementById(x).value="";
 	document.getElementById(x).style.color="black";
 }
</script>
<script type="text/javascript">
function showFolders(id,obj){
		if(id==7)
		{
			var url = "folder_findAllFolders?parentFolderId="+id;
			var html='';
			$.ajax({ 
				type: "get", 
				async:false,
				url: url,							
				dataType:"json",
				success : function(result) {				
					var html = "" ;
					if(!($.isEmptyObject(result))){
	
						for(var i=0;i<result.listTest.length;i++){
	   					html += "<li>";
				    	html += "<a href=\"file_userChifindAllvideo?folderId="+result.listTest[i].folderId+"&&engFoldername="+result.listTest[i].engFolderName+"&&chiRemark="+encodeURI(encodeURI(result.listTest[i].chiRemark))+"\" target=\"right\">"+result.listTest[i].chiFolderName+"</a>";
				    	html += "</li>";
	            			
	   					} 
					}					    				
	   				$("#list"+id).html(html);
				}
			}); 
		}else{
			var url = "folder_findAllFolders?parentFolderId="+id;
			var html='';
			$.ajax({ 
				type: "get", 
				async:false,
				url: url,							
				dataType:"json",
				success : function(result) {				
					var html = "" ;
					if(!($.isEmptyObject(result))){
	
						for(var i=0;i<result.listTest.length;i++){
	   					html += "<li>";
				    	html += "<a href=\"file_userChifindAllfiles?engFoldername="+result.listTest[i].engFolderName+"&&folderId="+result.listTest[i].folderId+"&&chiFoldername="+encodeURI(encodeURI(result.listTest[i].chiFolderName))+"&&underFolder="+encodeURIComponent(obj)+"&&chiRemark="+encodeURI(encodeURI(result.listTest[i].chiRemark))+"\" target=\"right\">"+result.listTest[i].chiFolderName+"</a>";
	           			html += "</li>";
	            			
	   					} 
					}		
	   										    				
	   				if(result.listTest.length<=10)
					{
						$('#target'+id+' .leftControl').hide();
					}
						   				
	   				$("#list"+id).html(html);
				}
			}); 
		}
}
</script>

</head>

<body>
<div class="bg">
  <div class="header"> 
    <img src="img/logo.jpg"/>
    <div class="log"> <a id="t1">登录</a> </div>
  </div>
  <div class="main">
    <div id="detail">
      <div class="tit"><span class="title">登录</span><i class="close"><img src="img/close.png" width="10" height="10" style="padding:10px" /></i></div>
      <div>
        <center>
			<form action="user_chidoLogin" method="post">
			   <table width="60%" border="0" cellspacing="0" cellpadding="5" style="padding-top:30px">
			     <tr>
			       <td width="40%" align="right">用户名：</td>
			       <td><input name="admin.username" type="text" style="width:150px"/></td>
			     </tr>
			     <tr>
			       <td width="40%" align="right">密码：</td>
			       <td><input name="admin.password" type="password" style="width:150px" /></td>
			     </tr>
			     <tr>
			       <td width="40%" align="right" height="40"></td>
			       <td><input name="log" type="submit" value="登录" /></td>
			     </tr>
			   </table>
			</form>
        </center>
      </div>
    </div>
    <script type="text/javascript">
			$("#t1").click(function(){
					popWin("detail");
			});
			
    </script>
   
    <div class="left">
    <dl>
      	<dt id="1" onclick="show('target1')"><a onclick="showFolders(1,this)" >建筑与空间</a></dt>
		<span id="target1" style="display:none" >
	      <div class="leftControl">
	        <div class="leftTop"> </div>
	        <div class="leftDown"> </div>
	      </div>      
	      <div class="list" id="list1"></div>
		</span>	
		
      <dt id="3" onclick="show('target3')" ><a onclick="showFolders(3,'Exhibition')">展览</a></dt>
      <span id="target3" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
      <div class="list" id="list3"></div>
      </span>

      <dt id="4" onclick="show('target4')" ><a onclick="showFolders(4,'Art & Design')">艺术与设计</a></dt>
      <span id="target4" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
       <div class="list" id="list4"></div>
      </span>
      
      <dt id="5" onclick="show('target5')" ><a onclick="showFolders(5,'Construction Related')">建筑相关</a></dt>
      <span id="target5" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
       <div class="list" id="list5"></div>     
      </span>
      
      <dt id="2" onclick="show('target2')"><a onclick="showFolders(2,this)">景观</a></dt>
      <span id="target2" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
      <div class="list" id="list2"></div>
      </span>
      
      <dt id="6" onclick="show('target6')" ><a onclick="showFolders(6,'Other')">其他</a></dt>
      <span id="target6" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
      <div class="list" id="list6"></div>
     
      </span>
      <dt id="7" onclick="show('target7')" ><a onclick="showFolders(7,this)">视频</a></dt>
      <span id="target7" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
       <div class="list" id="list7"></div>
      </span> 
      <br/>
      
      <dt id="8"><a href="news_userChifindAllNews" target="right">新闻</a></dt>
      
      <dt id="9"><a href="about_userChifindAllAbout" target="right">关于</a></dt>
      
      <dt id="10"><a href="con_chiUserFindAllContacts" target="right">联络</a></dt>
      <br />
      
      <dt id="11"><a href="user1.jsp">EN</a></dt>
    </dl>
  </div>
  <iframe name="right" width="70%" height="100%"  frameborder="0" allowfullscreen> </iframe>
</div>
</div>

</body>
</html>
