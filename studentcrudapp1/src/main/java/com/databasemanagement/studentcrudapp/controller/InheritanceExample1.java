package com.databasemanagement.studentcrudapp.controller;

import org.hibernate.*;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

public class InheritanceExample1 {

	public static void main(String[] args) {
		//creating session
		Session session = hibernateUtils.getSessionFactory().openSession();
		/*----- Transaction reference -----*/
		Transaction transaction = session.beginTransaction();
		/*---- Creating object of Employee Entity -----*/
		Employee1 emp1=new Employee1("emp101", "Abhishek kumar");
		Employee1 emp2 = new Employee1("emp104","Sushil kumar");
		/*---- Creating object of Contract Employee ----*/
		ContractEmployee1 emp3 = new ContractEmployee1("emp102","Nagesh Kumar", 500, 10);
		ContractEmployee1 emp4 = new ContractEmployee1("emp105","Rahul kumar",700,5);
		/*---- Creating object of Regular Employee -----*/
		RegularEmployee1 emp5 = new RegularEmployee1("emp103","Rohit Kumar", 4000, 10);
		RegularEmployee1 emp6 = new RegularEmployee1("emp106","Sushil kumar", 6000, 10);
		/*----- inserting into table -----*/
		session.save(emp1);
		session.save(emp2);
		session.save(emp3);
		session.save(emp4);
		session.save(emp5);
		session.save(emp6);
		/*---- commit into database ----*/
		transaction.commit();
		/*-- closing session factory -----*/
		hibernateUtils.getSessionFactory().close();
		/*------------------------------------------------------------*/
		System.out.println("----- Employees successfully inserted -------");
	}
}
