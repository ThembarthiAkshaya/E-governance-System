//in hibernate insertion
package com.databasemanagement.schoolmanagement;

import java.time.LocalDate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.databasemanagement.schoolmanagement.entities.Citizen;
import com.schoolmanagement.utils.HibernateUtils;

public class InsertingCitizen {

	public static void main(String[] args) {
		Session session=HibernateUtils.getSessionFactory().openSession();

		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();

		// Create a LocalDate object for the dateOfBirth
		LocalDate dob = LocalDate.of(2002,10,23); // Replace with the desired date (year, month, day)

		Citizen citizen1=new Citizen("citizen06","bhanu","kmr","3945543212","bhanureddy@gmail.com",dob,"narasimha");

		//inserting entities into database
		session.save(citizen1);

		//commit changes to database then only that will be in persistence state
		transaction.commit(); //now citizen in the persistence state

		//closing
		HibernateUtils.getSessionFactory().close();
		//getsFactory.close();

		System.out.println("Inserted successfully");

	}

}
