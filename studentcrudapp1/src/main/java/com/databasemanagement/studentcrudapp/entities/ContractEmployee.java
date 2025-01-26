package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value="Contract_Employee")
public class ContractEmployee extends Employee
{
	private float pay_per_hour;
	private int contract_period;
	
	public ContractEmployee(String empId, String empName, float pay_per_hour,int contract_period)
	{
		super(empId, empName);
		this.pay_per_hour = pay_per_hour;
		this.contract_period = contract_period;
	}
	
	public ContractEmployee()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ContractEmployee(String empId, String empName) 
	{
		super(empId, empName);
	   //TODO Auto-generated constructor stub
	}
	
	public float getPay_per_hour()
	{
		return pay_per_hour;
	}
	
	public void setPay_per_hour(float pay_per_hour)
	{
		this.pay_per_hour = pay_per_hour;
	}
	
	public int getContract_period() 
	{
		return contract_period;
	}
	
	public void setContract_period(int contract_period)
	{
		this.contract_period = contract_period;
	}
	
	@Override
	public String toString() 
	{
		return "\n------- Contract Employee ----------\n"+
	"\nEmpid :"+super.getEmpId()+
	"\n Employee Name : "+super.getEmpName()+
	"\npay_per_hour=" + pay_per_hour +
	"\ncontract_period=" +contract_period +
	"\n------------------------------------------------------------------";
	}
}
