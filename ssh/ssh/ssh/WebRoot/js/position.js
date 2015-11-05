$(document).ready(function(){
	
	var logoWidth=parseInt($('.left').css("width"));
	$('.header img').css('width',logoWidth);
	
	var ifrHeight=parseInt($('iframe').css("width"))*0.5;
	$('iframe').css('height',ifrHeight);
	
	var wd=$('#focus').css("width");
	var ht=$('#focus').css("height");
	
	wd=parseInt(wd);
	ht=parseInt(ht);
	var width=wd*0.33+'px';
	var height=ht*0.33+'px';
	var left=wd*0.005+'px';
	var top=ht*0.005+'px';

	$('#small li').css({'width':width,'height':height,'margin-top':top,'margin-left':left});
	$('#small').css('height',ht);
	$('.items--big').css('height',ht);
})