<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<script src="js/jquery-1.11.0.min.js"></script>
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
				    	html += "<a href=\"file_userFindAllvideo?folderId="+result.listTest[i].folderId+"&&topFolder=videoFiles"+"&&engFoldername="+result.listTest[i].engFolderName+"&&underFolder="+encodeURIComponent(obj.text)+"&&engRemark="+result.listTest[i].engRemark+"\" target=\"right\">"+result.listTest[i].engFolderName+"</a>";
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
				    	html += "<a href=\"file_userfindAllfiles?folderId="+result.listTest[i].folderId+"&&topFolder=img"+"&&engFoldername="+result.listTest[i].engFolderName+"&&underFolder="+encodeURIComponent(obj.text)+"&&engRemark="+result.listTest[i].engRemark+"\" target=\"right\">"+result.listTest[i].engFolderName+"</a>";
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
    <div class="log"> <a id="t1">login</a> </div>
  </div>
  <div class="main">
    <div id="detail">
      <div class="tit"><span class="title">login</span><i class="close"><img src="img/close.png" width="10" height="10" style="padding:10px" /></i></div>
      <div>
        <center>
			<form action="user_doLogin" method="post">
			   <table width="60%" border="0" cellspacing="0" cellpadding="5" style="padding-top:30px">
			     <tr>
			       <td width="40%" align="right">username：</td>
			       <td><input name="admin.username" type="text" style="width:150px"/></td>
			     </tr>
			     <tr>
			       <td width="40%" align="right">password：</td>
			       <td><input name="admin.password" type="password" style="width:150px" /></td>
			     </tr>
			     <tr>
			       <td width="40%" align="right" height="40"></td>
			       <td><input name="log" type="submit" value="login" /></td>
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
      	<dt id="1" onclick="show('target1')"><a onclick="showFolders(1,this)" >Architecture <i style="font-family:'Arial'">&</i> Space</a></dt>
		<span id="target1" style="display:none" >
	      <div class="leftControl">
	        <div class="leftTop"> </div>
	        <div class="leftDown"> </div>
	      </div>      
	      <div class="list" id="list1"></div>
		</span>	
		     
      <dt id="3" onclick="show('target3')" ><a onclick="showFolders(3,this)">Exhibition</a></dt>
      <span id="target3" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
      <div class="list" id="list3"></div>
      </span>

      <dt id="4" onclick="show('target4')" ><a onclick="showFolders(4,this)">Art <i style="font-family:'Arial'">&</i> Design</a></dt>
      <span id="target4" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
       <div class="list" id="list4"></div>
      </span>
      
      <dt id="5" onclick="show('target5')" ><a onclick="showFolders(5,this)">Construction Related</a></dt>
      <span id="target5" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
       <div class="list" id="list5"></div>     
      </span>
      
      <dt id="2" onclick="show('target2')"><a onclick="showFolders(2,this)">Landscape</a></dt>
      <span id="target2" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
      <div class="list" id="list2"></div>
      </span>
      
      <dt id="6" onclick="show('target6')" ><a onclick="showFolders(6,this)">Other</a></dt>
      <span id="target6" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
      <div class="list" id="list6"></div>
     
      </span>
      <dt id="7" onclick="show('target7')" ><a onclick="showFolders(7,this)">Video</a></dt>
      <span id="target7" style="display:none" >
      <div class="leftControl">
        <div class="leftTop"> </div>
        <div class="leftDown"> </div>
      </div>
       <div class="list" id="list7">
       		
       </div>
      </span> 
      <br/>
      
      <dt id="8"><a href="news_userFindAllNews" target="right">News</a></dt>
      
      <dt id="9" ><a href="about_userFindAllAbouts" target="right">About</a></dt>
      
      <dt id="10"><a href="con_userFindAllContacts" target="right">Contact</a></dt>
      <br />
      
      <dt id="11"><a href="user2.jsp">中文</a></dt>
    </dl>
  </div>
  <iframe name="right" width="70%" height="100%" frameborder="0" allowfullscreen> </iframe>
 
</div>
</div>

</body>
</html>
