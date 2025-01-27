package com.egovernance.entities;

import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;

/*--- using @Entity annotation to declare java class as entity ---*/
@Entity

/*--- using @Table annotation to set column related properties ---*/
@Table(name="document")

public class Document {

	/*--- using @Id annotation to declare member variable as primary key ---*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="document_id")
	private String documentId;
	
	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="document_type",length=50,nullable=false)
	private String documentType;
	
	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name="document_name",length=100,nullable=false)
	private String documentName;
	
	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name="upload_date",nullable=true)
	private LocalDate uploadDate;
	
	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
    @ManyToOne
    @JoinColumn(name="citizenId")
    private Citizen citizen;

    //default constructor
	public Document() {
		super();
	}

	//parameterized constructor
	public Document(String documentId, String documentType, String documentName,LocalDate uploadDate, Citizen citizen) {
		super();
		this.documentId = documentId;
		this.documentType = documentType;
		this.documentName = documentName;
		this.uploadDate=uploadDate;
		this.citizen = citizen;
	}

	//getter and setter methods
	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	@Override
	public String toString() {
		return "Document [documentId=" + documentId + ", documentType=" + documentType + ", documentName="
				+ documentName + ", uploadDate=" + uploadDate + "]";
	}
	
}
