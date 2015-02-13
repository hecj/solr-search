package com.freedom.search.webapp.services.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freedom.search.webapp.dao.LbEssayDAO;
import com.freedom.search.webapp.services.LbEssayService;

@Service("lbEssayService")
public class LbEssayServiceImp implements LbEssayService {
	
	@Resource
	private LbEssayDAO lbEssayDAO ;

	public void setLbEssayDAO(LbEssayDAO lbEssayDAO) {
		this.lbEssayDAO = lbEssayDAO;
	}
	
	
}
