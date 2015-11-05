package com.sis.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.sis.DAO.AboutDAO;
import com.sis.entity.About;

public class AboutDAOImpl extends HibernateDaoSupport implements AboutDAO {

	public List<About> findAllAbouts() {
		// TODO Auto-generated method stub
		String HQL = "from About e" ;
		List<About> list = this.getHibernateTemplate().find(HQL);
		if (list!=null && !list.isEmpty()){
			return list;
		}else{
			return null;
		}
	}

	public void updateAbout(About about) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(about);
	}

	public About findAboutById(int aboutId) {
		// TODO Auto-generated method stub
		About about = null;
		about = this.getHibernateTemplate().get(About.class, aboutId);
		return about;
	}

}
