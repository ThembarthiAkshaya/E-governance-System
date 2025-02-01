package com.egovernancesystem.controller;

import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.TaxRecord;
import com.egovernancesystem.service.TaxRecordService;
import com.egovernancesystem.serviceImpl.TaxRecordServiceImpl;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TaxRecordController {

    private TaxRecordService taxRecordService;

    // Constructor
    public TaxRecordController() {
        taxRecordService = new TaxRecordServiceImpl();
    }

    // Method to add a new tax record from user input
    public void addTaxRecordFromInput() {
        Scanner sc = new Scanner(System.in);

        // Collect tax record details from the user
        System.out.println("Enter Tax ID: ");
        String taxId = sc.nextLine();

        System.out.println("Enter Financial Year: ");
        String financialYear = sc.nextLine();

        System.out.println("Enter Tax Amount: ");
        int amount = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Enter Tax Status (e.g., Paid, Pending): ");
        String status = sc.nextLine();

        System.out.println("Enter Date of Submission (yyyy-MM-dd): ");
        String dateOfSubmissionStr = sc.nextLine();
        LocalDate dateOfSubmission = LocalDate.parse(dateOfSubmissionStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Enter Citizen ID for this tax record: ");
        String citizenId = sc.nextLine();

        // Fetch Citizen details (assumed to be in the database already)
        Citizen citizen = new Citizen(); // you would fetch the actual Citizen object from the database
        citizen.setCitizenId(citizenId); // Setting just the citizenId, you would typically fetch the complete object

        // Create a new TaxRecord object
        TaxRecord newTaxRecord = new TaxRecord(taxId, financialYear, status, amount, dateOfSubmission, citizen);

        // Call service layer to insert tax record
        int result = taxRecordService.insertTax(newTaxRecord);

        if (result > 0) {
            System.out.println("Tax record successfully added.");
        } else {
            System.out.println("Unable to add tax record.");
        }
    }

    // Method to get all tax records for a specific citizen
    public void getTaxRecordsByCitizenFromInput() {
        Scanner sc = new Scanner(System.in);

        // Ask for Citizen ID
        System.out.println("Enter Citizen ID to get tax records: ");
        String citizenId = sc.nextLine();

        // Call service to get tax records by citizen ID using the instantiated taxRecordService
        List<TaxRecord> taxRecords = taxRecordService.getTaxRecordsByCitizen(citizenId);

        if (taxRecords != null && !taxRecords.isEmpty()) {
            System.out.println("---- Tax Records for Citizen ID: " + citizenId + " ----");
            for (TaxRecord taxRecord : taxRecords) {
                System.out.println("Tax ID: " + taxRecord.getTaxId());
                System.out.println("Amount: " + taxRecord.getAmount());
                System.out.println("Financial Year: " + taxRecord.getFiancialYear());
                System.out.println("Status: " + taxRecord.getStatus());
                System.out.println("Date of Submission: " + taxRecord.getDateOfSubmission());
                System.out.println("-----------------------------------");
            }
        } else {
            System.out.println("No tax records found for Citizen ID: " + citizenId);
        }
    }
}
