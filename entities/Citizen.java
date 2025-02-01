package com.egovernancesystem.entities;

import com.egovernancesystem.entities.Address;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name="citizen")

public class Citizen {

	/*---- using @Id annotation to declare member variable as primary key ----*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="citizen_id",length=30)
	private String citizenId;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="name",length=100,nullable=false)
	private String citizenName;

	@Embedded
	private Address address;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="contact_number",length=15)
	private Long mobileNumber;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="email",length=100,unique=true)
	private String email;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="DOB",nullable=false)
	private LocalDate dateOfBirth;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="fathername",length=255,nullable=false)
	private String fatherName;

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<Payment> payment=new ArrayList<Payment>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<Complaint> complaint=new ArrayList<Complaint>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<TaxRecord> taxrecord=new ArrayList<TaxRecord>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<Document> document =new ArrayList<Document>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<Notification> notification=new ArrayList<Notification>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<Application> application =new ArrayList<Application>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<ServiceRequest> servicerequest =new ArrayList<ServiceRequest>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<Appointment> appointment=new ArrayList<Appointment>();

	@OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
	List<Feedback> feedback=new ArrayList<Feedback>();

	//default constructor
	public Citizen() {

	}
public Citizen(String citizenId, String citizenName, Address address, Long mobileNumber, String email,
			LocalDate dateOfBirth, String fatherName) {
		super();
		this.citizenId = citizenId;
		this.citizenName = citizenName;
		this.address = address;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.fatherName = fatherName;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public String getCitizenName() {
		return citizenName;
	}

	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	public List<Complaint> getComplaint() {
		return complaint;
	}

	public void setComplaint(List<Complaint> complaint) {
		this.complaint = complaint;
	}

	public List<TaxRecord> getTaxrecord() {
		return taxrecord;
	}

	public void setTaxrecord(List<TaxRecord> taxrecord) {
		this.taxrecord = taxrecord;
	}

	public List<Document> getDocument() {
		return document;
	}

	public void setDocument(List<Document> document) {
		this.document = document;
	}

	public List<Notification> getNotification() {
		return notification;
	}

	public void setNotification(List<Notification> notification) {
		this.notification = notification;
	}

	public List<Application> getApplication() {
		return application;
	}

	public void setApplication(List<Application> application) {
		this.application = application;
	}

	public List<ServiceRequest> getServicerequest() {
		return servicerequest;
	}

	public void setServicerequest(List<ServiceRequest> servicerequest) {
		this.servicerequest = servicerequest;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Citizen [citizenId=" + citizenId + ", citizenName=" + citizenName + ", address=" + address
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", dateOfBirth=" + dateOfBirth
				+ ", fatherName=" + fatherName + "]";
	}

}
