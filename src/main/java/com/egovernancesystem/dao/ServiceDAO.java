package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Service;
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

	public int updateService(Service service) 
	{
		int row = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = null;

		try 
		{
			transaction = session.beginTransaction();  // Start the transaction

			// Fetch the existing service entity to update it
			Service existingService = session.get(Service.class, service.getServiceId());

			if (existingService != null)
			{
				// Update the fields of the existing service entity
				existingService.setServiceName(service.getServiceName());
				existingService.setDescription(service.getDescription());
				existingService.setDepartment(service.getDepartment());

				// Update the service in the session
				session.update(existingService); 

				// Commit the transaction to persist the changes
				transaction.commit();  
				row = 1;  // Success
			} 
			else 
			{
				row = 0;  // Service not found
			}
		} 
		catch (Exception e)
		{
			if (transaction != null)
			{
				transaction.rollback();  // Rollback in case of an error
			}
			e.printStackTrace();
			row = 0;  // Failure
		} finally {
			session.close();  // Ensure session is closed
		}
		return row;  // Return result of the update operation
	}



	/*---- Method to delete a service by serviceId -----*/
	public int deleteService(String serviceId) {
		int result = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Fetch the service object first to ensure it exists
			Service service = session.get(Service.class, serviceId);  // Use the serviceId (String)

			if (service != null) {
				session.delete(service);  // Delete the Service object
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
