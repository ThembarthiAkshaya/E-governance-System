package com.egovernancesystem.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name="appointment")

public class Appointment {

	/*---- using @Id annotation to declare member variable as primary key ----*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="appointment_id",length=30)
	private String appointmentId;

	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name="status",length=50)
	private String status;

	/*--- using @Column annotation to set the column related properties ----*/
	@Column(name="appointment_date")
	private LocalDate appointmentDate;

	/*--- using @ManyToOne indicates that each department is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name = "citizenId") //foreign key column
	private Citizen citizen;

	//one government employee to many appointments over the course of their career.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")  
	private Employee employee;

	//one government employee to many appointments over the course of their career.
	@ManyToOne
	@JoinColumn(name = "departmentId")  // Foreign key column
	private Department department;

	//default constructor
	public Appointment() {
		super();
	}

	//parameterized constructor
	public Appointment(String appointmentId, String status, LocalDate appointmentDate, Citizen citizen,Department department,
			Employee employee) {
		super();
		this.appointmentId = appointmentId;
		this.status = status;
		this.appointmentDate = appointmentDate;
		this.citizen = citizen;
		this.department=department;
		this.employee = employee;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
	
	public Department getDepartment() {
	    return department;
	}

	public void setDepartment(Department department) {
	    this.department = department;
	}

	public Employee getGovernmentEmployee() {
		return employee;
	}

	public void setGovernmentEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", status=" + status + ", appointmentDate="
				+ appointmentDate + "]";
	}
	
}

