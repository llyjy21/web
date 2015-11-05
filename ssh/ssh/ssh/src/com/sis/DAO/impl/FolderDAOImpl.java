package com.sis.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.sis.DAO.FolderDAO;
import com.sis.entity.Folder;

public class FolderDAOImpl extends HibernateDaoSupport implements FolderDAO {

	private FolderDAO folderDAO;

	public FolderDAO getFolderDAO() {
		return folderDAO;
	}

	public void setFolderDAO(FolderDAO folderDAO) {
		this.folderDAO = folderDAO;
	}

	public List<Folder> findAllFolders(int parentFolderId) {
		
		String HQL = "from Folder e where e.parentFolderId ="+parentFolderId+" order by e.folderId desc" ;
		List<Folder> list = this.getHibernateTemplate().find(HQL);
		if (list!=null && !list.isEmpty()){
			return list;
		}else{
			return null;
			
		}
	}
	//新建文件夹
	public void doCreateFolder(Folder folder) {
		this.getHibernateTemplate().save(folder);
	}
	//删除文件夹
	public void removeFolder(int folderId) {
		
		Folder folder=this.findFolderById(folderId);
		this.getHibernateTemplate().delete(folder);
	
	}
	//更新文件夹名
	public void updateFolder(Folder folder) {
		this.getHibernateTemplate().update(folder);
	}
	
	public Folder findFolderById(int folderId) {  
	 	
        Folder folder = null;  
        folder = (Folder) this.getHibernateTemplate().get(Folder.class, folderId);  
        return folder;  
    }
	
}
