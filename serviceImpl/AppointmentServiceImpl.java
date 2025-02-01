package com.egovernancesystem.serviceImpl;

import java.util.List;
import com.egovernancesystem.dao.AppointmentDAO;
import com.egovernancesystem.entities.Appointment;
import com.egovernancesystem.service.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentDAO appointmentDao;

    // Constructor to initialize DAO
    public AppointmentServiceImpl() {
        appointmentDao = new AppointmentDAO();
    }

    @Override
    public int insertAppointment(Appointment appointment) {
        return appointmentDao.insertAppointment(appointment); // Call DAO to insert the appointment
    }

    @Override
    public int updateAppointment(Appointment appointment) {
        return appointmentDao.updateAppointment(appointment); // Call DAO to update the appointment
    }

    @Override
    public int deleteAppointment(String appointmentId) {
        return appointmentDao.deleteAppointment(appointmentId);  // Call DAO to delete the appointment by appointmentId
    }

    @Override
    public Appointment getAppointmentById(String appointmentId) {
        return appointmentDao.getAppointmentById(appointmentId);  // Fetch appointment details by ID
    }

    @Override
    public List<Appointment> getAllAppointmentsByCitizen(String citizenId) {
        return appointmentDao.getAllAppointmentsByCitizen(citizenId);  // Fetch appointments for a specific citizen
    }

    @Override
    public List<Appointment> getAllAppointmentsByDepartment(String departmentId) {
        return appointmentDao.getAllAppointmentsByDepartment(departmentId);  // Fetch appointments for a specific department
    }

    @Override
    public List<Appointment> getAllAppointmentsByEmployee(String employeeId) {
        return appointmentDao.getAllAppointmentsByEmployee(employeeId);  // Fetch appointments scheduled with a specific employee
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentDao.getAllAppointments();  // Fetch all appointments in the system
    }
}
