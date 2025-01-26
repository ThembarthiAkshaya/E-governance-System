package com.databasemanagement.studentcrudapp1;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

import org.hibernate.*;
import org.hibernate.Transaction;

public class FacultyCreation {

	public static void main(String[] args) {
		Session session=hibernateUtils.getSessionFactory().openSession();
		//creating transaction reference
		Transaction transaction=session.beginTransaction();
		
		//creating course entities
		Course course1=session.get(Course.class,"course103");
		Course course2=session.get(Course.class,"course104");

		/*---address object----*/
		Address address1=new Address("north delhi","Delhi","Delhi",123456);
		Address address2=new Address("north delhi","Delhi","Delhi",123456);
		//creating faculty entities
		Faculty faculty1=new Faculty("teah101","Ashish kumar",address1,course2);
		Faculty faculty2=new Faculty("teah102","Aman kumar",address2,course1);

		/*------saving entities ------*/
		session.save(course1);
		session.save(course2);
		session.save(faculty1);
		session.save(faculty2);

		/*--------to commit changes into database ----------*/
		transaction.commit();

		/*------closing the session-----------*/
		hibernateUtils.getSessionFactory().close();
	}
}
