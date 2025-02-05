package com.egovernancesystem.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/*--- using @Entity to declare java class as entity ---*/
@Entity

/*--- using @Table annotation to set table related properties ---*/
@Table(name="Department")
public class Department 
{
	/*--- using @Id to declare member variable as primary key ---*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="department_id",length=30)
	private String departmentId;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="name",length=30,nullable=false)
	private String departmentName;

	/*--- using @column annotation to set column related properties ---*/
	@Column(name="description",length=200)
	private String description;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	List<Service> service=new ArrayList<Service>();

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	List<Report> reports=new ArrayList<Report>();

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	List<Employee> employee=new ArrayList<Employee>();

	/*---- One department can have many appointments ----*/
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	List<Appointment> appointment=new ArrayList<Appointment>();

	//default constructor
	public Department() {
		super();
	}

	//parameterized constructor
	public Department(String departmentId, String departmentName, String description) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.description = description;
	}
	//getter and setters method
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Service> getService() {
		return service;
	}

	public void setService(List<Service> service) {
		this.service = service;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + ", description="
				+ description + "]";
	}

}
