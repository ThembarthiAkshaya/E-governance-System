package com.egovernancesystem.controller;

import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.Service;
import com.egovernancesystem.entities.ServiceRequest;
import com.egovernancesystem.service.RequestService;
import com.egovernancesystem.serviceImpl.RequestServiceImpl;
import java.util.List;
import java.util.Scanner;

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

        System.out.println("Enter Request Status (e.g., Pending, Completed): ");
        String status = sc.nextLine();

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
}
