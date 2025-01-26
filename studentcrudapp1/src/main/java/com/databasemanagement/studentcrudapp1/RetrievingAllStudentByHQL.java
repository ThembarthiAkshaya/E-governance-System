//program on retrieving the student details
package com.databasemanagement.studentcrudapp1;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;

public class RetrievingAllStudentByHQL 
{
	public static void main(String[] args) {

		//Creating session reference
				Session session = hibernateUtils.getSessionFactory().openSession();
				//creating reference of Query interface to fetch record of all the student
				Query query = session.createQuery("from Student");
				/*--- executing the query ----*/
				List<Student> studentList = query.list();
				if(studentList.size() > 0)
				{
					/*---- student records found ----*/
					System.out.println("------- Student details --------");
					for (Student student : studentList) 
					{
						System.out.println(student);
					}
				}
				else
				{
					System.out.println("No record found");
				}
				/*---- close the sessionFactory ----*/
				hibernateUtils.getSessionFactory().close();
	}
}
