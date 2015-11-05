package com.sis.DAO;

import java.util.List;
import com.sis.entity.About;

public interface AboutDAO {
	 public List<About> findAllAbouts();  
	 public void updateAbout(About about);  
	 public About findAboutById(int aboutId);
}
