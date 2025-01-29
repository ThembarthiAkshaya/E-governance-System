package com.egovernancesystem.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name="notification")

public class Notification {
	
	/*---- using @Id annotation to declare member variable as primary key ----*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="notification_id",length=30)
	private String notificationId;

	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name="notification_date",nullable=true)
	private LocalDate notificationDate;
	
	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="message",length=200,nullable=true)
	private String message;
	
	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name = "citizenId") //foreign key column
	private Citizen citizen;

	//default constructor
	public Notification() {
		super();
	}

	//parameterized constructor 
	public Notification(String notificationId, LocalDate notificationDate, String message, Citizen citizen) {
		super();
		this.notificationId = notificationId;
		this.notificationDate = notificationDate;
		this.message = message;
		this.citizen = citizen;
	}

	//getter and setter methods
	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public LocalDate getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(LocalDate notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + ", notificationDate=" + notificationDate
				+ ", message=" + message + "]";
	}
	
}
