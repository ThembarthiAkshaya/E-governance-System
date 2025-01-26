//configuration class
package com.databasemanagement.studentcrudapp1.utils;

//importing session factory class from hibernate frame work
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class hibernateUtils {

	private static SessionFactory sessionfactory;

	static
	{
		try 
		{
			//creating object of configuration class
			Configuration config=new Configuration();

			//setting the properties related to database connection

			//setting name of driver class
			config.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");

			//setting url for database
			config.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3306/akshaya");

			//setting user name of database
			config.setProperty("hibernate.connection.username","root");

			//setting password of database
			config.setProperty("hibernate.connection.password","Akshaya8313*");

			//setting properties to configure logging for sql statements
			
			//property to show sql
			config.setProperty("hibernate.show_sql",true);

			//property to show well formated
			config.setProperty("hibernate.format_sql", true);

			//property to schema generation
			config.setProperty("hibernate.hbm2ddl.auto","update");
			
			//setting property for enabling 2nd level cache
			config.setProperty("cache.use_second_level_cache",true);
			
			//setting property to specify name of region  factory class for 2nd level cache
			config.setProperty("cache.region.factory_class","org.hibernate.cache.jcache.internal.JCacheRegionFactory");
			
			//config.setProperty("hibernate.javax.cache.provider","org.ehcache.jsr107.EhcacheCachingProvider");
			/*-----------------------------------------------*/
			//setting annotated entity class 
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.Student.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.Course.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.Faculty.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.Employee.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.RegularEmployee.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.ContractEmployee.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.Employee1.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.RegularEmployee1.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.ContractEmployee1.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.Employee2.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.RegularEmployee2.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.ContractEmployee2.class);
			config.addAnnotatedClass(com.databasemanagement.studentcrudapp.entities.Product.class);
			
			//creating reference of standard registry 
			StandardServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();

			//initializing session factory
			sessionfactory=config.buildSessionFactory(serviceRegistry);
		}
		catch(Throwable tw) 
		{
			System.err.println("Unable to crate session factory:"+tw);
			throw new ExceptionInInitializerError(tw);
		}
	}

	//method to return session factory
	public static SessionFactory getSessionFactory() {
		return sessionfactory;
	}
}
