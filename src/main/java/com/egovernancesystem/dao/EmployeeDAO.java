package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Employee;
import com.egovernancesystem.utils.HibernateUtils;

public class EmployeeDAO {

    /*---- Method to add an employee into the database -----*/
    public int insertEmployee(Employee employee) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(employee);  // Save the Employee object
            transaction.commit();    // Commit the transaction
            row = 1;                 // Indicating success
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;                 // In case of failure
        } finally {
            session.close();         // Ensure session is closed
        }
        return row;
    }

    /*---- Method to update an employee in the database -----*/
    public int updateEmployee(Employee employee) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Create an HQL query to update specific fields of the Employee entity
            String hql = "UPDATE Employee e SET e.employeeName = :employeeName, e.role = :role, e.salary = :salary, e.department = :department WHERE e.employeeId = :employeeId";
            
            Query query = session.createQuery(hql);
            query.setParameter("employeeName", employee.getEmployeeName());  // Set new employee name
            query.setParameter("role", employee.getRole());                   // Set new role
            query.setParameter("salary", employee.getSalary());               // Set new salary
            query.setParameter("department", employee.getDepartment());       // Set new department
            query.setParameter("employeeId", employee.getEmployeeId());      // Set employee ID to identify the record
            
            row = query.executeUpdate();  // Execute the update query
            transaction.commit();         // Commit the transaction
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;                      // In case of failure
            transaction.rollback();       // Rollback transaction in case of error
        } finally {
            session.close();              // Ensure session is closed
        }
        return row;
    }

    /*---- Method to delete an employee by employeeId -----*/
    public int deleteEmployee(String employeeId) {
        int result = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Fetch the employee object first to ensure it exists
            Employee employee = session.get(Employee.class, employeeId);  // Use the employeeId (String)

            if (employee != null) {
                session.delete(employee);  // Delete the Employee object
                transaction.commit();      // Commit the transaction
                result = 1;                // Successful deletion
            } else {
                result = 0;  // Employee not found
            }
        } catch (Exception e) {
            transaction.rollback();  // Rollback if there is an error
            e.printStackTrace();
        } finally {
            session.close();          // Ensure session is closed
        }
        return result;
    }
    
    // Method to fetch an employee by employeeId 
    public Employee getEmployeeById(String employeeId) {
        Employee employee = null;
        Session session = HibernateUtils.getsFactory().openSession();
        try {
            // Fetch the employee object by employeeId
            employee = session.get(Employee.class, employeeId);  // employeeId is of type String
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed
        }
        return employee;  // Return null if not found
    }

    /*---- Method to fetch all employees from the database -----*/
    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = null;
        try (Session session = HibernateUtils.getsFactory().openSession()) {
            // Create a query to fetch all Employee records
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            // Execute the query and get the result list
            employeeList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
