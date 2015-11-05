<%@ page language="java" import="java.util.*,com.sis.entity.FileInfo" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'chiRightImg.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/demo-styles.css" />
    <link rel="stylesheet" href="css/styles.css" type="text/css"/>
    <link rel="stylesheet" href="css/style.css" type="text/css"/>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="js/position.js"></script>
    <script type="text/javascript" src="js/turn.js"></script>
    <script type="text/javascript" src="js/scripts.js"></script>
    
    <script>
	    $(document).ready(function(){
	      $('#gallery-container').sGallery({
	        fullScreenEnabled: true
	      });
	    });
  	</script>
	<script type="text/javascript">
	function showImg(){
			showFolders(1,this)
	}
	</script>
    <link rel="stylesheet" href="css/uploadify.css" type="text/css"/>
    <script type="text/javascript" src="js/jquery.uploadify.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){       	 
            $("#imageify").uploadify({
                'fileObjName' : 'file', //提交时候的字段名，和struts2里面用来接收File的字段名一致
				'method' : 'get',            //以get方式上传
                'formData' : {'uploadFilePath' :'<%=java.net.URLEncoder.encode(session.getAttribute("path").toString())%>','uploadfid' :'<%=java.net.URLEncoder.encode(session.getAttribute("fid").toString())%>','Strefname' :'<%=java.net.URLEncoder.encode(session.getAttribute("efname").toString())%>'},
                'fileTypeExts': '*.gif; *.jpg; *.png',//允许上传的文件类型，限制弹出文件选择框里能选择的文件
                'buttonText':'Browse...',        //上传按钮的文字
                'auto':false,    //选择一个文件是否自动上传
                'multi':true,    //是否允许多选(这里多选是指在弹出对话框中是否允许一次选择多个，但是可以通过每次只上传一个的方法上传多个文件)
                'swf' : 'swf/uploadify.swf',    //指定swf文件的，默认是uploadify.swf
                'uploader' : 'file_doCreatefile',                //服务器响应地址
                'onQueueComplete' : function() {
            		alert("文件上传成功！");
            		window.location.reload(); 
        		}   
            });
	});
    </script>

  </head>
  
  <body>
  
      <div class="demo-wrapper">
        <div id="focus">
          <div id="gallery-container">
            <ul id="small" class="items--small">
              <% 
                List<FileInfo> list=(List<FileInfo>)request.getAttribute("fileInfoList");  
                int index;          
				if(list!=null){
	                for(int  i=0;i<list.size();i++){
	                %>
              <li class="item">
                <% 
							String virtualPath = list.get(i).getFilePath();
							virtualPath+= "/"+session.getAttribute("efname");
							String virtualName = list.get(i).getFileName();
				%>
                <div class="delete">
                  <input name="delete" type="checkbox" value="ck<%=list.get(i).getFileId()%>"/>
                </div>
                <a href="#"><img src="uploads/<%=virtualPath%>/<%=virtualName%>" alt="" /></a></li>
              <%} 
               }%>
            </ul>
            <ul class="items--big">
              <% 
                     
				if(list!=null){
	                for(int  i=0;i<list.size();i++){
	                %>
              <li class="item--big">
                <% 
							String virtualPath = list.get(i).getFilePath();
							virtualPath+= "/"+session.getAttribute("efname");
							String virtualName = list.get(i).getFileName();
				%>
                <center>
                  <a href="#"><img src="uploads/<%=virtualPath%>/<%=virtualName%>" alt="" /></a>
                </center>
              </li>
              <%} 
               }%>
            </ul>
          </div>
        </div>
        <div class="mark">
        	<div class="controls"> <span class="control icon-arrow-left" data-direction="previous"></span> <span class="grid icon-grid"></span> <span class="control icon-arrow-right" data-direction="next"></span> </div>
        	<div class="remark"><%=session.getAttribute("cremark")%></div>
        </div>
      </div>
      <div class="upload">
        <table width="100%" border="0" class="uptable">
          <tr>
            <td width="20%" ><input type="checkbox" id="operAll" onclick="getAll()" style="float:left;" /><div style="float:left; margin-left:10px">全选</div></td>
            <td width="40%" align="left" ><input id="delete" type="button" value="删除" style="width:70px" onclick="deleteFile()"></td>
            <td width="40%">
	            <form method="post" enctype="multipart/form-data" action="">
	                <table width=100%>
		                <tr>
		                    <td><div id="queue"></div>
		                    <!-- 上传文件存放的地方，ID为queue -->
		                    <input id="imageify" name="imageify" type="file"/>
		                    </td>
		                    <td><a href="javascript:$('#imageify').uploadify('upload', '*')">Upload</a></td>
		                    <td><a href="javascript:$('#imageify').uploadify('cancel', '*')">Cancel</a></td>
		                  </tr>
	              </table>
	              </form>
              </td>
          </tr>
        </table>
	        <script>
	function deleteFile(){
	
		var checkedNum = $("input[name='delete']:checked").length;   
		if(checkedNum == 0) {   
			
			alert("请选择至少一项！");   
			return;   
	    }else{   
	              var checkedList = new Array();   
	              $("input[name='delete']:checked").each(function() {   
	                  checkedList.push($(this).val().substring(2));   
	              }); 
	              $.ajax({   
	                  type: "POST",   
	                  url: "file_removefile",   
	                  data: {'delitems':checkedList.toString()}, 
	                  success: function(result) {   
	                      $("[name ='delete']:checkbox").attr("checked", false); 
	                      window.location.reload();   
	                  }   
	              });   
		}               
	}
	function getAll()
	{ 
		var tit = document.getElementById("operAll"); 
		var inputs = document.getElementsByTagName("input"); 
		for(var i = 0; i < inputs.length; i++) 
		{  
			if(inputs[i].type == "checkbox")  
			{   
				if(tit.checked == true)   
				{    
					inputs[i].checked = true;   
				}
				else
				{    inputs[i].checked = false;   }  
			} 
		}
	}
	</script> 
      </div>
 
  </body>
</html>
