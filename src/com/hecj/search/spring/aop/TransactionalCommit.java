package com.hecj.search.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Repository;

import com.hecj.search.hibernate.HibernateSessionFactory;
import com.hecj.search.util.Log4jUtil;

@Aspect
@Repository
public class TransactionalCommit extends HibernateSessionFactory{
	
	private static final long serialVersionUID = 1L;

	@Before("execution(* com.hecj.search.services.ArticleService.searchArticleList(..))")
	public void beforeTransactional(){
		Log4jUtil.log("我在方法调用之前执行了...");
	}
	
	@After("execution(* com.hecj.search.services.ArticleService.searchArticleList(..))")
	public void afterTransactional(){
		Log4jUtil.log("我在方法调用之后执行了...");
	}
	
}
