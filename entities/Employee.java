package com.egovernancesystem.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name="government_employee")

public class Employee {

	/*---- using @Id annotation to declare member variable as primary key ----*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="employee_id",length=30)
	private String employeeId;

	/*--- using @Column annotation to set the column related properties ----*/
	@Column(name="employee_name",length=30,nullable=false)
	private String employeeName;

	/*--- using @Column annotation to set the column related properties ----*/
	@Column(name="role",length=27,nullable=false)
	private String role;

	/*--- using @Column annotation to set the column related properties ----*/
	@Column(name="employee_salary",length=27,nullable=false)
	private int salary;

	/*--- using @Column annotation to set the column related properties ----*/
	@Column(name="employee_password",length=30,nullable=false)
	private String password;

	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name = "departmentId") //foreign key column
	private Department department;

	@ManyToMany(mappedBy = "employee")
    Set<Approval> approvals = new HashSet<>();

	/*---- One government employee can have many appointments ----*/
	@OneToMany(mappedBy = "employee")
	private List<Appointment> appointments;


	//default constructor
	public Employee() {
		super();
	}

	//parameterized constructor
	public Employee(String employeeId, String employeeName, String role, int salary, String password,
			Department department) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.role = role;
		this.salary = salary;
		this.password = password;
		this.department = department;
	}

	//getter and setter methods

	
	
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", role=" + role + ", salary="
				+ salary + ", password=" + password + "]";
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<Approval> getStudents() {
		return approvals;
	}

	public void setStudents(Set<Approval> approvals) {
		this.approvals = approvals;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

}
