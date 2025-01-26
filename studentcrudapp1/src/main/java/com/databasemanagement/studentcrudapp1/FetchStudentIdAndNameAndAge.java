package com.databasemanagement.studentcrudapp1;

import com.databasemanagement.studentcrudapp.entities.*;
import com.databasemanagement.studentcrudapp1.utils.*;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.*;

public class FetchStudentIdAndNameAndAge
{

	public static void main(String[] args)
	{
		// creating session reference
		Session session= hibernateUtils.getSessionFactory().openSession();
		/*--- creating query reference to fetch studentid,studentname and age ---*/
		Query query = session.createQuery("select new com.databasemanagement.studentcrudapp.StudentIdNameAgeDTO(stdId,stdName,age) from Student");
		/*--- executing query ----*/
		List<StudentIdNameAgeDTO> stdlist=query.list();
		if(stdlist.size() > 0)
		{
			System.out.println("----- Student data -----");
			for (StudentIdNameAgeDTO studentIdNameAgeDTO : stdlist) 
			{
				System.out.println(studentIdNameAgeDTO);
			}
		}
		else
		{
			System.out.println("No record found");
		}
		/*---closing session factory ----*/
		hibernateUtils.getSessionFactory().close();
	}

}
