package com.hecj.search.admin.services.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hecj.search.admin.services.DataCollectService;
import com.hecj.search.admin.vo.DataCollectParams;

@Service("dataCollectService")
public class DataCollectServiceImp implements DataCollectService {

	@Override
	public List<Object> dataCollectService(DataCollectParams pDataCollectParams) {
		
		System.out.println(pDataCollectParams.getBaseURL());
		
		return null;
	}

}
