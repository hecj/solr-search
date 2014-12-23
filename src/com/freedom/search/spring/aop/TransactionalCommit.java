package com.freedom.search.spring.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Repository;

import com.freedom.search.hibernate.HibernateSessionFactory;
import com.freedom.search.util.Log4jUtil;

@Aspect
@Repository
public class TransactionalCommit extends HibernateSessionFactory{
	
	private static final long serialVersionUID = 1L;

	@Before("execution(* com.freedom.search.services.ArticleService.searchArticleList(..))")
	public void beforeTransactional(){
		Log4jUtil.log("我在方法调用之前执行了...");
	}
	
	@After("execution(* com.freedom.search.services.ArticleService.searchArticleList(..))")
	public void afterTransactional(){
		Log4jUtil.log("我在方法调用之后执行了...");
	}
	
}
