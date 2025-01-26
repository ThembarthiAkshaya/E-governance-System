package com.databasemanagement.schoolmanagement;

import java.io.*;
import java.sql.*;
public class StudentUpdate {
	public static void main(String[] args) throws IOException
	{
	String stdid,standard;
	int age,roll;
	/*---- Input of data Using BufferedReader class -----*/
	/*--- Creating object of BufferedReader class ------*/
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	/*---- Input of student id -----*/
	System.out.print("Enter student id : ");
	stdid= br.readLine();
	/*---- Input of standard --------*/
	System.out.print("Enter standard : ");
	standard = br.readLine();
	/*---- Input of age -----*/
	System.out.print("Enter age(in year) : ");
	age = Integer.parseInt(br.readLine());
	/*---- Input of roll number -----*/
	System.out.print("Enter roll number : ");
	roll = Integer.parseInt(br.readLine());
	System.out.println("--------------------------------------");
	try
	{
		//To register driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		//to create connection
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshaya","root","Akshaya8313*");
		//to create statement
		PreparedStatement stmt = con.prepareStatement("update student set standard=?,age=?,roll=? where stdid=?");
		
		/*--- to set data into parameter ------*/
		stmt.setString(1, standard);
		stmt.setInt(2, age);
		stmt.setInt(3, roll);
		stmt.setString(4, stdid);
		/*---------------------------------------*/
		/*--- to execute the query -----*/
		int row = stmt.executeUpdate();
		if(row > 0)
		{
			System.out.println("Student data updated successfully into table");
		}
		else
		{
			System.out.println("Unable to update student data");
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
