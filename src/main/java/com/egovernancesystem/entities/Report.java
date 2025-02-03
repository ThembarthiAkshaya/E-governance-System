package com.egovernancesystem.entities;

import java.util.List;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name="report")

public class Report {

	/*---- using @Id annotation to declare member variable as primary key ----*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="report_id",length=30)
	private String reportId;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="report_date",nullable=false)
	private LocalDate reportDate;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="report_type",nullable=false)
	private String reportType;


	/*--- Many-to-many relationship with Department ---*/
	/*--
	@ManyToMany
	@JoinTable(
			name = "department", // Join table
			joinColumns = @JoinColumn(name = "reportId"), // Foreign key in the join table referring to Report
			inverseJoinColumns = @JoinColumn(name = "departmentId") // Foreign key in the join table referring to Department
			)
	List<Department> department = new ArrayList<Department>(); 
--*/

	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name = "department_id") //foreign key column
	private Department department;

	//default constructor
	public Report() {
		super();
	}

	public Report(String reportId, LocalDate reportDate, String reportType, Department department) {
		super();
		this.reportId = reportId;
		this.reportDate = reportDate;
		this.reportType = reportType;
		this.department = department;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public LocalDate getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", reportDate=" + reportDate + ", reportType=" + reportType + "]";
	}

}
