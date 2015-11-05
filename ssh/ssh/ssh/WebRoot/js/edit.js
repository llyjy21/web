// JavaScript Document
$(function() {
//获取class为caname的元素
$("#news").click(function() {
var td = $(this);
var txt = td.text();

var input = $("<textarea id='news' cols='60' rows='15' class='news' style='margin-top:20px'>"+txt+"</textarea>");

td.html(input);
input.click(function() { return false; });
//获取焦点
input.trigger("focus");
//文本框失去焦点后提交内容，重新变为文本
input.blur(function() {
var newtxt = $(this).val();
//判断文本有没有修改
if (newtxt != txt) {
	td.html(newtxt);
	var caid = $.trim(td.prev().text());
	//ajax异步更改数据库,加参数date是解决缓存问题
	var url = "news_updateNews";
	var codeNewTxt = encodeURIComponent(newtxt);
	//使用get()方法打开一个一般处理程序，data接受返回的参数（在一般处理程序中返回参数的方法 context.Response.Write("要返回的参数");）
	//数据库的修改就在一般处理程序中完成
	$.get(url,{ engConx: codeNewTxt}, function(data) {
		if(data=="yes")
		{
			alert("新闻更新成功！");
			d.html(newtxt);
		}else if(data == "no"){
			alert("出现异常，新闻更新失败！");
			td.html(txt);
			return;	
		}
			
	});
}
else
{
	td.html(newtxt);
}
});
});

$("#about").click(function() {
var td = $(this);
var txt = td.text();
var input = $("<textarea id='about' cols='60' rows='15' class='about' style='margin-top:20px'>"+txt+"</textarea>");
td.html(input);
input.click(function() { return false; });
//获取焦点
input.trigger("focus");
//文本框失去焦点后提交内容，重新变为文本
input.blur(function() {
var abouttxt = $(this).val();
//判断文本有没有修改
if (abouttxt != txt) {
td.html(abouttxt);
var caid = $.trim(td.prev().text());
//ajax异步更改数据库,加参数date是解决缓存问题
var url = "about_updateAbout";
var codeNewTxt = encodeURIComponent(abouttxt);
//使用get()方法打开一个一般处理程序，data接受返回的参数（在一般处理程序中返回参数的方法 context.Response.Write("要返回的参数");）
//数据库的修改就在一般处理程序中完成
$.get(url,{ engConx: codeNewTxt}, function(data) {
	if(data=="yes")
	{
		alert("简介更新成功！");
		d.html(abouttxt);
	}else if(data == "no"){
		alert("出现异常，更新简介失败！");
		td.html(txt);
		return;	
	}
		
});
}
else
{
td.html(abouttxt);
}
});
});

$("#contact").click(function() {
	var td = $(this);
	var txt = td.text();
	var input = $("<textarea id='contact' cols='60' rows='10' class='contact' style='margin-top:20px'>"+txt+"</textarea>");
	td.html(input);
	input.click(function() { return false; });
	//获取焦点
	input.trigger("focus");
	//文本框失去焦点后提交内容，重新变为文本
	input.blur(function() {
	var contxt = $(this).val();
	//判断文本有没有修改
	if (contxt != txt) {
	td.html(contxt);
	var caid = $.trim(td.prev().text());
	//ajax异步更改数据库,加参数date是解决缓存问题
	var url = "con_updateContacts";
	var codeNewTxt = encodeURIComponent(contxt);
	//使用get()方法打开一个一般处理程序，data接受返回的参数（在一般处理程序中返回参数的方法 context.Response.Write("要返回的参数");）
	//数据库的修改就在一般处理程序中完成
	$.get(url,{ engConx: codeNewTxt}, function(data) {
		if(data=="yes")
		{
			alert("联系方式更新成功！");
			d.html(contxt);
		}else if(data == "no"){
			alert("出现异常，更新联系方式失败！");
			td.html(txt);
			return;	
		}
			
	});
	}
	else
	{
	td.html(contxt);
	}
	});
	});
});