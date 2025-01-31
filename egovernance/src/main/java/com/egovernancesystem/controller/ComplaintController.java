package com.egovernancesystem.controller;

import com.egovernancesystem.entities.Complaint;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.service.ComplaintService;
import com.egovernancesystem.serviceImpl.ComplaintServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class ComplaintController {

    // member variables
    private BufferedReader br;
    private ComplaintService complaintService;

    /*---- Constructor ---*/
    public ComplaintController() {
        try {
            /*---- Initializing BufferedReader object -----*/
            br = new BufferedReader(new InputStreamReader(System.in));
            /*---- Initializing ComplaintService -------*/
            complaintService = new ComplaintServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to add a new complaint from user input
    public void addComplaintFromInput() {
        Scanner sc = new Scanner(System.in);

        // Collect complaint details from the user
        System.out.println("Enter Complaint ID: ");
        String complaintId = sc.nextLine();

        System.out.println("Enter Complaint Date (yyyy-MM-dd): ");
        String complaintDateStr = sc.nextLine();
        LocalDate complaintDate = LocalDate.parse(complaintDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Enter Complaint Status: ");
        String status = sc.nextLine();

        System.out.println("Enter Complaint Description: ");
        String description = sc.nextLine();

        // Ask for Citizen ID
        System.out.println("Enter Citizen ID for this complaint: ");
        String citizenId = sc.nextLine();
        
        // Fetch Citizen details (assumed to be in the database already)
        Citizen citizen = new Citizen(); // you would fetch the actual Citizen object from the database
        citizen.setCitizenId(citizenId); // Setting just the citizenId, you would typically fetch the complete object

        Complaint newComplaint = new Complaint(complaintId, complaintDate, status, description, citizen);

        // Call service layer to insert complaint
        int result = complaintService.insertComplaint(newComplaint);

        if (result > 0) {
            System.out.println("Complaint successfully added.");
        } else {
            System.out.println("Unable to add complaint.");
        }
    }

    // Method to update an existing complaint
    public void updateComplaintFromInput() {
        Scanner sc = new Scanner(System.in);

        // Ask for Complaint ID to update
        System.out.println("Enter Complaint ID to update: ");
        String complaintId = sc.nextLine();

        // Fetch existing complaint details
        Complaint existingComplaint = complaintService.getComplaintById(complaintId);

        if (existingComplaint == null) {
            System.out.println("Complaint not found!");
            return;
        }

        // Provide options to update
        while (true) {
            System.out.println("Choose option to update:\n1. Update Status\n2. Update Complaint Date\n3. Update Description\n4. Exit");
            int option = sc.nextInt();
            sc.nextLine(); // consume newline

            if (option == 4) {
                System.out.println("Exiting update menu.");
                break;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter New Status: ");
                    String newStatus = sc.nextLine();
                    existingComplaint.setStatus(newStatus);
                    System.out.println("Status updated successfully.");
                    break;
                case 2:
                    System.out.print("Enter New Complaint Date (yyyy-MM-dd): ");
                    String newDateStr = sc.nextLine();
                    LocalDate newDate = LocalDate.parse(newDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    existingComplaint.setComplaintDate(newDate);
                    System.out.println("Complaint date updated successfully.");
                    break;
                case 3:
                    System.out.print("Enter New Description: ");
                    String newDescription = sc.nextLine();
                    existingComplaint.setDescription(newDescription);
                    System.out.println("Description updated successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    continue;
            }
        }

        // Call service to update the complaint
        int result = complaintService.updateComplaint(existingComplaint);

        if (result > 0) {
            System.out.println("Complaint updated successfully.");
        } else {
            System.out.println("Unable to update complaint.");
        }
    }

    // Method to delete a complaint by its ID
    public void deleteComplaintFromInput() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Complaint ID to delete: ");
        String complaintId = sc.nextLine();

        // Call service to delete complaint
        int result = complaintService.deleteComplaint(complaintId);

        if (result > 0) {
            System.out.println("Complaint deleted successfully.");
        } else {
            System.out.println("Complaint not found or unable to delete.");
        }
    }

    // Method to list all complaints
    public void getAllComplaints() {
        List<Complaint> complaintList = complaintService.getAllComplaints();
        if (complaintList.size() > 0) {
            System.out.println("---- List of Complaints ----");
            for (Complaint complaint : complaintList) {
                System.out.println(complaint);
            }
        } else {
            System.out.println("No complaints found.");
        }
    }


    // Method to get a complaint by its ID
    public void getComplaintById() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Complaint ID to get details: ");
        String complaintId = sc.nextLine();

        // Fetch complaint by ID
        Complaint complaint = complaintService.getComplaintById(complaintId);

        if (complaint != null) {
            System.out.println("Complaint Details:");
            System.out.println("Complaint ID: " + complaint.getCompaintId());
            System.out.println("Date: " + complaint.getComplaintDate());
            System.out.println("Status: " + complaint.getStatus());
            System.out.println("Description: " + complaint.getDescription());
            System.out.println("Citizen ID: " + complaint.getCitizen().getCitizenId());
        } else {
            System.out.println("Complaint not found.");
        }
    }
}

/*
 // Method for complaint operations ------
    public void complaintOperation() throws IOException {
        int choice, operation;
        do {
            // Displaying menu to the user 
            System.out.println("------------------------------------------");
            System.out.println("------- Complaint Management Portal --------");
            System.out.println("-------------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1. Add Complaint");
            System.out.println("2. Update Complaint");
            System.out.println("3. Delete Complaint");
            System.out.println("4. List all Complaints");
            System.out.println("5. Get Complaint by ID");
            System.out.println("--------------------------------------------");

            //Asking user to select any one operation 
            System.out.print("Select any one operation : ");
            operation = Integer.parseInt(br.readLine());
            System.out.println("-------------------------------------------");

            //Executing the task as per user's input
            switch (operation) {
                case 1:
                    addComplaintFromInput();
                    break;
                case 2:
                    updateComplaintFromInput();
                    break;
                case 3:
                    deleteComplaintFromInput();
                    break;
                case 4:
                    getAllComplaints();
                    break;
                case 5:
                    getComplaintById();
                    break;
                default:
                    System.out.println("Invalid selection");
            }

            System.out.println("---------------------------------------------------------");
         // Asking user whether he wants to continue or exit
            System.out.print("Press 0 to exit or any other number to continue : ");
            choice = Integer.parseInt(br.readLine());

        } while (choice != 0);
    }
 */
