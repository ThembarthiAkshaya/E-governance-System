package com.databasemanagement.studentcrudapp.entities;

import jakarta.persistence.*;
@Entity
@Table(name="product")
public class Product {
@Id
private String productId;

private int quantity;

private String productName;

private float price;

//default constructor
public Product() {
	super();
	// TODO Auto-generated constructor stub
}

//parameterized constructor
public Product(String productId, int quantity, String productName, float price) {
	super();
	this.productId = productId;
	this.quantity = quantity;
	this.productName = productName;
	this.price = price;
}

//getter and setter methods
public String getProductId() {
	return productId;
}
public void setProductId(String productId) {
	this.productId = productId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public float getPrice() {
	return price;
}
public void setPrice(float price) {
	this.price = price;
}

@Override
public String toString() {
	return "Product [productId=" + productId + ", quantity=" + quantity + ", productName=" + productName + ", price="
			+ price + "]";
}

}
