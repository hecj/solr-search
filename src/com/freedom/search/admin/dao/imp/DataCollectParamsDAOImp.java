package com.freedom.search.admin.dao.imp;


import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.freedom.search.admin.dao.DataCollectParamsDAO;
import com.freedom.search.admin.entity.DataCollectParams;

@Repository("dataCollectParamsDAO")
public class DataCollectParamsDAOImp extends BaseDAOImp<DataCollectParams> implements DataCollectParamsDAO {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = 1L;
	@Transactional
	@Override
	public Serializable save(DataCollectParams t) {
		return super.save(t);
	}
	
}
