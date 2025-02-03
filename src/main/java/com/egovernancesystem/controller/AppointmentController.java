package com.egovernancesystem.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import com.egovernancesystem.entities.Appointment;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.entities.Department;
import com.egovernancesystem.entities.Employee;
import com.egovernancesystem.service.AppointmentService;
import com.egovernancesystem.serviceImpl.AppointmentServiceImpl;
import com.egovernancesystem.utils.HibernateUtils;
import org.hibernate.Session;

public class AppointmentController {

    private AppointmentService appointmentService;
    private Scanner sc;

    // Constructor
    public AppointmentController() {
        try {
            appointmentService = new AppointmentServiceImpl();
            sc = new Scanner(System.in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*------------ Method to schedule an appointment ------------*/
    public void scheduleAppointment() {
        System.out.println("Enter appointment ID: ");
        String appointmentId = sc.nextLine();

        System.out.println("Enter status of the appointment: ");
        String status = sc.nextLine();

        System.out.println("Enter appointment date (yyyy-MM-dd): ");
        String appointmentDateStr = sc.nextLine();
        LocalDate appointmentDate = LocalDate.parse(appointmentDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Enter citizen ID associated with the appointment: ");
        String citizenId = sc.nextLine();

        // Fetch citizen from database using citizenId
        Session session = HibernateUtils.getsFactory().openSession();
        Citizen citizen = session.get(Citizen.class, citizenId);

        if (citizen == null) {
            System.out.println("Citizen with ID " + citizenId + " not found. Appointment cannot be scheduled.");
            return;
        }

        System.out.println("Enter department ID: ");
        String departmentId = sc.nextLine();

        // Fetch department from the database using departmentId
        Department department = session.get(Department.class, departmentId);
        if (department == null) {
            System.out.println("Department with ID " + departmentId + " not found.");
            return;
        }

        System.out.println("Enter employee ID: ");
        String employeeId = sc.nextLine();

        // Fetch employee from the database using employeeId
        Employee employee = session.get(Employee.class, employeeId);
        if (employee == null) {
            System.out.println("Employee with ID " + employeeId + " not found.");
            return;
        }

        // Create Appointment object using the user input
        Appointment appointment = new Appointment(appointmentId, status, appointmentDate, citizen, department, employee);

        // Call service layer to schedule the appointment
        int result = appointmentService.insertAppointment(appointment);

        if (result > 0) {
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Unable to schedule appointment.");
        }
    }

    /*------------ Method to update an existing appointment ------------*/
    public void updateAppointment() {
        System.out.println("Enter appointment ID to update: ");
        String appointmentId = sc.nextLine();

        // Fetch existing appointment from the database
        Appointment existingAppointment = appointmentService.getAppointmentById(appointmentId);

        if (existingAppointment == null) {
            System.out.println("Appointment with ID " + appointmentId + " not found!");
            return;
        }

        System.out.println("Enter new status: ");
        String newStatus = sc.nextLine();
        existingAppointment.setStatus(newStatus);

        System.out.println("Enter new appointment date (yyyy-MM-dd): ");
        String newDateStr = sc.nextLine();
        LocalDate newAppointmentDate = LocalDate.parse(newDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        existingAppointment.setAppointmentDate(newAppointmentDate);

        // Call service layer to update the appointment
        int result = appointmentService.updateAppointment(existingAppointment);

        if (result > 0) {
            System.out.println("Appointment updated successfully.");
        } else {
            System.out.println("Unable to update appointment.");
        }
    }

    /*------------ Method to cancel an appointment ------------*/
    public void cancelAppointment() {
        System.out.println("Enter appointment ID to cancel: ");
        String appointmentId = sc.nextLine();

        // Call service layer to cancel the appointment
        int result = appointmentService.deleteAppointment(appointmentId);

        if (result > 0) {
            System.out.println("Appointment with ID " + appointmentId + " canceled successfully.");
        } else {
            System.out.println("Appointment with ID " + appointmentId + " not found or unable to cancel.");
        }
    }

    /*------------ Method to get appointment details by ID ------------*/
    public void getAppointmentById() {
        System.out.println("Enter appointment ID to get details: ");
        String appointmentId = sc.nextLine();

        // Call service layer to get appointment details
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment != null) {
            System.out.println("Appointment Details:");
            System.out.println("Appointment ID: " + appointment.getAppointmentId());
            System.out.println("Status: " + appointment.getStatus());
            System.out.println("Appointment Date: " + appointment.getAppointmentDate());
            System.out.println("Citizen ID: " + appointment.getCitizen().getCitizenId());
            System.out.println("Citizen Name: " + appointment.getCitizen().getCitizenName());
            System.out.println("Department ID: " + appointment.getDepartment().getDepartmentId());
            System.out.println("Employee ID: " + appointment.getGovernmentEmployee().getEmployeeId());
        } else {
            System.out.println("Appointment with ID " + appointmentId + " not found.");
        }
    }

    /*------------ Method to get all appointments for a citizen ------------*/
    public void getAllAppointmentsByCitizen() {
        System.out.println("Enter citizen ID to get all appointments: ");
        String citizenId = sc.nextLine();

        // Call service layer to get all appointments for a citizen
        List<Appointment> appointments = appointmentService.getAllAppointmentsByCitizen(citizenId);

        if (appointments.size() > 0) {
            System.out.println("------- Appointment Details for Citizen: " + citizenId + " ---------");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        } else {
            System.out.println("No appointments found for citizen with ID " + citizenId);
        }
    }

    /*------------ Method to get all appointments ------------*/
    public void getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();

        if (appointments.size() > 0) {
            System.out.println("------- All Appointment Details ---------");
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        } else {
            System.out.println("No appointments found.");
        }
    }
}