package test.hecj.search.hibernate;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import test.hecj.search.hibernate.entity.Article;


//hibernate 4.3
final public class Hibernate4SesssionFactory {

	private static ServiceRegistry serviceRegistry = null;
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;

	static {
		cfg = new Configuration().configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
				cfg.getProperties()).build();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void main(String[] args) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		Article a = new Article();
		a.setAuther("fds");
//		a.setContent("fds");
		a.setCreateDate(new Date());
		
		session.save(a);
		
		session.getTransaction().commit();
	}
}