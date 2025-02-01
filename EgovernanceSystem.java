package com.egovernancesystem;

import java.util.Scanner;
import com.egovernancesystem.controller.*;

public class EgovernanceSystem 
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------Welcome to e-governance system services---------------");
		System.out.println("Select your role:");
		System.out.println("1. Citizen 2. Department Representative 3. Employee");
		int role = sc.nextInt();
		try {
			switch (role) {
			case 1:
				handleCitizenOperations(sc);
				break;
			case 2:
				handleDepartmentOperations(sc);
				break;
			case 3:
				// Add Employee functionality if needed
				break;
			default:
				System.out.println("Sorry, You have chosen an Invalid option.");
			}
		} catch (Exception e) {
			System.out.println("Error while handling the role selection: " + e.getMessage());
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
	public static void handleCitizenOperations(Scanner sc)
	{
		boolean exit = false;
		try 
		{
			while (!exit)
			{
				System.out.println("\n--------------- Citizen Menu ---------------");
				System.out.println("1. Citizen options");
				System.out.println("2. Payment options");
				System.out.println("3. Document options");
				System.out.println("4. Notification options");
				System.out.println("5. Complaint menu");
				System.out.println("6. Application menu");
				System.out.println("7. Tax record menu");
				System.out.println("8. Feedback menu");
				System.out.println("9. Service request menu");
				System.out.println("10. Appointment menu");
				System.out.println("0. Exit");
				System.out.print("Enter your choice: ");
				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline left by nextInt()

				switch (choice) {
				case 1:
					citizenMenu(sc); 
					break;
				case 2:
					paymentMenu(sc);
					break;
				case 3:
					documentMenu(sc);
					break;
				case 4:
					notificationMenu(sc);
					break;
				case 5:
					complaintMenu(sc);
					break;
				case 6:
					applicationMenu(sc);
					break;
				case 7:
					taxMenu(sc);
					break;
				case 8:
					feedbackMenu(sc);
					break;
				case 9:
					serviceRequestMenu(sc);
					break;
				case 10:
					appointmentMenu(sc);
					break;
				case 0:
					System.out.println("Exiting Citizen Menu...");
					exit = true; // Set exit flag to true to exit from the loop
					break;
				default:
					System.out.println("Invalid choice! Please select again.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void citizenMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Citizen Actions ---------------");
				System.out.println("1. View Profile");
				System.out.println("2. Update Profile");
				System.out.println("3. Delete Profile");
				System.out.println("4. Register Citizen");
				System.out.println("5. View all citizens");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				CitizenController citizenController = new CitizenController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					citizenController.getCitizenById();
					break;
				case 2:
					citizenController.updateCitizenFromInput();
					break;
				case 3:
					citizenController.deleteCitizenFromInput();
					break;
				case 4:
					citizenController.insertCitizenFromInput();
					break;
				case 5:
					citizenController.getAllCitizen();  // Show all citizens
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Citizen menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void paymentMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Payment Actions ---------------");
				System.out.println("1. Make Payment");
				System.out.println("2. Check Payment Status");
				System.out.println("3. View All Payments");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				PaymentController paymentController = new PaymentController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					paymentController.insertPaymentFromInput();;
					break;
				case 2:
					paymentController.getPaymentById();
					break;
				case 3:
					paymentController.getAllPayments();
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Payement menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void documentMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Document menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Document Actions ---------------");
				System.out.println("1. Upload Document");
				System.out.println("2. Delete Document");
				System.out.println("3. Update Document");
				System.out.println("4. View All Documents");
				System.out.println("5. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				DocumentController documentController = new DocumentController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					documentController.addDocumentFromInput();;
					break;
				case 2:
					documentController.deleteDocumentFromInput();
					break;
				case 3:
					documentController.updateDocumentFromInput();
					break;
				case 4:
					documentController.getAllDocuments();
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Document menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void notificationMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Payment Actions ---------------");
				System.out.println("1. View notification");
				System.out.println("2. Delete notification");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				NotificationController notificationController = new NotificationController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					notificationController.getNotificationById();;
					break;
				case 2:
					notificationController.deleteNotificationFromInput();;
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Notification menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void complaintMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Complaint Actions ---------------");
				System.out.println("1. Submit Complaint");
				System.out.println("2. View Complaints");
				System.out.println("3.View all compalints of a citizen");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				ComplaintController complaintController = new ComplaintController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					complaintController.addComplaintFromInput();;
					break;
				case 2:
					complaintController.getAllComplaints();
					break;
				case 3:
					complaintController.getComplaintsByCitizenFromInput();
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Complaint menu: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void feedbackMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Feedback Actions ---------------");
				System.out.println("1. Submit feedback");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				FeedbackController feedbackController = new FeedbackController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					feedbackController.addFeedbackFromInput();
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in feedback menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void applicationMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Applicaiton Actions ---------------");
				System.out.println("1. Make Application");
				System.out.println("2. Check Application Status");
				System.out.println("3. View All Applications");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				ApplicationController applicationController = new ApplicationController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					applicationController.insertApplicationFromInput();;
					break;
				case 2:
					applicationController.getApplicationById();
					break;
				case 3:
					applicationController.getAllApplications();
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Application menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void taxMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Tax Actions ---------------");
				System.out.println("1. Pay tax");
				System.out.println("2. View Tax Record History");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				TaxRecordController taxrecordController = new TaxRecordController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					taxrecordController.addTaxRecordFromInput();;
					break;
				case 2:
					taxrecordController.getTaxRecordsByCitizenFromInput();
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Tax Record menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void serviceRequestMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Service Request Actions ---------------");
				System.out.println("1. submit service request");
				System.out.println("2. view service request status");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				RequestController requestController = new RequestController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					requestController.addServiceRequestFromInput();
					break;
				case 2:
					requestController.getServiceRequestsByCitizenFromInput();
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Service Request menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	public static void appointmentMenu(Scanner sc) {
		try {
			// Infinite loop to keep showing the Citizen menu until "Exit" is selected
			while (true) {
				System.out.println("--------------- Appointment Actions ---------------");
				System.out.println("1. Schedule Appointment");
				System.out.println("2. Update Appointment Details");
				System.out.println("3. Cancel Appointment");
				System.out.println("4.View Appointment Details");
				System.out.println("5. View all appointments");
				System.out.println("0. Exit to main menu");
				System.out.print("Choose one option from the above list: ");
				int option = sc.nextInt();
				sc.nextLine();  // Consume newline character left by nextInt()

				AppointmentController appointmentController = new AppointmentController(); // Correcting typo in variable name

				switch (option) {
				case 1:
					appointmentController.scheduleAppointment();
					break;
				case 2:
					appointmentController.updateAppointment();
					break;
				case 3:
					appointmentController.cancelAppointment();
					break;
				case 4:
					appointmentController.getAppointmentById();
					break;
				case 5:
					appointmentController.getAllAppointments();  // Show all citizens
					break;
				case 0:
					System.out.println("Exiting to main menu...");
					return;  // Exit Citizen Menu and return to main menu
				default:
					System.out.println("Invalid option selected.");
				}
			}
		} catch (Exception e) {
			System.out.println("Error in Appointment menu: " + e.getMessage());
			e.printStackTrace();
		}
	}
	// Menu for Department Representative
	private static void handleDepartmentOperations(Scanner sc) {
		while (true) {
			System.out.println("\n------------------ Department Representative Menu ------------------");
			System.out.println("1. Manage Reports");
			System.out.println("2. Manage Employees");
			System.out.println("3. Manage Services");
			System.out.println("4. Manage Appointments");
			System.out.println("5. Exit");
			System.out.print("Select an option: ");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				manageReports(sc);
				break;
			case 2:
				manageEmployees(sc);
				break;
			case 3:
				manageServices(sc);
				break;
			case 4:
				manageAppointments(sc);
				break;
			case 5:
				System.out.println("Exiting Department Representative menu.");
				return; // Exit from the department menu
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	// Function to handle Report Management
	private static void manageReports(Scanner sc) {
		try {
			while(true) {
				System.out.println("\n------------------ Report Management ------------------");
				System.out.println("1. Submit a Report");
				System.out.println("2. View Reports");
				System.out.println("3. Update a Report");
				System.out.println("4. Delete a Report");
				System.out.print("Select an option: ");
				int option = sc.nextInt();
				ReportController reportcontroller=new ReportController();
				switch (option) {
				case 1:
					reportcontroller.submitReportFromInput();
					break;
				case 2:
					reportcontroller.getAllReports();
					break;
				case 3:
					reportcontroller.updateReportFromInput();
					break;
				case 4:
					reportcontroller.deleteReportFromInput();
					break;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			} 
		}
		catch (Exception e) {
			System.out.println("Error while managing reports: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Function to handle Employee Management
	private static void manageEmployees(Scanner sc) {
		try {
			System.out.println("\n------------------ Employee Management ------------------");
			System.out.println("1. Add Employee");
			System.out.println("2. View Employees");
			System.out.println("3. Update Employee Details");
			System.out.println("4. Remove Employee");
			System.out.print("Select an option: ");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				// Logic to add a new employee
				break;
			case 2:
				// Logic to view employees
				break;
			case 3:
				// Logic to update employee details
				break;
			case 4:
				// Logic to remove an employee
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		} catch (Exception e) {
			System.out.println("Error while managing employees: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Function to handle Service Management
	private static void manageServices(Scanner sc) {
		try {
			System.out.println("\n------------------ Service Management ------------------");
			System.out.println("1. Add Service");
			System.out.println("2. View Services");
			System.out.println("3. Update Service");
			System.out.println("4. Delete Service");
			System.out.print("Select an option: ");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				// Logic to add a new service
				break;
			case 2:
				// Logic to view services
				break;
			case 3:
				// Logic to update a service
				break;
			case 4:
				// Logic to delete a service
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		} catch (Exception e) {
			System.out.println("Error while managing services: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Function to handle Appointment Management
	private static void manageAppointments(Scanner sc) {
		try {
			System.out.println("\n------------------ Appointment Management ------------------");
			System.out.println("1. Schedule an Appointment");
			System.out.println("2. View Appointments");
			System.out.println("3. Update Appointment");
			System.out.println("4. Cancel Appointment");
			System.out.print("Select an option: ");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				// Logic to schedule an appointment
				break;
			case 2:
				// Logic to view appointments
				break;
			case 3:
				// Logic to update appointment details
				break;
			case 4:
				// Logic to cancel an appointment
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		} catch (Exception e) {
			System.out.println("Error while managing appointments: " + e.getMessage());
			e.printStackTrace();
		}
	}
}


/*--------------------citizen insertion----------------*/
/*---{

//SessionFactory getsFactory=HibernateUtils.getsFactory();
////openSession() method is used to create a new Session object in Hibernate.
//Session opensession=getsFactory.openSession();

Session session=HibernateUtils.getsFactory().openSession();

//creating transaction reference to modify the data in the database
Transaction transaction=session.beginTransaction();

// Create a LocalDate object for the dateOfBirth
LocalDate dob = LocalDate.of(2002,10,23); // Replace with the desired date (year, month, day)

Citizen citizen1=new Citizen("citizen06","bhanu","kmr","3945543212","bhanureddy@gmail.com",dob,"narasimha");

//inserting entities into database
session.save(citizen1);

//commit changes to database then only that will be in persistence state
transaction.commit(); //now citizen in the persistence state

//closing
HibernateUtils.getsFactory().close();
//getsFactory.close();

System.out.println("Inserted successfully");
}--*/


/*------for taking input from keyboard for citizen insertion-----*/
/*
try {
// Take input from the user
Scanner scanner = new Scanner(System.in);

System.out.println("Enter student id: ");
String citizenId = scanner.nextLine();

System.out.println("Enter student name: ");
String citizenName = scanner.nextLine();

System.out.println("Enter student address: ");
String address = scanner.nextLine();

System.out.println("Enter student mobilenumber: ");
String mobileNumber = scanner.nextLine();

System.out.println("Enter student email: ");
String email = scanner.nextLine();

System.out.println("Enter student dateOfBirth(yyyy-MM-dd): ");
String dobStr = scanner.nextLine();
LocalDate dateOfBirth = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

System.out.println("Enter student fathername: ");
String fatherName = scanner.nextLine();

// Create a new student object
Citizen newCitizen = new Citizen(citizenId,citizenName,address, mobileNumber, email, dateOfBirth,fatherName);

// Save the student object
session.save(newCitizen);

// Commit the transaction
session.getTransaction().commit();

System.out.println("Student saved successfully!");

} finally {
session.close();
}*/


/*---------------payment Insertion---------*/
/*
Session session=HibernateUtils.getsFactory().openSession();

//creating transaction reference to modify the data in the database
Transaction transaction=session.beginTransaction();

//// Step 1: Take citizen id to associate with the payment
//System.out.println("Enter citizen id (for an existing citizen): ");
//String citizenId = scanner.nextLine();

String citizenId="citizen01";

 // Retrieve the existing Citizen from the database using the citizenId
Citizen existingCitizen = session.get(Citizen.class, citizenId);

if (existingCitizen == null) {
    System.out.println("Citizen with ID " + citizenId + " does not exist!");
    return;
}

//create object of entities
Payment payment1=new Payment("payment01",19000,"cash","successfull",existingCitizen);//transient state(because no interaction with database

//inserting entities into database
session.save(payment1); //persistence state not because it has not committed it is cache

//commit changes to database then only that will be in persistence state
transaction.commit();

System.out.println("Payment successfully inserted");
 */


/*------------------Complaint insertion ----------------*/
/*
Session session=HibernateUtils.getsFactory().openSession();

//creating transaction reference to modify the data in the database
Transaction transaction=session.beginTransaction();

try {
	// Take input from the user
	Scanner scanner = new Scanner(System.in);

	System.out.println("Enter complaint id: ");
	String compaintId = scanner.nextLine();

	System.out.println("Enter complaint date(yyyy-MM-dd): ");
	String date = scanner.nextLine();
	LocalDate complaintDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	System.out.println("Enter complaint status: ");
	String status = scanner.nextLine();

	System.out.println("Enter complaint description: ");
	String details = scanner.nextLine();

	System.out.println("Enter citizen id:");
	String citizenId=scanner.nextLine();

	// Retrieve the existing Citizen from the database using the citizenId
	Citizen existingCitizen = session.get(Citizen.class, citizenId);

	if (existingCitizen == null) {
		System.out.println("Citizen with ID " + citizenId + " does not exist!");
		return;
	}

	// Create a new student object
	Complaint newComplaint = new Complaint(compaintId,complaintDate,status,details,existingCitizen);

	// Save the student object
	session.save(newComplaint);

	// Commit the transaction
	session.getTransaction().commit();

	System.out.println("complaint saved successfully!");

} 
finally 
{
	session.close();
}
 */

/*------------------Document insertion ---------------*/
/*--
 Session session=HibernateUtils.getsFactory().openSession();

		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();

		try {
			// Take input from the user
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter document id: ");
			String documentId = scanner.nextLine();

			System.out.println("Enter document type: ");
			String documentType = scanner.nextLine();

			System.out.println("Enter document name: : ");
			String documentName = scanner.nextLine();

			System.out.println("Enter document uploaded date(yyyy-MM-dd): ");
			String date = scanner.nextLine();
			LocalDate uploadDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			System.out.println("Enter citizen id:");
			String citizenId=scanner.nextLine();

			// Retrieve the existing Citizen from the database using the citizenId
			Citizen existingCitizen = session.get(Citizen.class, citizenId);

			if (existingCitizen == null) {
				System.out.println("Citizen with ID " + citizenId + " does not exist!");
				return;
			}

			// Create a new student object
			Document newComplaint = new Document(documentId,documentType,documentName,uploadDate,existingCitizen);

			// Save the student object
			session.save(newComplaint);

			// Commit the transaction
			session.getTransaction().commit();

			System.out.println("Document details uploaded successfully!");

		} 
		finally 
		{
			session.close();
		}
--*/

/*-----------------------TaxRecord insertion-------------------------------*/
/*
 Session session=HibernateUtils.getsFactory().openSession();

		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();

		try {
			// Take input from the user
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter tax id: ");
			String taxId = scanner.nextLine();


			System.out.println("Status of the tax: ");
			String status= scanner.nextLine();

			System.out.println("Enter tax submission date(yyyy-MM-dd): ");
			String date = scanner.nextLine();
			LocalDate dateOfSubmission = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			System.out.println("Enter citizen id:");
			String citizenId=scanner.nextLine();

			System.out.println("Enter tax amount: ");
			int amount = scanner.nextInt();

			System.out.println("Enter the year of the tax(yyyy): : ");
			int fiancialYear = scanner.nextInt();

			// Retrieve the existing Citizen from the database using the citizenId
			Citizen existingCitizen = session.get(Citizen.class, citizenId);

			if (existingCitizen == null) {
				System.out.println("Citizen with ID " + citizenId + " does not exist!");
				return;
			}

			// Create a new student object
			TaxRecord newComplaint = new TaxRecord(taxId,fiancialYear,status,amount,dateOfSubmission,existingCitizen);

			// Save the student object
			session.save(newComplaint);

			// Commit the transaction
			session.getTransaction().commit();

			System.out.println("Document details uploaded successfully!");

		} 
		finally 
		{
			session.close();
		}
 */

/*-----------------Notification insertion-------------*/
/*
Session session=HibernateUtils.getsFactory().openSession();

		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();

		try {
			// Take input from the user
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter notification id: ");
			String notificationId = scanner.nextLine();

			System.out.println("Enter the notifcation in detail manner:");
			String message= scanner.nextLine();

			System.out.println("Enter application date(yyyy-MM-dd): ");
			String date = scanner.nextLine();
			LocalDate notificationDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			System.out.println("Enter citizen id:");
			String citizenId=scanner.nextLine();

			// Retrieve the existing Citizen from the database using the citizenId
			Citizen existingCitizen = session.get(Citizen.class, citizenId);

			if (existingCitizen == null) {
				System.out.println("Citizen with ID " + citizenId + " does not exist!");
				return;
			}

			// Create a new student object
			Notification newNotication = new Notification(notificationId,notificationDate,message,existingCitizen);

			// Save the student object
			session.save(newNotication);

			// Commit the transaction
			session.getTransaction().commit();

			System.out.println("notification has sent successfully!..........");

		} 
		finally 
		{
			session.close();
		}
 */
/*------------------------------------------------------------*/
/*-----------Application-------------------------------------*/
/*
 {

    public static void main(String[] args) {

        Session session = HibernateUtils.getsFactory().openSession();

        // Creating transaction reference to modify the data in the database
        Transaction transaction = session.beginTransaction();

        try {
            // Take input from the user
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter application id: ");
            String applicationId = scanner.nextLine();

            System.out.println("Enter application date(yyyy-MM-dd): ");
        	String date = scanner.nextLine();
        	LocalDate applicationDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.println("Enter application status:");
            String status = scanner.nextLine();

            System.out.println("Enter citizen id:");
        	String citizenId=scanner.nextLine();

        	// Retrieve the existing Citizen from the database using the citizenId
        	Citizen existingCitizen = session.get(Citizen.class, citizenId);

        	if (existingCitizen == null) {
        		System.out.println("Citizen with ID " + citizenId + " does not exist!");
        		return;
        	}

        	// Create a new department object
        	Application newApplication = new Application (applicationId, applicationDate,status,existingCitizen);

        	// Save the student object
        	session.save(newApplication );

            // Commit the transaction
            transaction.commit();

            System.out.println("application has sent successfully.....");

        } finally {
            session.close();
        }

}
 */
/*-----------------------------------------------*/
/*---------------Department ----------------------*/
/*
 {

        Session session = HibernateUtils.getsFactory().openSession();

        // Creating transaction reference to modify the data in the database
        Transaction transaction = session.beginTransaction();

        try {
            // Take input from the user
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter department id: ");
            String departmentId = scanner.nextLine();

            System.out.println("Enter department name:");
            String departmentName = scanner.nextLine();

            // Report id as foreign key
            System.out.println("department description:");
            String description= scanner.nextLine();

        	// Create a new department object
        	Department newDepartment = new Department(departmentId,departmentName,description);

        	// Save the student object
        	session.save(newDepartment );

            // Commit the transaction
            transaction.commit();

            System.out.println("details inserted successfully");

        } finally {
            session.close();
        }

}
 */

/*---------------------------------------------------------*/
/*----------------Report inserting--------------*/
/*
Session session=HibernateUtils.getsFactory().openSession();

//creating transaction reference to modify the data in the database
Transaction transaction=session.beginTransaction();

try {
	// Take input from the user
	Scanner scanner = new Scanner(System.in);

	System.out.println("Enter report id: ");
	String reportId = scanner.nextLine();

	System.out.println("Enter report type:");
	String reportType= scanner.nextLine();

	System.out.println("Enter reported date(yyyy-MM-dd): ");
	String date = scanner.nextLine();
	LocalDate reportDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

	System.out.println("Enter department id:");
    String departmentId = scanner.nextLine();

    // Retrieve the existing Department from the database using the departmentId
    Department existingDepartment = session.get(Department.class, departmentId);

    if (existingDepartment == null) {
        System.out.println("Department with ID " + departmentId + " does not exist!");
        return;
    }

    // Create a new Report object and associate it with the department(s)
    Report newReport = new Report(reportId, reportDate, reportType,existingDepartment);

    // Save the report object to the database
    session.save(newReport);

    // Commit the transaction
    transaction.commit();

    System.out.println("Report details inserted successfully");

}
finally 
{
    session.close();
}
 */

/*--------------service insertion -----------------------*/
/*
 Session session=HibernateUtils.getsFactory().openSession();

		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();

		try {
			// Take input from the user
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter service id: ");
			String serviceId = scanner.nextLine();

			System.out.println("Enter service name:");
			String serviceName= scanner.nextLine();

			System.out.println("Enter service description:");
			String description= scanner.nextLine();

			System.out.println("Enter department id:");
            String departmentId = scanner.nextLine();

            // Retrieve the existing Department from the database using the departmentId
            Department existingDepartment = session.get(Department.class, departmentId);

            if (existingDepartment == null) {
                System.out.println("Department with ID " + departmentId + " does not exist!");
                return;
            }

            // Create a new Report object and associate it with the department(s)
            Service newService = new Service(serviceId, serviceName, description,existingDepartment);

            // Save the report object to the database
            session.save(newService);

            // Commit the transaction
            transaction.commit();

            System.out.println("Report details inserted successfully");

        }
		finally 
		{
            session.close();
        }
 */

/*-----------------Employee insertion--------------------*/
/*
 * Session session=HibernateUtils.getsFactory().openSession();

		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();

		try {
			// Take input from the user
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter employee id: ");
			String employeeId = scanner.nextLine();

			System.out.println("Enter employee name:");
			String employeeName= scanner.nextLine();

			System.out.println("Enter employee role:");
			String role= scanner.nextLine();

			System.out.println("Enter employee password: ");
			String password = scanner.nextLine();

			System.out.println("Enter department id:");
            String departmentId = scanner.nextLine();

            // Retrieve the existing Department from the database using the departmentId
            Department existingDepartment = session.get(Department.class, departmentId);

            if (existingDepartment == null) {
                System.out.println("Department with ID " + departmentId + " does not exist!");
                return;
            }

            System.out.println("Enter employee salary:");
			int salary=scanner.nextInt();

            // Create a new Report object and associate it with the department(s)
           Employee newEmployee = new Employee(employeeId, employeeName,role,salary,password,existingDepartment);

            // Save the report object to the database
            session.save(newEmployee);

            // Commit the transaction
            transaction.commit();

            System.out.println("Employee details inserted successfully");

        }
		finally 
		{
            session.close();
        }
 */

/*---------------------------------------------------------------------*/
/*------------ServiceRequest insertion --------------------*/
/*
 		Session session=HibernateUtils.getsFactory().openSession();

		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();

		try {
			// Take input from the user
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter service request id: ");
			String requestId = scanner.nextLine();

			System.out.println("Enter the status of the service request:");
			String status=scanner.nextLine();

			//report id as foreign key
			System.out.println("Enter citizen id:");
            String citizenId = scanner.nextLine();

            // Retrieve the existing Department from the database using the departmentId
          Citizen existingCitizen = session.get(Citizen.class, citizenId);

            if (existingCitizen == null) {
                System.out.println("Citizen with ID " +citizenId+ " does not exist!");
                return;
            }

          //service as foreign key
			System.out.println("Enter service id:");
            String serviceId = scanner.nextLine();

            // Retrieve the existing Department from the database using the departmentId
           Service existingService = session.get(Service.class, serviceId);

            if (existingService == null) {
                System.out.println("Service with ID " +serviceId+ " does not exist!");
                return;
            }

            // Create a new Report object and associate it with the department(s)
           ServiceRequest newServiceRequest = new ServiceRequest(requestId, status,existingCitizen,existingService);

            // Save the report object to the database
            session.save(newServiceRequest);

            // Commit the transaction
            transaction.commit();

            System.out.println("Service details inserted successfully");

        }
		finally 
		{
            session.close();
        }
 */
/*----------------------------------------------------------------------------*/
//Appointment insertion
/*--
 		Session session=HibernateUtils.getsFactory().openSession();

		//creating transaction reference to modify the data in the database
		Transaction transaction=session.beginTransaction();

		try {
			// Take input from the user
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter appointment id: ");
			String appointmentId = scanner.nextLine();

			System.out.println("Enter the status of the appointment:");
			String status=scanner.nextLine();

			System.out.println("Enter appointment date(yyyy-MM-dd): ");
			String date = scanner.nextLine();
			LocalDate appointmentDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			//report id as foreign key
			System.out.println("Enter citizen id:");
			String citizenId = scanner.nextLine();

			// Retrieve the existing Citizen from the database using the departmentId
			Citizen existingCitizen = session.get(Citizen.class, citizenId);

			if (existingCitizen == null) {
				System.out.println("Citizen with ID " +citizenId+ " does not exist!");
				return;
			}

			//service as foreign key
			System.out.println("Enter department id:");
			String departmentId = scanner.nextLine();

			// Retrieve the existing Department from the database using the departmentId
			Department existingDepartment = session.get(Department.class, departmentId);

			if ( existingDepartment == null) {
				System.out.println("Service with ID " +departmentId+ " does not exist!");
				return;
			}

			//report id as foreign key
			System.out.println("Enter citizen id:");
			String employeeId = scanner.nextLine();

			// Retrieve the existing Citizen from the database using the departmentId
			Employee existingEmployee = session.get(Employee.class, employeeId);

			if (existingEmployee == null) {
				System.out.println("Citizen with ID " +citizenId+ " does not exist!");
				return;
			}

			// Create a new Report object and associate it with the department(s)
			Appointment newAppointment = new Appointment(appointmentId, status,appointmentDate,existingCitizen,existingDepartment,existingEmployee);

			// Save the report object to the database
			session.save(newAppointment);

			// Commit the transaction
			transaction.commit();

			System.out.println("Appointment details inserted successfully");

		}
		finally 
		{
			session.close();
		}
 */
/*-----------------------------------------------------------------------*/
/*-------------------Approval insertion ----------------------------------*/
/*
  {

        Session session = HibernateUtils.getsFactory().openSession();

        // Creating transaction reference to modify the data in the database
        Transaction transaction = session.beginTransaction();

        try {
            // Take input from the user
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter Approval id: ");
            String approvalId = scanner.nextLine();

            System.out.println("Enter the status of the approval:");
            String status = scanner.nextLine();

            // Report id as foreign key
            System.out.println("Enter request id:");
            String requestId = scanner.nextLine();

            // Retrieve the existing ServiceRequest from the database using the requestId
            ServiceRequest existingRequest = session.get(ServiceRequest.class, requestId);

            if (existingRequest == null) {
                System.out.println("Request with ID " + requestId + " does not exist!");
                return;
            }

            // Take input for the Employee (foreign key in the Approval entity)
            System.out.println("Enter Employee id for the approval:");
            String employeeId = scanner.nextLine();

            // Retrieve the existing Employee from the database using the employeeId
            Employee existingEmployee = session.get(Employee.class, employeeId);

            if (existingEmployee == null) {
                System.out.println("Employee with ID " + employeeId + " does not exist!");
                return;
            }

            // Get the number of approvals for the employee
            System.out.print("Enter number of approvals for the employee: ");
            int approvalCount = Integer.parseInt(scanner.nextLine());

            // Create a set to hold approvals
            Set<Approval> approvals = new HashSet<>();

            // Take input for approvals
            for (int i = 1; i <= approvalCount; i++) {
                System.out.print("Enter approval " + i + " id: ");
                String approvalId1 = scanner.nextLine();

                System.out.print("Enter approval " + i + " status: ");
                String status1 = scanner.nextLine();

                // Create a new Approval object
                Approval approval = new Approval(approvalId1, status1, existingRequest, null); // Initially no employees linked

                // Add the employee to the approval
                Set<Employee> employees = new HashSet<>();
                employees.add(existingEmployee);  // Associate this approval with the employee
                approval.setEmployee(employees);

                // Add approval to the set
                approvals.add(approval);
            }

            // Save each approval object to the database
            for (Approval approval : approvals) {
                session.save(approval);
            }

            // Commit the transaction
            transaction.commit();

            System.out.println("Approval details inserted successfully");

        } finally {
            session.close();
        }
    }
 */

/*-----------------------------------------------------------------*/
/*----------------Feedback insertion-------------------------*/
/*--
{
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
--*/
