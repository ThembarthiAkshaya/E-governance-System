//this class is used to create field in a table so embedded
package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.*;

@Embeddable
public class Address {
	private String streetName;
	private String distirictName;
	private String statename;
	private int pinCode;

	//parameterized constructor
	public Address(String streetName, String distirictName, String statename, int pinCode) {
		super();
		this.streetName = streetName;
		this.distirictName = distirictName;
		this.statename = statename;
		this.pinCode = pinCode;
	}

	//default constructor
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	//getter and setter methods
	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getDistirictName() {
		return distirictName;
	}

	public void setDistirictName(String distirictName) {
		this.distirictName = distirictName;
	}

	public String getStatename() {
		return statename;
	}

	public void setStatename(String statename) {
		this.statename = statename;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "----------------Address----------\n streetName:" + streetName + "\n distirictName:" + distirictName + ", statename=" + statename
				+ ", pinCode=" + pinCode  ;
	}

}
