package com.egovernance.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*--- using @Entity to declare java class as entity ---*/
@Entity

/*--- using @Table annotation to set table related properties ---*/
@Table(name="service_request")

public class ServiceRequest {

	/*--- using @Id to declare member variable as primary key ---*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="request_id",length=30)
	private String requestId;
	
	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name="status",length=10,nullable=false)
	private String status;
	
	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name = "citizenId") //foreign key column
	private Citizen citizen;
	
	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name = "service_id") //foreign key column
	private Service service;
	
	@OneToMany(mappedBy = "serviceRequest", cascade = CascadeType.ALL)
	List<Approval> approval=new ArrayList<Approval>();
	
	@OneToMany(mappedBy = "serviceRequest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Feedback> feedback=new ArrayList<Feedback>();

	//default constructor
	public ServiceRequest() {
		super();
	}

	//parameterized constructor
	public ServiceRequest(String requestId, String status, Citizen citizen, Service service) {
		super();
		this.requestId = requestId;
		this.status = status;
		this.citizen = citizen;
		this.service = service;
	}

	//getter and setter methods
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Approval> getApproval() {
		return approval;
	}

	public void setApproval(List<Approval> approval) {
		this.approval = approval;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "ServiceRequest [requestId=" + requestId + ", status=" + status + "]";
	}

}
