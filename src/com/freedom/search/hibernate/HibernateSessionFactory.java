package com.freedom.search.hibernate;

import javax.annotation.Resource;

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
	public SessionFactory sessionFactory = null ;;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}