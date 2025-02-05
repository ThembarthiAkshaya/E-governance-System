package com.egovernancesystem.serviceImpl;

import java.util.List;
import java.util.Set;
import com.egovernancesystem.dao.EmployeeDAO;
import com.egovernancesystem.entities.*;
import com.egovernancesystem.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	private EmployeeDAO employeeDao;

	// Constructor to initialize DAO
	public EmployeeServiceImpl() {
		employeeDao = new EmployeeDAO();  // Initialize the DAO for interacting with the database
	}

	@Override
	public int insertEmployee(Employee employee) {
		return employeeDao.insertEmployee(employee); // Call DAO to insert the report into the database
	}

	@Override
	public int updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee); // Call DAO to update the report in the database
	}

	@Override
	public int deleteEmployee(String employeeId) {
		return employeeDao.deleteEmployee(employeeId); // Call DAO to delete the report by reportId
	}

	@Override
    public Employee getEmployeeById(String employeeId) {
        return employeeDao.getEmployeeById(employeeId); // Call DAO to get report by reportId
    }
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees(); // Call DAO to fetch all reports
	}
	
	@Override
    public Set<Approval> getApprovalsByEmployeeId(String employeeId) {
        Employee employee = employeeDao.getEmployeeById(employeeId);
        if (employee != null) {
            return employee.getApproval();
        } else {
            throw new IllegalArgumentException("Employee not found with id: " + employeeId);
        }
    }
   
}
