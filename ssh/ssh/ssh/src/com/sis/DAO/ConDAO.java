package com.sis.DAO;

import java.util.List;

import com.sis.entity.Contact;

public interface ConDAO {
	 public List<Contact> findAllContacts();  
	 public void updateContact(Contact con); 
	 public Contact findConsById(int conId);
}
