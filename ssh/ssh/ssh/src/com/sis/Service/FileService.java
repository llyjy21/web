package com.sis.Service;

import java.util.List;
import com.sis.entity.FileInfo;

public interface FileService {
	public void doCreateFile(FileInfo fileInfo);  
	 public List<FileInfo> findAllFiles(Integer folderId);  
	 public void removeFile(int fileId);  
	 public FileInfo findFileById(int fileId);
}
