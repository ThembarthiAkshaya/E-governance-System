package com.egovernance.entities;

import java.util.ArrayList;
import java.util.List;
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
	@JoinColumn(name = "requestId") //foreign key column
	private ServiceRequest serviceRequest;
	
	/*--- Many-to-many relationship with Department ---*/
	@ManyToMany
	@JoinTable(
			name = "employee_approval", // Join table
			joinColumns = @JoinColumn(name = "approvalId"),
			inverseJoinColumns = @JoinColumn(name = "employeeId")// Foreign key in the join table referring to Department
			)
	List<Employee> employee=new ArrayList<Employee>();

	//default constructor
	public Approval() {
		super();
	}

	public Approval(String approvalId, String status, ServiceRequest serviceRequest, List<Employee> employee) {
		super();
		this.approvalId = approvalId;
		this.status = status;
		this.serviceRequest = serviceRequest;
		this.employee = employee;
	}
   
	//getter and setter methods
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

	public ServiceRequest getServicerequest() {
		return serviceRequest;
	}

	public void setServicerequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Approval [approvalId=" + approvalId + ", status=" + status + "]";
	}

}
