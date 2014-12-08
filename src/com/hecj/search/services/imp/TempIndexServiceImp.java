package com.hecj.search.services.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hecj.search.hibernate.dao.TempIndexDAO;
import com.hecj.search.services.TempIndexService;

@Service("tempIndexService")
public class TempIndexServiceImp implements TempIndexService {
	
	@Resource
	private TempIndexDAO tempIndexDAO ;
	
	public void setTempIndexDAO(TempIndexDAO tempIndexDAO) {
		this.tempIndexDAO = tempIndexDAO;
	}


}
