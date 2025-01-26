package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Employee")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Employee_Type",discriminatorType =DiscriminatorType.STRING)
@DiscriminatorValue(value="Normal_Employee")
public class Employee
{
	@Id
	private String empId;
	private String empName;
	
	public Employee(String empId, String empName)
	{
		super();
		this.empId = empId;
		this.empName = empName;
	}
	
	public Employee() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getEmpId() 
	{
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
		return "--------------- Employee -------------------"+
	"\nempId=" + empId + 
	"\nempName=" + empName;
	}
}
