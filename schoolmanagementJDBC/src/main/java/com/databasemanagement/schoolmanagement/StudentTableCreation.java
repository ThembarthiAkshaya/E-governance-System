//program is to create the database table student
package com.databasemanagement.schoolmanagement;

import java.sql.*;

public class StudentTableCreation {

	public static void main(String args[]) {
	
	try 
	{
		//register my sql driver
		//loads the MySQL JDBC driver which is necessary to establish a connection between Java and the MySQL database.
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//to establish connection to the mysql database
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshaya","root","Akshaya8313*");
		
		//creating a Statement object,is used to send SQL commands to the database.
		Statement stmt=con.createStatement();
		
		//execute() method of the Statement object to run an SQL query
		stmt.execute("create table student(stdid varchar(30) primary key,stdName varchar(30) not null,standard varchar(30) not null,roll int not null,age int not null,address varchar(200) not null)");
		
		System.out.println("Table created");
		
		//to close the connection
		con.close();
	}
	catch(ClassNotFoundException ce) {
		System.out.println(ce);
	}
	catch(SQLException se) {
		System.out.println(se);
	}
  }
}
