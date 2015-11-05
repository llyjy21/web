package com.sis.Service.impl;

import java.util.List;

import com.sis.DAO.ConDAO;
import com.sis.Service.ConService;
import com.sis.entity.Contact;

public class ConServiceImpl implements ConService {
	private ConDAO condao;
	private Contact contact;

	public ConDAO getCondao() {
		return condao;
	}

	public void setCondao(ConDAO condao) {
		this.condao = condao;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List<Contact> findAllContacts() {
		// TODO Auto-generated method stub
		return this.condao.findAllContacts();
	}
	public void updateContact(Contact con) {
		// TODO Auto-generated method stub
		this.condao.updateContact(con);
	}

	public Contact findConsById(int conId) {
		// TODO Auto-generated method stub
		return this.condao.findConsById(conId);
	}

}
