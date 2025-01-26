package com.egovernance.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
	
	public static SessionFactory getsFactory() {
		StandardServiceRegistry ssRegistry=new StandardServiceRegistryBuilder().configure("config.xml").build();
	       Metadata metadata=new MetadataSources(ssRegistry).getMetadataBuilder().build();
			
		  SessionFactory sFactory=metadata.buildSessionFactory();
		
		return sFactory;
	}

}
