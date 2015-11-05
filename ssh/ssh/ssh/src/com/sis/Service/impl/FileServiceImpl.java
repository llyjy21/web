package com.sis.Service.impl;

import java.util.List;
import com.sis.DAO.FileDAO;
import com.sis.Service.FileService;
import com.sis.entity.FileInfo;

public class FileServiceImpl implements FileService {
	private FileDAO filedao;
	public FileDAO getFiledao() {
		return filedao;
	}

	public void setFiledao(FileDAO filedao) {
		this.filedao = filedao;
	}

	public void doCreateFile(FileInfo fileInfo) {
		// TODO Auto-generated method stub
		filedao.doCreateFile(fileInfo);
	}

	public List<FileInfo> findAllFiles(Integer folderId) {
		// TODO Auto-generated method stub
		return filedao.findAllFiles(folderId);
	}

	public void removeFile(int fileId) {
		// TODO Auto-generated method stub
		filedao.removeFile(fileId);
	}
	public FileInfo findFileById(int fileId) {
		// TODO Auto-generated method stub
		return filedao.findFileById(fileId);
	}

}
