package com.databasemanagement.schoolmanagement.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@NamedQueries({
	@NamedQuery(name="insertCitizen",query = "insert into Citizen(citizenId,citizenName,address,mobileNumber,email,dateOfBirth,fatherName) values(:citizenId,:citizenName,:address,:mobileNumber,:email,:dateOfBirth,:fatherName)")
	//	@NamedQuery(name="fetchStudentNames",query="select stdName from Student"),
	//	@NamedQuery(name="UpdateStandardAndRollAndAge",query ="update Student set standard =: x, roll =: y, age =: z where stdId =: m")
})

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
	private String address;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="contact_number",length=15)
	private String mobileNumber;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="email",length=100,unique=true)
	private String email;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="DOB",nullable=false)
	private LocalDate dateOfBirth;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="fathername",length=255,nullable=false)
	private String fatherName;

	
	public Citizen(String citizenId, String citizenName, String address, String mobileNumber, String email,
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
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

	@Override
	public String toString() {
		return "Citizen [citizenId=" + citizenId + ", citizenName=" + citizenName + ", address=" + address
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", dateOfBirth=" + dateOfBirth
				+ ", fatherName=" + fatherName + "]";
	}

}
