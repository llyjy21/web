<%@ page language="java" import="java.util.*,com.sis.entity.News" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'News2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/news.css">
	<script src="js/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<style>

		.cke_focused,
		.cke_editable.cke_focused
		{
			outline: 3px dotted blue !important;
			*border: 3px dotted blue !important;	/* For IE7 */
		}

	</style>
	<script type="text/javascript">
		$(document).ready(function(){
						
			$("#chisubmit").click(function(){
  				
  				var html = CKEDITOR.instances.editor2.getData();  //编辑器内容
  				var chitext = encodeURI(encodeURI(html));
  				$("#chiNews").attr("action", "news_chidoCreateNews?chiConx="+chitext);
  				ResetText();
  				
			});
			
			function ResetText(){
				CKEDITOR.instances.editor2.setData('');
			}
			
					
		})
		
	</script>
</head>
  
<body>
  <div class="chi news">
  	<div>
  	<ul id="news">
  		<% 
          List<News> list=(List<News>)request.getAttribute("newsList");  
                int index;          
				if(list!=null){
	                for(int  i=0;i<list.size();i++){
	                
	                %>
		              <li>
		                <% 
		                	String chiConx="";
		                	int id=0;
		                	if(list.get(i).getChiConx()!=null)
		                	{
								chiConx = list.get(i).getChiConx();
								id=	list.get(i).getNewsId();
								%>
								<script type="text/javascript">
									function deleteNews(id){
								          $.ajax({   
								                 type: "POST",   
								                 url: "news_removeNews",   
								                 data: {'newsId':id}, 
								                 success: function(result) {   
								                    window.location.reload();   
								                 }   
								             }); 
								        
									}
									$('ul').append("<a href='javascript:void(0);' onclick='deleteNews(<%=id%>)'><span>删除</span></a>");
								</script>
								
								<%
							}		
						%>
			            <%=chiConx %>     
		              </li>
		           <%} 
         		}%>
               
  	</ul>
  	</div>
    <div style="margin-top:10px">
    	<form action=" " method="post" id="chiNews">
		<textarea cols="30" rows="30" id="editor2" name="editor2">
		
		</textarea>
		
		<script>
			CKEDITOR.replace( 'editor2' );
		</script>
	
		
		<p>
		<input type="submit" value="Submit" id="chisubmit">
		</p>
		</form>
		
	</div>	
  </div>		
	
  </body>
</html>
