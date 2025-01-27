package com.egovernance.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

/*--- using @Entity annotation to declare java class as entity ---*/
@Entity

/*--- using @Table annotation to set table related properties ---*/
@Table(name="complaint")

public class Complaint {

	/*--- using @Id annotation to declare data member as primary key ---*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="Complaint_Id")
	private String compaintId;

	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name="complaint_date",nullable=false)
	private LocalDate complaintDate;

	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name="status",length=50,nullable=false)
	private String status;
	
	@Column(name="description")
	private String details;
	
	/*--- using @ManyToOne indicates that each complaint is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name="citizenId") //foreign key column
	private Citizen citizen;

	//default constructor
	public Complaint() {
		super();
	}

	//parameterized constructor
	public Complaint(String compaintId, LocalDate complaintDate, String status, String details, Citizen citizen) {
		super();
		this.compaintId = compaintId;
		this.complaintDate = complaintDate;
		this.status = status;
		this.details=details;
		this.citizen = citizen;
	}

	//getter and setter methods
	public String getCompaintId() {
		return compaintId;
	}

	public void setCompaintId(String compaintId) {
		this.compaintId = compaintId;
	}

	public LocalDate getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(LocalDate complaintDate) {
		this.complaintDate = complaintDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return details;
	}

	public void setDescription(String details) {
		this.details = details;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	@Override
	public String toString() {
		return "Complaint [compaintId=" + compaintId + ", complaintDate=" + complaintDate + ", status=" + status
				+ ", details=" + details + "]";
	}
	
	
}
