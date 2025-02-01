package com.egovernancesystem.controller;

import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.Feedback;
import com.egovernancesystem.entities.ServiceRequest;
import com.egovernancesystem.service.FeedbackService;
import com.egovernancesystem.serviceImpl.FeedbackServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FeedbackController {

    private BufferedReader br;
    private FeedbackService feedbackService;

    public FeedbackController() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            feedbackService = new FeedbackServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add feedback from user input
    public void addFeedbackFromInput() throws IOException {
    	Session session = HibernateUtils.getsFactory().openSession();

        // Creating transaction reference to modify the data in the database
        Transaction transaction = session.beginTransaction();

        try {
            // Take input from the user
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter feedback id: ");
            String feedbackId = scanner.nextLine();

            System.out.println("Enter the comment:");
            String comment = scanner.nextLine();
            
            // Report id as foreign key
            System.out.println("Enter request id:");
            String requestId = scanner.nextLine();

            // Retrieve the existing ServiceRequest from the database using the requestId
            ServiceRequest existingRequest = session.get(ServiceRequest.class, requestId);

            if (existingRequest == null) {
                System.out.println("Request with ID " + requestId + " does not exist!");
                return;
            }
            
            System.out.println("Enter citizen id:");
        	String citizenId=scanner.nextLine();

        	// Retrieve the existing Citizen from the database using the citizenId
        	Citizen existingCitizen = session.get(Citizen.class, citizenId);

        	if (existingCitizen == null) {
        		System.out.println("Citizen with ID " + citizenId + " does not exist!");
        		return;
        	}

        	 System.out.println("Enter the rating(1 to 5):");
             int rating=scanner.nextInt();
             
        	// Create a new student object
        	Feedback newFeedback = new Feedback(feedbackId,rating,comment,existingCitizen,existingRequest);

        	// Save the student object
        	session.save(newFeedback);
        	
            // Commit the transaction
            transaction.commit();

            System.out.println("Aww....your feedback has sent successfully!");

        } finally {
            session.close();
        }
    }

    // Update feedback from user input
    public void updateFeedbackFromInput() throws IOException {
        System.out.print("Enter Feedback ID to update: ");
        String feedbackId = br.readLine();

        // Fetch the existing feedback
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback == null) {
            System.out.println("Feedback with ID " + feedbackId + " not found.");
            return;
        }

        // Update feedback fields
        System.out.print("Enter new Comments: ");
        String comments = br.readLine();
        feedback.setComment(comments);

        System.out.print("Enter new Rating (1 to 5): ");
        int rating = Integer.parseInt(br.readLine());
        feedback.setRating(rating);

        // Call service to update feedback
        boolean result = feedbackService.updateFeedback(feedback);
        if (result) {
            System.out.println("Feedback updated successfully.");
        } else {
            System.out.println("Unable to update feedback.");
        }
    }

    // Delete feedback from user input
    public void deleteFeedbackFromInput() throws IOException {
        System.out.print("Enter Feedback ID to delete: ");
        String feedbackId = br.readLine();

        // Call service to delete feedback
        boolean result = feedbackService.deleteFeedback(feedbackId);
        if (result) {
            System.out.println("Feedback deleted successfully.");
        } else {
            System.out.println("Unable to delete feedback.");
        }
    }

    // List all feedbacks
    private void getAllFeedbacks() {
        List<Feedback> feedbackList = feedbackService.getAllFeedbacks();
        if (feedbackList.isEmpty()) {
            System.out.println("No feedbacks found.");
        } else {
            feedbackList.forEach(System.out::println);
        }
    }

    // Get feedbacks by Citizen ID
    public void getFeedbacksByCitizen() throws IOException {
        System.out.print("Enter Citizen ID: ");
        String citizenId = br.readLine();
        List<Feedback> feedbackList = feedbackService.getFeedbacksByCitizen(citizenId);
        if (feedbackList.isEmpty()) {
            System.out.println("No feedbacks found for Citizen ID " + citizenId);
        } else {
            feedbackList.forEach(System.out::println);
        }
    }

    // Get feedback by ID
    public void getFeedbackById() throws IOException {
        System.out.print("Enter Feedback ID: ");
        String feedbackId = br.readLine();
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback != null) {
            System.out.println(feedback);
        } else {
            System.out.println("Feedback not found.");
        }
    }

    // Get feedbacks by Service Request ID
    public void getFeedbacksByServiceRequest() throws IOException {
        System.out.print("Enter Service Request ID: ");
        String serviceRequestId = br.readLine();
        List<Feedback> feedbackList = feedbackService.getFeedbacksByServiceRequest(serviceRequestId);
        if (feedbackList.isEmpty()) {
            System.out.println("No feedbacks found for Service Request ID " + serviceRequestId);
        } else {
            feedbackList.forEach(System.out::println);
        }
    }
}

/*------------
public void feedbackOperation() throws IOException {
        int choice;
        do {
            System.out.println("--------------------------------------------");
            System.out.println("------- Feedback Management Portal --------");
            System.out.println("--------------------------------------------");
            System.out.println("1. Add Feedback");
            System.out.println("2. Update Feedback");
            System.out.println("3. Delete Feedback");
            System.out.println("4. List all Feedbacks");
            System.out.println("5. List Feedbacks by Citizen");
            System.out.println("6. Get Feedback by ID");
            System.out.println("7. List Feedbacks by Service Request");
            System.out.println("--------------------------------------------");

            System.out.print("Select an operation: ");
            int operation = Integer.parseInt(br.readLine());
            switch (operation) {
                case 1:
                    addFeedbackFromInput();
                    break;
                case 2:
                    updateFeedbackFromInput();
                    break;
                case 3:
                    deleteFeedbackFromInput();
                    break;
                case 4:
                    getAllFeedbacks();
                    break;
                case 5:
                    getFeedbacksByCitizen();
                    break;
                case 6:
                    getFeedbackById();
                    break;
                case 7:
                    getFeedbacksByServiceRequest();
                    break;
                default:
                    System.out.println("Invalid selection");
            }

            System.out.println("------------------------------------------------");
            System.out.print("Press 0 to exit or any other number to continue: ");
            choice = Integer.parseInt(br.readLine());

        } while (choice != 0);
    }
 */
 
