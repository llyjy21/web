package com.sis.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.sis.DAO.NewsDAO;
import com.sis.entity.FileInfo;
import com.sis.entity.Folder;
import com.sis.entity.News;

public class NewsDAOImpl extends HibernateDaoSupport implements NewsDAO {

	public String doCreateNews(News news) {
		// TODO Auto-generated method stub		
		this.getHibernateTemplate().save(news);
		return null;
	}
	
	public List<News> findAllNews() {
		// TODO Auto-generated method stub
		String HQL = "from News e order by e.newsId desc" ;
		List<News> list = this.getHibernateTemplate().find(HQL);
		
		if (list!=null && !list.isEmpty()){
			return list;
		}else{
			return null;
		}
	}

	public void updateNews(News news) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(news);
	}

	public News findNewsById(int newsId) {
		// TODO Auto-generated method stub
		News news = null;  
        news = (News) this.getHibernateTemplate().get(News.class, newsId);  
        return news;  
	}
	
	public void removeNews(int newsId) {
		// TODO Auto-generated method stub
		News news=this.findNewsById(newsId);
		this.getHibernateTemplate().delete(news);
	}
	
}
