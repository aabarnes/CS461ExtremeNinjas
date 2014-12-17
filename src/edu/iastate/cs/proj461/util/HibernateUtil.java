package edu.iastate.cs.proj461.util;
/*
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
			//configuration.configure("hibernate.cfg.xml");
			configuration.configure();
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder();
			ssrb.applySettings(configuration.getProperties());
			ServiceRegistry sr = ssrb.build();
			//sessionFactory = configuration.buildSessionFactory(ssrb.build());
			sessionFactory = configuration.buildSessionFactory(sr);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
		}
	}
	
	public static synchronized SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
*/

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;
 
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    public static synchronized void initSessionFactory() {
    	if(sessionFactory != null) {
    		throw new IllegalStateException("Hibernate SessionFactory has already been initialized");
    	}
    	try {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            System.out.println("Hibernate Configuration loaded");
            
    		HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();

            PBEStringEncryptor encryptor = registry.getPBEStringEncryptor("configurationHibernateEncryptor");
            System.out.println(configuration.getProperty("hibernate.connection.password"));
            configuration.setProperty("hibernate.connection.password", encryptor.decrypt(configuration.getProperty("hibernate.connection.password")));
            System.out.println(configuration.getProperty("hibernate.connection.password"));
            
            ServiceRegistry serviceRegistry
                = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).buildServiceRegistry();
            System.out.println("Hibernate serviceRegistry created");
            
             // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);  
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		throw new ExceptionInInitializerError(ex);
    	}
    }
     
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
    		throw new IllegalStateException("Hibernate SessionFactory has not been initialized");
        }
         
        return sessionFactory;
    }
}