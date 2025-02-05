package com.egovernancesystem.service;

import java.util.List;
import java.util.Set;

import com.egovernancesystem.entities.*;

public interface EmployeeService {
	/*--- Method to submit a report for a department ---*/
	int insertEmployee(Employee employee);

	/*--- Method to update a report ---*/
	int updateEmployee(Employee employee);

	/*--- Method to delete a report by report ID ---*/
	int deleteEmployee(String employeeId);
	
	// Method to view a report's details by report ID 
	Employee getEmployeeById(String employeeId);

	/*--- Method to view all reports ---*/
	List<Employee> getAllEmployees();
	
	/*--- Method to get all approvals of an employee ---*/
	
    Set<Approval> getApprovalsByEmployeeId(String employeeId);
  
}
