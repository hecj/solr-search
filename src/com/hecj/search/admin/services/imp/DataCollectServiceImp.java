package com.hecj.search.admin.services.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hecj.search.admin.database.factory.DataBase;
import com.hecj.search.admin.database.factory.DataBaseFactory;
import com.hecj.search.admin.services.DataCollectService;
import com.hecj.search.admin.vo.DataCollectParams;

@Service("dataCollectService")
public class DataCollectServiceImp implements DataCollectService {
	
	@Resource
	private DataBaseFactory dataBaseFactory;
	
	public void setDataBaseFactory(DataBaseFactory dataBaseFactory) {
		this.dataBaseFactory = dataBaseFactory;
	}

	@Override
	public List<Object> dataCollectService(DataCollectParams pDataCollectParams) {
		
		System.out.println(pDataCollectParams.getBaseURL());
		DataBase mDataBase = dataBaseFactory.getDataBase(pDataCollectParams.getDataBaseType());
		System.out.println("dataBaseFactory:"+dataBaseFactory);
		System.out.println("mDataBase:"+mDataBase);
		/*
		 * 建表
		 */
		
		/*
		 * 数据搜集
		 * 插入数据库
		 * 分批量提交数据库
		 */
		
		return null;
	}

}
