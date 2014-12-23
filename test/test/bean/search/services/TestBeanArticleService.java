package test.bean.search.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freedom.search.hibernate.entity.Article;
import com.freedom.search.hibernate.entity.Attachment;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.services.ArticleService;
import com.freedom.search.util.Pagination;

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
		Article article = articleService.searchArticleById("14190217019438125610");
		System.out.println(article.getArticleNo());
		System.out.println(article.getAttachments().size());
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
	
	@Test
	public void test01(){
		
		Article article = new Article();
		article.setTitle("测试");
		article.setArticleNo(UUIDUtil.autoUUID());
		
		Set<Attachment> sets = new HashSet<Attachment>();
		Attachment attachment = new Attachment();
		attachment.setAttachmentNo(UUIDUtil.autoUUID());
		attachment.setTitle("测试 -----");
		attachment.setArticle(article);
		sets.add(attachment);
		
		Attachment attachment2 = new Attachment();
		attachment2.setAttachmentNo(UUIDUtil.autoUUID());
		attachment2.setTitle("测试 ");
		attachment2.setArticle(article);
		sets.add(attachment2);
		
		article.setAttachments(sets);
		
		articleService.addArticle(article);
	}
}
