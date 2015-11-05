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
  				//$("ul").prepend(" <li>"+html+"</li>");
  				$("#newNews").attr("action", "news_doCreateNews?engConx="+html);
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
		                	if(list.get(i).getEngConx()!=null)
								engConx = list.get(i).getEngConx();
									
						%>
			            <%=engConx %>     
		              </li>
		           <%} 
         		}%>
               
  	</ul>
  	</div>
    <div>
    	
	</div>	
  </div>		
	
  </body>
</html>
