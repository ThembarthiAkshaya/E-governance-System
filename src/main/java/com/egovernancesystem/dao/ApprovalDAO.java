package com.egovernancesystem.dao;

import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.egovernancesystem.entities.Appointment;
import com.egovernancesystem.entities.Approval;
import com.egovernancesystem.entities.Payment;
import com.egovernancesystem.utils.HibernateUtils;

public class ApprovalDAO
{
	/*---- Method to insert data into payment table -----*/
	public int insertApproval(Approval approval) {
		int row = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			session.save(approval);  // Save the Payment object
			transaction.commit();   // Commit the transaction
			row = 1;                // Indicating success
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			row = 0;  // In case of failure
		}
		finally {
			session.close();  // Ensure session is closed
		}
		return row;
	}
	public List<Approval> findApprovalsByEmployeeId(String employeeId) {
	    String hql = "SELECT a FROM Approval a WHERE a.employee.id = :employeeId";
	    try (Session session = HibernateUtils.getSessionFactory().openSession()) {
	        return session.createQuery(hql, Approval.class)
	                      .setParameter("employeeId", employeeId)
	                      .getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Collections.emptyList();  // Handle the exception or return empty list
	    }
	}
	/*---- Method to fetch an appointment by appointmentId -----*/
	public Approval getApprovalById(String approvalId) {
		Approval approval = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			// Fetch the appointment object by appointmentId
			approval = session.get(Approval.class, approvalId);  // appointmentId is of type String
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();  // Ensure session is closed
		}
		return approval;  // Return null if not found
	}

	/*---- Method to delete all approvals by employeeId -----*/
	public int deleteApprovalsByEmployeeId(String employeeId)
	{
		int result = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Create an HQL query to delete approvals by employeeId
			String hql = "DELETE FROM Approval a WHERE a.employee.employeeId = :employeeId";
			Query query = session.createQuery(hql);
			query.setParameter("employeeId", employeeId);  // Set the employeeId parameter
			result = query.executeUpdate();  // Execute the delete query
			transaction.commit();  // Commit the transaction
		} catch (Exception e) {
			transaction.rollback();  // Rollback transaction in case of error
			e.printStackTrace();
		} finally {
			session.close();  // Ensure session is closed
		}
		return result;  // Return the number of records deleted
	}


	/*---- Method to change the status of all approvals for a specific employeeId -----*/
	public int changeApprovalStatusByEmployeeId(String employeeId, String newStatus) {
	    Session session = HibernateUtils.getSessionFactory().openSession();
	    Transaction transaction = session.beginTransaction();
	    int result = 0;
	    try {
	        // HQL to update approval status for the given employeeId
	        String hql = "UPDATE Approval a SET a.status = :newStatus WHERE ELEMENT(a.employee).employeeId = :employeeId";
	        Query query = session.createQuery(hql);
	        query.setParameter("newStatus", newStatus);
	        query.setParameter("employeeId", employeeId);
	        
	        result = query.executeUpdate();
	        transaction.commit();
	    } catch (Exception e) {
	        transaction.rollback();
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return result;
	}

}