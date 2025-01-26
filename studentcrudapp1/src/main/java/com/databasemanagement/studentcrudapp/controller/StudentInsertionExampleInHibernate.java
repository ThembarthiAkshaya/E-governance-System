//program on inserting student record into database
package com.databasemanagement.studentcrudapp.controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

public class StudentInsertionExampleInHibernate
{
   public static void main(String[] args)
   {
		//creating session from session factory 
        //openSession() method is used to create a new Session object in Hibernate.
		Session session=hibernateUtils.getSessionFactory().openSession();
		
		//creating transaction reference to modify the data in the database
		//one session has one transaction
		Transaction transaction=session.beginTransaction();
		
		Course course1=new Course("couse101","AJP","Advance java program",178);
        Course course2=new Course("course102","JPMC","java full stack",134);
		
        //create object of entities
		Student student1=new Student("std110","Abhish","16th",1432,16,"delhi",course1);//transient state(because no interaction with database
		Student student2=new Student("std111","Akshaya","11th",7046,9,"kamareddy",course2);//transient state(because no interaction with database
		
		//inserting entities into database
		session.save(student1); //persistence state not because it has not committed it is cache
		session.save(student2);//persistence state
		
		//commit changes to database then only that will be in persistence state
		transaction.commit(); //now student1 and student2 are in the persistence state
		
		System.out.println("Students successfully inserted");
		
		//closing session factory
		hibernateUtils.getSessionFactory().close();
	}
}
