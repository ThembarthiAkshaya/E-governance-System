package com.egovernance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name = "feedback")

public class Feedback {

	/*---- using @Id annotation to declare member variable as primary key ----*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name = "feedback_id", length = 30)
	private String feedbackId;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name = "rating")
	private int rating;

	/*--- using @Column annotation to set the column related properties ---*/
	@Column(name = "comment", nullable = true)
	private String comment;

	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name = "citizenId") // foreign key column
	private Citizen citizen;

	/*--- using @ManyToOne indicates that each feedback is associated with one service_request ---*/
	@ManyToOne
	@JoinColumn(name = "requestId") // foreign key column
	private ServiceRequest serviceRequest;

	// default constructor
	public Feedback() {
		super();
	}

	public Feedback(String feedbackId, int rating, String comment, Citizen citizen, ServiceRequest serviceRequest) {
		super();
		this.feedbackId = feedbackId;
		this.rating = rating;
		this.comment = comment;
		this.citizen = citizen;
		this.serviceRequest = serviceRequest;
	}

	// getter and setter methods

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", rating=" + rating + ", comment=" + comment + "]";
	}

}
