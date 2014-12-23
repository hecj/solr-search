package test.freedom.search.solr;
import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freedom.search.senum.EnumUtils;
import com.freedom.search.solr.bean.ArticleBean;
import com.freedom.search.solr.services.SolrArticleService;
import com.freedom.search.solr.util.PropertiesUtil;
import com.freedom.search.solr.util.SolrServerUtil;

public class SolrTest {

	private SolrArticleService solrArticleService ;
	
	@Before
	public void before() {
		System.out.println(PropertiesUtil.getProperties().getProperty("SOLR_URL"));
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		solrArticleService = (SolrArticleService) ctx.getBean("solrArticleService");
	
	}
	
	@Test
	public void add(){
		
		ArticleBean mArticleBean = new ArticleBean();
		mArticleBean.setArticleNo(System.currentTimeMillis()+"");
		mArticleBean.setTitle("阿娇发生的快乐");
		mArticleBean.setContent("佛挡杀佛");
		solrArticleService.addArticleBeanIndex(mArticleBean);
	}
	
	@Test
	public void queryList(){
		
		List<Object> mList = solrArticleService.queryArticleBeanList("标题", 0, 5);
		List<ArticleBean> mArticleBeanList = (List<ArticleBean>) mList.get(0);
		for(ArticleBean a:mArticleBeanList){
			System.out.println(a.getTitle()+"~"+a.getTitle());
		}
	}
	
	@Test
	public void removeAllIndex(){
		try {
			SolrServerUtil.getServer().deleteByQuery("*:*");
			SolrServerUtil.getServer().commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test01(){
		System.out.println(EnumUtils.ObjectType.Article.toString());
	}
}
