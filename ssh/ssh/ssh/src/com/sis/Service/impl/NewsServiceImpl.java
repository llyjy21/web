package com.sis.Service.impl;

import java.util.List;
import com.sis.DAO.NewsDAO;
import com.sis.Service.NewsService;

import com.sis.entity.Folder;
import com.sis.entity.News;

public class NewsServiceImpl implements NewsService {
	private NewsDAO newsdao;
	private News news;
	public NewsDAO getNewsdao() {
		return newsdao;
	}

	public void setNewsdao(NewsDAO newsdao) {
		this.newsdao = newsdao;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
	public List<News> findAllNews() {
		// TODO Auto-generated method stub
		return this.newsdao.findAllNews();
	}

	public void updateNews(News news) {
		// TODO Auto-generated method stub
		this.newsdao.updateNews(news);
	}

	public News findNewsById(int newsId) {
		// TODO Auto-generated method stub
		return this.newsdao.findNewsById(newsId);
	}

	public String doCreateNews(News news) {
		// TODO Auto-generated method stub
		this.newsdao.doCreateNews(news);
		return null;
	}

	public void removeNews(int newsId) {
		// TODO Auto-generated method stub
		newsdao.removeNews(newsId);
	}

}
