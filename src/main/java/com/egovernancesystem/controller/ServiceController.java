package com.egovernancesystem.controller;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.Department;
import com.egovernancesystem.entities.Service;
import com.egovernancesystem.service.ServiceService;
import com.egovernancesystem.serviceImpl.ServiceServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;

public class ServiceController {

	private Scanner sc;
	private ServiceService serviceService;

	/*---- Constructor ---*/
	public ServiceController() {
		try {
			sc = new Scanner(System.in);
			serviceService = new ServiceServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*------------ Method to add a new service ------------*/
	public void addServiceFromInput() {
		// Collect service information from the user
		System.out.println("Enter service id: ");
		String serviceId = sc.nextLine();

		System.out.println("Enter service name: ");
		String serviceName = sc.nextLine();

		System.out.println("Enter description: ");
		String description = sc.nextLine();

		System.out.println("Enter department ID associated with this service: ");
		String departmentId = sc.nextLine();

		// Fetch department from the database using departmentId
		Session session = HibernateUtils.getsFactory().openSession();
		Department department = session.get(Department.class, departmentId);

		if (department == null) {
			System.out.println("Department with ID " + departmentId + " not found. Service cannot be added.");
			return;
		}

		// Create Service object using user input
		Service newService = new Service(serviceId, serviceName, description, department);

		// Call service layer to add service
		int result = serviceService.insertService(newService);

		if (result > 0) {
			System.out.println("Service successfully added to the database.");
		} else {
			System.out.println("Unable to add service to the database.");
		}
	}

	/*------------ Method to update an existing service from user input ------------*/
	public int updateService(Service service) {
		int row = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = null;
		try {
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
			} else {
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
		}
		finally
		{
			session.close();  // Ensure session is closed
		}
		return row;  // Return result of the update operation
	}


	/*------------ Method to delete a service ------------*/
	public void deleteServiceFromInput() {
		System.out.println("Enter service id to delete:");
		String serviceId = sc.nextLine();

		// Call the service to delete the service
		int result = serviceService.deleteService(serviceId);

		if (result > 0) {
			System.out.println("Service with ID " + serviceId + " deleted successfully.");
		} else {
			System.out.println("Service with ID " + serviceId + " not found or unable to delete.");
		}
	}

	/*------------ Method to get all services ------------*/
	public void getAllServices() {
		List<Service> serviceList = serviceService.getAllServices();

		if (serviceList.size() > 0) {
			System.out.println("------- Service details --------");
			for (Service service : serviceList) {
				System.out.println(service);
			}
		} else {
			System.out.println("No service records found.");
		}
	}

	public void getServiceById() 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Service ID to get details: ");
		String serviceId = scanner.nextLine(); // Citizen ID as String

		// Call the service to get citizen details
		Service service = serviceService.getServiceById(serviceId);

		if (service != null) {
			System.out.println("Service Details:");
			System.out.println("Citizen ID: " + service.getServiceId());
			System.out.println("Name: " + service.getServiceName());
			System.out.println("description: "+service.getDescription());
			System.out.println("Department id :"+service.getDepartment());
		} else {
			System.out.println("Service with ID " + serviceId + " not found.");
		}
	}
}
