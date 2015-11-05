package com.sis.Action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sis.Service.FolderService;
import com.sis.entity.Folder;

public class FolderAction extends ActionSupport{
	private Map<String,Object> dataMap;
	private static final long serialVersionUID = 1L;
	private FolderService folderService;
	private Folder folder;
	private List<Folder> folderList;
	private Integer folderId;
	private String engFolderName;
	private String chiFolderName;
	private String underFolder;
	private String topFolder;
	private String newEngFolderName;
	private int parentFolderId;
	private String engRemark;
	private String newEngRemark;
	private String chiRemark;
	private String newChiFolderName;
	private String newChiRemark;
	public String getNewChiFolderName() {
		return newChiFolderName;
	}
	public void setNewChiFolderName(String newChiFolderName) {
		this.newChiFolderName = newChiFolderName;
	}
	public String getNewChiRemark() {
		return newChiRemark;
	}
	public void setNewChiRemark(String newChiRemark) {
		this.newChiRemark = newChiRemark;
	}
	public String getNewEngRemark() {
		return newEngRemark;
	}
	public void setNewEngRemark(String newEngRemark) {
		this.newEngRemark = newEngRemark;
	}
	public String getNewEngFolderName() {
		return newEngFolderName;
	}
	public void setNewEngFolderName(String newEngFolderName) {
		this.newEngFolderName = newEngFolderName;
	}
	public Map<String, Object> getDataMap() {
		 return dataMap;
    }
	public String getEngRemark() {
		return engRemark;
	}
	public void setEngRemark(String engRemark) {
		this.engRemark = engRemark;
	}
	public String getChiRemark() {
		return chiRemark;
	}
	public void setChiRemark(String chiRemark) {
		this.chiRemark = chiRemark;
	}
	public String getChiFolderName() {
		return chiFolderName;
	}
	public void setChiFolderName(String chiFolderName) {
		this.chiFolderName = chiFolderName;
	}
	public String getEngFolderName() {
		return engFolderName;
	}
	public void setEngFolderName(String engFolderName) {
		this.engFolderName = engFolderName;
	}
	public int getParentFolderId() {
		return parentFolderId;
	}
	public void setParentFolderId(int parentFolderId) {
		this.parentFolderId = parentFolderId;
	}
	public String getTopFolder() {
		return topFolder;
	}
	public void setTopFolder(String topFolder) {
		this.topFolder = topFolder;
	}
	public String getUnderFolder() {
		return underFolder;
	}
	public void setUnderFolder(String underFolder) {
		this.underFolder = underFolder;
	}
	public Integer getFolderId() {
		return folderId;
	}
	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
	public List<Folder> getFolderList() {
		return folderList;
	}
	public void setFolderList(List<Folder> folderList) {
		this.folderList = folderList;
	}
	public FolderService getFolderService() {
		return folderService;
	}
	public void setFolderService(FolderService folderService) {
		this.folderService = folderService;
	}
	public Folder getFolder() {
		return folder;
	}
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	public String doCreateFolder(){

			String engFoldername = this.getEngFolderName();
			String topFolder = this.getTopFolder();
			String underFolder = this.getUnderFolder();
			//获取需要上传文件的文件路径  
			String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads"); 
			String filePath = uploadFile+"\\"+topFolder+"\\"+underFolder+"\\"+engFoldername;
			Folder folder = new Folder();
			folder.setParentFolderId(this.getParentFolderId());
			folder.setEngFolderName(engFolderName);
			folder.setChiFolderName(this.getChiFolderName());
			folder.setChiRemark(this.getChiRemark());
			folder.setEngRemark(this.getEngRemark());
			
			//创建文件夹
			File dir = new File(filePath);
			if(dir.exists()) {
				//request.setAttribute("errors","创建目录" + engFilename + "失败，目标目录已存在！");
				return "createSuc";
			}
			if(!filePath.endsWith(File.separator))
				filePath = filePath + File.separator;
		    // 创建单个目录
		    if(dir.mkdirs()) {
			  
			     //request.setAttribute("errors","创建目录" + engFilename + "成功！1");
			     this.folderService.doCreateFolder(folder); 
			     return "createSuc";			  	
		    } else {
			    
			     //request.setAttribute("errors","创建目录" + engFilename + "成功！2");
			     this.folderService.doCreateFolder(folder); 
			     return "createSuc";
		    }
	}  
	 public String findAllFolders() {
		dataMap = new HashMap<String, Object>();
		List<Folder> list = this.folderService.findAllFolders(this.parentFolderId);		
		if (list!=null && !list.isEmpty()){
			dataMap.put("listTest", list);
	        // 返回结果  
	        return SUCCESS;  
		}else if(list==null)
		{
			this.folderList = list;
			return SUCCESS;
		}else{
			return ERROR;
		}
	}  
	
	public String removeFolder() {
		 
		boolean flag;
		HttpServletRequest request = ServletActionContext.getRequest();
		String topFolder = this.getTopFolder();
		String underFolder = this.getUnderFolder();
		String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads"); 
		String filePath = uploadFile+"\\"+topFolder+"\\"+underFolder+"\\"+engFolderName;
		File dir = new File(filePath);
		
		try {
		    
	        //删除文件夹下的所有文件(包括子目录)
	        File[] files = dir.listFiles();
	        //删除子文件
	        if(files!=null){
	        	for (int i = 0; i < files.length; i++) {
	        		
		            if (files[i].isFile()) {
		            	File dfile = new File(files[i].getAbsolutePath());
		                flag = dfile.delete();
		                if (!flag) break;
		            }
	        	}
	        	dir.delete();//删除当前目录
	        }
	        else{
	        	dir.delete();
	        }	  

	        folderService.removeFolder(this.getFolderId());
			request.setAttribute("errors","删除目录" +engFolderName + "成功！");
			return "createSuc";
		} catch (Exception e) {
			request.setAttribute("errors","删除目录" + engFolderName + "失败！");
			return "error";
		}
	}  
	
	 public String updateFolder() {
		
		Folder folder1 = (Folder)this.folderService.findFolderById(folderId);		
		folder1.setEngFolderName(newEngFolderName);
		folder1.setEngRemark(newEngRemark);
		String topFolder = this.getTopFolder();
		String underFolder = this.getUnderFolder();
		//更新路径
		String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
		String filePath = uploadFile+"\\"+topFolder+"\\"+underFolder+"\\"+engFolderName;
		String newfilePath = uploadFile+"\\"+topFolder+"\\"+underFolder+"\\"+newEngFolderName;
		File dir = new File(filePath);
		File newdir = new File(newfilePath);
		if(dir.renameTo(newdir)){			
			this.folderService.updateFolder(folder1);
			return "createSuc";
		}
		return ERROR;
	}
	 //处理视频
	 public String videoupdateFolder() {
			
			Folder folder1 = (Folder)this.folderService.findFolderById(folderId);		
			folder1.setEngFolderName(newEngFolderName);
			folder1.setEngRemark(newEngRemark);
			String topFolder = this.getTopFolder();
			//更新路径
			String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads"); 
			String filePath = uploadFile+"\\"+topFolder+"\\"+engFolderName;
			String newfilePath = uploadFile+"\\"+topFolder+"\\"+newEngFolderName;
			File dir = new File(filePath);
			File newdir = new File(newfilePath);
			if(dir.renameTo(newdir)){			
				this.folderService.updateFolder(folder1);
				return "createSuc";
			}
			return ERROR;
		}
	 public String videoremoveFolder() {
		 
			boolean flag;
			String topFolder = this.getTopFolder();
			String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
			String filePath = uploadFile+"\\"+topFolder+"\\"+engFolderName;
			File dir = new File(filePath);
			try {
			    
		        //删除文件夹下的所有文件(包括子目录)
		        File[] files = dir.listFiles();
		        //删除子文件
		        if(files!=null){
		        	for (int i = 0; i < files.length; i++) {
		        		
			            if (files[i].isFile()) {
			            	File dfile = new File(files[i].getAbsolutePath());
			                flag = dfile.delete();
			                if (!flag) break;
			            }
		        	}
		        	dir.delete();//删除当前目录
		        }
		        else{
		        	dir.delete();
		        }	  
		        folderService.removeFolder(this.getFolderId());
				return "createSuc";
			} catch (Exception e) {
				return "error";
			}
		}  
	 public String videodoCreateFolder(){

			String engFoldername = this.getEngFolderName();
			String topFolder = this.getTopFolder();
			//获取需要上传文件的文件路径  
			String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
			String filePath = uploadFile+"\\"+topFolder+"\\"+engFoldername;
			Folder folder = new Folder();
			folder.setParentFolderId(this.getParentFolderId());
			folder.setEngFolderName(engFolderName);
			folder.setChiFolderName(this.getChiFolderName());
			folder.setChiRemark(this.getChiRemark());
			folder.setEngRemark(this.getEngRemark());
			
			//创建文件夹
			File dir = new File(filePath);
			if(dir.exists()) {
				//request.setAttribute("errors","创建目录" + engFilename + "失败，目标目录已存在！");
				return "error";
			}
			if(!filePath.endsWith(File.separator))
				filePath = filePath + File.separator;
		    // 创建单个目录
		    if(dir.mkdirs()) {
			  
			     //request.setAttribute("errors","创建目录" + engFilename + "成功！1");
			     this.folderService.doCreateFolder(folder); 
			     return "createSuc";			  	
		    } else {
			    
			     //request.setAttribute("errors","创建目录" + engFilename + "成功！2");
			     this.folderService.doCreateFolder(folder); 
			     return "createSuc";
		    }
	} 
	 
	 
	//中文功能
	 public String chidoCreateFolder(){

			String engFoldername = this.getEngFolderName();
			String topFolder = this.getTopFolder();
			String underFolder = this.getUnderFolder();
			//获取需要上传文件的文件路径  
			String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
			String filePath = uploadFile+"\\"+topFolder+"\\"+underFolder+"\\"+engFoldername;
			Folder folder = new Folder();
			folder.setParentFolderId(this.getParentFolderId());
			folder.setEngFolderName(engFolderName);
			folder.setChiFolderName(this.getChiFolderName());
			folder.setChiRemark(this.getChiRemark());
			folder.setEngRemark(this.getEngRemark());
			
			//创建文件夹
			File dir = new File(filePath);
			if(dir.exists()) {
				//request.setAttribute("errors","创建目录" + engFilename + "失败，目标目录已存在！");
				return "error";
			}
			if(!filePath.endsWith(File.separator))
				filePath = filePath + File.separator;
		    // 创建单个目录
		    if(dir.mkdirs()) {
			  
			     //request.setAttribute("errors","创建目录" + engFilename + "成功！1");
			     this.folderService.doCreateFolder(folder); 
			     return "chicreateSuc";			  	
		    } else {
			    
			     //request.setAttribute("errors","创建目录" + engFilename + "成功！2");
			     this.folderService.doCreateFolder(folder); 
			     return "chicreateSuc";
		    }
	}
	 public String  chiremoveFolder() {
		 
			boolean flag;
			HttpServletRequest request = ServletActionContext.getRequest();
			String topFolder = this.getTopFolder();
			String underFolder = this.getUnderFolder();
			String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
			String filePath = uploadFile+"\\"+topFolder+"\\"+underFolder+"\\"+engFolderName;
			File dir = new File(filePath);
			
			try {
			    
		        //删除文件夹下的所有文件(包括子目录)
		        File[] files = dir.listFiles();
		        //删除子文件
		        if(files!=null){
		        	for (int i = 0; i < files.length; i++) {
		        		
			            if (files[i].isFile()) {
			            	File dfile = new File(files[i].getAbsolutePath());
			                flag = dfile.delete();
			                if (!flag) break;
			            }
		        	}
		        	dir.delete();//删除当前目录
		        }
		        else{
		        	dir.delete();
		        }	  

		        folderService.removeFolder(this.getFolderId());
				request.setAttribute("errors","删除目录" +engFolderName + "成功！");
				return "chicreateSuc";
			} catch (Exception e) {
				request.setAttribute("errors","删除目录" + engFolderName + "失败！");
				return "error";
			}
		}  
	 public String chiupdateFolder() {
			
			Folder folder1 = (Folder)this.folderService.findFolderById(folderId);			
			folder1.setChiFolderName(newChiFolderName);
			folder1.setChiRemark(newChiRemark);
			this.folderService.updateFolder(folder1);
			return "chicreateSuc";
		}
	 //中文视频
	 public String chivideoupdateFolder() {
			
			Folder folder1 = (Folder)this.folderService.findFolderById(folderId);		
			folder1.setChiFolderName(newChiFolderName);
			folder1.setChiRemark(newChiRemark);
			this.folderService.updateFolder(folder1);
			return "chicreateSuc";
			
		}
	 public String chivideoremoveFolder() {
		 
			boolean flag;
			String topFolder = this.getTopFolder();
			String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
			String filePath = uploadFile+"\\"+topFolder+"\\"+engFolderName;
			File dir = new File(filePath);
			try {
			    
		        //删除文件夹下的所有文件(包括子目录)
		        File[] files = dir.listFiles();
		        //删除子文件
		        if(files!=null){
		        	for (int i = 0; i < files.length; i++) {
		        		
			            if (files[i].isFile()) {
			            	File dfile = new File(files[i].getAbsolutePath());
			                flag = dfile.delete();
			                if (!flag) break;
			            }
		        	}
		        	dir.delete();//删除当前目录
		        }
		        else{
		        	dir.delete();
		        }	  
		        folderService.removeFolder(this.getFolderId());
				return "chicreateSuc";
			} catch (Exception e) {
				return "error";
			}
		}  
	 public String chivideodoCreateFolder(){

			String engFoldername = this.getEngFolderName();
			String topFolder = this.getTopFolder();
			//获取需要上传文件的文件路径  
			String uploadFile= ServletActionContext.getServletContext().getRealPath("/uploads");
			String filePath = uploadFile+"\\"+topFolder+"\\"+engFoldername;
			Folder folder = new Folder();
			folder.setParentFolderId(this.getParentFolderId());
			folder.setEngFolderName(engFolderName);
			folder.setChiFolderName(this.getChiFolderName());
			folder.setChiRemark(this.getChiRemark());
			folder.setEngRemark(this.getEngRemark());
			
			//创建文件夹
			File dir = new File(filePath);
			if(dir.exists()) {
				//request.setAttribute("errors","创建目录" + engFilename + "失败，目标目录已存在！");
				return "error";
			}
			if(!filePath.endsWith(File.separator))
				filePath = filePath + File.separator;
		    // 创建单个目录
		    if(dir.mkdirs()) {
			  
			     //request.setAttribute("errors","创建目录" + engFilename + "成功！1");
			     this.folderService.doCreateFolder(folder); 
			     return "chicreateSuc";			  	
		    } else {
			    
			     //request.setAttribute("errors","创建目录" + engFilename + "成功！2");
			     this.folderService.doCreateFolder(folder); 
			     return "chicreateSuc";
		    }
	} 
}
