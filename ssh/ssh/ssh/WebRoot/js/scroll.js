$(function() {
	 $(".bg").fadeIn(2000);
	
	var sHeight = 20; 
	len = 0; 
	var index = 0;
	var picTimer;
	
	//上一页按钮
	$(".leftTop").click(function(e) {
		tag=e.target.parentNode.parentNode.id;
		len=$("#"+tag +" li").length;
		index -= 1;
		if(index == -1) {index = 0;}
		showPics(index,tag);
	});

	//下一页按钮
	$(".leftDown").click(function(e) {
		tag=e.target.parentNode.parentNode.id;
		len=$("#"+tag +" li").length;
		index += 1;
		console.log(index);
		if(len>=10)
		{
			if(index == (len-8)) {index = len - 9;}
			showPics(index,tag);
		}
		
	});

	function showPics(index,tag) { //普通切换
		
		var nowTop = -index*sHeight;
		$("#"+tag +" .list").stop(true,false).animate({"top":nowTop},300); 
		index=0;
		
	}
});

