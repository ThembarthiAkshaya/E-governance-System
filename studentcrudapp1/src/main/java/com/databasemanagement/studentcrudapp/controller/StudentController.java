package com.databasemanagement.studentcrudapp.controller;
import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp.serviceImpl.StudentSerivceImpl;
import com.databasemanagement.studentcrudapp.services.StudentService;
import java.io.*;
import java.util.*;
public class StudentController 
{
	//member variable
	private BufferedReader br;
	private StudentService studentservice;
	/*---- Constructor ---*/
	public StudentController() {
		/*---- Initialising BufferedReader object -----*/
		br = new BufferedReader(new InputStreamReader(System.in));
		/*---- Initialising StudentService -------*/
		studentservice = new StudentSerivceImpl();
	}
	/*------ Method for student operation ------*/
	public void studentOperation()throws IOException
	{
		int choice,operation;
		do
		{
			/*----- Displaying menu to the user ------*/
			System.out.println("------------------------------------------");
			System.out.println("------- Student Management Portal --------");
			System.out.println("-------------------------------------------");
			System.out.println("1. Register New Student");
			System.out.println("2. Update Standard, Age and Roll Number");
			System.out.println("3. Update Address");
			System.out.println("4. Display All Student");
			System.out.println("5. Display Student List Standard wise");
			System.out.println("6. Display Details of One Student");
			System.out.println("--------------------------------------------");
			/*----- Asking user to select any one operation ------*/
			System.out.print("Select any one operation : ");
			operation =Integer.parseInt(br.readLine());
			System.out.println("-------------------------------------------");
			/*---- Executing the task as per user's input -----*/
			switch(operation)
			{
			case 1: registerStudent();
			break;
			case 2: updateStandardAndAgeAndRoll();
			break;
			case 3: updateStudentAddress();
			break;
			case 4: getStudentList();
			break;
			case 5: getStudentListStandardWise();
			break;
			case 6: getStudentDetails();
			break;
			default: System.out.println("Invalid selection");
			}
			System.out.println("---------------------------------------------------------");
			/*---- Asking user whether he wants to continue or exit -----*/
			System.out.print("Press 0 to exit or any other number to continue : ");
			choice = Integer.parseInt(br.readLine());			
		}while(choice!=0);
	}
	/*------------------------------------------------------*/
	/*---- Method to register student ----*/
	public void registerStudent()throws IOException
	{
		/*----------------- Receiving data from User through keyboard------------------------*/
		System.out.println("---------- Student Data Entry -------");
    	System.out.print("Student Id : ");
    	String sid = br.readLine();
    	System.out.print("Student Name : ");
    	String sname = br.readLine();
    	System.out.print("Standard : ");
    	String standard = br.readLine();
    	System.out.print("Roll number : ");
    	int roll = Integer.parseInt(br.readLine());
    	System.out.print("Age (in year) : ");
    	int age = Integer.parseInt(br.readLine());
    	System.out.print("Address : ");
    	String address = br.readLine();
    	System.out.println("---------------------------------------------------");
    	/*----------------------Creating Student object ------------------------*/
    	Student student = new Student(sid, sname, standard, roll, age, address);
    	/*------- Calling service layer to register student -----*/
    	int row = studentservice.insertStudent(student);
    	if(row > 0)
    	{
    		System.out.println("Student data successfully inserted into database");
    	}
    	else
    	{
    		System.out.println("Unable to insert data into database");
    	}
	}
	/*---- Method to update standard,age and roll of student ----*/
	public void updateStandardAndAgeAndRoll()throws IOException
	{
		/*----------------- Receiving data from User through keyboard------------------------*/
		System.out.println("---------- Standard and Age and Roll updation-------");
    	System.out.print("Enter Student Id : ");
    	String sid = br.readLine();
    	System.out.print("Enter Standard : ");
    	String standard = br.readLine();
    	System.out.print("Roll number : ");
    	int roll = Integer.parseInt(br.readLine());
    	System.out.print("Age (in year) : ");
    	int age = Integer.parseInt(br.readLine());
    	System.out.println("---------------------------------------------------");
    	/*------- Calling service layer to update student standard, age and roll-----*/
    	int row = studentservice.updateStandardAndAgeAndRoll(sid, standard, age, roll);
    	if(row > 0)
    	{
    		System.out.println("Student data successfully updated into database");
    	}
    	else
    	{
    		System.out.println("Unable to update data into database");
    	}
	}
	/*---- Method to update address of student ----*/
	public void updateStudentAddress()throws IOException
	{
		/*----------------- Receiving data from User through keyboard------------------------*/
		System.out.println("---------- Address updation-------");
    	System.out.print("Enter Student Id : ");
    	String sid = br.readLine();
    	System.out.print("Enter Address : ");
    	String address = br.readLine();
    	System.out.println("---------------------------------------------------");
    	/*------- Calling service layer to update student address-----*/
    	int row = studentservice.updateStudentAddress(sid, address);
    	if(row > 0)
    	{
    		System.out.println("Student address successfully updated into database");
    	}
    	else
    	{
    		System.out.println("Unable to update student address into database");
    	}
	}
	/*---- Method to fetch all student data -----*/
	public void getStudentList()
	{
		/*---- calling service layer to retrieve all student data -----*/
		ArrayList<Student> studentList=studentservice.getStudentList();
		if(studentList.size() > 0)
		{
			/*---- Record found -----*/
			System.out.println("------------ Student List ------------");
			for (Student student : studentList) 
			{
				System.out.println(student);
			}
		}
		else
		{
			/*--- no record found ----*/
			System.out.println("No record found ");
		}
	}
	/*---- Method to fetch student list standardwise -----*/
	public void getStudentListStandardWise()throws IOException
	{
		/*--- Input of standard through keyboard ----*/
		System.out.print("Enter Standard : ");
		String standard = br.readLine();
		/*---- calling service layer to retrieve all student data -----*/
		ArrayList<Student> studentList=studentservice.getStudentListStandardWise(standard);
		if(studentList.size() > 0)
		{
			/*---- Record found -----*/
			System.out.println("------------ Student List for Standard : "+standard+" ------------");
			for (Student student : studentList) 
			{
				System.out.println("Student Id : "+student.getStdid());
				System.out.println("Name : "+student.getStdName());
				System.out.println("Age : "+student.getAge()+" year");
				System.out.println("Address : "+student.getAddress());
				System.out.println("Roll No. : "+student.getRoll());
				System.out.println("-------------------------------------------");
			}
		}
		else
		{
			/*--- no record found ----*/
			System.out.println("No record found ");
		}
	}
	/*---- Method to fetch student details of Particular Student -----*/
	public void getStudentDetails()throws IOException
	{
		/*--- Input of standard through keyboard ----*/
		System.out.print("Enter Student id: ");
		String stdId = br.readLine();
		/*---- calling service layer to retrieve  student data -----*/
		Student student=studentservice.getStudentDetails(stdId);
		if(student!=null)
		{
				System.out.println("Student Id : "+student.getStdid());
				System.out.println("Name : "+student.getStdName());
				System.out.println("Age : "+student.getAge()+" year");
				System.out.println("Address : "+student.getAddress());
				System.out.println("Standard : "+student.getStandard());
				System.out.println("Roll No. : "+student.getRoll());
				System.out.println("-------------------------------------------");
		}
		else
		{
			/*--- no record found ----*/
			System.out.println("No record found ");
		}
	}
}

