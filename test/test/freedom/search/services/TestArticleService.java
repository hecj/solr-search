package test.freedom.search.services;

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

import com.freedom.search.hibernate.entity.LaArticle;
import com.freedom.search.services.ArticleService;
import com.freedom.search.util.Pagination;

public class TestArticleService {
	
	private ArticleService articleService ;
	private SessionFactory sessionFactory ;
	
	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		articleService = (ArticleService) ctx.getBean("articleService");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
	}
	
	@Test
	public void testSearchArticleList(){
		
		Pagination mPagination = new Pagination();
		Map mMap = new HashMap();
		mMap.put("pagination", mPagination);
		Map rMap = articleService.searchArticleList(mMap);
		List<LaArticle> mList = (List<LaArticle>) rMap.get("rArticleList");
		Pagination rPagination = (Pagination) rMap.get("pPagination");
		for(LaArticle mArticle : mList){
			System.out.println(mArticle.getArticleNo()+"~"+mArticle.getTitle());
		}
		System.out.println(articleService);
		System.out.println("总条数："+rPagination.getCountSize());
		
	}
	
	@Test
	public void testSearchArticleById(){
		LaArticle article = articleService.searchArticleById("14177621650426547330");
//		System.out.println(article.getAttachments());
	}
	
	@Test
	public void createQueryList(){
		
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("select s from Article s where s.articleNo='14177621650423776550'");
		List<LaArticle> mList = query.list();
		for(LaArticle mArticle : mList){
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
		List<LaArticle> mList = (List<LaArticle>) rMap.get("rArticleList");
		Pagination rPagination = (Pagination) rMap.get("pPagination");
		for(LaArticle mArticle : mList){
			System.out.println(mArticle.getArticleNo()+"~"+mArticle.getTitle());
		}
		System.out.println(articleService);
		System.out.println("总条数："+rPagination.getCountSize());
		
	}
}
