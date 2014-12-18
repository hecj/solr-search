package com.hecj.search.admin.dao;

import java.io.Serializable;

import com.hecj.search.admin.entity.DataCollectParams;

public interface DataCollectParamsDAO extends BaseDAO<DataCollectParams>{

	Serializable save(DataCollectParams t);
	
}
