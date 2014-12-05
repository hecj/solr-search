
package test.hecj.spring.aop;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hecj.search.hibernate.entity.Article;
import com.hecj.search.hibernate.entity.Attachment;
import com.hecj.search.hibernate.util.UUIDUtil;
import com.hecj.search.services.ArticleService;

public class SpringBeanTest {

	/**
	 * @函数功能说明
	 * @修改作�?名字 HECJ  
	 * @修改时间 2014-12-2
	 * @修改内容
	 * @参数�?@param args    
	 * @return void   
	 * @throws
	 */
	

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("test/hecj/spring/aop/applicationContext.xml");

		MyBean myBean = (MyBean) ctx.getBean("myBean");
		myBean.init();
	
	}

}
