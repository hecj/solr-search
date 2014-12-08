package com.hecj.search.services.imp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hecj.search.hibernate.dao.TempIndexDAO;
import com.hecj.search.services.TempIndexService;
import com.hecj.search.solr.util.PropertiesUtil;
import com.hecj.search.solr.util.SolrServerUtil;
import com.hecj.search.util.StringUtil;

@Service("tempIndexService")
@Transactional
public class TempIndexServiceImp implements TempIndexService {
	
	@Resource
	private TempIndexDAO tempIndexDAO ;
	
	public void setTempIndexDAO(TempIndexDAO tempIndexDAO) {
		this.tempIndexDAO = tempIndexDAO;
	}

	@Override
	public void commitTempIndexSerivice() {
		
		
		String mtempIndexCount = "select count(t) from TempIndex t";
		try{
			
			long mCountSize = Long.parseLong(tempIndexDAO.queryUniqueResultByHQL(mtempIndexCount).toString());
			/*
			 * 达到多少条上线后提交数据
			 */
			String commitCountStr = PropertiesUtil.getProperties().getProperty("COMMIT_COUNT");
			/*
			 * 若配置文件没有配置,默认10条数据提交
			 */
			int commitCount = 10;
			if(!StringUtil.isStrEmpty(commitCountStr)){
				commitCount = Integer.parseInt(commitCountStr);
			}
			/*
			 * 提交内存索引数据
			 */
			if(mCountSize >= commitCount){
				System.out.println("com.hecj.search.services.imp.TempIndexServiceImp.commitTempIndexSerivice() commit start ...");
				SolrServerUtil.getServer().commit();
				int deleteCount = tempIndexDAO.executeHQL("delete from TempIndex t");
				System.out.println("本次任务共提交索引个数:"+deleteCount);
				System.out.println("com.hecj.search.services.imp.TempIndexServiceImp.commitTempIndexSerivice() commit end ...");
			}else{
				
				System.out.println("本次任务扫描临时索引个数："+mCountSize+",提交峰值为："+commitCount);
			}
			
		}catch(Exception mException){
			
			mException.printStackTrace();
		}
	}


}
