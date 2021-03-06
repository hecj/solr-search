package com.freedom.search.admin.database.factory.imp;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.freedom.search.admin.database.factory.DataBase;
import com.freedom.search.admin.entity.LzDataField;
import com.freedom.search.hibernate.HibernateSessionFactory;
import com.freedom.search.util.StringUtil;

@Repository("mySQLDataBase")
public class MySQLDataBase extends HibernateSessionFactory implements DataBase {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public boolean createTable(List<Object> params) {
		StringBuffer ctSQL = new StringBuffer("CREATE TABLE ");
		Session session = sessionFactory.openSession();
		
		try {
			
			String tableName = (String) params.get(0);
			/*
			 * 判断表是否存在
			 * show tables like 'tb_articlE'
			 */
			String existTableName = (String) session.createSQLQuery("show tables like '"+tableName+"'").uniqueResult();
			if(StringUtil.isStrEmpty(existTableName)){
				session.beginTransaction();
				ctSQL.append(tableName+" (");
				ctSQL.append("id varchar(32) ,");
				Set<LzDataField> fields = (Set<LzDataField>) params.get(1);
				for(LzDataField d:fields){
					String fieldName = d.getFieldName();
					String fieldType = d.getFieldType();
					int fieldLenth = d.getFieldLenth();
					ctSQL.append(fieldName+" "+fieldType+"("+fieldLenth+") ,");
				}
				ctSQL.append("PRIMARY KEY (id)");
				ctSQL.append(" )");
				
				session.createSQLQuery(ctSQL.toString()).executeUpdate();
				session.getTransaction().commit();
			}
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw ex;
		} finally{
			
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return false;
	}

}
