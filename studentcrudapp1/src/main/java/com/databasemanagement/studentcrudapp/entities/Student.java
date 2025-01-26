//fields should be mentioned as member variables
package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@NamedQueries({
	@NamedQuery(name="fetchStudentList",query = "from Student"),
	@NamedQuery(name="fetchStudentNames",query="select stdName from Student"),
	@NamedQuery(name="UpdateStandardAndRollAndAge",query ="update Student set standard =: x, roll =: y, age =: z where stdId =: m")
})

/*-----using @entity annotation to declare java class as entity---*/
@Entity
@Cacheable(true)
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)

//using @table annotation to set table related information.it is used to do manually
@Table(name="Student_List") //name of the table will be stored as this in database
public class Student
{
	//mapping
	//mandatory to represent one data as primary in hibernate
	//using @Id annotation to declare member variable as primary key
	@Id
	private String stdId;

	//using @Column to set column related properties
	@Column(name="Student_Name",length=300,nullable=false)
	private String stdName;

	@Column(length=30,nullable=false)
	private String standard;

	@Column(nullable=false)
	private int roll;

	@Column(nullable=false)
	private int age;

	@Column(length=400,nullable=false)
	private String address;

	//many students can be associated with one course
	@ManyToOne
	/*---course details-----*/
	private Course coursedata;


	/*------parameterized constructor-----*/
	public Student(String stdid, String stdName, String standard, int roll, int age, String address) {
		super();
		this.stdId = stdid;
		this.stdName = stdName;
		this.standard = standard;
		this.roll = roll;
		this.age = age;
		this.address = address;
//		this.coursedata = coursedata;
	}

	//default constructor
	public Student() {
		super();
	}

	//getter and setter methods
	public String getStdid() {
		return stdId;
	}
	public void setStdid(String stdid) {
		this.stdId = stdid;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Course getCoursedata() {
		return coursedata;
	}

	public void setCoursedata(Course coursedata) {
		this.coursedata = coursedata;
	}

	public Student(Course coursedata) {
		super();
		this.coursedata = coursedata;
	}

	/*---overriding toString method--*/
	//so object print in string form
	@Override
	public String toString() {
		return "---------------Student------------------"+
				"\n stdid:" + stdId +
				"\n stdName:" + stdName + 
				"\n standard:" + standard +
				"\n roll=" + roll+
				"\n age=" + age +
				"\n  address:" + address +
				"\n coursedata:" + coursedata;
	}

}
