package test.freedom.search.hibernate;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {
	
	private Session session ;
	
	@Before
	public void before(){
		session = Hibernate4SesssionFactory.getSessionFactory().openSession();
	}
	
	@Test
	public void test01(){
		
		try{
			session.beginTransaction();
			
			session.createSQLQuery("create table A(a int)").executeUpdate();
			
			session.getTransaction().commit();
			
		} catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(session != null || session.isOpen()){
				session.close();
			}
		}
	}
}
