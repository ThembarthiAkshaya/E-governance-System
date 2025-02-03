package com.egovernancesystem.serviceImpl;

import java.util.List;
import com.egovernancesystem.dao.EmployeeDAO;
import com.egovernancesystem.entities.Employee;
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
}
