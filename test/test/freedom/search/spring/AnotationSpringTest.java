package test.freedom.search.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freedom.search.hibernate.entity.LaArticle;
import com.freedom.search.hibernate.entity.LaAttachment;
import com.freedom.search.hibernate.util.UUIDUtil;
import com.freedom.search.services.ArticleService;
import com.freedom.search.util.Pagination;


public class AnotationSpringTest {
	
	public static void main(String[] args) {

		// 读取配置文件
		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean/applicationContext.xml");
		// 查找bean
//		HibernateSessionFactory hibernateSessionFactory = (HibernateSessionFactory) ctx.getBean("hibernateSessionFactory");
//		System.out.println(hibernateSessionFactory);
//		SessionFactory sessionFactory = hibernateSessionFactory.getSessionFactory();
//		
//		Session session = sessionFactory.openSession();
//		session.createQuery("from Article ").list();
		
//		ArticleDAO articleDAO = (ArticleDAO) ctx.getBean("articleDAO");
//		System.out.println(articleDAO.findById(1L).getId());
//		ArticleService articleService = (ArticleService) ctx.getBean("articleService");
////		System.out.println(articleService.searchArticleById(1l).getId());
////		System.out.println(articleService.searchArticleById(1L).getId());
//		Article a = new Article();
//		a.setArtNo(UUIDUtil.autoUUID());
//		a.setCreateDate(new Date());
//		a.setUpdateDate(new Date());
//		articleService.addArticle(a);
		
	
		
		ArticleService articleService = (ArticleService) ctx.getBean("articleService");
		
		
		List<LaArticle> list= new ArrayList<LaArticle>();
		list.add(initArticle());
		list.add(initArticle());
		list.add(initArticle());
		list.add(initArticle());
		list.get(3).getAttachments().iterator().next().setAttachmentNo(UUIDUtil.autoUUID());
		articleService.addArticle(list);
		
//		MyBean myBean = (MyBean) ctx.getBean("myBean");
//		myBean.init();

		//ArticleService articleService = (ArticleService) ctx.getBean("articleService");
//		articleService.deleteArticle("14176579898767653659");
		
		
		
		
		 
	/*	AttachmentDAO attachmentDAO = (AttachmentDAO) ctx.getBean("attachmentDAO");
//		attachmentDAO.delete(attachmentDAO.findById("14176633050250920106"));
		
		Article article = new Article();
		article.setTitle("hassssha");
		article.setArticleNo(UUIDUtil.autoUUID());
		
		Attachment attachment2 = new Attachment();
		attachment2.setAttachmentNo(UUIDUtil.autoUUID());
		attachment2.setTitle("haha2ffff");
		attachment2.setArticle(article);
		
		attachmentDAO.save(attachment2);*/
		
		Pagination mPagination = new Pagination();
		Map mMap = new HashMap();
		mMap.put("pagination", mPagination);
		articleService.searchArticleList(mMap);
		System.out.println(articleService);
		
	}
	
	public static LaArticle initArticle(){
		LaArticle article = new LaArticle();
		article.setTitle("测试");
		article.setArticleNo(UUIDUtil.autoUUID());
		
		Set<LaAttachment> sets = new HashSet<LaAttachment>();
		LaAttachment attachment = new LaAttachment();
		attachment.setAttachmentNo(UUIDUtil.autoUUID());
		attachment.setTitle("测试 -----");
		attachment.setArticle(article);
		sets.add(attachment);
		
		LaAttachment attachment2 = new LaAttachment();
		attachment2.setAttachmentNo(UUIDUtil.autoUUID());
		attachment2.setTitle("测试 ");
		attachment2.setArticle(article);
		sets.add(attachment2);
		
		article.setAttachments(sets);
		
		LaArticle article3 = new LaArticle();
		article3.setTitle("测试");
		article3.setArticleNo(UUIDUtil.autoUUID());
		
		article.setAttachments(sets);
		
		return article;
	}
}
