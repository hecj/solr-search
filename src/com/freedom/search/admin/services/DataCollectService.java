package com.freedom.search.admin.services;

import java.util.List;
import java.util.Map;

import com.freedom.search.admin.entity.LzDataCollectParams;
import com.freedom.search.util.Result;
/**
 * @类功能说明：数据搜集业务类
 * @类修改者：
 * @修改日期：
 * @修改说明：
 * @作者：HECJ
 * @创建时间：2014-12-18 下午10:18:45
 * @版本：V1.0
 */
public interface DataCollectService {
	
	/**
	 * 数据搜集业务方法
	 */
	public List<Object> dataCollectService(LzDataCollectParams pDataCollectParams);
	
	/**
	 * 分页查询数据搜集<br>
	 * params:<br>
	 * Map[condition]<br>
	 * Map[pagination]<br>
	 */
	public Result searchDataCollectByPagination(Map<String,Object> pParams);
	
	/**
	 * @函数功能说明 根据Id查询
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-20
	 * @修改内容
	 * @参数： @param id
	 * @参数： @return    
	 * @return DataCollectParams   
	 * throws
	 */
	public LzDataCollectParams searchDataCollectParams(String id);
	
	/**
	 * @函数功能说明 删除
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-21
	 * @修改内容
	 * @参数： @param id    
	 * @return void   
	 * throws
	 */
	public void deleteDataCollectParams(String id);
	
	/**
	 * @函数功能说明 修改
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-24
	 * @修改内容
	 * @参数： @param dataCollectParams    
	 * @return void   
	 * @throws
	 */
	public void editDataCollectParams(LzDataCollectParams dataCollectParams);
	
	/**
	 * @函数功能说明 添加
	 * @修改作者名字 HECJ  
	 * @修改时间 2014-12-24
	 * @修改内容
	 * @参数： @param dataCollectParams    
	 * @return void   
	 * @throws
	 */
	public void addDataCollectParams(LzDataCollectParams dataCollectParams);
}

