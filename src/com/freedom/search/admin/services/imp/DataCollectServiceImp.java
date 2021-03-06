package com.freedom.search.admin.services.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freedom.search.admin.Enum.EnumAdminUtils;
import com.freedom.search.admin.dao.DataCollectParamsDAO;
import com.freedom.search.admin.database.factory.DataBase;
import com.freedom.search.admin.database.factory.DataBaseFactory;
import com.freedom.search.admin.entity.LzDataCollectParams;
import com.freedom.search.admin.entity.LzDataField;
import com.freedom.search.admin.services.DataCollectService;
import com.freedom.search.hibernate.HibernateSessionFactory;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.util.Log4jUtil;
import com.freedom.search.util.Pagination;
import com.freedom.search.util.PattenUtils;
import com.freedom.search.util.Result;
import com.freedom.search.util.ResultSupport;
import com.freedom.search.util.StringUtil;
import com.freedom.search.util.http.HtmlUtils;

@Service("dataCollectService")
public class DataCollectServiceImp extends HibernateSessionFactory implements DataCollectService {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private DataBaseFactory dataBaseFactory;
	@Resource
	private DataCollectParamsDAO dataCollectParamsDAO;
	
	public void setDataBaseFactory(DataBaseFactory dataBaseFactory) {
		this.dataBaseFactory = dataBaseFactory;
	}
	
	public void setDataCollectParamsDAO(DataCollectParamsDAO dataCollectParamsDAO) {
		this.dataCollectParamsDAO = dataCollectParamsDAO;
	}

	@Override
	public List<Object> dataCollectService(LzDataCollectParams pDataCollectParams) {
		Jerry doc = null;
		try {
			
			DataBase mDataBase = dataBaseFactory.getDataBase(pDataCollectParams.getDataBaseType());
			/*
			 * 建表
			 */
			List<Object> list = new ArrayList<Object>();
			list.add(0, pDataCollectParams.getTableName());
			list.add(1, pDataCollectParams.getDataFields());
			mDataBase.createTable(list);
			/*
			 * 数据搜集 
			 * 插入数据库 
			 * 分批量提交数据库
			 */
			String baseURL = pDataCollectParams.getBaseURL();
			
			if(!StringUtil.isStrEmpty(pDataCollectParams.getPageParams())){
				for(int i=pDataCollectParams.getStart();i<=pDataCollectParams.getEnd();i+=pDataCollectParams.getStep()){
					String url = baseURL.replace(pDataCollectParams.getPageParams(), String.valueOf(i));
					queryFieldData(pDataCollectParams, url, doc);
				}
			}else{
				queryFieldData(pDataCollectParams, baseURL, doc);
			}
			
			/*
			 * 保存配置记录
			 */
			dataCollectParamsDAO.save(pDataCollectParams);
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			doc = null;
			System.gc();
		}
		return null;
	}
	
	/*
	 * 匹配数据并插入
	 */
	 private void queryFieldData(final LzDataCollectParams pDataCollectParams,String url,Jerry doc){
		final Session session = sessionFactory.openSession();
		session.beginTransaction();
		String content = "";
		if(!StringUtil.isStrEmpty(pDataCollectParams.getEncode())){
			if(!StringUtil.isStrEmpty(pDataCollectParams.getIP())){
				content = HtmlUtils.getHtmlContentByHttpClientProxy(url,pDataCollectParams.getIP(), pDataCollectParams.getPORT(), pDataCollectParams.getEncode());
			}else{
				content = HtmlUtils.getHtmlContentByHttpClient(url, pDataCollectParams.getEncode());
			}
		}else{
			if(!StringUtil.isStrEmpty(pDataCollectParams.getIP())){
				content = HtmlUtils.getHtmlContentByHttpClientProxy(url,pDataCollectParams.getIP(), pDataCollectParams.getPORT());
			}else{
				content = HtmlUtils.getHtmlContentByHttpClient(url);
			}
		}
		/*
		 * 解析
		 */
		doc = Jerry.jerry(content);
		if (doc != null && doc.size() > 0 && doc.html() != null&& !doc.html().equals(pDataCollectParams.getBaseSelect())) {
			doc.$(pDataCollectParams.getBaseSelect()).each(new JerryFunction() {
				public boolean onNode(Jerry $this, int index) {
					Map<String,String> data = new HashMap<String,String>();
					for(LzDataField d : pDataCollectParams.getDataFields()){
						String field = ""; 
						//解析字段
						if(!StringUtil.isStrEmpty(d.getFieldSelect())){
							if(d.getSelectMethod().equals(EnumAdminUtils.SelectMethod.TEXT.toString())){
								field = $this.find(d.getFieldSelect()).text();
							}else if(d.getSelectMethod().equals(EnumAdminUtils.SelectMethod.HTML.toString())){
								field = $this.find(d.getFieldSelect()).html();
							}else if(d.getSelectMethod().equals(EnumAdminUtils.SelectMethod.ATTR.toString())){
								field = $this.find(d.getFieldSelect()).attr(d.getTargetAttr());
							}
							
						}else{
							if(d.getSelectMethod().equals(EnumAdminUtils.SelectMethod.TEXT.toString())){
								field = $this.text();
							}else if(d.getSelectMethod().equals(EnumAdminUtils.SelectMethod.HTML.toString())){
								field = $this.html();
							}else if(d.getSelectMethod().equals(EnumAdminUtils.SelectMethod.ATTR.toString())){
								field = $this.attr(d.getTargetAttr());
							}
						}
						//正则
						if(!StringUtil.isStrEmpty(d.getPattern())){
							field+=PattenUtils.pattenUniqueContent(field, d.getPattern());
						}
						//替换
						if(!StringUtil.isStrEmpty(d.getOldPlace())&&!StringUtil.isStrEmpty(d.getNewPlace())){
							field.replaceAll(d.getOldPlace(), d.getNewPlace());
						}
						data.put(d.getFieldName(), field);
					}
					//拼接字段及Value
					String fields = "";
					String values = "";
					int n = 0;
					for(String key:data.keySet()){
						if(n==data.size()-1){
							fields+=key;
							values+="'"+data.get(key)+"'";
						}else{
							fields+=key+",";
							values+="'"+data.get(key)+"',";
						}
						n++;
					}
					Log4jUtil.log(values);
					//获取的值不为空时,插入数据
					if(!StringUtil.isStrEmpty(values)){
						//插入数据
						StringBuffer insertSQL = new StringBuffer();
						insertSQL.append("insert into "+pDataCollectParams.getTableName()+"(");
						insertSQL.append("id,"+fields+")");
						insertSQL.append(" values('"+UUIDUtil.autoUUID()+"',"+values+")");
//						System.out.println(insertSQL);
						session.createSQLQuery(insertSQL.toString()).executeUpdate();
					}
					return true;
				}
			});
		}
		//刷新到数据库并提交
		session.flush();
		session.clear();
		session.getTransaction().commit();
	}
	
	@Transactional
	@Override
	public Result searchDataCollectByPagination(Map<String, Object> pParams) {
		Result result = new ResultSupport();
		try{
			
			String encode = (String) pParams.get("encode");
			Pagination pagination = (Pagination) pParams.get("pagination");
			String mQueryHQL = "select d from LzDataCollectParams d where 1=1";
			String mContHQL = "select count(d) from LzDataCollectParams d where 1=1";
			
			//动态拼接SQL
			if(!StringUtil.isStrEmpty(encode)){
				mQueryHQL += " and d.encode='"+encode+"'";
				mContHQL += " and d.encode='"+encode+"'";
			}
			System.out.println(mQueryHQL);
			List<LzDataCollectParams> DataCollectParamsList = dataCollectParamsDAO.queryListByParamsAndPagination(mQueryHQL, pagination.startCursor().intValue(), pagination.getPageSize(),new Object[]{});
			long count = Long.parseLong(dataCollectParamsDAO.queryUniqueResultByHQL(mContHQL).toString());
			pagination.setCountSize(count);
			
			result.setData(DataCollectParamsList);
			result.setPagination(pagination);
			result.setResult(true);
		}catch(Exception ex){
			
			result.setResult(false);
			Log4jUtil.log(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	@Override
	public LzDataCollectParams searchDataCollectParams(String id) {
		
		return dataCollectParamsDAO.findById(id);
	}

	@Transactional
	@Override
	public void deleteDataCollectParams(String id) {
		dataCollectParamsDAO.delete(dataCollectParamsDAO.findById(id));
	}
	
	@Transactional
	@Override
	public void editDataCollectParams(LzDataCollectParams dataCollectParams) {
		dataCollectParamsDAO.executeHQL("delete from LzDataField f where f.dataCollectParams='"+dataCollectParams.getId()+"'");
		dataCollectParamsDAO.executeHQL("delete from LzDataCollectParams f where f.id='"+dataCollectParams.getId()+"'");
		dataCollectParamsDAO.save(dataCollectParams);
	}
	
	@Transactional
	@Override
	public void addDataCollectParams(LzDataCollectParams dataCollectParams) {
		dataCollectParamsDAO.save(dataCollectParams);
	}
}
