package com.databasemanagement.studentcrudapp.services;
import com.databasemanagement.studentcrudapp.entities.*;
import java.util.*;
public interface StudentService 
{
	/*--- Method to register Student -----*/
	int insertStudent(Student student);
	/*---- Method to update standard roll and age -----*/
	int updateStandardAndAgeAndRoll(String stdid,String standard,int age,int roll);
	/*---- Method to update address of student ----*/
	int updateStudentAddress(String stdid,String address);
	/*---- Method to fetch all students data ----*/
	ArrayList<Student> getStudentList();
	/*----- Method to fetch student's list standard wise ----*/
	ArrayList<Student> getStudentListStandardWise(String standard);
	/*---- Method to fetch details of Particular Student ----------*/
	Student getStudentDetails(String stdId);
}
