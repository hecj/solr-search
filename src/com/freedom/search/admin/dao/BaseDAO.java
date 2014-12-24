package com.freedom.search.admin.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @类功能说明：DAO base接口类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-4 上午09:40:16
 * @版本：V1.0
 */
public interface BaseDAO<T> {
	
	/**
	 * @函数功能说明 根据主键查询对象
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-4
	 * @修改内容 
	 * @参数： @param id
	 * @参数： @return    
	 * @return T   
	 * @throws
	 */
	public T findById(Serializable id);
	
	/**
	 * @函数功能说明 保存对象
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-4
	 * @修改内容
	 * @参数： @param t
	 * @参数： @return    
	 * @return boolean   
	 * @throws
	 */
	public Serializable save(T t);
	/**
	 * @函数功能说明 保存对象
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-4
	 * @修改内容
	 * @参数： @param t
	 * @参数： @return    
	 * @return boolean   
	 * @throws
	 */
	public void merge(T t);
	
	/**
	 * @函数功能说明 删除对象
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-4
	 * @修改内容
	 * @参数： @param t
	 * @参数： @return    
	 * @return boolean   
	 * @throws
	 */
	
	public boolean persist(T t);
	
	/**
	 * @函数功能说明 删除对象
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-4
	 * @修改内容
	 * @参数： @param t
	 * @参数： @return    
	 * @return boolean   
	 * @throws
	 */
	
	public boolean delete(T t);
	
	/**
	 * @函数功能说明 分页查询
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-5
	 * @修改内容
	 * @参数： @param pagination
	 * @参数： @return    
	 * @return List<T>   
	 * @throws
	 */
	public List<T> queryListByPagination(String pHQL,int... pPagination);
	/**
	 * @函数功能说明 不分页
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-5
	 * @修改内容
	 * @参数： @param pagination
	 * @参数： @return    
	 * @return List<T>   
	 * @throws
	 */
	public List<T> queryListByParams(String pHQL,Object... pParams);
	/**
	 * @函数功能说明 分页
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-5
	 * @修改内容
	 * @参数： @param pagination
	 * @参数： @return    
	 * @return List<T>   
	 * @throws
	 */
	public List<T> queryListByParamsAndPagination(String pHQL,int start,int rows,Object... pParams);
	
	/**
	 * @函数功能说明 统计总数
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-5
	 * @修改内容
	 * @参数： @param mHQL
	 * @参数： @return    
	 * @return long   
	 * @throws
	 */
	public Object queryUniqueResultByHQL(String pHQL);
	
	/**
	 * @函数功能说明 执行HQL语句
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-8
	 * @修改内容
	 * @参数： @param pHQL    
	 * @return void   
	 * @throws
	 */
	public int executeHQL(String pHQL);
	
	/**
	 * @函数功能说明 update
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-24
	 * @修改内容
	 * @参数： @param t    
	 * @return void   
	 * @throws
	 */
	public void update(T t);
}
