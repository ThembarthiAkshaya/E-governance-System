package com.egovernancesystem.controller;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import com.egovernancesystem.entities.Department;
import com.egovernancesystem.entities.Employee;
import com.egovernancesystem.service.EmployeeService;
import com.egovernancesystem.serviceImpl.EmployeeServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;

public class EmployeeController {

    private Scanner sc;
    private EmployeeService employeeService;

    /*---- Constructor ---*/
    public EmployeeController() {
        try {
            sc = new Scanner(System.in);
            employeeService = new EmployeeServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*------------ Method to add a new employee ------------*/
    public void addEmployeeFromInput() {
        // Collect employee information from the user
        System.out.println("Enter employee id: ");
        String employeeId = sc.nextLine();

        System.out.println("Enter employee name: ");
        String employeeName = sc.nextLine();

        System.out.println("Enter role: ");
        String role = sc.nextLine();

        System.out.println("Enter salary: ");
        int salary = sc.nextInt();
        sc.nextLine(); // consume the newline character

        System.out.println("Enter password: ");
        String password = sc.nextLine();

        System.out.println("Enter department ID associated with this employee: ");
        String departmentId = sc.nextLine();

        // Fetch department from the database using departmentId
        Session session = HibernateUtils.getSessionFactory().openSession();
        Department department = session.get(Department.class, departmentId);

        if (department == null) {
            System.out.println("Department with ID " + departmentId + " not found. Employee cannot be added.");
            return;
        }

        // Create Employee object using user input
        Employee newEmployee = new Employee(employeeId, employeeName, role, salary, password, department);

        // Call service layer to add employee
        int result = employeeService.insertEmployee(newEmployee);

        if (result > 0) {
            System.out.println("Employee successfully added to the database.");
        } else {
            System.out.println("Unable to add employee to the database.");
        }
    }

    /*------------ Method to update an existing employee from user input ------------*/
    public void updateEmployeeFromInput() {
        System.out.println("Enter employee id to update:");
        String employeeId = sc.nextLine();

        // Fetch the existing employee from the database
        Employee existingEmployee = employeeService.getEmployeeById(employeeId);

        if (existingEmployee == null) {
            System.out.println("Employee with ID " + employeeId + " does not exist!");
            return;
        }

        // Update Employee details
        while (true) {
            System.out.println("Choose option to update:\n1. Update Name\n2. Update Role\n3. Update Salary\n4. Update Department\n5. Exit");
            int option = sc.nextInt();
            sc.nextLine(); // consume newline

            if (option == 5) {
                System.out.println("Exiting update menu.");
                break;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter New Employee Name: ");
                    String newName = sc.nextLine();
                    existingEmployee.setEmployeeName(newName);
                    System.out.println("Employee name updated successfully.");
                    break;

                case 2:
                    System.out.print("Enter New Role: ");
                    String newRole = sc.nextLine();
                    existingEmployee.setRole(newRole);
                    System.out.println("Employee role updated successfully.");
                    break;

                case 3:
                    System.out.print("Enter New Salary: ");
                    int newSalary = sc.nextInt();
                    sc.nextLine(); // consume newline
                    existingEmployee.setSalary(newSalary);
                    System.out.println("Employee salary updated successfully.");
                    break;

                case 4:
                    System.out.print("Enter New Department ID: ");
                    String newDepartmentId = sc.nextLine();
                    Department newDepartment = HibernateUtils.getSessionFactory().openSession().get(Department.class, newDepartmentId);
                    if (newDepartment != null) {
                        existingEmployee.setDepartment(newDepartment);
                        System.out.println("Employee department updated successfully.");
                    } else {
                        System.out.println("Department with ID " + newDepartmentId + " not found.");
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        // Call the service to update the employee in the database
        int result = employeeService.updateEmployee(existingEmployee);

        if (result > 0) {
            System.out.println("Employee data updated successfully.");
        } else {
            System.out.println("Unable to update employee data.");
        }
    }

    /*------------ Method to delete an employee ------------*/
    public void deleteEmployeeFromInput() {
        System.out.println("Enter employee id to delete:");
        String employeeId = sc.nextLine();

        // Call the service to delete the employee
        int result = employeeService.deleteEmployee(employeeId);

        if (result > 0) {
            System.out.println("Employee with ID " + employeeId + " deleted successfully.");
        } else {
            System.out.println("Employee with ID " + employeeId + " not found or unable to delete.");
        }
    }

    /*------------ Method to get all employees ------------*/
    public void getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();

        if (employeeList.size() > 0) {
            System.out.println("------- Employee details --------");
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        } else {
            System.out.println("No employee records found.");
        }
    }
}
