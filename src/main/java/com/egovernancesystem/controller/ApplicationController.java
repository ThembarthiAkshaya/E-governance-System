package com.egovernancesystem.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import com.egovernancesystem.entities.Application;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.service.*;
import com.egovernancesystem.serviceImpl.ApplicationServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ApplicationController {

    // Member variables
    private BufferedReader br;
    private ApplicationService applicationService;

    // Constructor
    public ApplicationController() {
        try {
            // Initializing BufferedReader
            br = new BufferedReader(new InputStreamReader(System.in));

            // Initializing ApplicationService
            applicationService = new ApplicationServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*------------------------------------------------------*/
    // Method to insert a new application from user input
    public void insertApplicationFromInput() {
        Scanner sc = new Scanner(System.in);

        // Collect application information from user
        System.out.println("Enter application ID: ");
        String applicationId = sc.nextLine();

        //System.out.println("Enter application status: ");
        String status ="applied";

        System.out.println("Enter application date (yyyy-MM-dd): ");
        String dateStr = sc.nextLine();
        LocalDate applicationDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Enter citizen ID associated with the application: ");
        String citizenId = sc.nextLine();

        // Fetch Citizen by ID from the database
        Session session = HibernateUtils.getSessionFactory().openSession();
        Citizen citizen = session.get(Citizen.class, citizenId);

        if (citizen == null) {
            System.out.println("Citizen with ID " + citizenId + " not found. Application cannot be added.");
            return;
        }

        // Create Application object using user input
        Application newApplication = new Application(applicationId, applicationDate, status, citizen);

        // Call service layer to insert application
        int result = applicationService.insertApplication(newApplication);

        if (result > 0) {
            System.out.println("Application successfully inserted into the database.");
        } else {
            System.out.println("Unable to insert application into the database.");
        }
    }

    /*------------------------------------------------------*/
    // Method to get all applications
    public void getAllApplications() {
        // Creating session reference
        Session session = HibernateUtils.getSessionFactory().openSession();

        // Creating reference of Query interface to fetch all applications
        Query query = session.createQuery("from Application");

        // Executing the query
        List<Application> applicationList = query.list();

        if (applicationList.size() > 0) {
            System.out.println("------- Application details --------");
            for (Application application : applicationList) {
                System.out.println(application);
            }
        } else {
            System.out.println("No applications found.");
        }

        // Close session
        session.close();
    }

    /*------------------------------------------------------*/
    // Method to get application by ID
    public void getApplicationById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Application ID to get details: ");
        String applicationId = scanner.nextLine();

        // Call the service to get application details
        Application application = applicationService.getApplicationById(applicationId);

        if (application != null) {
            System.out.println("Application Details:");
            System.out.println("Application ID: " + application.getApplicationId());
            System.out.println("Application Date: " + application.getApplicationDate());
            System.out.println("Status: " + application.getStatus());
            System.out.println("Citizen ID: " + application.getCitizen().getCitizenId());
            System.out.println("Citizen Name: " + application.getCitizen().getCitizenName());
        } else {
            System.out.println("Application with ID " + applicationId + " not found.");
        }
    }
}