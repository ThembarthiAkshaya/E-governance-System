package com.egovernancesystem.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/*--- using @Entity to declare java class as entity ---*/
@Entity

/*--- using @Table annotation to set table related properties ---*/
@Table(name="service")

public class Service {

	/*--- using @Id to declare member variable as primary key ---*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="service_id",length=30)
	private String serviceId;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="service_name",length=30,nullable=false)
	private String serviceName;
	
	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="description",length=200,nullable=false)
	private String description;
	
	/*--- using @ManyToOne indicates that each service is associated with one department ---*/
	@ManyToOne
	@JoinColumn(name = "department_id") //foreign key column
	private Department department;

	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	 List<ServiceRequest> serviceRequest=new ArrayList<ServiceRequest>();
	
	//default constructor
	public Service() {
		super();
	}

	//parameterized constructor
	public Service(String serviceId, String serviceName, String description, Department department) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.description = description;
		this.department = department;
	}

	//getter and setter methods
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<ServiceRequest> getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(List<ServiceRequest> serviceRequest) {
		this.serviceRequest = serviceRequest;
	}
	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceName=" + serviceName + ", description=" + description
				+ "]";
	}

}
