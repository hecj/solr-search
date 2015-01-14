package com.freedom.search.admin.dao;

import java.io.Serializable;

import com.freedom.search.admin.entity.LzDataCollectParams;

public interface DataCollectParamsDAO extends BaseDAO<LzDataCollectParams>{

	Serializable save(LzDataCollectParams t);
	
}
