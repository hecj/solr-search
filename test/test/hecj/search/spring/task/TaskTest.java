package test.hecj.search.spring.task;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hecj.search.hibernate.entity.TaskController;
import com.hecj.search.services.TaskControllerService;
import com.hecj.search.services.TempIndexService;
import com.hecj.search.solr.util.PropertiesUtil;

public class TaskTest {
	
	private TempIndexService tempIndexService;
	private TaskControllerService taskControllerService;
	
	@Before
	public void before() {
		System.out.println(PropertiesUtil.getProperties().getProperty("SOLR_URL"));
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		tempIndexService = (TempIndexService) ctx.getBean("tempIndexService");
		taskControllerService = (TaskControllerService) ctx.getBean("taskControllerService");
	
	}
	
	@Test
	public void test01(){
		
		tempIndexService.recoverTempIndexService();
	}
	
	@Test
	public void test02(){
		
		System.out.println(this.getClass().getSimpleName());
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
	}
	
	@Test
	public void test03(){
		
		TaskController t = taskControllerService.searchTaskController("TempIndexTask","commitTempIndex" );
		System.out.println(t.getTaskClass());
	}
	
	
	
}
