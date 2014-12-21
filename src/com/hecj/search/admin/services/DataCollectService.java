package com.hecj.search.admin.services;

import java.util.List;
import java.util.Map;

import com.hecj.search.admin.entity.DataCollectParams;
import com.hecj.search.util.Result;
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
	public List<Object> dataCollectService(DataCollectParams pDataCollectParams);
	
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
	public DataCollectParams searchDataCollectParams(String id);
}

