package com.databasemanagement.studentcrudapp1.utils;
import java.sql.*;
import com.databasemanagement.studentcrudapp.config.*;
public class DatabaseConnectionUtils 
{
	//Connection object, which will hold the connection to the database. It is initially set to null.
	private static Connection connection=null;
	
	static
	{
		try 
		{
			/*----register driver for my sql-----------*/
			Class.forName("com.mysql.cj.jdbc.Driver");
           
			//creating connection
			connection=DriverManager.getConnection(DatabaseConfig.DB_URL,DatabaseConfig.USER_NAME,DatabaseConfig.PASSWORD);
			
			//calling the create table method
			createTable();
			
        }
		catch(ClassNotFoundException ce) {
			System.out.println("Driver not found");
		}
		catch(SQLException se) {
			System.out.println(se);
		}
	}
	/*----------------------------------------------------*/
	/*------------Method to return connection------------*/
	public static Connection getConnection() {
		return connection;
	}

	/*-----------method to close the connection-------------*/
	public static void closeConnection() {

		try{
			if(connection!=null) {
				connection.close();
			}
			
		}
		catch (SQLException se) {
			System.out.println(se.getMessage());
		}
	}
	
	//method to create table
	private static void createTable() {
		try {
			//create statement
			Statement stmt=connection.createStatement();
			
			//execute query to create a table
			stmt.execute("create table if not exists student(studentid varchar(30) primary key,stdName varchar(30) not null,standard varchar(30) not null,roll int not null,age int not null,address varchar(200) not null)");
			
		}
		catch(SQLException e) {
			System.out.println(e);
		}
	}
}

























