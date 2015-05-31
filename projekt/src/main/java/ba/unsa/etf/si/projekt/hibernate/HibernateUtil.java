package ba.unsa.etf.si.projekt.hibernate;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;

	static {

		try {
			java.util.Properties properties = new Properties();
			properties.load(new FileInputStream("db.properties"));
		    Configuration configuration = new Configuration();
		    configuration.configure().addProperties(properties);
		    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
		            configuration.getProperties()).build();
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		} catch (Throwable ex) {

			

			throw new ExceptionInInitializerError(ex);

		}

	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;

	}

}