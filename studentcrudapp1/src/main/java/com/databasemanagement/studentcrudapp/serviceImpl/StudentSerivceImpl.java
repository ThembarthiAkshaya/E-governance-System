package com.databasemanagement.studentcrudapp.serviceImpl;

import com.databasemanagement.studentcrudapp.services.*;
import com.databasemanagement.studentcrudapp.dao.*;
import com.databasemanagement.studentcrudapp.entities.Student;
import java.util.*;

public class StudentSerivceImpl implements StudentService
{
	private StudentDAO studentdao=null;
	
	public StudentSerivceImpl() {
		studentdao = new StudentDAO();
	}

	@Override
	public int insertStudent(Student student) {
		// TODO Auto-generated method stub
		return studentdao.insertStudent(student);
	}

	@Override
	public int updateStandardAndAgeAndRoll(String stdid, String standard, int age, int roll) {
		// TODO Auto-generated method stub
		return studentdao.updateStudentStandardAndAgeAndRoll(stdid, standard, age, roll);
	}

	@Override
	public int updateStudentAddress(String stdid, String address) {
		// TODO Auto-generated method stub
		return studentdao.updateStudentAddress(stdid, address);
	}

	@Override
	public ArrayList<Student> getStudentList() {
		// TODO Auto-generated method stub
		return studentdao.getStudentList();
	}

	@Override
	public ArrayList<Student> getStudentListStandardWise(String standard) {
		// TODO Auto-generated method stub
		return studentdao.getStudentListStandardWise(standard);
	}

	@Override
	public Student getStudentDetails(String stdId) {
		// TODO Auto-generated method stub
		return studentdao.getStudentDetails(stdId);
	}

}
