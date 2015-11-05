package com.sis.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.sis.DAO.ConDAO;
import com.sis.entity.Contact;

public class ConDAOImpl extends HibernateDaoSupport implements ConDAO {

	public List<Contact> findAllContacts() {
		// TODO Auto-generated method stub
		String HQL = "from Contact e" ;
		List<Contact> list = this.getHibernateTemplate().find(HQL);
		if (list!=null && !list.isEmpty()){
			return list;
		}else{
			return null;
		}
	}

	public void updateContact(Contact con) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(con);
	}

	public Contact findConsById(int conId) {
		// TODO Auto-generated method stub
		Contact con = null;  
		con = (Contact) this.getHibernateTemplate().get(Contact.class, conId);  
        return con;  
	}

}
