package com.sis.Service.impl;

import java.util.List;

import com.sis.DAO.FolderDAO;
import com.sis.Service.FolderService;
import com.sis.entity.Folder;

public class FolderServiceImpl implements FolderService {

	private FolderDAO folderdao;
	private Folder folder;
	
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	public FolderDAO getFolderdao() {
		return folderdao;
	}

	public void setFolderdao(FolderDAO folderdao) {
		this.folderdao = folderdao;
	}
	

	public List<Folder> findAllFolders(int parentFolderId) {
		
		return this.folderdao.findAllFolders(parentFolderId);
	}

	public void removeFolder(int folderId) {
		// TODO Auto-generated method stub
		 this.folderdao.removeFolder(folderId);
	}

	public void updateFolder(Folder folder) {
		// TODO Auto-generated method stub
		this.folderdao.updateFolder(folder);
		
	}
	public void doCreateFolder(Folder folder) {
		// TODO Auto-generated method stub
		this.folderdao.doCreateFolder(folder);
	}
	public Folder findFolderById(int folderId) {
		// TODO Auto-generated method stub
		return this.folderdao.findFolderById(folderId);
	}

	

}
