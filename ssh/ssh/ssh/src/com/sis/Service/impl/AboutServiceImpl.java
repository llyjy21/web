package com.sis.Service.impl;

import java.util.List;

import com.sis.DAO.AboutDAO;
import com.sis.Service.AboutService;
import com.sis.entity.About;

public class AboutServiceImpl implements AboutService {
	private AboutDAO aboutdao;
	private About about;
	public AboutDAO getAboutdao() {
		return aboutdao;
	}

	public void setAboutdao(AboutDAO aboutdao) {
		this.aboutdao = aboutdao;
	}

	public About getAbout() {
		return about;
	}

	public void setAbout(About about) {
		this.about = about;
	}

	public List<About> findAllAbouts() {
		// TODO Auto-generated method stub
		return this.aboutdao.findAllAbouts();
	}

	public void updateAbout(About about) {
		// TODO Auto-generated method stub
		this.aboutdao.updateAbout(about);
	}

	public About findAboutById(int aboutId) {
		// TODO Auto-generated method stub
		return this.aboutdao.findAboutById(aboutId);
	}

}
