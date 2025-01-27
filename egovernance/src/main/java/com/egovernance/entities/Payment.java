package com.egovernance.entities;

import jakarta.persistence.*;

/*---- using @Entity to declare java class as entity ----*/
@Entity

/*---- using @Table annotation to set table related information ----*/
@Table(name="payment")

public class Payment {

	/*---- using @Id annotation to declare member variable as primary key ----*/
	/*--- using @Column annotation to set column related properties ---*/
	@Id
	@Column(name="Payment_Id",length=30)
	private String paymentId;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="Amount",nullable=false)
	private int amount;

	/*--- using @Column annotation to set column related properties ---*/
	@Column(name="payment_method",length=50,nullable=false)
	private String paymentMethod;

	@Column(name="status",nullable=false,length=50)
	private String status;

	/*--- using @ManyToOne indicates that each tax_record is associated with one citizen ---*/
	@ManyToOne
	@JoinColumn(name = "citizenId") //foreign key column
	private Citizen citizen;

	//default constructor
	public Payment() {
		super();
	}
	//all objects should be include
	//parameterized constructor
	public Payment(String paymentId, int amount, String paymentMethod, String status, Citizen citizen) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.citizen = citizen;
	}

	//getter and setter method

	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Citizen getCitizen() {
		return citizen;
	}
	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", amount=" + amount + ", paymentMethod=" + paymentMethod
				+ ", status=" + status + "]";
	}

}
