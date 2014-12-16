package com.hecj.search.admin.database.factory.imp;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hecj.search.admin.database.factory.DataBase;

@Repository("oracleDataBase")
public class OracleDataBase implements DataBase {

	@Override
	public boolean createTable(List<Object> params) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
