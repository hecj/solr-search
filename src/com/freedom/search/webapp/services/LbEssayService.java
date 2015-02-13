package com.freedom.search.webapp.services;

import com.freedom.search.webapp.entity.LbEssay;

public interface LbEssayService {
	
	public boolean addLbEssay(LbEssay lbEssay);
	
	public boolean delLbEssay(String id);
	
	public boolean editLbEssay(LbEssay lbEssay);
}
