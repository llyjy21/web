package com.sis.entity;

/**
 * Contact entity. @author MyEclipse Persistence Tools
 */

public class Contact implements java.io.Serializable {

	// Fields

	private Integer contactId;
	private String engConx;
	private String chiConx;

	// Constructors

	/** default constructor */
	public Contact() {
	}

	/** full constructor */
	public Contact(String engConx, String chiConx) {
		this.engConx = engConx;
		this.chiConx = chiConx;
	}

	// Property accessors

	public Integer getContactId() {
		return this.contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public String getEngConx() {
		return this.engConx;
	}

	public void setEngConx(String engConx) {
		this.engConx = engConx;
	}

	public String getChiConx() {
		return this.chiConx;
	}

	public void setChiConx(String chiConx) {
		this.chiConx = chiConx;
	}

}