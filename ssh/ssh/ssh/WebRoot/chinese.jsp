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

<link rel="stylesheet" href="css/chistyle.css" type="text/css"/>
<script src="js/jquery-1.11.0.min.js"></script>
<script language="javascript" type="text/javascript" src="js/turn.js"></script>
<script language="javascript" type="text/javascript" src="js/tb.js"></script>
<script type="text/javascript" src="js/scroll.js"></script>
<script type="text/javascript" src="js/tc.all.js"></script>
<script type="text/javascript" src="js/position.js"></script>
<script type="text/javascript">
 function popUpdate(id,name,mark)
 {	
 	$("#update").attr("action", "folder_chiupdateFolder?folderId="+id+"");
 	$("#newChiFolderName").attr("value", name);
 	$("#newChiRemark").attr("value", mark);
 	popWin("detail2");
 }
 function videopopUpdate(top,id,name,mark)
 {
 	$("#update").attr("action", "folder_chivideoupdateFolder?engFolderName="+name+"&folderId="+id+"&topFolder="+top+"");
 	popWin("detail2");

 }
 function newFolder(top,under,parent)
 {
 	url = encodeURIComponent(under);
 	$("#newfolder").attr("action", "folder_chidoCreateFolder?underFolder="+url+"&topFolder="+top+"&parentFolderId="+parent+"");
 	popWin("detail1");
 	
 }
 function videoNewFolder(top,parent)
 {
 	$("#newfolder").attr("action", "folder_chivideodoCreateFolder?topFolder="+top+"&parentFolderId="+parent+"");
 	popWin("detail1");
 	
 }
 function fs(x)
 {
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
					    	html += "<a href=\"file_chifindAllvideo?folderId="+result.listTest[i].folderId+"&&engFoldername="+result.listTest[i].engFolderName+"&&chiRemark="+encodeURI(encodeURI(result.listTest[i].chiRemark))+"\" target=\"right\">"+result.listTest[i].chiFolderName+"</a>";
		           			html += "<div class=\"operation\"><a id=\"update\" onclick=\"videopopUpdate('"+obj.text+"',"+result.listTest[i].folderId+",'"+result.listTest[i].engFolderName+"','"+result.listTest[i].engRemark+"')\">修改</a>|<a href=\"folder_chivideoremoveFolder?folderId="+result.listTest[i].folderId+"&&engFolderName="+result.listTest[i].engFolderName+"&topFolder="+obj.text+"\">删除</a></div>";
		           			html += "</li>";
	   					} 
					}					    				
	   				html += "<div class=\"add\" onclick=\"videoNewFolder('"+obj.text+"',"+id+")\"></div>";
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
				    	html += "<a href=\"file_chiFindAllfiles?engFoldername="+result.listTest[i].engFolderName+"&&folderId="+result.listTest[i].folderId+"&&chiFoldername="+encodeURI(encodeURI(result.listTest[i].chiFolderName))+"&&underFolder="+encodeURIComponent(obj)+"&&chiRemark="+encodeURI(encodeURI(result.listTest[i].chiRemark))+"\" target=\"right\">"+result.listTest[i].chiFolderName+"</a>";
	           			html += "<div class=\"operation\"><a id=\"update\" onclick=\"popUpdate("+result.listTest[i].folderId+",'"+result.listTest[i].chiFolderName+"','"+result.listTest[i].chiRemark+"')\">修改</a>|<a href=\"folder_chiremoveFolder?folderId="+result.listTest[i].folderId+"&&chiFolderName="+result.listTest[i].chiFolderName+"&&underFolder="+encodeURIComponent(obj)+"&topFolder=img\">删除</a></div>";
	           			html += "</li>";
	            			
	   					} 
					}										    				
	   				html += "<div class=\"add\" onclick=\"newFolder('img','"+obj+"',"+id+")\"></div>";
	   				
	   				$("#list"+id).html(html);
	   				
	   				if(result.listTest.length<=9)
					{
						$('#target'+id+' .leftControl').hide();
					}
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
    <div class="log"> <a id="t1" href="user2.jsp">注销</a> </div>
  </div>
  <div class="main">
    <div id="detail">
      <div class="tit"><span class="title">登录</span><i class="close"><img src="img/close.png" width="10" height="10" style="padding:10px" /></i></div>
      <div>
        <center>
			<form action="user_doLogin" method="post">
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
			       <td><input name="log" type="submit" value="登出" /></td>
			     </tr>
			   </table>
			</form>
        </center>
      </div>
    </div>
    
    <div id="detail1">
      <div class="tit"> <span class="title">新建文件夹</span> <i class="close"><img src="img/close.png" width="10" height="10" style="padding:10px" /></i> </div>
      <center>
      <form method="post" id="newfolder">
        <table width="80%" border="0" cellspacing="0" cellpadding="5" style="padding-top:30px">
          <tr>
            <td width="40%" align="right">英文文件夹名：</td>
            <td><input name="engFolderName" type="text" style="width:150px"/></td>
          </tr>
          <tr>
            <td width="40%" align="right">中文文件夹名：</td>
            <td><input name="chiFolderName" type="text" style="width:150px"/></td>
          </tr>
          <tr>
            <td width="40%" align="right">英文备注：</td>
            <td><input name="engRemark" type="text" style="width:150px" /></td>
          </tr>
          <tr>
            <td width="40%" align="right">中文备注：</td>
            <td><input name="chiRemark" type="text" style="width:150px" /></td>
          </tr>
          <tr>
            <td width="40%" align="right" height="40"></td>
            <td><input name="log" type="submit" value="新建" /></td>
          </tr>
        </table>
        </form>
      </center>
    </div>
    <div id="detail2">
      <div class="tit"><span class="title">修改</span><i class="close"><img src="img/close.png" width="10" height="10" style="padding:10px" /></i></div>
      <div>
        <center>
          <form id="update" method="post">
            <table width="80%" border="0" cellspacing="0" cellpadding="5" style="padding-top:30px">
              <tr>
                <td width="50%" align="right">新中文文件夹名：</td>
                <td><input type="text" style="width:150px; font-size:12px; color:#929292;" onfocus="fs(this.id)" id="newChiFolderName" name="newChiFolderName" value="" /></td>
              </tr>
              <tr>
                <td width="50%" align="right">新中文备注：</td>
                <td><input type="text" style="width:150px; font-size:12px; color:#929292;" onfocus="fs(this.id)" id="newChiRemark" name="newChiRemark"/></td>
              </tr>
              <tr>
                <td width="50%" align="right" height="40"></td>
                <td><input type="submit" value="保存" /></td>
              </tr>
            </table>
          </form>
        </center>
      </div>
    </div>
    <div class="left">
    <dl>
      	<dt id="1" onclick="show('target1')"><a onclick="showFolders(1,'Architecture & Space')" >建筑与空间</a></dt>
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
      
      <dt id="2" onclick="show('target2')"><a onclick="showFolders(2,'Landscape')">景观</a></dt>
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
      
      <dt id="8"><a href="news_chifindAllNews" target="right">新闻</a></dt>
      
      <dt id="9"><a href="about_chifindAllAbout" target="right">关于</a></dt>
      
      <dt id="10"><a href="con_chifindAllContacts" target="right">联络</a></dt>
      <br />
      
      <dt id="11"><a href="main.jsp">EN</a></dt>
    </dl>
  </div>
  <iframe name="right" width="70%" height="100%" frameborder="0" allowfullscreen> </iframe>
</div>
</div>

</body>
</html>
