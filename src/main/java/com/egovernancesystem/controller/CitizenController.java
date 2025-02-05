package com.egovernancesystem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Address;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.service.CitizenService;
import com.egovernancesystem.serviceImpl.CitizenServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;

public class CitizenController {
	//member variable
	private BufferedReader br;
	private CitizenService citizenservice;

	/*---- Constructor ---*/
	public CitizenController() {
		try {
			/*---- Initializing BufferedReader object -----*/
			br = new BufferedReader(new InputStreamReader(System.in));
			/*---- Initializing CitizenService -------*/
			citizenservice = new CitizenServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*------------------------------------------------------*/
	// Method to insert a new citizen from user input
	public void insertCitizenFromInput() {
		Scanner sc = new Scanner(System.in);

		// Collect citizen information from the user
		System.out.println("Enter citizen id: ");
		String citizenId = sc.nextLine();

		System.out.println("Enter citizen name: ");
		String citizenName = sc.nextLine();

		System.out.println("Enter Street:");
		String street = sc.nextLine();

		System.out.println("Enter district name:");
		String districtname = sc.nextLine();

		System.out.println("Enter state name:");
		String statename = sc.nextLine();

		System.out.println("Enter Zip Code:");
		int zipCode = sc.nextInt();

		// Create Address object using user input
		Address address = new Address(street,districtname,statename, zipCode);


		System.out.println("Enter citizen mobilenumber: ");
		Long mobileNumber = sc.nextLong();
		sc.nextLine();  // Consume the newline

		System.out.println("Enter citizen email: ");
		String email = sc.nextLine();

		System.out.println("Enter citizen dateOfBirth(yyyy-MM-dd): ");
		String dobStr = sc.nextLine();
		LocalDate dateOfBirth = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		System.out.println("Enter citizen fathername: ");
		String fatherName = sc.nextLine();

		Citizen newCitizen = new Citizen(citizenId, citizenName, address, mobileNumber, email, dateOfBirth, fatherName);

		// Call service layer to insert citizen
		int result = citizenservice.insertCitizen(newCitizen);

		if (result > 0) {
			System.out.println("Citizen data successfully inserted into database.");
		} else {
			System.out.println("Unable to insert data into database.");
		}
	}

	// Method to update an existing citizen from user input
	public void updateCitizenFromInput() {
		Scanner sc = new Scanner(System.in);

		// Create reference to session factory
		Session session = HibernateUtils.getSessionFactory().openSession();

		// Begin a transaction
		Transaction transaction = session.beginTransaction();

		System.out.println("Enter citizen id to update:");
		String citizenId = sc.nextLine();

		// Fetch the existing citizen from the database
		Citizen existingCitizen = session.get(Citizen.class, citizenId);

		if (existingCitizen == null) {
			System.out.println("Citizen with ID " + citizenId + " does not exist!");
			session.close();
			return;
		}

		// Update Citizen details
		while (true) {
			System.out.println("Choose option to update:\n1. Update Name\n2. Update Date of Birth\n3. Update Phone Number\n4. Update Email\n5. Update Address\n6. Update Father Name\n7. Exit");
			int option = sc.nextInt();
			sc.nextLine(); // consume newline

			if (option == 7) {
				System.out.println("Exiting update menu.");
				break;
			}

			switch (option) {
			case 1:
				System.out.print("Enter New Name: ");
				String newName = sc.nextLine();
				existingCitizen.setCitizenName(newName);
				System.out.println("Citizen name updated successfully.");
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
				System.out.print("Enter New Email: ");
				String newEmail = sc.nextLine();
				existingCitizen.setEmail(newEmail);
				System.out.println("Email updated successfully.");
				break;

			case 5:
				System.out.println("Enter Street:");
				String street = sc.nextLine();

				System.out.println("Enter district name:");
				String districtname = sc.nextLine();

				System.out.println("Enter state name:");
				String statename = sc.nextLine();

				System.out.println("Enter Zip Code:");
				int zipCode = sc.nextInt();

				Address address = existingCitizen.getAddress();
				if (address == null) {
					address = new Address();  // Initialize the address if it is null
				}
				address.setStreetName(street);
				address.setDistirictName(districtname);
				address.setStatename(statename);
				address.setPinCode(zipCode);
				existingCitizen.setAddress(address);
				System.out.println("Address updated successfully.");
				break;

			case 6:
				System.out.print("Enter New Father Name: ");
				String newFatherName = sc.nextLine();
				existingCitizen.setFatherName(newFatherName);
				System.out.println("Father name updated successfully.");
				break;

			default:
				System.out.println("Invalid option. Please choose again.");
				continue;
			}
			// Now commit the changes to the database using the service layer
			int result = citizenservice.updateCitizen(existingCitizen);
			if (result > 0) {
				System.out.println("Citizen data updated successfully.");
			} else {
				System.out.println("Unable to update citizen data.");
			}
		}
	}


	// Method to delete a citizen from user input
	public void deleteCitizenFromInput()
	{
		Session session = HibernateUtils.getSessionFactory().openSession();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter citizen id to delete:");
		String citizenId = sc.nextLine();

		// Call the service to delete the citizen
		int result = citizenservice.deleteCitizen(citizenId);

		if (result > 0) {
			System.out.println("Citizen with ID " + citizenId + " deleted successfully.");
		} else {
			System.out.println("Citizen with ID " + citizenId + " not found or unable to delete.");
		}
	}

	/*------------Method to get all the citizen list ------------*/
	public void getAllCitizen()
	{
		//Creating session reference
		Session session = HibernateUtils.getSessionFactory().openSession();
		//creating reference of Query interface to fetch record of all the student
		Query query = session.createQuery("from Citizen");
		/*--- executing the query ----*/
		List<Citizen> citizenList = query.list();
		if(citizenList.size() > 0)
		{
			/*---- student records found ----*/
			System.out.println("------- Citizen details --------");
			for (Citizen citizen : citizenList) 
			{
				System.out.println(citizen);
			}
		}
		else
		{
			System.out.println("No record found");
		}
		/*---- close the sessionFactory ----*/
		HibernateUtils.getSessionFactory().close();
	}


	public void getCitizenById() 
	{
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Citizen ID to get details: ");
		String citizenId = scanner.nextLine(); // Citizen ID as String

		// Call the service to get citizen details
		Citizen citizen = citizenservice.getCitizenById(citizenId);

		if (citizen != null) {
			System.out.println("Citizen Details:");
			System.out.println("Citizen ID: " + citizen.getCitizenId());
			System.out.println("Name: " + citizen.getCitizenName());
			System.out.println("Father name: "+citizen.getFatherName());
			System.out.println("Date of birth :"+citizen.getDateOfBirth());
			System.out.println("Email: " + citizen.getEmail());
			System.out.println("Phone: " + citizen.getMobileNumber());
			System.out.println("Address: "+citizen.getAddress());

		} else {
			System.out.println("Citizen with ID " + citizenId + " not found.");
		}
	}
}

/*-------------------------no need of this menu --------------------*/
/*------ Method for student operation ------*/
/*------
	public void citizenOperation()throws IOException
	{
		int choice,operation;
		do
		{
			//Displaying menu to the user 
			System.out.println("------------------------------------------");
			System.out.println("------- Citizen Management Portal --------");
			System.out.println("-------------------------------------------");
			System.out.println("Select an option:");
			System.out.println("1. Register Citizen");
			System.out.println("2. Update Citizen");
			System.out.println("3. Delete Citizen");
			System.out.println("4. List of all the Citizen");
			System.out.println("5.List of Citizen by id");
			System.out.println("--------------------------------------------");
			// Asking user to select any one operation
			System.out.print("Select any one operation : ");
			operation =Integer.parseInt(br.readLine());
			System.out.println("-------------------------------------------");
			//Executing the task as per user's input
			switch(operation)
			{
			case 1: insertCitizenFromInput();
			break;
			case 2: updateCitizenFromInput();
			break;
			case 3: deleteCitizenFromInput();
			break;
			case 4:  getAllCitizen();
			break;
			case 5: getCitizenById();
			break;
			default: System.out.println("Invalid selection");
			}
			System.out.println("---------------------------------------------------------");
			//Asking user whether he wants to continue or exit
			System.out.print("Press 0 to exit or any other number to continue : ");
			choice = Integer.parseInt(br.readLine());			
		}while(choice!=0);
	}
	---------------*/