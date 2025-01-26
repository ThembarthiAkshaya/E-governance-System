package com.databasemanagement.schoolmanagement;

import java.sql.*;

import java.io.*;

public class StudentInsertion {

	public static void main(String[] args) throws IOException
	{
		
		String stdId,stdName,standard,address;
		int age,roll;

		//input of data using buffered class
		//creating object of buffered reader class
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		//input of student id
		System.out.println("Enter Student Id:");
		stdId=br.readLine();

		//input of student name
		System.out.println("enter name:");
		stdName=br.readLine();

		//input of student age
		System.out.println("enter age(in year):");
		age=Integer.parseInt(br.readLine());

		//input of student age
		System.out.println("enter roll:");
		roll=Integer.parseInt(br.readLine());

		//input of student id
		System.out.println("Enter Student standard:");
		standard=br.readLine();
		
		//input of student address
		System.out.println("Enter address:");
		address=br.readLine();

		try
		{
			//register my sql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//to create connection
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshaya","root","Akshaya8313*");
			
			//creating a Statement object,is used to send SQL commands to the database.
			PreparedStatement stmt=con.prepareStatement("insert into student values(?,?,?,?,?,?)");
			
			//setting values for the place holders in SQL query
			stmt.setString(1, stdId);
			stmt.setString(2, stdName);
			stmt.setString(3, standard);
			stmt.setInt(4, roll);
			stmt.setInt(5, age);
			stmt.setString(6, address);
			
			// indicates the number of rows affected by the query.
			int row=stmt.executeUpdate();
			
			if(row>0) {
				System.out.println("Successfully inserted");
			}
			else {
				System.out.println("not inserted");
			}
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
