package com.sis.Service;

import java.util.List;

import com.sis.entity.Folder;

public interface FolderService {
	public void doCreateFolder(Folder folder);  
	 public List<Folder> findAllFolders(int parentFolderId);  
	 public void removeFolder(int folderId);  
	 public void updateFolder(Folder folder);  
	 public Folder findFolderById(int folderId);
}
