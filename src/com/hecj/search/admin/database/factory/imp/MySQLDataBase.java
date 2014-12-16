package com.hecj.search.admin.database.factory.imp;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.hecj.search.admin.database.factory.DataBase;
import com.hecj.search.admin.vo.DataField;
import com.hecj.search.hibernate.HibernateSessionFactory;

@Repository("mySQLDataBase")
public class MySQLDataBase extends HibernateSessionFactory implements DataBase {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public boolean createTable(List<Object> params) {
		StringBuffer ctSQL = new StringBuffer("CREATE TABLE ");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			String tableName = (String) params.get(0);
			ctSQL.append(tableName+" (");
			ctSQL.append("id varchar(30) ,");
			List<DataField> fields = (List<DataField>) params.get(1);
			for(int i=0;i<fields.size();i++){
				DataField d = fields.get(i);
				String fieldName = d.getFieldName();
				String fieldType = d.getFieldType();
				int fieldLenth = d.getFieldLenth();
				ctSQL.append(fieldName+" "+fieldType+"("+fieldLenth+") ,");
			}
			ctSQL.append("PRIMARY KEY (id)");
			ctSQL.append(" )");
			
			session.createSQLQuery(ctSQL.toString()).executeUpdate();
			session.getTransaction().commit();
			
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		} finally{
			
			if(session != null && session.isOpen()){
				session.close();
			}
		}
		return false;
	}

}
