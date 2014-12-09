package test.bean.search.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.services.ArticleService;
import com.hecj.search.util.Pagination;

public class TestBeanArticleService {
	
	private ArticleService articleService ;
	private SessionFactory sessionFactory ;
	
	@Before
	public void init(){
		System.out.println(sessionFactory);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("test/bean/applicationContext.xml");
		articleService = (ArticleService) ctx.getBean("articleService");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		
		System.out.println(sessionFactory);
	}
	
	@Test
	public void testSearchArticleList(){
		
		Pagination mPagination = new Pagination();
		Map mMap = new HashMap();
		mMap.put("pagination", mPagination);
		Map rMap = articleService.searchArticleList(mMap);
		List<Article> mList = (List<Article>) rMap.get("rArticleList");
		Pagination rPagination = (Pagination) rMap.get("pPagination");
		for(Article mArticle : mList){
			System.out.println(mArticle.getArticleNo()+"~"+mArticle.getTitle());
		}
		System.out.println(articleService);
		System.out.println("总条数："+rPagination.getCountSize());
		
	}
	
	@Test
	public void testSearchArticleById(){
		Article article = articleService.searchArticleById("14177621650426547330");
//		System.out.println(article.getAttachments());
	}
	
	@Test
	public void createQueryList(){
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select s from Article s where s.articleNo='14177621650423776550'");
		List<Article> mList = query.list();
		for(Article mArticle : mList){
			System.out.println(mArticle.getArticleNo());
		}
		session.close();
	}
	
	@Test
	public void testSearchArticleListBySolr(){
		
		Pagination mPagination = new Pagination();
		Map mMap = new HashMap();
		mMap.put("pagination", mPagination);
		mMap.put("queryString", "标题");
		Map rMap = articleService.searchArticleListBySolr(mMap);
		List<Article> mList = (List<Article>) rMap.get("rArticleList");
		Pagination rPagination = (Pagination) rMap.get("pPagination");
		for(Article mArticle : mList){
			System.out.println(mArticle.getArticleNo()+"~"+mArticle.getTitle());
		}
		System.out.println(articleService);
		System.out.println("总条数："+rPagination.getCountSize());
		
	}
}
