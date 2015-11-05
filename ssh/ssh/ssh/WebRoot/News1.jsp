<%@ page language="java" import="java.util.*,com.sis.entity.News" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'News3.jsp' starting page</title>
    
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
						
			$("#submit").click(function(){
  				
  				var html= CKEDITOR.instances.editor1.getData();  //编辑器内容
  				$("#newNews").attr("action", "news_engdoCreateNews?engConx="+html);
  				ResetText();
  				
			});
			
			function ResetText(){
				CKEDITOR.instances.editor1.setData('');
			}
	
		})
		
	</script>
</head>
  
<body>
  <div class="news">
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
		                	String engConx="";
		                	int id=0;
		                	if(list.get(i).getEngConx()!=null)
							{
								engConx = list.get(i).getEngConx();
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
			            <%=engConx %>     
		              </li>
		              
		            <%} 
         		}%>
               
  	</ul>
  
  	</div>
    <div style="margin-top:10px">
    	<form action=" " method="post" id="newNews">
		<textarea cols="30" rows="30" id="editor1" name="editor1">
		
		</textarea>
		
		<script>
			CKEDITOR.replace( 'editor1' );
		</script>
	
		
		<p>
		<input type="submit" value="Submit" id="submit">
		</p>
		</form>
		
	</div>	
  </div>		
	
  </body>
</html>
