package com.egovernancesystem.controller;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import com.egovernancesystem.entities.Department;
import com.egovernancesystem.entities.Service;
import com.egovernancesystem.service.ServiceService;
import com.egovernancesystem.serviceImpl.ServiceServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;

public class ServiceController {

    private Scanner sc;
    private ServiceService serviceService;

    /*---- Constructor ---*/
    public ServiceController() {
        try {
            sc = new Scanner(System.in);
            serviceService = new ServiceServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*------------ Method to add a new service ------------*/
    public void addServiceFromInput() {
        // Collect service information from the user
        System.out.println("Enter service id: ");
        String serviceId = sc.nextLine();

        System.out.println("Enter service name: ");
        String serviceName = sc.nextLine();

        System.out.println("Enter description: ");
        String description = sc.nextLine();

        System.out.println("Enter department ID associated with this service: ");
        String departmentId = sc.nextLine();

        // Fetch department from the database using departmentId
        Session session = HibernateUtils.getsFactory().openSession();
        Department department = session.get(Department.class, departmentId);

        if (department == null) {
            System.out.println("Department with ID " + departmentId + " not found. Service cannot be added.");
            return;
        }

        // Create Service object using user input
        Service newService = new Service(serviceId, serviceName, description, department);

        // Call service layer to add service
        int result = serviceService.insertService(newService);

        if (result > 0) {
            System.out.println("Service successfully added to the database.");
        } else {
            System.out.println("Unable to add service to the database.");
        }
    }

    /*------------ Method to update an existing service from user input ------------*/
    public void updateServiceFromInput() {
        System.out.println("Enter service id to update:");
        String serviceId = sc.nextLine();

        // Fetch the existing service from the database
        Service existingService = serviceService.getServiceById(serviceId);

        if (existingService == null) {
            System.out.println("Service with ID " + serviceId + " does not exist!");
            return;
        }

        // Update Service details
        while (true) {
            System.out.println("Choose option to update:\n1. Update Name\n2. Update Description\n3. Update Department\n4. Exit");
            int option = sc.nextInt();
            sc.nextLine(); // consume newline

            if (option == 4) {
                System.out.println("Exiting update menu.");
                break;
            }

            switch (option) {
                case 1:
                    System.out.print("Enter New Service Name: ");
                    String newName = sc.nextLine();
                    existingService.setServiceName(newName);
                    System.out.println("Service name updated successfully.");
                    break;

                case 2:
                    System.out.print("Enter New Description: ");
                    String newDescription = sc.nextLine();
                    existingService.setDescription(newDescription);
                    System.out.println("Service description updated successfully.");
                    break;

                case 3:
                    System.out.print("Enter New Department ID: ");
                    String newDepartmentId = sc.nextLine();
                    Department newDepartment = HibernateUtils.getsFactory().openSession().get(Department.class, newDepartmentId);
                    if (newDepartment != null) {
                        existingService.setDepartment(newDepartment);
                        System.out.println("Service department updated successfully.");
                    } else {
                        System.out.println("Department with ID " + newDepartmentId + " not found.");
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        // Call the service to update the service in the database
        int result = serviceService.updateService(existingService);

        if (result > 0) {
            System.out.println("Service data updated successfully.");
        } else {
            System.out.println("Unable to update service data.");
        }
    }

    /*------------ Method to delete a service ------------*/
    public void deleteServiceFromInput() {
        System.out.println("Enter service id to delete:");
        String serviceId = sc.nextLine();

        // Call the service to delete the service
        int result = serviceService.deleteService(serviceId);

        if (result > 0) {
            System.out.println("Service with ID " + serviceId + " deleted successfully.");
        } else {
            System.out.println("Service with ID " + serviceId + " not found or unable to delete.");
        }
    }

    /*------------ Method to get all services ------------*/
    public void getAllServices() {
        List<Service> serviceList = serviceService.getAllServices();

        if (serviceList.size() > 0) {
            System.out.println("------- Service details --------");
            for (Service service : serviceList) {
                System.out.println(service);
            }
        } else {
            System.out.println("No service records found.");
        }
    }
}
