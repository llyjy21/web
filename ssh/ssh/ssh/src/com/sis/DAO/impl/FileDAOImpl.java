package com.sis.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.sis.DAO.FileDAO;
import com.sis.entity.FileInfo;

public class FileDAOImpl extends HibernateDaoSupport implements FileDAO {


	public void doCreateFile(FileInfo fileInfo) {
		// TODO Auto-generated method stub		
		this.getHibernateTemplate().save(fileInfo);
	}

	public List<FileInfo> findAllFiles(Integer folderId) {
		// TODO Auto-generated method stub
		String HQL = "from FileInfo e where e.folder.folderId="+folderId+" order by e.folder.folderId asc" ;
		List<FileInfo> list = this.getHibernateTemplate().find(HQL);
		if (list!=null && !list.isEmpty()){
			return list;
		}else{
			return null;
		}
	}

	public void removeFile(int fileId) {
		// TODO Auto-generated method stub
		FileInfo fileInfo=this.findFileById(fileId);
		this.getHibernateTemplate().delete(fileInfo);
	}
	public FileInfo findFileById(int fileId) {
		// TODO Auto-generated method stub
		 FileInfo fileInfo = null;  
		 fileInfo = (FileInfo) this.getHibernateTemplate().get(FileInfo.class, fileId);  
	     return fileInfo;  
	}

}
