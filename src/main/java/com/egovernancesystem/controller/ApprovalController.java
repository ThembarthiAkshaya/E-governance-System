package com.egovernancesystem.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import com.egovernancesystem.entities.Approval;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.Employee;
import com.egovernancesystem.entities.Payment;
import com.egovernancesystem.entities.ServiceRequest;
import com.egovernancesystem.service.ApprovalService;
import com.egovernancesystem.serviceImpl.ApprovalServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ApprovalController {

	// Member variables
	private BufferedReader br;
	private ApprovalService approvalService;

	// Constructor
	public ApprovalController() {
		try {
			// Initializing BufferedReader
			br = new BufferedReader(new InputStreamReader(System.in));

			// Initializing ApprovalService
			approvalService = new ApprovalServiceImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------*/
	public void insertApprovalFromInput() {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Enter approval ID: ");
			String approvalId = sc.nextLine();

			//System.out.println("Enter approval status (e.g., Pending, Completed, Failed): ");
			String status ="pending";

			System.out.println("Enter reqeust ID for this payment: ");
			String requestId = sc.nextLine();

			// Fetch citizen from database using citizenId
			Session session = HibernateUtils.getSessionFactory().openSession();
			ServiceRequest servicerequest= session.get(ServiceRequest.class, requestId);

			if (servicerequest == null) {
				System.out.println("Service request with ID " + requestId + " does not exist!");
				session.close();
				return;
			}
			
			System.out.println("Enter employee ID for this payment: ");
			String employeeId = sc.nextLine();

			Employee employee= session.get(Employee.class, employeeId);

			if (employee == null) {
				System.out.println("Service request with ID " + requestId + " does not exist!");
				session.close();
				return;
			}

			// Create new approval object
			Approval newApproval = new Approval(approvalId,status, servicerequest,employee);

			// Call service layer to insert payment
			int result = approvalService.insertApproval(newApproval);

			if (result > 0) {
				System.out.println("Approval successfully inserted into database.");
			} else {
				System.out.println("Unable to insert payment into database.");
			}
		} catch (Exception e) {
			System.out.println("Error while inserting payment: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/*------------------------------------------------------*/
	// Method to change the status of all approvals for a specific employee ID
    public void changeApprovalStatusByEmployeeId() {
        Scanner scanner = new Scanner(System.in);

        // Collect employee ID and new status from the user
        System.out.println("Enter employee ID to update approvals: ");
        String employeeId = scanner.nextLine();

        System.out.println("Enter new status: ");
        String newStatus = scanner.nextLine();

        // Retrieve the list of approvals associated with the given employee ID
        List<Approval> approvals = approvalService.getAllApprovalsByEmployee(employeeId);

        // Check if approvals exist
        if (approvals != null && !approvals.isEmpty()) {
            // Call the service to update the status for all approvals associated with the employee
            int updatedCount = approvalService.changeApprovalStatusByEmployeeId(employeeId, newStatus);

            if (updatedCount > 0) {
                System.out.println(updatedCount + " approvals updated with the new status.");
            } else {
                System.out.println("Failed to update approvals. Please check the status or employee ID.");
            }
        } else {
            System.out.println("No approvals found for the given employee ID.");
        }
    }


	/*------------------------------------------------------*/
	// Method to get all approvals
	public void getAllApprovals() {
		// Creating session reference
		Session session = HibernateUtils.getSessionFactory().openSession();

		// Creating reference of Query interface to fetch all approvals
		Query query = session.createQuery("from Approval");

		// Executing the query
		List<Approval> approvalList = query.list();

		if (approvalList.size() > 0) {
			System.out.println("------- Approval details --------");
			for (Approval approval : approvalList) {
				System.out.println(approval);
			}
		} else {
			System.out.println("No approvals found.");
		}

		// Close session
		session.close();
	}

	// Method to delete all approvals for a given employee ID
	public void deleteApprovalsByEmployeeId() {
		Scanner scanner = new Scanner(System.in);

		// Collect employee ID from the user
		System.out.println("Enter employee ID to delete all approvals: ");
		String employeeId = scanner.nextLine();

		// Call the service layer to delete approvals for the given employeeId
		int deletedCount = approvalService.deleteApprovalsByEmployeeId(employeeId);

		// Output the result
		if (deletedCount > 0) {
			System.out.println(deletedCount + " approvals successfully deleted for employee ID: " + employeeId);
		} else {
			System.out.println("No approvals found for employee ID: " + employeeId);
		}
	}

	/*------------------------------------------------------*/
	// Method to get approval by ID
		public void getApprovalById() {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Approval ID to get details: ");
			String paymentId = scanner.nextLine(); // Payment ID as String

			// Call the service to get payment details
			Approval approval = approvalService.getApprovalById(paymentId);

			if (approval != null) {
				System.out.println("Approval Details:");
				System.out.println("Approval ID: " + approval.getApprovalId());
				System.out.println("Status: " + approval.getStatus());
				System.out.println("Request ID: " + approval.getServiceRequest().getRequestId());
				System.out.println("Employee ID: "+approval.getEmployee().getEmployeeId());
				System.out.println("Employee name " + approval.getEmployee().getEmployeeName());
			} else {
				System.out.println("Approval with ID " + paymentId + " not found.");
			}
		}
	
}