package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Service;
import com.egovernancesystem.entities.ServiceRequest;
import com.egovernancesystem.utils.HibernateUtils;

public class ServiceDAO {

	/*---- Method to insert a service into the database -----*/
	public int insertService(Service service) {
		int row = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(service);  // Save the Service object
			transaction.commit();   // Commit the transaction
			row = 1;                // Indicating success
		} catch (Exception e) {
			e.printStackTrace();
			row = 0;                // In case of failure
		} finally {
			session.close();        // Ensure session is closed
		}
		return row;
	}

	/*---- Method to update an appointment in the database -----*/
	public int updateService(Service service) {
		int row = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Create an HQL query to update specific fields of the Appointment entity
			String hql = "UPDATE Service a SET a.serviceName = :serviceName, a.description = :description, a.department = :departmetn WHERE a.serviceId = :serviceId";

			Query query = session.createQuery(hql);
			query.setParameter("name", service.getServiceName()); 
			query.setParameter("description", service.getDescription()); 
			query.setParameter("department ", service.getDepartment());  
			query.setParameter("serviceId", service.getServiceId()); 

			row = query.executeUpdate();  // Execute the update query
			transaction.commit();         // Commit the transaction
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			row = 0;                      // In case of failure
			transaction.rollback();       // Rollback transaction in case of error
		}
		finally 
		{
			session.close();              // Ensure session is closed
		}
		return row;
	}

	/*---- Method to delete a service by serviceId -----*/
	public int deleteRequest(String requestId) {
		int result = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Fetch the service object first to ensure it exists
			ServiceRequest servicerequest = session.get(ServiceRequest.class, requestId);  // Use the serviceId (String)

			if (servicerequest != null) {
				session.delete(servicerequest);  // Delete the Service object
				transaction.commit();     // Commit the transaction
				result = 1;               // Successful deletion
			} else {
				result = 0;  // Service not found
			}
		} catch (Exception e) {
			transaction.rollback();  // Rollback if there is an error
			e.printStackTrace();
		} finally {
			session.close();         // Ensure session is closed
		}
		return result;
	}

	/*---- Method to fetch a service by serviceId -----*/
	public Service getServiceById(String serviceId) {
		Service service = null;
		Session session = HibernateUtils.getsFactory().openSession();
		try {
			// Fetch the service object by serviceId
			service = session.get(Service.class, serviceId);  // serviceId is of type String
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();  // Ensure session is closed
		}
		return service;  // Return null if not found
	}

	/*---- Method to fetch all services from the database -----*/
	public List<Service> getAllServices() {
		List<Service> serviceList = null;
		try (Session session = HibernateUtils.getsFactory().openSession()) {
			// Create a query to fetch all Service records
			Query<Service> query = session.createQuery("from Service", Service.class);
			// Execute the query and get the result list
			serviceList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serviceList;
	}
}
