package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.Entity; 

@Entity 
public class ContractEmployee2 extends Employee2{
	private float pay_per_hour; 
	private int contract_period; 

	public ContractEmployee2(String empId, String empName, float pay_per_hour, int contract_period) 
	{ 
		super(empId, empName); 
		this.pay_per_hour = pay_per_hour; 
		this.contract_period = contract_period; 
	} 

	public ContractEmployee2() { 
		super(); 
		// TODO Auto-generated constructor stub 
	}

	public ContractEmployee2(String empId, String empName) 
	{ 
		super(empId, empName); 
		// TODO Auto-generated constructor stub 
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
	public String toString() { 
		return "\n------- Contract Employee ----------"+
				"\n Empid : "+super.getEmpId()+ 
				"\n Employee Name : "+super.getEmpName()+ 
				"pay_per_hour=" + pay_per_hour + 
				"\n contract_period=" + contract_period +  
				"\n------------------------------------------------------------------"; 
	}
}
