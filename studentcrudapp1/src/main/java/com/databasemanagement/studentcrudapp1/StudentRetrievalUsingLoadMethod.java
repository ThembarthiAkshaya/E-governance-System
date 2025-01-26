//program to retrieve the student details by using load method
package com.databasemanagement.studentcrudapp1;

import org.hibernate.Session;
import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

public class StudentRetrievalUsingLoadMethod {

	public static void main(String[] args) {
		//to create session 
		Session session=hibernateUtils.getSessionFactory().openSession();

		//to retrieve student by using student id
		Student student1=session.load(Student.class,"std101"); //persistence state
		
		//only once select query will be fired by using load method if we use the same project
		Student student2=session.load(Student.class, "std101");
		
        //it will throw an exception when we trying to retrieve the student details which is not present
		Student student3=session.load(Student.class,"std111");
		
		//either the object is present or not
		//it returns the data of entity if any data is present otherwise
		System.out.println(student1);
		System.out.println(student2);
		System.out.println(student3);

		//to close session factory
		hibernateUtils.getSessionFactory().close();
	}
}
