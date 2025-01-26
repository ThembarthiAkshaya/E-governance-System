//program to retrieve the student details by using get method
package com.databasemanagement.studentcrudapp1;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

import org.hibernate.Session;

public class StudentRetrievlUsingGetMethod {

	public static void main(String[] args) {

		//to create reference to session factory
		Session session=hibernateUtils.getSessionFactory().openSession();

		//to retrieve student by using student id
		Student student1=session.get(Student.class,"std101"); //persistence state

		//to check data is present or not
		if(student1==null) 
		{
			System.out.println("no any student exists with id std101");
		}
		else
		{
			System.out.println("----------------first record------------");
			System.out.println(student1);
		}

		System.out.println("-------------------------------------------");
		System.out.println("------------ Second object ----------------");

		//calling the same query second time with another object.only once select query will be fired
		Student student2=session.get(Student.class,"std101"); //persistence state

		//to check data is present or not
		if(student2==null)
		{
			System.out.println("no any student exists with id std101");
		}
		else
		{
			System.out.println("----------------second record------------");
			System.out.println(student2);
		}

		//to close session factory
		hibernateUtils.getSessionFactory().close();

		//now student1 is in detached(disconnected) state

		//select * takes a lot time so its better to use field name
		//performance management issues will be handled automatically

	}

}
