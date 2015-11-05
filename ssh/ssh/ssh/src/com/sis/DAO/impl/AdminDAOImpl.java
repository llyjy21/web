package com.sis.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.sis.DAO.AdminDAO;
import com.sis.entity.Admin;


public class AdminDAOImpl extends HibernateDaoSupport implements AdminDAO {
	private AdminDAO admindao;
	
	public AdminDAO getAdmindao() {
		return admindao;
	}

	public void setAdmindao(AdminDAO admindao) {
		this.admindao = admindao;
	}

	public boolean login(Admin admin) {

		String HQL = "from Admin e where e.username='"+ admin.getUsername()+"' and e.password='"+admin.getPassword()+"'" ;
		List<Admin> list = this.getHibernateTemplate().find(HQL);
		if (list!=null && !list.isEmpty()){
			return true;
		}else{
			
			return false;
			
		}
	}
}
