package com.databasemanagement.studentcrudapp1;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

import org.hibernate.*;

public class StandardAndRollUpdate {

	public static void main(String[] args) {
		
		/*------to create session reference-----*/
		Session session=hibernateUtils.getSessionFactory().openSession();

		/*--- to retrieve student record ---*/
		Student student=session.get(Student.class,"std102");

		/*---verifying student record exists or not ---*/
		if(student==null) {
			System.out.println("no record found for the student record std102");
		}
		else 
		{
			/*---record is found---*/
			System.out.println("-----------------------------------");
			System.out.println(student);
			System.out.println("--------------------------------------");

			//for changing data we have to open the transaction interface
			//creating reference of Transaction interface
			Transaction transaction=session.beginTransaction();

			/*---to set student standard ---*/
			student.setStandard("12th");

			/*--- to set new roll number ---*/
			student.setRoll(7046);

			/*---- to update data ----*/
			session.update(student);

			/*---to commit changes ---*/
			transaction.commit();

			/*--displaying updated record ----*/
			System.out.println("-------After Updation student details are:-------");
			System.out.println(student);

		}

		/*-----to close sessionFactory----*/
		hibernateUtils.getSessionFactory().close();
	}
}
