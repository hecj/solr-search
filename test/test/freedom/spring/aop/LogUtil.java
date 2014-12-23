package test.freedom.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Repository;

@Aspect
@Repository
public class LogUtil {

	@Before("execution(void test.hecj.spring.aop.MyBean.init())")
	public void beforeMethod(){
		System.out.println("调用之前打印日志");
	}
}
