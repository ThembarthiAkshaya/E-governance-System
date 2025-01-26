package com.databasemanagement.studentcrudapp.dao;

import java.sql.*;
import java.util.*;
import com.databasemanagement.studentcrudapp.entities.Student;
import com.databasemanagement.studentcrudapp1.utils.DatabaseConnectionUtils;

public class StudentDAO 
{
	/*---- Method to insert data into student table -----*/
	public int insertStudent(Student student)
	{
		int row=0;
		try
		{
			Connection con = DatabaseConnectionUtils.getConnection();
				/*--- Creating Prepared statement -----*/
				PreparedStatement stmt = con.prepareStatement("insert into student values(?,?,?,?,?,?)");
				/*------------------------------------------------------------------------------------------*/
				/*---- Setting data into prepared statement -----*/
				stmt.setString(1,student.getStdid());
				stmt.setString(2, student.getStdName());
				stmt.setString(3, student.getStandard());
				stmt.setInt(4, student.getRoll());
				stmt.setInt(5, student.getAge());
				stmt.setString(6, student.getAddress());
				/*------------------------------------------------*/
				/*---- to execute query-------*/
				row = stmt.executeUpdate();
				return row;
		}
		catch (SQLException e) {
			System.out.println(e);
			return row;
		}
	}
	/*---- Method to update standard,roll and age of student -----*/
	public int updateStudentStandardAndAgeAndRoll(String stdid,String standard,int age,int roll)
	{
		int row=0;
		try
		{
			Connection con = DatabaseConnectionUtils.getConnection();
				/*--- Creating Prepared statement -----*/
				PreparedStatement stmt = con.prepareStatement("update student set standard=?,roll=?,age=? where stdid = ?");
				/*------------------------------------------------------------------------------------------*/
				/*---- Setting data into prepared statement -----*/
				stmt.setString(1,standard);
				stmt.setInt(2, roll);
				stmt.setInt(3,age);
				stmt.setString(4, stdid);
				/*------------------------------------------------*/
				/*---- to execute query-------*/
				row = stmt.executeUpdate();
				return row;		
		}
		catch (SQLException e) {
			System.out.println(e);
			return row;
		}
	}
	/*---- Method to update address of student -----*/
	public int updateStudentAddress(String stdid,String address)
	{
		int row=0;
		try
		{
				Connection con = DatabaseConnectionUtils.getConnection();
				/*--- Creating Prepared statement -----*/
				PreparedStatement stmt = con.prepareStatement("update student set address=? where stdid = ?");
				/*------------------------------------------------------------------------------------------*/
				/*---- Setting data into prepared statement -----*/
				stmt.setString(1,address);
				stmt.setString(2, stdid);
				/*------------------------------------------------*/
				/*---- to execute query-------*/
				row = stmt.executeUpdate();
				return row;		
		}
		catch (SQLException e) {
			System.out.println(e);
			return row;
		}
	}
	/*----- Method for fetching all student List -----*/
	public ArrayList<Student> getStudentList()
	{
		/*---- creating blank student list ---*/
		ArrayList<Student> studentList=new ArrayList<Student>();
		try
		{
			/*------------------------------------------------*/
			Connection con=DatabaseConnectionUtils.getConnection();
			/*----- Creating Statement -----*/
			Statement stmt=con.createStatement();
			/*---- executing query ----*/
			ResultSet result=stmt.executeQuery("select * from student");
			/*---- Traversing result set -----*/
			while(result.next())
			{
				
				/*--- Creating student object -----*/
				Student student=new Student();
				/*--- setting data into student object from resultset -----*/
				student.setStdid(result.getString("stdid"));
				student.setStdName(result.getString("stdname"));
				student.setStandard(result.getString("standard"));
				student.setRoll(result.getInt("roll"));
				student.setAge(result.getInt("age"));
				student.setAddress(result.getString("address"));
				/*-----------------------------------------------*/
				/*---- Inserting this student into student list -----*/
				studentList.add(student);
			}
			/*--- return the array list ----*/
			return studentList;
		}catch (SQLException e)
		{
			System.out.println(e);
			return studentList;
		}
	}
	
	/*----- Method for fetching student List of particular standard -----*/
	public ArrayList<Student> getStudentListStandardWise(String standard)
	{
		/*---- creating blank student list ---*/
		ArrayList<Student> studentList=new ArrayList<Student>();
		try
		{
			/*------------------------------------------------*/
			Connection con=DatabaseConnectionUtils.getConnection();
			/*----- Creating Statement -----*/
			PreparedStatement stmt=con.prepareStatement("select * from student where standard = ?");
			/*---- Setting data into query parameter ------*/
			stmt.setString(1, standard);
			/*---- executing query ----*/
			ResultSet result=stmt.executeQuery();
			/*---- Traversing result set -----*/
			while(result.next())
			{
				
				/*--- Creating student object -----*/
				Student student=new Student();
				/*--- setting data into student object from resultset -----*/
				student.setStdid(result.getString("stdid"));
				student.setStdName(result.getString("stdname"));
				student.setStandard(result.getString("standard"));
				student.setRoll(result.getInt("roll"));
				student.setAge(result.getInt("age"));
				student.setAddress(result.getString("address"));
				/*-----------------------------------------------*/
				/*---- Inserting this student into student list -----*/
				studentList.add(student);
			}
			/*--- return the array list ----*/
			return studentList;
		}catch (SQLException e)
		{
			System.out.println(e);
			return studentList;
		}
	}
	/*----- Method for fetching details of Particular Student -----*/
	public Student getStudentDetails(String stdId)
	{
		Student student=new Student();
		try
		{
			/*------------------------------------------------*/
			Connection con=DatabaseConnectionUtils.getConnection();
			/*----- Creating Statement -----*/
			PreparedStatement stmt=con.prepareStatement("select * from student where stdid = ?");
			/*---- Setting data into query parameter ------*/
			stmt.setString(1, stdId);
			/*---- executing query ----*/
			ResultSet result=stmt.executeQuery();
			/*---- Traversing result set -----*/
			while(result.next())
			{
				
				
				/*--- setting data into student object from resultset -----*/
				student.setStdid(result.getString("stdid"));
				student.setStdName(result.getString("stdname"));
				student.setStandard(result.getString("standard"));
				student.setRoll(result.getInt("roll"));
				student.setAge(result.getInt("age"));
				student.setAddress(result.getString("address"));
				/*-----------------------------------------------*/
				
			}
			/*--- return the array list ----*/
			return student;
		}catch (SQLException e)
		{
			System.out.println(e);
			return student;
		}
	}
}
