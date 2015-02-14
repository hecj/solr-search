package com.freedom.search.webapp.services;

import java.util.Map;

import com.freedom.search.util.Result;
import com.freedom.search.webapp.entity.LbEssay;

public interface LbEssayService {
	
	public boolean addLbEssay(LbEssay lbEssay);
	
	public boolean delLbEssay(String id);
	
	public LbEssay searchEssay(String id);
	
	public boolean editLbEssay(LbEssay lbEssay);
	
	public Result searchEssayList(Map<String,Object> map);
}
