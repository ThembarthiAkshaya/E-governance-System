package com.databasemanagement.studentcrudapp1;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.hibernateUtils;

import org.hibernate.*;

public class ProductInsertion {

	public static void main(String[] args) {
		//creating session from session factory 
		Session session=hibernateUtils.getSessionFactory().openSession();
		
		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();
		
		Product product1=new Product("product101",3,"books",100);
		Product product2=new Product("product102",3,"pen box",60);
		Product product3=new Product("product103",5,"markers",75);
		Product product4=new Product("product104",2,"charts",10);
		Product product5=new Product("product105",3,"pencil",15);
		
		//inserting entities into database
		session.save(product1);
		session.save(product2);
		session.save(product3);
		session.save(product4);
		session.save(product5);
		
		//commiting the transaction
		transaction.commit(); //now student1 and student2 are in the persistence state
		
		System.out.println("products successfully inserted");
		
		//closing session factory
		hibernateUtils.getSessionFactory().close();
	}

}
