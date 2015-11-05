package com.sis.Action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sis.Service.AboutService;
import com.sis.entity.About;

public class AboutAction extends ActionSupport{
	private AboutService aboutService;
	private About about;
	private List<About> aboutList;
	private String chiConx;
	private String engConx;
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
	public AboutService getAboutService() {
		return aboutService;
	}
	public void setAboutService(AboutService aboutService) {
		this.aboutService = aboutService;
	}
	public About getAbout() {
		return about;
	}
	public void setAbout(About about) {
		this.about = about;
	}
	public List<About> getAboutList() {
		return aboutList;
	}
	public void setAboutList(List<About> aboutList) {
		this.aboutList = aboutList;
	}
	
	public String findAllAbouts() {
		
		List<About> list = this.aboutService.findAllAbouts();
		if (list!=null && !list.isEmpty()){
			this.aboutList = list;
	        return "success";  
		}else if(list==null)
		{
			this.aboutList = list;
			return "success";
		}else{
			return ERROR;
		}
	}  
	
	public String userFindAllAbouts() {
		
		List<About> list = this.aboutService.findAllAbouts();
		if (list!=null && !list.isEmpty()){
			this.aboutList = list;
	        return "usersuccess";  
		}else if(list==null)
		{
			this.aboutList = list;
			return "usersuccess";
		}else{
			return ERROR;
		}
	}  

	public void updateAbout() {
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		About about =null;
		about = this.aboutService.findAboutById(1);
		try {
			about.setEngConx(URLDecoder.decode(this.getEngConx(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{			
			this.aboutService.updateAbout(about);
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
	public String chifindAllAbout() {
		
		List<About> list = this.aboutService.findAllAbouts();
		if (list!=null && !list.isEmpty()){
			this.aboutList = list;
	        return "chisuccess";  
		}else if(list==null)
		{
			this.aboutList = list;
			return "chisuccess";
		}else{
			return ERROR;
		}
	}
	
	public String userChifindAllAbout() {
		
		List<About> list = this.aboutService.findAllAbouts();
		if (list!=null && !list.isEmpty()){
			this.aboutList = list;
	        return "userchisuccess";  
		}else if(list==null)
		{
			this.aboutList = list;
			return "userchisuccess";
		}else{
			return ERROR;
		}
	}  

	public void chiupdateAbout() {
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		About about =null;
		about = this.aboutService.findAboutById(1);
		try {
			about.setChiConx(URLDecoder.decode(this.getChiConx(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{			
			this.aboutService.updateAbout(about);
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
}
