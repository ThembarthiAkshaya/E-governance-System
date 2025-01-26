package com.databasemanagement.studentcrudapp1;

import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;

import com.databasemanagement.studentcrudapp1.utils.*;

public class FetchAllStudentName {

	public static void main(String[] args) {
		// to create session reference
		Session session = hibernateUtils.getSessionFactory().openSession();
		/*--- creating query reference to retrieve name of all the students ---*/
		Query query = session.createNamedQuery("fetchStudentNames");
		/*---- to execute the query ----*/
		List<String> studentNames = query.list();
		if(studentNames.size()>0)
		{
			System.out.println("----- Name of students ----");
			for (String stdname : studentNames) 
			{
				System.out.println(stdname);
			}
		}
		else
		{
			System.out.println("No data found");
		}
		/*--- To close SessionFactory -----*/
		hibernateUtils.getSessionFactory().close();
	}

}
