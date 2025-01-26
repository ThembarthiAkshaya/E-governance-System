//table per class inheritance example program
package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee2")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee2
{
	@Id
	private String empId;
	private String empName;
	
	public Employee2(String empId, String empName)
	{
		super();
		this.empId = empId;
		this.empName = empName;
	}
	
	public Employee2() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getEmpId() {
		return empId;
	}
	
	public void setEmpId(String empId)
	{
		this.empId = empId;
	}
	
	public String getEmpName() 
	{
		return empName;
	}
	
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	
	@Override
	public String toString()
	{
		return "----------------Employee------------------"+
	"\nempId:" + empId +
	"\n empName:" + empName;
	}
}
