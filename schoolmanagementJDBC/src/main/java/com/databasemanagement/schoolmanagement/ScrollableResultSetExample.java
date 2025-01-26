package com.databasemanagement.schoolmanagement;
import java.sql.*;
public class ScrollableResultSetExample {

	public static void main(String[] args) {

		try
		{
			//To register driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//to create connection
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshaya","root","Akshaya8313*");
			
			//to create statement
			//to make result set scrollable and updatable
			Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			/*--- to execute select query -----*/
			ResultSet result = stmt.executeQuery("Select * from student");
			
			/*---------------------------------------*/
			/*--- moving the cursor to fifth record -----*/
			result.absolute(5);
			System.out.println("------- 5th Record ----");
			/*--- fetching data using column index ----*/
			System.out.println("Student Id : "+result.getString(1));
			System.out.println("Student Name : "+result.getString(2));
			/*--- fetching data using column name ----*/
			System.out.println("Standard : "+result.getString("standard"));
			System.out.println("Roll Number : "+result.getInt("roll"));
			System.out.println("Age : "+result.getInt("age")+" year");
			System.out.println("------------------------------------------------");
			/*---------- -------------------------------------------*/
			
			/*---- To update stanadrd and age of this student ------*/
			result.updateString("standard","10th");
			result.updateInt("age", 14);
			//commit the update in database
			result.updateRow();
			System.out.println("----------------------------------------------------");
			System.out.println("-------------After Updating the record--------------");
			/*--- fetching data using column index ----*/
			System.out.println("Student Id : "+result.getString(1));
			System.out.println("Student Name : "+result.getString(2));
			/*--- fetching data using column name ----*/
			System.out.println("Standard : "+result.getString("standard"));
			System.out.println("Roll Number : "+result.getInt("roll"));
			System.out.println("Age : "+result.getInt("age")+" year");
			System.out.println("Address : "+result.getString("address"));
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
