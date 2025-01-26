package com.databasemanagement.studentcrudapp1;

public class StudentIdNameAgeDTO {
	private String stdId;
	private String stdName;
	private int age;
	public StudentIdNameAgeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentIdNameAgeDTO(String stdId, String stdName, int age) {
		super();
		this.stdId = stdId;
		this.stdName = stdName;
		this.age = age;
	}
	public String getStdId() {
		return stdId;
	}
	public void setStdId(String stdId) {
		this.stdId = stdId;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Sstudent Id : " + stdId + 
				"\n student Name: " + stdName + 
				"\n Age : " + age + 
				"year\n-------------------------------------\n";
	}
}
