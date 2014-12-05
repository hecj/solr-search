package com.hecj.search.hibernate.util;

public class UUIDUtil {
	
	/**
	 * @函数功能说明 随机生成UUID
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-3
	 * @修改内容
	 * @参数： @return    
	 * @return String   
	 * @throws
	 */
	public static String autoUUID(){
		
		String endUUID = String.valueOf((int)(Math.random()*10000000l));
		int endUUIDLenth = endUUID.length() ;
		for(int i = 0; i<7-endUUIDLenth; i++){
			endUUID = "0"+endUUID;
		}
		return String.valueOf(System.currentTimeMillis())+endUUID;
	}
	
}
