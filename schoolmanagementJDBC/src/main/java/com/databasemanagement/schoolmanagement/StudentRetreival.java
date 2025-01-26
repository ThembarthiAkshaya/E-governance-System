package com.databasemanagement.schoolmanagement;

import java.sql.*;

public class StudentRetreival {

	public static void main(String[] args) {
		try
		{
			//To register driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//to create connection
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshaya","root","Akshaya8313*");
			
			//to create statement
			Statement stmt= con.createStatement();
			
			/*--- to execute select query -----*/
			ResultSet result = stmt.executeQuery("Select * from student");
			
			/*---------------------------------------*/
			/*--- fetching data from result set -----*/
			while(result.next())
			{
				/*--- fetching data using column index ----*/
				System.out.println("Student Id : "+result.getString(1));
				System.out.println("Student Name : "+result.getString(2));
				/*--- fetching data using column name ----*/
				System.out.println("Standard : "+result.getString("standard"));
				System.out.println("Roll Number : "+result.getInt("roll"));
				System.out.println("Age : "+result.getInt("age")+" year");
				System.out.println("Address : "+result.getString("address"));
				System.out.println("------------------------------------------------");
			}
			//close the connection
			con.close();
		}
		catch(ClassNotFoundException ce)
		{
			System.out.println(ce);
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}

}
