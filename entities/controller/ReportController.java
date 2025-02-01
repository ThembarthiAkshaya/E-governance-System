package com.egovernancesystem.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import com.egovernancesystem.entities.Department;
import com.egovernancesystem.entities.Report;
import com.egovernancesystem.service.ReportService;
import com.egovernancesystem.serviceImpl.ReportServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;

public class ReportController {

	private BufferedReader br;
	private ReportService reportService;

	/*---- Constructor ---*/
	public ReportController() {
		try {
			/*---- Initializing BufferedReader object -----*/
			br = new BufferedReader(new InputStreamReader(System.in));
			/*---- Initializing ReportService -------*/
			reportService = new ReportServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*------------Method to submit a report for a department from user input ------------*/
	public void submitReportFromInput() {
		Scanner sc = new Scanner(System.in);

		// Collect report information from the user
		System.out.println("Enter report id: ");
		String reportId = sc.nextLine();

		System.out.println("Enter report type: ");
		String reportType = sc.nextLine();

		System.out.println("Enter report date (yyyy-MM-dd): ");
		String reportDateStr = sc.nextLine();
		LocalDate reportDate = LocalDate.parse(reportDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		System.out.println("Enter department ID associated with this report: ");
		String departmentId = sc.nextLine();

		// Fetch department from the database using departmentId
		Session session = HibernateUtils.getsFactory().openSession();
		Department department = session.get(Department.class, departmentId);

		if (department == null) {
			System.out.println("Department with ID " + departmentId + " not found. Report cannot be submitted.");
			return;
		}

		// Create Report object using user input
		Report newReport = new Report(reportId, reportDate, reportType, department);

		// Call service layer to submit report
		int result = reportService.insertReport(newReport);

		if (result > 0) {
			System.out.println("Report successfully submitted to the database.");
		} else {
			System.out.println("Unable to submit report to the database.");
		}
	}

	/*------------Method to update an existing report from user input ------------*/
	public void updateReportFromInput() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter report id to update:");
		String reportId = sc.nextLine();

		// Fetch the existing report from the database using reportService
		Report existingReport = reportService.getReportById(reportId);

		if (existingReport == null) {
			System.out.println("Report with ID " + reportId + " does not exist!");
			return;
		}

		// Update Report details
		while (true) {
			System.out.println("Choose option to update:\n1. Update Type\n2. Update Date\n3. Update Department\n4. Exit");
			int option = sc.nextInt();
			sc.nextLine(); // consume newline

			if (option == 4) {
				System.out.println("Exiting update menu.");
				break;
			}

			switch (option) {
			case 1:
				System.out.print("Enter New Report Type: ");
				String newType = sc.nextLine();
				existingReport.setReportType(newType);
				System.out.println("Report type updated successfully.");
				break;

			case 2:
				System.out.print("Enter New Report Date (yyyy-MM-dd): ");
				String newReportDateStr = sc.nextLine();
				LocalDate newReportDate = LocalDate.parse(newReportDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				existingReport.setReportDate(newReportDate);
				System.out.println("Report date updated successfully.");
				break;

			case 3:
				System.out.print("Enter New Department ID: ");
				String newDepartmentId = sc.nextLine();
				Department newDepartment = HibernateUtils.getsFactory().openSession().get(Department.class, newDepartmentId);
				if (newDepartment != null) {
					existingReport.setDepartment(newDepartment);
					System.out.println("Department updated successfully.");
				} else {
					System.out.println("Department with ID " + newDepartmentId + " not found.");
				}
				break;

			default:
				System.out.println("Invalid option. Please choose again.");
			}
		}

		// Call the service to update the report in the database
		int result = reportService.updateReport(existingReport);

		if (result > 0) {
			System.out.println("Report data updated successfully.");
		} else {
			System.out.println("Unable to update report data.");
		}
	}

	/*------------Method to delete a report from user input ------------*/
	public void deleteReportFromInput() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter report id to delete:");
		String reportId = sc.nextLine();

		// Call the service to delete the report
		int result = reportService.deleteReport(reportId);

		if (result > 0) {
			System.out.println("Report with ID " + reportId + " deleted successfully.");
		} else {
			System.out.println("Report with ID " + reportId + " not found or unable to delete.");
		}
	}

	/*------------Method to get all the reports ------------*/
	public void getAllReports() {
		List<Report> reportList = reportService.getAllReports();

		if (reportList.size() > 0) {
			System.out.println("------- Report details --------");
			for (Report report : reportList) {
				System.out.println(report);
			}
		} else {
			System.out.println("No report records found.");
		}
	}
}
/*
 //Method to get a report by ID
    public void getReportById() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Report ID to get details: ");
        String reportId = scanner.nextLine();

        // Call the service to get report details
        Report report = reportService.getReportById(reportId);

        if (report != null) {
            System.out.println("Report Details:");
            System.out.println("Report ID: " + report.getReportId());
            System.out.println("Type: " + report.getReportType());
            System.out.println("Date: " + report.getReportDate());
            System.out.println("Department ID: " + report.getDepartment().getDepartmentId());
            System.out.println("Department Name: " + report.getDepartment().getDepartmentName());
        } else {
            System.out.println("Report with ID " + reportId + " not found.");
        }
    }

    //-Method to get reports by Department ID 
    public void getReportsByDepartmentId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Department ID to get reports: ");
        String departmentId = sc.nextLine();

        // Call the service to get reports for the department
        List<Report> reportList = reportService.getReportsByDepartmentId(departmentId);

        if (reportList.size() > 0) {
            System.out.println("------- Report details for department " + departmentId + " --------");
            for (Report report : reportList) {
                System.out.println(report);
            }
        } else {
            System.out.println("No reports found for department with ID " + departmentId);
        }
    }
 */
