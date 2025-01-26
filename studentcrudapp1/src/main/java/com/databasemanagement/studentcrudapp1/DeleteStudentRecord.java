package com.databasemanagement.studentcrudapp1;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

import org.hibernate.*;

public class DeleteStudentRecord {

	public static void main(String[] args) {
		
		/*--------------create session reference----------------------*/
		Session session=hibernateUtils.getSessionFactory().openSession();
		
		/*----------------- to retrieve student record -----------------*/
		Student student=session.get(Student.class,"std102");
		
		/*---verifying student record exists or not ---*/
		if(student==null) {
			System.out.println("no record found for the student record std102");
		}
		else 
		{
			/*---- record is found ------*/
			System.out.println("---------------------------------");
			System.out.println(student);
			System.out.println("---------------------------------------");
			/*---------------------------------------------*/
			/*----- creating transaction reference --------*/
			Transaction transaction=session.beginTransaction();
			
			/*---deleting student record -----*/
			session.delete(student);
			
			/*---to commit changes ---*/
			transaction.commit();
			
			System.out.println("Above studentt's record deleted successfully");
			
			//closing the connection
			hibernateUtils.getSessionFactory().close();
		}
	}

}
