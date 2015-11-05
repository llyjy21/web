package com.sis.DAO;

import java.util.List;
import com.sis.entity.FileInfo;

public interface FileDAO {

	 public void doCreateFile(FileInfo fileInfo);  
	 public List<FileInfo> findAllFiles(Integer folderId);  
	 public void removeFile(int fileId);  
	 public FileInfo findFileById(int fileId);
}
