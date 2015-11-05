package com.sis.DAO;

import java.util.List;

import com.sis.entity.FileInfo;
import com.sis.entity.News;

public interface NewsDAO {
	
	 public String doCreateNews(News news);  
	 public List<News> findAllNews();  
	 public void updateNews(News news);  
	 public News findNewsById(int newsId);
	 public void removeNews(int newsId);  
}
