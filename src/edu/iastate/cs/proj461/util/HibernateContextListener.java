package edu.iastate.cs.proj461.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

public class HibernateContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Hibernate SessionFactory destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		strongEncryptor.setPassword("proj461");
		HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
		registry.registerPBEStringEncryptor("configurationHibernateEncryptor", strongEncryptor);
		
		HibernateUtil.initSessionFactory();
		System.out.println("Hibernate SessionFactory initialized.");
	
	}

}
