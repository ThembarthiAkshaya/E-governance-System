package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Payment;
import com.egovernancesystem.utils.HibernateUtils;

public class PaymentDAO {

	/*---- Method to insert data into payment table -----*/
	public int insertPayment(Payment payment) {
		int row = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(payment);  // Save the Payment object
			transaction.commit();   // Commit the transaction
			row = 1;                // Indicating success
		} catch (Exception e) {
			e.printStackTrace();
			row = 0;  // In case of failure
		} finally {
			session.close();  // Ensure session is closed
		}
		return row;
	}

	// Method to fetch a payment by paymentId (String type)
	public Payment getPaymentById(String paymentId) {
		Payment payment = null;
		Session session = HibernateUtils.getsFactory().openSession();
		try {
			// Fetch the payment object by paymentId
			payment = session.get(Payment.class, paymentId);  // paymentId is of type String
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();  // Ensure session is closed
		}
		return payment;  // Return null if not found
	}
	
	// Method to fetch all payments from the database
    public List<Payment> getAllPayments() {
        List<Payment> paymentList = null;
        // Create session reference
        try (Session session = HibernateUtils.getsFactory().openSession()) {
            // Create a query to fetch all Payment records
            Query<Payment> query = session.createQuery("from Payment", Payment.class);
            // Execute the query and get the result list
            paymentList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentList;
    }
}

/*
 * // Method to update payment details in the database
    public int updatePayment(Payment payment) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(payment);  // Use update() to modify the payment object
            transaction.commit();
            row = 1;  // Indicating success
            System.out.println("Payment updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;  // In case of failure
        } finally {
            session.close();  // Ensure session is closed
        }
        return row;
    }

    // Method to delete payment by paymentId (String type)
    public int deletePayment(String paymentId) {
        int result = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Fetch the payment object first to ensure it exists
            Payment payment = session.get(Payment.class, paymentId);  // Use the paymentId (String)

            if (payment != null) {
                session.delete(payment);  // Delete the payment object
                transaction.commit();     // Commit the transaction
                result = 1;               // Successful deletion
            } else {
                result = 0;  // Payment not found
            }
        } catch (Exception e) {
            transaction.rollback();  // Rollback if there is an error
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed
        }
        return result;
    }
 */
