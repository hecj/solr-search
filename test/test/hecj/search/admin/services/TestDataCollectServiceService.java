package test.hecj.search.admin.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hecj.search.admin.entity.DataCollectParams;
import com.hecj.search.admin.services.DataCollectService;
import com.hecj.search.util.Pagination;
import com.hecj.search.util.ResultData;

public class TestDataCollectServiceService {
	
	private DataCollectService dataCollectService ;
	private SessionFactory sessionFactory ;
	
	@Before
	public void init(){
		System.out.println(sessionFactory);
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:test/bean/applicationContext.xml");
		dataCollectService = (DataCollectService) ctx.getBean("dataCollectService");
		sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
		
		System.out.println(sessionFactory);
	}
	
	@Test
	public void testSearchArticleList(){
		
		Pagination mPagination = new Pagination();
		mPagination.setPageSize(2);
		Map mMap = new HashMap();
		mMap.put("pagination", mPagination);
		
		ResultData result = dataCollectService.searchDataCollectByPagination(mMap);
		System.out.println(result.isSuccess());
		System.out.println(result.getPagination().getCountSize());
		List<DataCollectParams> list = (List<DataCollectParams>) result.getData();
		for(DataCollectParams d:list){
			System.out.println(d.getBaseURL());
		}
	}
	
	
}
