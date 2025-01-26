package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.*;

@Entity
public class Course {
	@Id
	private String courseId;
	@Column(length=100,nullable=false)
	private String courseame;
	@Column(length=100,nullable=false)
	private String description;
	@Column(nullable=false)
	private int duration;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*--parameterized constructor ----*/
	public Course(String courseId, String courseame, String description, int duration) {
		super();
		this.courseId = courseId;
		this.courseame = courseame;
		this.description = description;
		this.duration = duration;
	}

	//getter and setter methods
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseame() {
		return courseame;
	}
	public void setCourseame(String courseame) {
		this.courseame = courseame;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/*---overriding toString method--*/
	//so object print in string form
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseame=" + courseame + ", description=" + description + ", duration="
				+ duration + ", getCourseId()=" + getCourseId() + ", getCourseame()=" + getCourseame()
				+ ", getDescription()=" + getDescription() + ", getDuration()=" + getDuration() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}

