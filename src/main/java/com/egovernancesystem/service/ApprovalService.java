package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.Approval;

public interface ApprovalService
{
	/*--- Method to insert a new complaint ---*/
    int insertApproval(Approval approval);
    
    /*--- Method to get Appointment details by ID ---*/
    Approval getApprovalById(String approvalId);
    
    /*--- Method to get all Appointments scheduled with a specific Government Employee ---*/
    List<Approval> getAllApprovalsByEmployee(String employeeId);
    
    /*--- Method to cancel all appointments by employeeId ---*/
    int deleteApprovalsByEmployeeId(String employeeId);
    
    /*--- Method to change the status of all appointments by employeeId ---*/
    int changeApprovalStatusByEmployeeId(String employeeId, String newStatus);
}