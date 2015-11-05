package com.sis.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sis.Service.NewsService;
import com.sis.entity.FileInfo;
import com.sis.entity.Folder;
import com.sis.entity.News;

public class NewsAction extends ActionSupport{

	private Map<String,Object> dataMap;
	private static final long serialVersionUID = 1L;
	private NewsService newsService;
	private News news;
	private List<News> newsList;
	private String chiConx;
	private String engConx;
	private String imgPath;
	private int newsId;
	
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getChiConx() {
		return chiConx;
	}
	public void setChiConx(String chiConx) {
		this.chiConx = chiConx;
	}
	public String getEngConx() {
		return engConx;
	}
	public void setEngConx(String engConx) {
		this.engConx = engConx;
	}
	public List<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	public NewsService getNewsService() {
		return newsService;
	}
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	
	public String engdoCreateNews(){

		String engConx = this.getEngConx();
		String chiConx = null;
		String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
		String filePath = uploadFile+"\\News";
		
		News news = new News();
		news.setEngConx(engConx);
		news.setChiConx(chiConx);
			
		File file = new File(filePath);
		if(!file .exists()  && !file .isDirectory())    
		{    
		    file.mkdirs();    
		}    
		  		  
		this.newsService.doCreateNews(news);
		
		return "success";
	}
	
	public String chidoCreateNews(){

		String engConx = null;
		String chiConx = this.getChiConx();
		String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
		String filePath = uploadFile+"\\News";
		
		News news = new News();
		news.setEngConx(engConx);
				
		try {
			news.setChiConx(URLDecoder.decode(chiConx,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File file = new File(filePath);
		if(!file .exists()  && !file .isDirectory())    
		{    
		    file.mkdirs();    
		}    
		
		this.newsService.doCreateNews(news);
		
		return "chisuccess";
	}  
	
	//管理员
	public String findAllNews() {
		
		List<News> list = this.newsService.findAllNews();		
		if (list!=null && !list.isEmpty()){
			this.newsList = list;
	        return "success";  
		}else if(list==null)
		{
			this.newsList = list;
			return "success";
		}else{
			return ERROR;
		}
	} 
	
	public void updateNews() {
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		News news =null;
		news = this.newsService.findNewsById(1);
		try {
			news.setEngConx(URLDecoder.decode(this.getEngConx(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{			
			this.newsService.updateNews(news);
			try {
				((ServletOutputStream) out).print("yes");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}catch(Exception e){
			try {
				((ServletOutputStream) out).print("no");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public String chifindAllNews() {
		
		List<News> list = this.newsService.findAllNews();		
		if (list!=null && !list.isEmpty()){
			this.newsList = list;
	        return "chisuccess";  
		}else if(list==null)
		{
			this.newsList = list;
			return "chisuccess";
		}else{
			return ERROR;
		}
	}  

	public void chiupdateNews() {
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		News news =null;
		news = this.newsService.findNewsById(1);
		try {

			news.setChiConx(URLDecoder.decode(this.getChiConx(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{			
			this.newsService.updateNews(news);
			try {
				((ServletOutputStream) out).print("yes");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}catch(Exception e){
			try {
				((ServletOutputStream) out).print("no");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//用户
	
	public String userFindAllNews() {
		
		List<News> list = this.newsService.findAllNews();		
		if (list!=null && !list.isEmpty()){
			this.newsList = list;
	        return "userSuc";  
		}else if(list==null)
		{
			this.newsList = list;
			return "userSuc";
		}else{
			return ERROR;
		}
	} 
	
	public String userChifindAllNews() {
		
		List<News> list = this.newsService.findAllNews();		
		if (list!=null && !list.isEmpty()){
			this.newsList = list;
	        return "userChiSuc";  
		}else if(list==null)
		{
			this.newsList = list;
			return "userChiSuc";
		}else{
			return ERROR;
		}
	}  
	
	public String removeNews() {

		//News news = null;
		try{
				//news = this.newsService.findNewsById(this.getNewsId());
				this.newsService.removeNews(this.getNewsId());
				return "success";
		} catch (Exception e) {
			return "success";
		}
	}  
	
	
	//upload
	private File upload;
	private String uploadContentType;
	private String uploadFileName;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String execute() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// CKEditor提交的很重要的一个参数
		String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");

		String expandedName = ""; // 文件扩展名
		if (uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) {
			// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			expandedName = ".jpg";
		} else if (uploadContentType.equals("image/png") || uploadContentType.equals("image/x-png")) {
			// IE6上传的png图片的headimageContentType是"image/x-png"
			expandedName = ".png";
		} else if (uploadContentType.equals("image/gif")) {
			expandedName = ".gif";
		} else if (uploadContentType.equals("image/bmp")) {
			expandedName = ".bmp";
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'','文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
			out.println("</script>");
			return null;
		}
		
		/*
		if (upload.length() > 600 * 1024) {
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",''," + "'文件大小不得大于600k');");
			out.println("</script>");
			return null;
		}*/

		//将文件保存到项目目录下  
        InputStream is = new FileInputStream(upload);  
        String uploadPath = ServletActionContext.getServletContext().getRealPath("/uploads/News");  //设置保存目录  
        String fileName = java.util.UUID.randomUUID().toString();  //采用UUID的方式随机命名  
        fileName += uploadFileName.substring(uploadFileName.length() - 4);  
        File toFile = new File(uploadPath, fileName);  
        OutputStream os = new FileOutputStream(toFile);     
        byte[] buffer = new byte[1024];     
        int length = 0;  
        while ((length = is.read(buffer)) > 0) {     
            os.write(buffer, 0, length);     
        }     
        is.close();  
        os.close();  
		
		// 返回“图像”选项卡并显示图片
		out.println("<script type=\"text/javascript\">");
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
				+ ",' uploads/News/" + fileName + "','')"); // 相对路径用于显示图片
		out.println("</script>");
		return null;
	}
}

