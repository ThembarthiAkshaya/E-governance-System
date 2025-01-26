package com.databasemanagement.studentcrudapp.entities;
import jakarta.persistence.*;
@Entity
public class Faculty {

	@Id
	private String facultyId;
	private String facultyName;
	@Embedded
	private Address address;

	//one teacher can teach one course only
	@OneToOne
	private Course course;

	//parameterized constructor
	public Faculty(String facultyId, String facultyName, Address address, Course course) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.address = address;
		this.course = course;
	}

	//default constructor
	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}

	//getter and setter methods
	public String getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}

}
