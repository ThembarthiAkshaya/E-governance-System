package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.Appointment;

public interface AppointmentService {

    /*--- Method to schedule an Appointment ---*/
    int insertAppointment(Appointment appointment);

    /*--- Method to update an Appointment ---*/
    int updateAppointment(Appointment appointment);

    /*--- Method to cancel an Appointment ---*/
    int deleteAppointment(String appointmentId);

    /*--- Method to get Appointment details by ID ---*/
    Appointment getAppointmentById(String appointmentId);

    /*--- Method to get all Appointments for a Citizen ---*/
    List<Appointment> getAllAppointmentsByCitizen(String citizenId);

    /*--- Method to get all Appointments for a specific Department ---*/
    List<Appointment> getAllAppointmentsByDepartment(String departmentId);

    /*--- Method to get all Appointments scheduled with a specific Government Employee ---*/
    List<Appointment> getAllAppointmentsByEmployee(String employeeId);

    /*--- Method to get all Appointments ---*/
    List<Appointment> getAllAppointments();

}
