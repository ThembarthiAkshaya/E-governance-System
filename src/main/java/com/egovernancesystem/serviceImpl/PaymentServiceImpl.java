package com.egovernancesystem.serviceImpl;

import java.util.List;
import com.egovernancesystem.dao.PaymentDAO;
import com.egovernancesystem.entities.Payment;
import com.egovernancesystem.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    private PaymentDAO paymentDao;

    // Constructor to initialize DAO
    public PaymentServiceImpl() {
        paymentDao = new PaymentDAO();
    }

    @Override
    public int insertPayment(Payment payment) {
        return paymentDao.insertPayment(payment);  // Call DAO to insert payment
    }

    @Override
    public Payment getPaymentById(String paymentId) {
    	 // Call DAO to get payment details by paymentId
        return paymentDao.getPaymentById(paymentId); 
    }
    @Override
    public List<Payment> getAllPayments() {
    	// Fetch all payments from DAO
        return paymentDao.getAllPayments();  
    }
}

/*
@Override
public int updatePayment(Payment payment) {
    return paymentDao.updatePayment(payment);  // Call DAO to update payment
}

@Override
public int deletePayment(String paymentId) {
    return paymentDao.deletePayment(paymentId);  // Call DAO to delete payment by paymentId
}


*/
