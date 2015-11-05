package com.sis.entity;

/**
 * FileInfo entity. @author MyEclipse Persistence Tools
 */

public class FileInfo implements java.io.Serializable {

	// Fields

	private Integer fileId;
	private Folder folder;
	private String fileName;
	private String filePath;

	// Constructors

	/** default constructor */
	public FileInfo() {
	}

	/** full constructor */
	public FileInfo(Folder folder, String fileName, String filePath) {
		this.folder = folder;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	// Property accessors

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Folder getFolder() {
		return this.folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}