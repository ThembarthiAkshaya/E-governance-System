package com.egovernance.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.egovernance.entities.Address;
import com.egovernance.entities.Citizen;
import com.egovernance.utils.HibernateUtils;

public class CitizenDAO {

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

		// Create a new Citizen object
		Citizen newCitizen = new Citizen(citizenId, citizenName, address, mobileNumber, email, dateOfBirth, fatherName);

		// Save the Citizen object to the database
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(newCitizen); // Save the Citizen object
			transaction.commit();     // Commit the transaction
			System.out.println("Citizen saved successfully!");
		} finally {
			session.close(); // Ensure session is closed
		}
	}

	// Method to update an existing citizen from user input
	public void updateCitizenFromInput() 
	{
		Session session = HibernateUtils.getsFactory().openSession();
		Scanner sc = new Scanner(System.in);

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
		}

		// Save the updated citizen object to the database
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(existingCitizen); // Save or update the citizen
		transaction.commit(); // Commit the transaction
		session.close();
		System.out.println("Citizen details updated successfully!");
	}

	// Method to delete a citizen from user input
	public void deleteCitizenFromInput()
	{
		Session session = HibernateUtils.getsFactory().openSession();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter citizen id to delete:");
		String citizenId = sc.nextLine();

		// Fetch the existing citizen from the database
		Citizen existingCitizen = session.get(Citizen.class, citizenId);

		// Verify if the citizen exists or not
		if (existingCitizen == null) {
			System.out.println("Citizen with ID " + citizenId + " does not exist!");
			session.close();
			return;
		}

		// Print citizen details and delete the record
		System.out.println("---------------------------------");
		System.out.println(existingCitizen);
		System.out.println("---------------------------------");

		// Create a transaction reference
		Transaction transaction = session.beginTransaction();

		// Delete citizen record
		session.delete(existingCitizen);

		// Commit changes
		transaction.commit();

		System.out.println("Citizen's record deleted successfully!");

		// Close the session
		session.close();
		sc.close();
	}
	
	/*------------Method to get all the citizen list ------------*/
	public void getAllCitizen()
	{
		//Creating session reference
		Session session = HibernateUtils.getsFactory().openSession();
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
		HibernateUtils.getsFactory().close();
	}
	
	
	public void getCitizenById() 
	{
		Scanner sc=new Scanner(System.in);
		//to create reference to session factory
		Session session=HibernateUtils.getsFactory().openSession();
		System.out.println("Enter citizen id to delete:");
		String citizenId = sc.nextLine();

		//to retrieve student by using student id
		Citizen citizen1=session.load(Citizen.class,citizenId); //persistence state

		//to check data is present or not
		if(citizen1==null) 
		{
			System.out.println("no any student exists with id std101");
		}
		else
		{
			System.out.println("----------------first record------------");
			System.out.println(citizen1);
		}
		//to close session factory
		HibernateUtils.getsFactory().close();
	}
}

