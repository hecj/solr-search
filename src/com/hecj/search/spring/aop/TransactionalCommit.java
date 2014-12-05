package com.hecj.search.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Repository;

import com.hecj.search.hibernate.HibernateSessionFactory;

@Aspect
@Repository
public class TransactionalCommit extends HibernateSessionFactory{
	
	private static final long serialVersionUID = 1L;

	@Before("execution(* com.hecj.search.services.ArticleService.searchArticleList(..))")
	public void beforeTransactional(){
		System.out.println("我在方法调用之前执行了...");
	}
	
	@After("execution(* com.hecj.search.services.ArticleService.searchArticleList(..))")
	public void afterTransactional(){
		
		System.out.println("我在方法调用之后执行了...");
	}
	
}
