package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.egovernancesystem.entities.Appointment;
import com.egovernancesystem.entities.Service;
import com.egovernancesystem.entities.ServiceRequest;
import com.egovernancesystem.utils.HibernateUtils;

public class RequestDAO {
	// Method to insert a new complaint into the database
	public int insertRequest(ServiceRequest taxrecord) {
		int row = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(taxrecord);
			transaction.commit();
			row = 1;  // Success
		} catch (Exception e) {
			e.printStackTrace();
			row = 0;  // Failure
		} finally {
			session.close();
		}
		return row;
	}
	
	/*---- Method to update an appointment in the database -----*/
	public int updateRequest(ServiceRequest servicerequest) {
		int row = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Create an HQL query to update specific fields of the Appointment entity
			String hql = "UPDATE ServiceRequest a SET a.status = :status, a.citizen = :citizen, a.service = :service WHERE a.requestId = :requestId";

			Query query = session.createQuery(hql);
			query.setParameter("status", servicerequest.getStatus());  // Set new status
			query.setParameter("citizen", servicerequest.getCitizen());  // Set new citizen
			query.setParameter("service", servicerequest.getService());  // Set new service
			query.setParameter("requestId", servicerequest.getRequestId());  // Set requestId to identify the record

			row = query.executeUpdate();  // Execute the update query
			transaction.commit();         // Commit the transaction
		} catch (Exception e) {
			e.printStackTrace();
			row = 0;                      // In case of failure
			transaction.rollback();       // Rollback transaction in case of error
		} finally {
			session.close();              // Ensure session is closed
		}
		return row;
	}

	/*---- Method to delete an request by requestId -----*/
	public int deleteRequest(String requestId) {
		int result = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Fetch the appointment object first to ensure it exists
			ServiceRequest servicerequest = session.get(ServiceRequest.class, requestId);

			if (servicerequest != null) {
				session.delete(servicerequest);  // Delete the Appointment object
				transaction.commit();         // Commit the transaction
				result = 1;                   // Successful deletion
			} else {
				result = 0;  // Appointment not found
			}
		} catch (Exception e) {
			transaction.rollback();  // Rollback if there is an error
			e.printStackTrace();
		} finally {
			session.close();         // Ensure session is closed
		}
		return result;
	}
	
	// Method to get a request by its ID
	public ServiceRequest getServiceRequestById(String requestId) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		ServiceRequest servicerequest = session.get(ServiceRequest.class, requestId);
		session.close();
		return servicerequest;
	}
	public List<ServiceRequest> getServiceRequestByCitizen(String citizenId) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM ServiceRequest c WHERE c.citizen.citizenId = :citizenId";
		Query<ServiceRequest> query = session.createQuery(hql,ServiceRequest.class);
		query.setParameter("citizenId", citizenId);
		List<ServiceRequest> taxrecords = query.list();
		transaction.commit();
		return taxrecords;
	}
}
