package com.egovernancesystem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.Payment;
import com.egovernancesystem.service.PaymentService;
import com.egovernancesystem.serviceImpl.PaymentServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;

public class PaymentController {
	// Member variables
	private BufferedReader br;
	private PaymentService paymentService;

	// Constructor to initialize necessary objects
	public PaymentController() {
		try {
			// Initializing BufferedReader object
			br = new BufferedReader(new InputStreamReader(System.in));
			// Initializing PaymentService
			paymentService = new PaymentServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertPaymentFromInput() {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Enter payment ID: ");
			String paymentId = sc.nextLine();

			System.out.println("Enter payment amount: ");
			int amount = sc.nextInt();
			sc.nextLine();  // Consume newline

			System.out.println("Enter payment method (e.g., Credit Card, PayPal, etc.): ");
			String paymentMethod = sc.nextLine();

			System.out.println("Enter payment status (e.g., Pending, Completed, Failed): ");
			String status = sc.nextLine();

			System.out.println("Enter citizen ID for this payment: ");
			String citizenId = sc.nextLine();

			// Fetch citizen from database using citizenId
			Session session = HibernateUtils.getsFactory().openSession();
			Citizen citizen = session.get(Citizen.class, citizenId);

			if (citizen == null) {
				System.out.println("Citizen with ID " + citizenId + " does not exist!");
				session.close();
				return;
			}

			// Create new payment object
			Payment newPayment = new Payment(paymentId, amount, paymentMethod, status, citizen);

			// Call service layer to insert payment
			int result = paymentService.insertPayment(newPayment);

			if (result > 0) {
				System.out.println("Payment successfully inserted into database.");
			} else {
				System.out.println("Unable to insert payment into database.");
			}
		} catch (Exception e) {
			System.out.println("Error while inserting payment: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	// Method to get all payments
	public void getAllPayments() {
		// Creating session reference
		Session session = HibernateUtils.getsFactory().openSession();
		// Creating reference of Query interface to fetch all payments
		Query query = session.createQuery("from Payment");
		// Executing the query
		List<Payment> paymentList = query.list();
		if (paymentList.size() > 0) {
			// Payment records found
			System.out.println("------- Payment Details --------");
			for (Payment payment : paymentList) {
				System.out.println(payment);
			}
		} else {
			System.out.println("No records found");
		}
		// Close the sessionFactory
		HibernateUtils.getsFactory().close();
	}
	// Method to update an existing payment from user input
	public void updatePaymentFromInput() {
		Scanner sc = new Scanner(System.in);

		// Create reference to session factory
		Session session = HibernateUtils.getsFactory().openSession();
		// Begin a transaction
		Transaction transaction = session.beginTransaction();

		System.out.println("Enter payment ID to update:");
		String paymentId = sc.nextLine();

		// Fetch the existing payment from the database
		Payment existingPayment = session.get(Payment.class, paymentId);

		if (existingPayment == null) {
			System.out.println("Payment with ID " + paymentId + " does not exist!");
			session.close();
			return;
		}

		// Update Payment details
		while (true) {
			System.out.println("Choose option to update:\n1. Update Amount\n2. Update Payment Method\n3. Update Status\n4. Update Citizen ID\n5. Exit");
			int option = sc.nextInt();
			sc.nextLine(); // Consume newline

			if (option == 5) {
				System.out.println("Exiting update menu.");
				break;
			}

			switch (option) {
			case 1:
				System.out.print("Enter New Amount: ");
				int newAmount = sc.nextInt();
				existingPayment.setAmount(newAmount);
				System.out.println("Amount updated successfully.");
				break;

			case 2:
				System.out.print("Enter New Payment Method: ");
				String newPaymentMethod = sc.nextLine();
				existingPayment.setPaymentMethod(newPaymentMethod);
				System.out.println("Payment Method updated successfully.");
				break;

			case 3:
				System.out.print("Enter New Status: ");
				String newStatus = sc.nextLine();
				existingPayment.setStatus(newStatus);
				System.out.println("Payment Status updated successfully.");
				break;

			case 4:
				System.out.println("Enter new Citizen ID: ");
				String newCitizenId = sc.nextLine();
				Citizen newCitizen = session.get(Citizen.class, newCitizenId);
				if (newCitizen == null) {
					System.out.println("Citizen with ID " + newCitizenId + " not found.");
				} else {
					existingPayment.setCitizen(newCitizen);
					System.out.println("Citizen updated successfully.");
				}
				break;

			default:
				System.out.println("Invalid option. Please choose again.");
				continue;
			}
		}

		// Commit the transaction to save the updates
		try {
			session.update(existingPayment); // Use session.update() to ensure entity is updated
			transaction.commit();  // Commit the transaction to save changes in the database
			System.out.println("Payment data updated successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();  // Rollback in case of an error
			System.out.println("Unable to update payment data.");
		} finally {
			session.close();  // Close session after committing the transaction
		}
	}
	// Method to get a payment by its ID
	public void getPaymentById() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Payment ID to get details: ");
		String paymentId = scanner.nextLine(); // Payment ID as String

		// Call the service to get payment details
		Payment payment = paymentService.getPaymentById(paymentId);

		if (payment != null) {
			System.out.println("Payment Details:");
			System.out.println("Payment ID: " + payment.getPaymentId());
			System.out.println("Amount: " + payment.getAmount());
			System.out.println("Payment Method: " + payment.getPaymentMethod());
			System.out.println("Status: " + payment.getStatus());
			System.out.println("Citizen ID: " + payment.getCitizen().getCitizenId());
			System.out.println("Citizen Name: " + payment.getCitizen().getCitizenName());
		} else {
			System.out.println("Payment with ID " + paymentId + " not found.");
		}
	}
}

/*
 * 





    case 2:
           updatePaymentFromInput();
           break;
    case 3:
           deletePaymentFromInput();
           break;
   case 4:
           getAllPayments();
           break;

             System.out.println("2. Update Payment");
			System.out.println("3. Delete Payment");
			System.out.println("4. List of all Payments")
 */

/*-----------
//Method for managing Payment operations
	public void paymentOperation() throws IOException {
		int choice, operation;
		do {
			// Displaying menu to the user
			System.out.println("------------------------------------------");
			System.out.println("--------- Payment Management Portal --------");
			System.out.println("-------------------------------------------");
			System.out.println("Select an option:");
			System.out.println("1. Register Payment");;
			System.out.println("2. Get Payment by ID");
			System.out.println("--------------------------------------------");
			// Asking user to select any one operation
			System.out.print("Select any one operation: ");
			operation = Integer.parseInt(br.readLine());
			System.out.println("-------------------------------------------");

			// Executing the task based on user's input
			switch (operation) {
			case 1:
				insertPaymentFromInput();
				break;

			case 2:
				getPaymentById();
				break;
			default:
				System.out.println("Invalid selection");
			}
			System.out.println("---------------------------------------------------------");
			// Asking user whether they want to continue or exit
			System.out.print("Press 0 to exit or any other number to continue: ");
			choice = Integer.parseInt(br.readLine());
		} while (choice != 0);
	}
	
	// Method to delete a payment from user input
	public void deletePaymentFromInput() {
		Session session = HibernateUtils.getsFactory().openSession();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter payment ID to delete:");
		String paymentId = sc.nextLine();

		// Call the service to delete the payment
		int result = paymentService.deletePayment(paymentId);

		if (result > 0) {
			System.out.println("Payment with ID " + paymentId + " deleted successfully.");
		} else {
			System.out.println("Payment with ID " + paymentId + " not found or unable to delete.");
		}
	}
-----------*/