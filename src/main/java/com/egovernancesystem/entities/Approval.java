package com.egovernancesystem.entities;

import jakarta.persistence.*;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name="approval")

public class Approval {

	/*---- using @Id annotation to declare member variable as primary key ----*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="approval_id",length=30)
	private String approvalId;
	
	/*--- using @Column annotation to set column related properties ----*/
	@Column(name="approval_status",length=15,nullable=false)
    private String status;
	
	/*--- using @ManyToOne indicates that each approval is associated with one service request ---*/
	@ManyToOne
	@JoinColumn(name = "request_id") //foreign key column
	private ServiceRequest serviceRequest;
	
	/*--- using @ManyToOne indicates that each approval is associated with one service request ---*/
	@ManyToOne
	@JoinColumn(name = "employee_id") //foreign key column
	private Employee employee;
	
	//default constructor
	public Approval() {
		super();
	}
   
	public Approval(String approvalId, String status, ServiceRequest serviceRequest, Employee employee) {
		super();
		this.approvalId = approvalId;
		this.status = status;
		this.serviceRequest = serviceRequest;
		this.employee = employee;
	}

	public String getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(String approvalId) {
		this.approvalId = approvalId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Approval [approvalId=" + approvalId + ", status=" + status + "]";
	}
}
