package com.sis.Service.impl;

import com.sis.DAO.AdminDAO;
import com.sis.Service.AdminService;
import com.sis.entity.Admin;

public class AdminServiceImpl implements AdminService {
	
	private AdminDAO admindao; 

	public AdminDAO getAdmindao() {
		return admindao;
	}
	public void setAdmindao(AdminDAO admindao) {
		this.admindao = admindao;
	}
	
	public boolean login(Admin admin) {
		
		return this.admindao.login(admin);
	}

}
