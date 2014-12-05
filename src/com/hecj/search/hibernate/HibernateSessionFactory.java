package com.hecj.search.hibernate;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTransactionManager;

/**
 * Configures and provides access to Hibernate sessions, tied to the
 * current thread of execution.  Follows the Thread Local Session
 * pattern, see {@link http://hibernate.org/42.html }.
 */
public class HibernateSessionFactory extends HibernateTransactionManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private SessionFactory sessionFactory = null ;;
	private Session session = null ;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	protected synchronized Session getSession(){
		
		if(session == null ){
			session = sessionFactory.openSession();
		}
		return session;
	}

}