package edu.iastate.cs.proj461.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static SessionFactory serviceRegistry;
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(ssrb.build());
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
		}
	}
	
	public static synchronized SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
