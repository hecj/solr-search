package test.freedom.search.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class Hibernate4SesssionFactory2 {
	private static SessionFactory sessionFactory;
	static {
		try {

			/** 此方法在Hibernate4中被标记为过时 */
			// sessionFactory = new
			// Configuration().configure().buildSessionFactory();

			/** Hibernate4取得SessionFactory的方法 */
			Configuration cfg = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);

		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
