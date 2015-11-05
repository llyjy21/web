package com.sis.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sis.Service.FileService;
import com.sis.Service.FolderService;
import com.sis.entity.FileInfo;
import com.sis.entity.Folder;
public class FileAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private FileService fileService;
	private FolderService folderService;	
	private List<FileInfo> fileInfoList;
	private Integer folderId;
	private File file;//上传的文件     	
	private String fileFileName;//文件的名称    
	private String engRemark;
	private String engFoldername;
	private String underFolder;
	private Folder folder;
	private String filePath;
	private String name;
	private String delitems;
	private FileInfo fileinfo;
	private String uploadFilePath;
	private String uploadfid;
	private String Strefname;
	private String chiRemark;
	private String chiFoldername;
	public String getChiRemark() {
		return chiRemark;
	}
	public void setChiRemark(String chiRemark) {
		this.chiRemark = chiRemark;
	}
	public String getChiFoldername() {
		return chiFoldername;
	}
	public void setChiFoldername(String chiFoldername) {
		this.chiFoldername = chiFoldername;
	}
	public String getStrefname() {
		return Strefname;
	}
	public void setStrefname(String strefname) {
		Strefname = strefname;
	}
	public String getUploadfid() {
		return uploadfid;
	}
	public void setUploadfid(String uploadfid) {
		this.uploadfid = uploadfid;
	}
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	public FileInfo getFileinfo() {
		return fileinfo;
	}
	public void setFileinfo(FileInfo fileinfo) {
		this.fileinfo = fileinfo;
	}
	public String getDelitems() {
		return delitems;
	}
	public void setDelitems(String delitems) {
		this.delitems = delitems;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getEngRemark() {
		return engRemark;
	}
	public void setEngRemark(String engRemark) {
		this.engRemark = engRemark;
	}
	public String getEngFoldername() {
		return engFoldername;
	}
	public void setEngFoldername(String engFoldername) {
		this.engFoldername = engFoldername;
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
	public List<FileInfo> getFileInfoList() {
		return fileInfoList;
	}
	public void setFileInfoList(List<FileInfo> fileInfoList) {
		this.fileInfoList = fileInfoList;
	}
	
	public FileService getFileService() {
		return fileService;
	}
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	//图片文件处理
	public String doCreatefile() throws Exception{
		
		//获取需要上传文件的文件路径  
		String savePath = ServletActionContext.getServletContext().getRealPath("/uploads/img"); 
		savePath += URLDecoder.decode(this.getUploadFilePath(),"UTF-8");
        String efnameStr = URLDecoder.decode(this.getStrefname(),"UTF-8");  
        int index = fileFileName.lastIndexOf(".");
        String lastName = fileFileName.substring(index, fileFileName.length());//文件后缀
        Date now = new Date(); 
        long longtime = now.getTime(); 
        String newname = longtime + lastName;//新文件名 
        //声明文件输入流，为输入流指定文件路径  
        FileInputStream input=new FileInputStream(file);  
        //获取输出流，获取文件的文件地址及名称  
        FileOutputStream out=new FileOutputStream(savePath +"\\" +efnameStr+"\\" +newname);  
        try{  
            byte[] b=new byte[1024];//每次写入的大小  
            int i=0;  
            while((i=input.read(b))>0){  
                out.write(b,0,i);  
            }
            Folder folder = (Folder)this.folderService.findFolderById(Integer.parseInt(java.net.URLDecoder.decode(this.getUploadfid())));
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFolder(folder);
            fileInfo.setFilePath("img"+URLDecoder.decode(this.getUploadFilePath(),"UTF-8"));
            fileInfo.setFileName(newname);
            this.fileService.doCreateFile(fileInfo); 
            
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            input.close();  
            out.close();  
        }  
        return null;  
	 } 
	//查询图片文件
	 public String findAllfiles() {	
		 this.fileInfoList = this.fileService.findAllFiles(this.folderId); 
		 String filePath = "/"+underFolder;
		 ActionContext.getContext().getSession().put("efname",engFoldername);
		 ActionContext.getContext().getSession().put("path",filePath);
		 ActionContext.getContext().getSession().put("fid",this.folderId);
		 ActionContext.getContext().getSession().put("remark",this.getEngRemark());
		 if (fileInfoList!=null && !fileInfoList.isEmpty()){
			 return SUCCESS;	
		 }else if(fileInfoList==null)
		{
			this.fileInfoList = null;
			return SUCCESS;
		}else{
			return SUCCESS;
		}
	} 
	 public String userfindAllfiles() {	
		 this.fileInfoList = this.fileService.findAllFiles(this.folderId);
		 //获取需要上传文件的文件路径  
		 String filePath = "/"+underFolder;
		 ActionContext.getContext().getSession().put("efname",engFoldername);
		 ActionContext.getContext().getSession().put("path",filePath);
		 ActionContext.getContext().getSession().put("fid",this.folderId);
		 ActionContext.getContext().getSession().put("remark",this.getEngRemark());
		 if (fileInfoList!=null && !fileInfoList.isEmpty()){
			 return "userSuc";	
		 }else if(fileInfoList==null)
		{
			this.fileInfoList = null;
			return "userSuc";
		}else{
			return "userSuc";
		}
	} 
	 public String userChifindAllfiles() {	
		 this.fileInfoList = this.fileService.findAllFiles(this.folderId);
		 //获取需要上传文件的文件路径  
		 String filePath = "/"+underFolder;
		 ActionContext.getContext().getSession().put("efname",engFoldername);
		 ActionContext.getContext().getSession().put("path",filePath);
		 ActionContext.getContext().getSession().put("fid",this.folderId);
		 ActionContext.getContext().getSession().put("remark",this.getEngRemark());
		 if (fileInfoList!=null && !fileInfoList.isEmpty()){
			 return "userChiSuc";	
		 }else if(fileInfoList==null)
		{
			this.fileInfoList = null;
			return "userChiSuc";
		}else{
			return "userChiSuc";
		}
	}
	 //删除文件
	 public String removefile() {
		
		String deletePath = ServletActionContext.getServletContext().getRealPath("/uploads");
		boolean flag;
		FileInfo fileinfo = null;
		String[] item = this.getDelitems().split(",");		
		try{
			for (int i = 0; i < item.length; i++) {
				fileinfo = this.fileService.findFileById(Integer.parseInt(item[i]));
				File dfile = new File(deletePath+"\\"+fileinfo.getFilePath()+"\\"+ActionContext.getContext().getSession().get("efname").toString()+"\\"+fileinfo.getFileName());
	            flag = dfile.delete();
	            if (!flag) break;
			}
			for (int i = 0; i < item.length; i++) {
				this.fileService.removeFile(Integer.parseInt(item[i]));
			}
			return SUCCESS;
		} catch (Exception e) {
			return SUCCESS;
		}
	}  
	
	 
	 
	 //视频文件处理
	 public String doCreatevideo() throws Exception{
			
		//获取需要上传文件的文件路径  
		String savePath = ServletActionContext.getServletContext().getRealPath("/uploads/Video");
        String efnameStr = URLDecoder.decode(this.getStrefname(),"UTF-8");  
        int index = fileFileName.lastIndexOf(".");
        String lastName = fileFileName.substring(index, fileFileName.length());//文件后缀
        Date now = new Date(); 
        long longtime = now.getTime(); 
        String newname = longtime + lastName;//新文件名 
        //声明文件输入流，为输入流指定文件路径  
        FileInputStream input=new FileInputStream(file);  
        //获取输出流，获取文件的文件地址及名称  
        FileOutputStream out=new FileOutputStream(savePath + "/" + efnameStr + "/" + newname);  
        try{  
            byte[] b=new byte[1024];//每次写入的大小  
            int i=0;  
            while((i=input.read(b))>0){  
                out.write(b,0,i);  
            }
            Folder folder = (Folder)this.folderService.findFolderById(Integer.parseInt(java.net.URLDecoder.decode(this.getUploadfid())));
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFolder(folder);
            fileInfo.setFilePath("Video");
            fileInfo.setFileName(newname);
            this.fileService.doCreateFile(fileInfo); 
            
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            input.close();  
            out.close();  
        }  
        return null;  
	 } 
	 
	 public String findAllvideo() {	
		 this.fileInfoList = this.fileService.findAllFiles(this.folderId);
		 ActionContext.getContext().getSession().put("efname",engFoldername);
		 ActionContext.getContext().getSession().put("fid",this.folderId);
		 ActionContext.getContext().getSession().put("remark",this.getEngRemark());
		 if (fileInfoList!=null && !fileInfoList.isEmpty()){
			 return "sucVideo";	
		 }else if(fileInfoList==null)
		{
			this.fileInfoList = null;
			return "sucVideo";
		}else{
			return "sucVideo";
		}
	} 
	 public String userFindAllvideo(){
		this.fileInfoList = this.fileService.findAllFiles(this.folderId);
		 ActionContext.getContext().getSession().put("efname",engFoldername);
		 ActionContext.getContext().getSession().put("fid",this.folderId);
		 ActionContext.getContext().getSession().put("remark",this.getEngRemark());
		 if (fileInfoList!=null && !fileInfoList.isEmpty()){
			 return "userSucVideo";	
		 }else if(fileInfoList==null)
		{
			this.fileInfoList = null;
			return "userSucVideo";
		}else{
			return "userSucVideo";
		}
	 }
	//中文
	//查询图片文件
	 public String chiFindAllfiles() {	
		 this.fileInfoList = this.fileService.findAllFiles(this.folderId);
		 //获取需要上传文件的文件路径  
		 String filePath = "/"+underFolder;
		 try {
			ActionContext.getContext().getSession().put("cfname",URLDecoder.decode(chiFoldername, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 ActionContext.getContext().getSession().put("path",filePath);
		 ActionContext.getContext().getSession().put("fid",this.folderId);
		 ActionContext.getContext().getSession().put("efname",engFoldername);
		 try {
			ActionContext.getContext().getSession().put("cremark",URLDecoder.decode(this.getChiRemark(), "UTF-8"));
			
		 } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if (fileInfoList!=null && !fileInfoList.isEmpty()){
			 return "chisuccess";	
		 }else if(fileInfoList==null)
		{
			this.fileInfoList = null;
			return "chisuccess";	
		}else{
			return "chisuccess";	
		}
	} 
	 //中文视频查询
	 public String chifindAllvideo() {	
		 this.fileInfoList = this.fileService.findAllFiles(this.folderId);
		 ActionContext.getContext().getSession().put("efname",engFoldername);
		 ActionContext.getContext().getSession().put("fid",this.folderId);
		 try {
			ActionContext.getContext().getSession().put("cremark",URLDecoder.decode(this.getChiRemark(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if (fileInfoList!=null && !fileInfoList.isEmpty()){
			 return "chisucVideo";	
		 }else if(fileInfoList==null)
		{
			this.fileInfoList = null;
			return "chisucVideo";
		}else{
			return "chisucVideo";
		}
	} 
	//用户中文视频查询
		 public String userChifindAllvideo() {	
			 this.fileInfoList = this.fileService.findAllFiles(this.folderId);
			 ActionContext.getContext().getSession().put("efname",engFoldername);
			 ActionContext.getContext().getSession().put("fid",this.folderId);
			 try {
				ActionContext.getContext().getSession().put("cremark",URLDecoder.decode(this.getChiRemark(),"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if (fileInfoList!=null && !fileInfoList.isEmpty()){
				 return "userChisucVideo";	
			 }else if(fileInfoList==null)
			{
				this.fileInfoList = null;
				return "userChisucVideo";
			}else{
				return "userChisucVideo";
			}
		} 
}
