package com.sis.entity;

/**
 * About entity. @author MyEclipse Persistence Tools
 */

public class About implements java.io.Serializable {

	// Fields

	private Integer aboutId;
	private String engConx;
	private String chiConx;

	// Constructors

	/** default constructor */
	public About() {
	}

	/** full constructor */
	public About(String engConx, String chiConx) {
		this.engConx = engConx;
		this.chiConx = chiConx;
	}

	// Property accessors

	public Integer getAboutId() {
		return this.aboutId;
	}

	public void setAboutId(Integer aboutId) {
		this.aboutId = aboutId;
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