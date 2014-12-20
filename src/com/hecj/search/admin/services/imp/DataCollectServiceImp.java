package com.hecj.search.admin.services.imp;

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

import com.hecj.search.admin.dao.DataCollectParamsDAO;
import com.hecj.search.admin.database.factory.DataBase;
import com.hecj.search.admin.database.factory.DataBaseFactory;
import com.hecj.search.admin.entity.DataCollectParams;
import com.hecj.search.admin.entity.DataField;
import com.hecj.search.admin.senum.EnumAdminUtils;
import com.hecj.search.admin.services.DataCollectService;
import com.hecj.search.hibernate.HibernateSessionFactory;
import com.hecj.search.hibernate.util.UUIDUtil;
import com.hecj.search.util.Log4jUtil;
import com.hecj.search.util.Pagination;
import com.hecj.search.util.PattenUtils;
import com.hecj.search.util.ResultData;
import com.hecj.search.util.StringUtil;
import com.hecj.search.util.http.HtmlUtils;

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
	public List<Object> dataCollectService(DataCollectParams pDataCollectParams) {
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
	 private void queryFieldData(final DataCollectParams pDataCollectParams,String url,Jerry doc){
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
					for(DataField d : pDataCollectParams.getDataFields()){
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
	public ResultData searchDataCollectByPagination(Map<String, Object> pParams) {
		ResultData result = new ResultData();
		try{
			Pagination pagination = (Pagination) pParams.get("pagination");
			String mQueryHQL = "select d from DataCollectParams d";
			String mContHQL = "select count(d) from DataCollectParams d";
			List<DataCollectParams> DataCollectParamsList = dataCollectParamsDAO.queryListByParamsAndPagination(mQueryHQL, pagination.startCursor().intValue(), pagination.getPageSize(),new Object[]{});
			long count = Long.parseLong(dataCollectParamsDAO.queryUniqueResultByHQL(mContHQL).toString());
			pagination.setCountSize(count);
			
			result.setData(DataCollectParamsList);
			result.setPagination(pagination);
			result.setSuccess(true);
		}catch(Exception ex){
			
			result.setSuccess(false);
			Log4jUtil.log(ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}
	
	@Transactional
	@Override
	public DataCollectParams searchDataCollectParams(String id) {
		
		return dataCollectParamsDAO.findById(id);
	}
}
