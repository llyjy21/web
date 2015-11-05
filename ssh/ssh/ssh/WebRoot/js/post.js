$(document).ready(function() {
    
	$('#postbtn').click(function(){
		
		var html='';
		var title=$('newstitle').val();
		var data=$('.forumeditor').val();
		
		html+='	<table width="580px" class="topic"><tr><td class="postdetail">';
		html+='	<div class="postcontent">';
		html+='<h2>'+title+'</h2>';
		html+='<div id="content_0" class="contentmsg">'+data+'</div>';
		html+='</div></td></tr></table>';
		
		$('.right').prepend(html);
		
		var url = "news_updateNews";
		var codeNewTxt = encodeURIComponent(abouttxt);
		//使用get()方法打开一个一般处理程序，data接受返回的参数（在一般处理程序中返回参数的方法 context.Response.Write("要返回的参数");）
		//数据库的修改就在一般处理程序中完成
		$.get(url,{ engConx: codeNewTxt}, function(data) {
			if(data=="yes")
			{
				alert("发布成功！");
				d.html(abouttxt);
			}else if(data == "no"){
				alert("出现异常，发布失败！");
				td.html(txt);
				return;	
			}
				
		});
			
	});
});	
