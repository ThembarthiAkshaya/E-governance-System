package com.egovernancesystem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.Notification;
import com.egovernancesystem.service.NotificationService;
import com.egovernancesystem.serviceImpl.NotificationServiceImpl;

public class NotificationController {

    // member variable
    private BufferedReader br;
    private NotificationService notificationService;

    /*---- Constructor ---*/
    public NotificationController() {
        try {
            /*---- Initializing BufferedReader object -----*/
            br = new BufferedReader(new InputStreamReader(System.in));
            /*---- Initializing NotificationService -------*/
            notificationService = new NotificationServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*------ Method for notification operations ------*/
    public void notificationOperation() throws IOException {
        int choice, operation;
        do {
            /*----- Displaying menu to the user ------*/
            System.out.println("------------------------------------------");
            System.out.println("------- Notification Management Portal --------");
            System.out.println("-------------------------------------------");
            System.out.println("Select an option:");
            System.out.println("1. Add Notification");
            System.out.println("2. Update Notification");
            System.out.println("3. Delete Notification");
            System.out.println("4. List all Notifications");
            System.out.println("5. List Notifications by Citizen");
            System.out.println("6. Get Notification by ID");
            System.out.println("--------------------------------------------");

            /*----- Asking user to select any one operation ------*/
            System.out.print("Select any one operation : ");
            operation = Integer.parseInt(br.readLine());
            System.out.println("-------------------------------------------");

            /*---- Executing the task as per user's input -----*/
            switch (operation) {
                case 1:
                    addNotificationFromInput();
                    break;
                case 2:
                    updateNotificationFromInput();
                    break;
                case 3:
                    deleteNotificationFromInput();
                    break;
                case 4:
                    getAllNotifications();
                    break;
                case 5:
                    getNotificationsByCitizen();
                    break;
                case 6:
                    getNotificationById();
                    break;
                default:
                    System.out.println("Invalid selection");
            }

            System.out.println("---------------------------------------------------------");
            /*---- Asking user whether he wants to continue or exit -----*/
            System.out.print("Press 0 to exit or any other number to continue : ");
            choice = Integer.parseInt(br.readLine());

        } while (choice != 0);
    }

    // Method to add a new notification from user input
    public void addNotificationFromInput() {
        Scanner sc = new Scanner(System.in);

        // Collect notification details from the user
        System.out.println("Enter Notification ID: ");
        String notificationId = sc.nextLine();

        System.out.println("Enter Notification Message: ");
        String message = sc.nextLine();

        System.out.println("Enter Notification Date (yyyy-MM-dd): ");
        String notificationDateStr = sc.nextLine();
        LocalDate notificationDate = LocalDate.parse(notificationDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Ask for Citizen ID
        System.out.println("Enter Citizen ID for this notification: ");
        String citizenId = sc.nextLine();
        
        // Fetch Citizen details (assumed to be in the database already)
        Citizen citizen = new Citizen(); // you would fetch the actual Citizen object from the database
        citizen.setCitizenId(citizenId); // Setting just the citizenId, you would typically fetch the complete object

        Notification newNotification = new Notification(notificationId, notificationDate, message, citizen);

        // Call service layer to insert notification
        int result = notificationService.insertNotification(newNotification);

        if (result > 0) {
            System.out.println("Notification successfully added.");
        } else {
            System.out.println("Unable to add notification.");
        }
    }

    // Method to update an existing notification
    public void updateNotificationFromInput() {
        Scanner sc = new Scanner(System.in);

        // Ask for Notification ID to update
        System.out.println("Enter Notification ID to update: ");
        String notificationId = sc.nextLine();

        // Fetch existing notification details
        Notification existingNotification = notificationService.getNotificationById(notificationId);

        if (existingNotification == null) {
            System.out.println("Notification not found!");
            return;
        }

        // Provide options to update
        while (true) {
            System.out.println("Choose option to update:\n1. Update Message\n2. Update Notification Date\n3. Exit");
            int option = sc.nextInt();
            sc.nextLine(); // consume newline

            if (option == 3) {
                System.out.println("Exiting update menu.");
                break;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter New Message: ");
                    String newMessage = sc.nextLine();
                    existingNotification.setMessage(newMessage);
                    System.out.println("Message updated successfully.");
                    break;
                case 2:
                    System.out.print("Enter New Notification Date (yyyy-MM-dd): ");
                    String newDateStr = sc.nextLine();
                    LocalDate newDate = LocalDate.parse(newDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    existingNotification.setNotificationDate(newDate);
                    System.out.println("Notification date updated successfully.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    continue;
            }
        }

        // Call service to update the notification
        int result = notificationService.updateNotification(existingNotification);

        if (result > 0) {
            System.out.println("Notification updated successfully.");
        } else {
            System.out.println("Unable to update notification.");
        }
    }

    // Method to delete a notification by its ID
    public void deleteNotificationFromInput() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Notification ID to delete: ");
        String notificationId = sc.nextLine();

        // Call service to delete notification
        int result = notificationService.deleteNotification(notificationId);

        if (result > 0) {
            System.out.println("Notification deleted successfully.");
        } else {
            System.out.println("Notification not found or unable to delete.");
        }
    }

    // Method to list all notifications
    public void getAllNotifications() {
        List<Notification> notificationList = notificationService.getAllNotifications();
        if (notificationList.size() > 0) {
            System.out.println("---- List of Notifications ----");
            for (Notification notification : notificationList) {
                System.out.println(notification);
            }
        } else {
            System.out.println("No notifications found.");
        }
    }

    // Method to list notifications by citizen ID
    public void getNotificationsByCitizen() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Citizen ID to fetch notifications: ");
        String citizenId = sc.nextLine();

        // Fetch notifications for the specified citizen
        List<Notification> notifications = notificationService.getNotificationsByCitizen(citizenId);

        if (notifications.size() > 0) {
            System.out.println("---- Notifications for Citizen ID: " + citizenId + " ----");
            for (Notification notification : notifications) {
                System.out.println(notification);
            }
        } else {
            System.out.println("No notifications found for Citizen ID " + citizenId);
        }
    }

    // Method to get a notification by its ID
    public void getNotificationById() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Notification ID to get details: ");
        String notificationId = sc.nextLine();

        // Fetch notification by ID
        Notification notification = notificationService.getNotificationById(notificationId);

        if (notification != null) {
            System.out.println("Notification Details:");
            System.out.println("Notification ID: " + notification.getNotificationId());
            System.out.println("Date: " + notification.getNotificationDate());
            System.out.println("Message: " + notification.getMessage());
            System.out.println("Citizen ID: " + notification.getCitizen().getCitizenId());
        } else {
            System.out.println("Notification not found.");
        }
    }
}