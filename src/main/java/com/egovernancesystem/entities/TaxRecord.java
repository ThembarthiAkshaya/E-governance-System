package com.egovernancesystem.entities;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;

/*--- using @Entity annotation to declare java class as entity ---*/
@Entity

/*--- using @Table annotation to set column related properties ---*/
@Table(name="tax_record")

public class TaxRecord {

	/*--- using @Id to declare member variable as primary key ---*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="tax_id",length=30)
	private String taxId;
 
	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name="amount",nullable=false)
	private int amount;
	
	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="Financial_year",nullable=false)
	private String fiancialYear;

	/*--- using @Column properties to set column related properties ---*/
	@Column(name="status",length=50,nullable=false)
	private String status;
	
	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="DateOfSubmission",nullable=false)
	private LocalDate dateOfSubmission;

	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name="citizen_id")  //foreign key column 
	private Citizen citizen;

	//default constructor
	public TaxRecord() {
		super();
	}

	//parameterized constructor
	public TaxRecord(String taxId, String fiancialYear, String status,int amount,LocalDate dateOfSubmission, Citizen citizen) {
		super();
		this.taxId = taxId;
		this.fiancialYear = fiancialYear;
		this.status = status;
		this.amount=amount;
		this.dateOfSubmission=dateOfSubmission;
		this.citizen = citizen;
	}

	//getter and setter methods
	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getFiancialYear() {
		return fiancialYear;
	}

	public void setFiancialYear(String fiancialYear) {
		this.fiancialYear = fiancialYear;
	}

	public LocalDate getDateOfSubmission() {
		return dateOfSubmission;
	}

	public void setDateOfSubmission(LocalDate dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	@Override
	public String toString() {
		return "TaxRecord [taxId=" + taxId + ", amount=" + amount + ", fiancialYear=" + fiancialYear + ", status="
				+ status + ", dateOfSubmission=" + dateOfSubmission + "]";
	}
	
	
}
