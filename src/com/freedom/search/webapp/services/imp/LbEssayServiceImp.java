package com.freedom.search.webapp.services.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.freedom.search.util.StringUtil;
import com.freedom.search.webapp.dao.LbEssayDAO;
import com.freedom.search.webapp.entity.LbEssay;
import com.freedom.search.webapp.services.LbEssayService;

@Service("lbEssayService")
public class LbEssayServiceImp implements LbEssayService {
	
	@Resource
	private LbEssayDAO lbEssayDAO ;

	public void setLbEssayDAO(LbEssayDAO lbEssayDAO) {
		this.lbEssayDAO = lbEssayDAO;
	}

	@Override
	public boolean addLbEssay(LbEssay lbEssay) {
		if(!StringUtil.isObjectNull(lbEssayDAO.save(lbEssay))){
			return true;
		}
		return false;
	}

	@Override
	public boolean delLbEssay(String id) {
		return lbEssayDAO.delete(lbEssayDAO.findById(id));
	}

	@Override
	public boolean editLbEssay(LbEssay lbEssay) {
		lbEssayDAO.merge(lbEssay);
		return true;
	}
	
	
}
