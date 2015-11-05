package com.sis.Service;

import java.util.List;

import com.sis.entity.About;

public interface AboutService {
	 public List<About> findAllAbouts();  
	 public void updateAbout(About about);  
	 public About findAboutById(int aboutId);
}
