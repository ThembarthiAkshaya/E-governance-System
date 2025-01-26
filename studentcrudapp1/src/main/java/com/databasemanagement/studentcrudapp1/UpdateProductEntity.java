package com.databasemanagement.studentcrudapp1;
import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

import org.hibernate.*;
import org.hibernate.query.Query;
import java.io.*;
public class UpdateProductEntity {

	public static void main(String[] args) throws IOException{
		// Creating object of BufferedReader class to receive input of data through keyboard
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter product id : ");
		String pid = br.readLine();
		System.out.print("Enter product name : ");
		String pname = br.readLine();
		System.out.print("Enter quantity: ");
		int quantity = Integer.parseInt(br.readLine());
		System.out.print("Enter price(in rupees) : ");
		int price = Integer.parseInt(br.readLine());
		System.out.println("------------------------------------");
		/*-----------------------------------------------------------------------*/
		//creating session reference
		Session session = hibernateUtils.getSessionFactory().openSession();
		/*--- Creating reference of Transaction -------*/
		Transaction transaction = session.beginTransaction();
		//creating reference of Query interface ----
		Query query = session.createQuery("update Product set productName =: x, quantity =: y, price =: z where productId =: m");
		/*---- setting data into the parameter -----*/
		query.setParameter("x",pname);
		query.setParameter("y", quantity);
		query.setParameter("z",price);
		query.setParameter("m",pid);
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
