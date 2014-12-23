package com.freedom.search.services;


/**
 * @类功能说明：类是索引业务类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-8 上午11:23:52
 * @版本：V1.0
 */
public interface TempIndexService {
	
	
	/**
	 * @函数功能说明 临时表索引处理
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-8
	 * @修改内容
	 * @参数：     
	 * @return void   
	 * throws
	 */
	public void commitTempIndexSerivice();
	
	/**
	 * @函数功能说明 简单重构索引
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-8
	 * @修改内容
	 * @参数：     
	 * @return void   
	 * throws
	 */
	public void refactorIndexService();
	
	/**
	 * @函数功能说明 恢复服务器重启前未提交的索引
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-9
	 * @修改内容
	 * @参数：     
	 * @return void   
	 * @throws
	 */
	public void recoverTempIndexService();
	
}