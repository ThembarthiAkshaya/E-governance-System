package com.databasemanagement.schoolmanagement;

import java.io.*;
import java.sql.*;
public class StudentDeletion {

	public static void main(String[] args) throws IOException
	{
		String stdid;
		
		/*---- Input of data Using BufferedReader class -----*/
		/*--- Creating object of BufferedReader class ------*/
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		/*---- Input of student id -----*/
		System.out.print("Enter student id : ");
		stdid= br.readLine();
		System.out.println("--------------------------------------");
		try
		{
			//To register driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//to create connection
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshaya","root","Akshaya8313*");
			//to create statement
			PreparedStatement stmt = con.prepareStatement("delete from student where stdid=?");
			/*--- to set data into parameter ------*/
			stmt.setString(1, stdid);
			/*---------------------------------------*/
			/*--- to execute the query -----*/
			int row = stmt.executeUpdate();
			if(row > 0)
			{
				System.out.println("Student successfully deleted from table");
			}
			else
			{
				System.out.println("Unable to delete student data");
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


