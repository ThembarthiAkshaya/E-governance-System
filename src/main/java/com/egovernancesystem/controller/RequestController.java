package com.egovernancesystem.controller;

import com.egovernancesystem.entities.Appointment;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.Department;
import com.egovernancesystem.entities.Service;
import com.egovernancesystem.entities.ServiceRequest;
import com.egovernancesystem.service.RequestService;
import com.egovernancesystem.serviceImpl.RequestServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RequestController {

	private RequestService requestService;

	// Constructor to initialize the RequestService
	public RequestController() {
		requestService = new RequestServiceImpl();
	}

	// Method to add a new service request from user input
	public void addServiceRequestFromInput() {
		Scanner sc = new Scanner(System.in);

		// Collect service request details from the user
		System.out.println("Enter Service Request ID: ");
		String requestId = sc.nextLine();

		//System.out.println("Enter Request Status (e.g., Pending, Completed): ");
		String status ="request has been sent";

		System.out.println("Enter Citizen ID for this service request: ");
		String citizenId = sc.nextLine();

		// Fetch Citizen details (you would typically fetch the Citizen from the database)
	Citizen citizen = new Citizen();
		citizen.setCitizenId(citizenId); // Setting just the citizenId

		System.out.println("Enter Service ID for this service request: ");
		String serviceId = sc.nextLine();

		// Fetch Service details (you would typically fetch the Service object from the database)
		Service service = new Service();
		service.setServiceId(serviceId); // Setting just the serviceId

		// Create a new ServiceRequest object
		ServiceRequest newServiceRequest = new ServiceRequest(requestId, status, citizen, service);

		// Call service layer to insert the service request
		int result = requestService.insertRequest(newServiceRequest);

		if (result > 0) {
			System.out.println("Service request successfully added.");
		} else {
			System.out.println("Unable to add service request.");
		}
	}

	public void updateRequest()
	{
		Scanner sc = new Scanner(System.in);
		Session session=HibernateUtils.getSessionFactory().openSession();
		System.out.println("Enter request ID to update: ");
		String requestId = sc.nextLine();

		// Fetch existing service request from the database
		ServiceRequest existingServiceRequest = requestService.getServiceRequestById(requestId);

		if (existingServiceRequest == null) {
			System.out.println("Request with ID " + requestId + " not found!");
			return;
		}

		System.out.println("Enter new status: ");
		String newStatus = sc.nextLine();
		existingServiceRequest.setStatus(newStatus);

		System.out.println("Enter citizen ID (existing citizen ID): ");
		String newCitizenId = sc.nextLine();

		// Fetch the Citizen entity from the database using the provided citizenId
		Citizen citizen = session.get(Citizen.class, newCitizenId);
		if (citizen == null) {
			System.out.println("Citizen with ID " + newCitizenId + " not found.");
			return;
		}

		// Set the fetched Citizen entity to the ServiceRequest object
		existingServiceRequest.setCitizen(citizen);
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();  // Start the transaction

			// Update the service request entity
			session.update(existingServiceRequest);  // This will update the ServiceRequest in the session

			// Commit the transaction to persist the changes
			transaction.commit();
			System.out.println("Service request updated successfully.");

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();  // Rollback in case of an error
			}
			e.printStackTrace();
			System.out.println("Unable to update service request.");
		} finally {
			session.close();  // Ensure session is closed
		}
	}

	/*------------ Method to delete a service ------------*/
	public void deleteRequestFromInput() {
		System.out.println("Enter service request id to delete:");
		Scanner sc=new Scanner(System.in);
		String requestId = sc.nextLine();

		// Call the service to delete the service
		int result = requestService.deleteRequest(requestId);

		if (result > 0) {
			System.out.println("Service request with ID " + requestId + " deleted successfully.");
		} else {
			System.out.println("Service request with ID " + requestId + " not found or unable to delete.");
		}
	}

	// Method to get all service requests for a specific citizen
	public void getServiceRequestsByCitizenFromInput() {
		Scanner sc = new Scanner(System.in);

		// Ask for Citizen ID
		System.out.println("Enter Citizen ID to get service requests: ");
		String citizenId = sc.nextLine();

		// Call service to get service requests by citizen ID using the instantiated requestService
		List<ServiceRequest> serviceRequests = requestService.getServiceRequestByCitizen(citizenId);

		if (serviceRequests != null && !serviceRequests.isEmpty()) {
			System.out.println("---- Service Requests for Citizen ID: " + citizenId + " ----");
			for (ServiceRequest serviceRequest : serviceRequests) {
				System.out.println("Request ID: " + serviceRequest.getRequestId());
				System.out.println("Status: " + serviceRequest.getStatus());
				System.out.println("Citizen ID: " + serviceRequest.getCitizen().getCitizenId());
				System.out.println("Service ID: " + serviceRequest.getService().getServiceId());
				System.out.println("-----------------------------------");
			}
		} else {
			System.out.println("No service requests found for Citizen ID: " + citizenId);
		}
	}

	public void getServiceRequestById() 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Service request ID to get details: ");
		String requestId = scanner.nextLine(); // Citizen ID as String

		// Call the service to get citizen details
		ServiceRequest servicerequest = requestService.getServiceRequestById(requestId);

		if (servicerequest != null) {
			System.out.println("Service Details:");
			System.out.println("status of the request:"+servicerequest.getStatus());
			System.out.println("Citizen ID: " + servicerequest.getCitizen());
			System.out.println("service id :"+servicerequest.getService());
		} else {
			System.out.println("Service request with ID " + requestId + " not found.");
		}
	}
}