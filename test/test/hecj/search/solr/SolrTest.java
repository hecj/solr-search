package test.hecj.search.solr;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hecj.search.solr.bean.ArticleBean;
import com.hecj.search.solr.services.SolrArticleService;
import com.hecj.search.solr.util.PropertiesUtil;

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

}
