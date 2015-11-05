package com.sis.Action;

import com.opensymphony.xwork2.ActionSupport;
import com.sis.entity.Admin;
import com.sis.Service.AdminService;
public class AdminAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private AdminService adminService;
	private Admin admin;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String doLogin() throws Exception {
		
		boolean user = this.adminService.login(admin);
		if(user){
			
            return "engSuc";
		}else{
			
			return ERROR;
		}

	}
	public String chidoLogin() throws Exception {
		
		boolean user = this.adminService.login(admin);
		if(user){
			
            return "chiSuc";
		}else{
			
			return ERROR;
		}

	}
}
