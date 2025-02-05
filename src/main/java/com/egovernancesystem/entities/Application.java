package com.egovernancesystem.entities;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.*;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name="application")

public class Application {

	/*--- using @Id to set member variable as primary key ---*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="application_id",length=30)
	private String applicationId;
	
	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="application_date",nullable=false)
	private LocalDate applicationDate;
	
	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="status",length=50,nullable=false)
	private String status;

	/*--- using @ManyToOne indicates that each application is associated with one department ---*/
	@ManyToOne
	@JoinColumn(name = "citizenId") //foreign key column
	private Citizen citizen;

	//default constructor
	public Application() {
		super();
	}
	
	//parameterized constructor
	public Application(String applicationId, LocalDate applicationDate, String status, Citizen citizen) {
		super();
		this.applicationId = applicationId;
		this.applicationDate = applicationDate;
		this.status = status;
		this.citizen = citizen;
	}

	//getter and setter methods
	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId + ", applicationDate=" + applicationDate + ", status="
				+ status + "]";
	}

}
