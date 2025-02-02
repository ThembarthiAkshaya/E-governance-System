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

    public int updateService(Service service) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Corrected HQL query: Removed extra comma before WHERE
            String hql = "UPDATE Service s SET s.serviceName = :serviceName, s.description = :description WHERE s.serviceId = :serviceId";
            
            Query query = session.createQuery(hql);
            query.setParameter("serviceName", service.getServiceName());  // Set new service name
            query.setParameter("description", service.getDescription()); // Set new description
            query.setParameter("serviceId", service.getServiceId());     // Set serviceId to identify the record
            
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
