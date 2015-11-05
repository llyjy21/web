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
import com.sis.Service.ConService;
import com.sis.entity.Contact;

public class ContactAction extends ActionSupport{
	private ConService conService;
	private Contact con;
	private List<Contact> conList;
	private String chiConx;
	private String engConx;
	public ConService getConService() {
		return conService;
	}
	public void setConService(ConService conService) {
		this.conService = conService;
	}
	public Contact getCon() {
		return con;
	}
	public void setCon(Contact con) {
		this.con = con;
	}
	public List<Contact> getConList() {
		return conList;
	}
	public void setConList(List<Contact> conList) {
		this.conList = conList;
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

	public String findAllContacts() {
		
		List<Contact> list = this.conService.findAllContacts();
		if (list!=null && !list.isEmpty()){
			this.conList = list;
	        return "success";  
		}else if(list==null)
		{
			this.conList = list;
			return "success";
		}else{
			return ERROR;
		}
	}  
	
	public String userFindAllContacts() {
		
		List<Contact> list = this.conService.findAllContacts();
		if (list!=null && !list.isEmpty()){
			this.conList = list;
	        return "usersuccess";  
		}else if(list==null)
		{
			this.conList = list;
			return "usersuccess";
		}else{
			return ERROR;
		}
	}  

	public void updateContacts() {
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Contact con =null;
		con = this.conService.findConsById(1);
		try {
			con.setEngConx(URLDecoder.decode(this.getEngConx(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{			
			this.conService.updateContact(con);
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
	public String chifindAllContacts() {
		
		List<Contact> list = this.conService.findAllContacts();
		if (list!=null && !list.isEmpty()){
			this.conList = list;
	        return "chisuccess";  
		}else if(list==null)
		{
			this.conList = list;
			return "chisuccess";
		}else{
			return ERROR;
		}
	}  
	
	public String chiUserFindAllContacts() {
		
		List<Contact> list = this.conService.findAllContacts();
		if (list!=null && !list.isEmpty()){
			this.conList = list;
	        return "userchisuccess";  
		}else if(list==null)
		{
			this.conList = list;
			return "userchisuccess";
		}else{
			return ERROR;
		}
	}  

	public void chiupdateContact() {
		HttpServletResponse response = ServletActionContext.getResponse();
		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Contact con =null;
		con = this.conService.findConsById(1);
		try {
			con.setChiConx(URLDecoder.decode(this.getChiConx(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{			
			this.conService.updateContact(con);
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
