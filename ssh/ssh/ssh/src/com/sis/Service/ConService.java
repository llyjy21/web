package com.sis.Service;

import java.util.List;
import com.sis.entity.Contact;

public interface ConService {
	 public List<Contact> findAllContacts();  
	 public void updateContact(Contact con); 
	 public Contact findConsById(int conId);
}
