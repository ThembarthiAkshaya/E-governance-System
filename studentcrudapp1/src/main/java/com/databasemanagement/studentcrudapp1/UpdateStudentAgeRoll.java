//execute update method will be used
package com.databasemanagement.studentcrudapp1;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

import org.hibernate.*;
import org.hibernate.query.Query;
import java.io.*;

public class UpdateStudentAgeRoll {

	public static void main(String[] args) throws IOException {
		// Creating object of BufferedReader class to receive input of data through keyboard
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter Student id : ");
				String sid = br.readLine();
				System.out.print("Enter standard : ");
				String std = br.readLine();
				System.out.print("Enter roll number : ");
				int rollNo = Integer.parseInt(br.readLine());
				System.out.print("Enter Age(in year) : ");
				int age = Integer.parseInt(br.readLine());
				System.out.println("------------------------------------");
				/*-----------------------------------------------------------------------*/
				//creating session reference
				Session session = hibernateUtils.getSessionFactory().openSession();
				/*--- Creating reference of Transaction -------*/
				Transaction transaction = session.beginTransaction();
				//creating reference of Query interface ----
				Query query = session.createQuery("update Student set standard =: x, roll =: y, age =: z where stdId =: m");
				/*---- setting data into the parameter -----*/
				query.setParameter("x",std);
				query.setParameter("y", rollNo);
				query.setParameter("z",age);
				query.setParameter("m",sid);
				/*------------------------------------------------------------*/
				/*---- to execute update query -----*/
				int row = query.executeUpdate();
				/*--- to commit changes into database ----*/
				transaction.commit();
				if(row > 0)
				{
					System.out.println("Data Successfully gets updated");
				}
				else
				{
					System.out.println("Unable to update data");
				}
				/*---- to close session factory ------*/
				hibernateUtils.getSessionFactory().close();
	}

}
