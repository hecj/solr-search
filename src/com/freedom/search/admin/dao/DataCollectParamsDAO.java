package com.freedom.search.admin.dao;

import java.io.Serializable;

import com.freedom.search.admin.entity.DataCollectParams;

public interface DataCollectParamsDAO extends BaseDAO<DataCollectParams>{

	Serializable save(DataCollectParams t);
	
}
