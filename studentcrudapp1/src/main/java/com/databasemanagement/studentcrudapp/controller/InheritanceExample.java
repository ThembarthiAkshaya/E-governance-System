package com.databasemanagement.studentcrudapp.controller;
import org.hibernate.*;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;
public class InheritanceExample {

	public static void main(String[] args) {
		//creating session
		Session session = hibernateUtils.getSessionFactory().openSession();
		/*----- Transaction reference -----*/
		Transaction transaction = session.beginTransaction();
		/*---- Creating object of Employee Entity -----*/
		Employee emp1=new Employee("emp101", "Abhishek kumar");
		Employee emp2 = new Employee("emp104","Sushil kumar");
		/*---- Creating object of Contract Employee ----*/
		ContractEmployee emp3 = new ContractEmployee("emp102","Nagesh Kumar", 500, 10);
		ContractEmployee emp4 = new ContractEmployee("emp105","Rahul kumar",700,5);
		/*---- Creating object of Regular Employee -----*/
		RegularEmployee emp5 = new RegularEmployee("emp103","Rohit Kumar", 4000, 10);
		RegularEmployee emp6 = new RegularEmployee("emp106","Sushil kumar", 6000, 10);
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