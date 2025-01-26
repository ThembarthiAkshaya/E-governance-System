//configuration class
package com.schoolmanagement.utils;

//importing session factory class from hibernate frame work
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

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
			
			/*-----------------------------------------------*/
			//setting annotated entity class 
			config.addAnnotatedClass(com.databasemanagement.schoolmanagement.entities.Citizen.class);
			

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
