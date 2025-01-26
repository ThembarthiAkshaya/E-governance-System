//table per class inheritance example program
package com.databasemanagement.studentcrudapp.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name="Employee1")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee1 {
	@Id
	private String empId;
	private String empName;
	public Employee1(String empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}
	public Employee1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + "]";
	}
}
