package com.egovernancesystem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.egovernancesystem.entities.Document;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.service.DocumentService;
import com.egovernancesystem.serviceImpl.*;
import com.egovernancesystem.utils.HibernateUtils;

public class DocumentController {
	private BufferedReader br;
	private DocumentService documentService;

	/*---- Constructor ---*/
	public DocumentController()
	{
		try
		{
			/*---- Initializing BufferedReader object -----*/
			br = new BufferedReader(new InputStreamReader(System.in));
			/*---- Initializing DocumentService -------*/
			documentService = new DocumentServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*------------Method to add a document from user input ------------*/
	public void addDocumentFromInput() {
		Scanner sc = new Scanner(System.in);

		// Collect document information from the user
		System.out.println("Enter document id: ");
		String documentId = sc.nextLine();

		System.out.println("Enter document type: ");
		String documentType = sc.nextLine();

		System.out.println("Enter document name: ");
		String documentName = sc.nextLine();

		System.out.println("Enter upload date (yyyy-MM-dd): ");
		String uploadDateStr = sc.nextLine();
		LocalDate uploadDate = LocalDate.parse(uploadDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		System.out.println("Enter citizen ID associated with this document: ");
		String citizenId = sc.nextLine();

		// Fetch citizen from database using citizenId
		Session session = HibernateUtils.getsFactory().openSession();
		Citizen citizen = session.get(Citizen.class, citizenId);

		if (citizen == null) {
			System.out.println("Citizen with ID " + citizenId + " not found. Document cannot be added.");
			return;
		}

		// Create Document object using user input
		Document newDocument = new Document(documentId, documentType, documentName, uploadDate, citizen);

		// Call service layer to insert document
		int result = documentService.insertDocument(newDocument);

		if (result > 0) {
			System.out.println("Document successfully added to the database.");
		} else {
			System.out.println("Unable to add document to the database.");
		}
	}

	/*------------Method to update an existing document from user input ------------*/
	public void updateDocumentFromInput() {
	    Scanner sc = new Scanner(System.in);

	    System.out.println("Enter document id to update:");
	    String documentId = sc.nextLine();

	    // Fetch the existing document from the database using documentService
	    Document existingDocument = documentService.getDocumentById(documentId);

	    if (existingDocument == null) {
	        System.out.println("Document with ID " + documentId + " does not exist!");
	        return;
	    }

	    // Update Document details
	    while (true) {
	        System.out.println("Choose option to update:\n1. Update Type\n2. Update Name\n3. Update Upload Date\n4. Exit");
	        int option = sc.nextInt();
	        sc.nextLine(); // consume newline

	        if (option == 4) {
	            System.out.println("Exiting update menu.");
	            break;
	        }

	        switch (option) {
	        case 1:
	            System.out.print("Enter New Document Type: ");
	            String newType = sc.nextLine();
	            existingDocument.setDocumentType(newType);
	            System.out.println("Document type updated successfully.");
	            break;

	        case 2:
	            System.out.print("Enter New Document Name: ");
	            String newName = sc.nextLine();
	            existingDocument.setDocumentName(newName);
	            System.out.println("Document name updated successfully.");
	            break;

	        case 3:
	            System.out.print("Enter New Upload Date (yyyy-MM-dd): ");
	            String newUploadDateStr = sc.nextLine();
	            LocalDate newUploadDate = LocalDate.parse(newUploadDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	            existingDocument.setUploadDate(newUploadDate);
	            System.out.println("Upload date updated successfully.");
	            break;

	        default:
	            System.out.println("Invalid option. Please choose again.");
	        }
	    }

	    // Call the DAO method to update the document in the database
	    int result = documentService.updateDocument(existingDocument);

	    if (result > 0) {
	        System.out.println("Document data updated successfully.");
	    } else {
	        System.out.println("Unable to update document data.");
	    }
	}

	/*------------Method to delete a document from user input ------------*/
	public void deleteDocumentFromInput() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter document id to delete:");
		String documentId = sc.nextLine();

		// Call the service to delete the document
		int result = documentService.deleteDocument(documentId);

		if (result > 0) {
			System.out.println("Document with ID " + documentId + " deleted successfully.");
		} else {
			System.out.println("Document with ID " + documentId + " not found or unable to delete.");
		}
	}

	/*------------Method to get all the documents ------------*/
	public void getAllDocuments() {
		List<Document> documentList = documentService.getAllDocuments();

		if (documentList.size() > 0) {
			System.out.println("------- Document details --------");
			for (Document document : documentList) {
				System.out.println(document);
			}
		} else {
			System.out.println("No document records found.");
		}
	}

	/*------------Method to get a document by ID ------------*/
	public void getDocumentById() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter Document ID to get details: ");
		String documentId = scanner.nextLine();

		// Call the service to get document details
		Document document = documentService.getDocumentById(documentId);

		if (document != null) {
			System.out.println("Document Details:");
			System.out.println("Document ID: " + document.getDocumentId());
			System.out.println("Type: " + document.getDocumentType());
			System.out.println("Name: " + document.getDocumentName());
			System.out.println("Upload Date: " + document.getUploadDate());
			System.out.println("Citizen ID: " + document.getCitizen().getCitizenId());
			System.out.println("Citizen Name: " + document.getCitizen().getCitizenName());
		} else {
			System.out.println("Document with ID " + documentId + " not found.");
		}
	}
}
/*
 // Method for document operation 
	public void documentOperation() throws IOException {
		int choice, operation;
		do {
			//Displaying menu to the user
			System.out.println("------------------------------------------");
			System.out.println("------- Document Management Portal --------");
			System.out.println("-------------------------------------------");
			System.out.println("Select an option:");
			System.out.println("1. Add Document");
			System.out.println("2. Update Document");
			System.out.println("3. Delete Document");
			System.out.println("4. List of all Documents");
			System.out.println("5. List of Document by ID");
			System.out.println("--------------------------------------------");
			// Asking user to select any one operation 
			System.out.print("Select any one operation : ");
			operation = Integer.parseInt(br.readLine());
			System.out.println("-------------------------------------------");
			//Executing the task as per user's input 
			switch (operation) {
			case 1:
				addDocumentFromInput();
				break;
			case 2:
				updateDocumentFromInput();
				break;
			case 3:
				deleteDocumentFromInput();
				break;
			case 4:
				getAllDocuments();
				break;
			case 5:
				getDocumentById();
				break;
			default:
				System.out.println("Invalid selection");
			}
			System.out.println("---------------------------------------------------------");
			//Asking user whether he wants to continue or exit
			System.out.print("Press 0 to exit or any other number to continue : ");
			choice = Integer.parseInt(br.readLine());
		} while (choice != 0);
	}
	*/

