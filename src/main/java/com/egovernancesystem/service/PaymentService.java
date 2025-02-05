package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.Payment;

public interface PaymentService {
	/*--- Method to register Student -----*/
	int insertPayment(Payment payment);

	/*------ Method to get citizen by ID ----*/
	Payment getPaymentById(String paymentId);

	 // Method to get all citizens
		    List<Payment> getAllPayments();
}

/*
 * //--- Method to update Student -----
	int updatePayment(Payment payment);

	//--- Method to delete citizen -----
	 int deletePayment(String paymentId);
 */
