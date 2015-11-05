package com.sis.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Folder entity. @author MyEclipse Persistence Tools
 */

public class Folder implements java.io.Serializable {

	// Fields

	private Integer folderId;
	private Integer parentFolderId;
	private String engFolderName;
	private String engRemark;
	private String chiFolderName;
	private String chiRemark;
	private Set fileInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Folder() {
	}

	/** minimal constructor */
	public Folder(Integer parentFolderId, String engFolderName,
			String chiFolderName) {
		this.parentFolderId = parentFolderId;
		this.engFolderName = engFolderName;
		this.chiFolderName = chiFolderName;
	}

	/** full constructor */
	public Folder(Integer parentFolderId, String engFolderName,
			String engRemark, String chiFolderName, String chiRemark,
			Set fileInfos) {
		this.parentFolderId = parentFolderId;
		this.engFolderName = engFolderName;
		this.engRemark = engRemark;
		this.chiFolderName = chiFolderName;
		this.chiRemark = chiRemark;
		this.fileInfos = fileInfos;
	}

	// Property accessors

	public Integer getFolderId() {
		return this.folderId;
	}

	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}

	public Integer getParentFolderId() {
		return this.parentFolderId;
	}

	public void setParentFolderId(Integer parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public String getEngFolderName() {
		return this.engFolderName;
	}

	public void setEngFolderName(String engFolderName) {
		this.engFolderName = engFolderName;
	}

	public String getEngRemark() {
		return this.engRemark;
	}

	public void setEngRemark(String engRemark) {
		this.engRemark = engRemark;
	}

	public String getChiFolderName() {
		return this.chiFolderName;
	}

	public void setChiFolderName(String chiFolderName) {
		this.chiFolderName = chiFolderName;
	}

	public String getChiRemark() {
		return this.chiRemark;
	}

	public void setChiRemark(String chiRemark) {
		this.chiRemark = chiRemark;
	}

	public Set getFileInfos() {
		return this.fileInfos;
	}

	public void setFileInfos(Set fileInfos) {
		this.fileInfos = fileInfos;
	}

}