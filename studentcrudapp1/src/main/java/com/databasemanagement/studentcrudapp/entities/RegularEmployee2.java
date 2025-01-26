package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.Entity;

@Entity
public class RegularEmployee2 extends Employee2
{
	private float salary;
	private int bonus;
	
	public RegularEmployee2(String empId, String empName, float salary, int bonus) 
	{
		super(empId, empName);
		this.salary = salary;
		this.bonus = bonus;
	}
	
	public RegularEmployee2() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RegularEmployee2(String empId, String empName) {
		super(empId, empName);
		// TODO Auto-generated constructor stub
	}
	
	//getter and setter methods
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	@Override
	public String toString() {
		return "\n------- Regular_Employee ----------\n Empid :"+super.getEmpId()+
				"\n Employee Name : "+super.getEmpName()+
				"\n salary=" + salary +
				"\n bonus=" + bonus +
				"\n--------------------------------------\n";
	}
}
