package com.freedom.search.admin.database.factory;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.admin.database.factory.imp.MySQLDataBase;
import com.freedom.search.admin.database.factory.imp.OracleDataBase;

/**
 * @类功能说明：适配数据库处理类
 * @类功能说明：
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-16 上午12:57:55
 * @版本：V1.0
 */
@Service("dataBaseFactory")
public class DataBaseFactory {
	
	@Resource
	private MySQLDataBase mySQLDataBase;
	@Resource
	private OracleDataBase oracleDataBase;
	public void setMySQLDataBase(MySQLDataBase mySQLDataBase) {
		this.mySQLDataBase = mySQLDataBase;
	}

	public void setOracleDataBase(OracleDataBase oracleDataBase) {
		this.oracleDataBase = oracleDataBase;
	}


	public DataBase getDataBase(String dataBaseType){
		
		if(dataBaseType.equals(EnumAdminUtils.DataBaseType.MySQL.toString())){
			return mySQLDataBase;
		}else if(dataBaseType.equals(EnumAdminUtils.DataBaseType.Oracle.toString())){
			return oracleDataBase;
		}
		return null;
	}
}
