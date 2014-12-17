package com.hecj.search.admin.services.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import jodd.jerry.Jerry;
import jodd.jerry.JerryFunction;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.hecj.search.admin.database.factory.DataBase;
import com.hecj.search.admin.database.factory.DataBaseFactory;
import com.hecj.search.admin.services.DataCollectService;
import com.hecj.search.admin.vo.DataCollectParams;
import com.hecj.search.admin.vo.DataField;
import com.hecj.search.hibernate.HibernateSessionFactory;
import com.hecj.search.hibernate.util.UUIDUtil;
import com.hecj.search.util.PattenUtils;
import com.hecj.search.util.StringUtil;
import com.hecj.search.util.http.HtmlUtils;

@Service("dataCollectService")
public class DataCollectServiceImp extends HibernateSessionFactory implements DataCollectService {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private DataBaseFactory dataBaseFactory;
	
	public void setDataBaseFactory(DataBaseFactory dataBaseFactory) {
		this.dataBaseFactory = dataBaseFactory;
	}
	@Override
	public List<Object> dataCollectService(DataCollectParams pDataCollectParams) {
		Jerry doc = null;
		Session session = null ;
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
			session = sessionFactory.openSession();
			Integer commitCount = 0;
			if(!StringUtil.isStrEmpty(pDataCollectParams.getPageParams())){
				for(int i=pDataCollectParams.getStart();i<=pDataCollectParams.getEnd();i+=pDataCollectParams.getStep()){
					String url = baseURL.replace(pDataCollectParams.getPageParams(), String.valueOf(i));
					queryFieldData(pDataCollectParams, url, doc, commitCount, session);
				}
			}else{
				queryFieldData(pDataCollectParams, baseURL, doc, commitCount, session);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doc = null;
			if(session != null && session.isOpen()){
				session.close();
			}
			System.gc();
		}
		return null;
	}
	
	/*
	 * 匹配数据并插入
	 */
	 private void queryFieldData(final DataCollectParams pDataCollectParams,String url,Jerry doc,Integer commitCount,final Session session){
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
							field = $this.find(d.getFieldSelect()).text();
						}else{
							field = $this.text();
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
					System.out.println($this.html());
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
}
