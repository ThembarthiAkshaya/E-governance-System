package com.egovernance.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.egovernance.dao.CitizenDAO;
import com.egovernance.entities.Citizen;
import com.egovernance.service.CitizenService;
import com.egovernance.serviceImpl.CitizenServiceImpl;
import com.egovernance.utils.HibernateUtils;

public class EgovernanceSystem {

    public static void main(String[] args) {
    	
        // Initialize the DAO and pass it to the service
        CitizenDAO citizenDAO = new CitizenDAO();
        
        CitizenService citizenService = new CitizenServiceImpl(citizenDAO);  // Inject DAO into Service

        Scanner sc = new Scanner(System.in);

        System.out.println("---------------Welcome to e-governance system services---------------");
        System.out.println("Select your role:");
        System.out.println("1. Citizen 2. Administrator 3. Employee");
        int role = sc.nextInt();

        switch (role) {
            case 1:
                // Handle Citizen role
                handleCitizenOptions(citizenService, sc);
                break;
            default:
                System.out.println("Sorry, You have chosen an Invalid option");
        }

        sc.close();
    }

    private static void handleCitizenOptions(CitizenService citizenService, Scanner sc)
    {
    	Session session=HibernateUtils.getsFactory().openSession();
    	
        Transaction transaction = session.beginTransaction();
        
        System.out.println("Select an option:");
        System.out.println("1. Register Citizen");
        System.out.println("2.Update Citizen");
        int option = sc.nextInt();
        sc.nextLine();  // Consume newline character after number input

        if (option == 1) {
            // Collect citizen information from the user
            // Take input from the user
            System.out.println("Enter citizen id: ");
            String citizenId = sc.nextLine();

            System.out.println("Enter citizen name: ");
            String citizenName = sc.nextLine();

            System.out.println("Enter citizen address: ");
            String address = sc.nextLine();

            System.out.println("Enter citizen mobilenumber: ");
            Long mobileNumber = sc.nextLong();

            System.out.println("Enter citizen email: ");
            String email = sc.nextLine();

            System.out.println("Enter citizen dateOfBirth(yyyy-MM-dd): ");
            String dobStr = sc.nextLine();
            LocalDate dateOfBirth = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.println("Enter citizen fathername: ");
            String fatherName = sc.nextLine();

            // Create a new Citizen object
            Citizen newCitizen = new Citizen(citizenId, citizenName, address, mobileNumber, email, dateOfBirth, fatherName);

            // Call the service to insert citizen into the database
            citizenService.insertCitizen(newCitizen);  // Corrected to newCitizen
        } 
        else if(option==2)
        {
        	System.out.println("Enter citizen id to update:");
        	String citizenId=sc.nextLine();
        	
        	//checking the whether the citizen is exist or not
        	// Retrieve the existing Citizen from the database using the citizenId
        	Citizen existingCitizen = session.get(Citizen.class, citizenId);

        	if (existingCitizen == null) {
        	    System.out.println("Citizen with ID " + citizenId + " does not exist!");
        	    return;
        	}
        	while (true) {
                System.out.println("Choose option to update:\n1. Update Name\n2. Update Date of Birth\n3. Update Phone Number\n4. Update Email\n5. Update Address\n6.update Fathername \n7. Exit");
                int oneOption = sc.nextInt();
                sc.nextLine(); // consume newline

                if (oneOption == 7) {
                    System.out.println("Exiting update menu.");
                    break;
                }

                switch (option) {
                    case 1:
                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        existingCitizen.setCitizenName(newName);
                        System.out.println("citizen name updated successfully.");
                        break;

                    case 2:
                        System.out.print("Enter New Date of Birth (yyyy-MM-dd): ");
                        String dobStr = sc.nextLine();
                        LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        existingCitizen.setDateOfBirth(dob);
                        System.out.println("Date of Birth updated successfully.");
                        break;

                    case 3:
                        System.out.print("Enter New Phone Number: ");
                        Long newPhoneNumber = sc.nextLong();
                        existingCitizen.setMobileNumber(newPhoneNumber);
                        System.out.println("Phone number updated successfully.");
                        break;

                    case 4:
                        System.out.print("Enter New Mail: ");
                        String newMail = sc.nextLine();
                        existingCitizen.setEmail(newMail);
                        System.out.println("Email updated successfully.");
                        break;

                    case 5:
                        System.out.print("Enter New Address: ");
                        String newAddress = sc.nextLine();
                        existingCitizen.setAddress(newAddress);
                        System.out.println("Address updated successfully.");
                        break;
                        
                    case 6:
                        System.out.print("Enter New father Name: ");
                        String newFatherName = sc.nextLine();
                        existingCitizen.setCitizenName(newFatherName);
                        System.out.println("father name updated successfully.");
                        break;

                    default:
                        System.out.println("Invalid option. Please choose again.");
                        continue;
                }

                // Save the updated citizen
                session.saveOrUpdate(existingCitizen);
                transaction.commit();
                System.out.println("admin details updated successfully!");

                // Start a new transaction for further updates if needed
                transaction = session.beginTransaction();
            }
        }
        else {
            System.out.println("Invalid Option");
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
