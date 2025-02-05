package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Appointment;
import com.egovernancesystem.utils.HibernateUtils;

public class AppointmentDAO {

	/*---- Method to insert appointment into the database -----*/
	public int insertAppointment(Appointment appointment) {
		int row = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(appointment);  // Save the Appointment object
			transaction.commit();       // Commit the transaction
			row = 1;                    // Indicating success
		} catch (Exception e) {
			e.printStackTrace();
			row = 0;                    // In case of failure
		} finally {
			session.close();            // Ensure session is closed
		}
		return row;
	}

	/*---- Method to update an appointment in the database -----*/
	public int updateAppointment(Appointment appointment) {
		int row = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Create an HQL query to update specific fields of the Appointment entity
			String hql = "UPDATE Appointment a SET a.status = :status, a.appointmentDate = :appointmentDate, a.citizen = :citizen, a.department = :department, a.employee = :employee WHERE a.appointmentId = :appointmentId";

			Query query = session.createQuery(hql);
			query.setParameter("status", appointment.getStatus());  // Set new status
			query.setParameter("appointmentDate", appointment.getAppointmentDate());  // Set new appointment date
			query.setParameter("citizen", appointment.getCitizen());  // Set new citizen
			query.setParameter("department", appointment.getDepartment());  // Set new department
			query.setParameter("employee", appointment.getGovernmentEmployee());  // Set new employee
			query.setParameter("appointmentId", appointment.getAppointmentId());  // Set appointmentId to identify the record

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

	/*---- Method to delete an appointment by appointmentId -----*/
	public int deleteAppointment(String appointmentId) {
		int result = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Fetch the appointment object first to ensure it exists
			Appointment appointment = session.get(Appointment.class, appointmentId);  // Use the appointmentId (String)

			if (appointment != null) {
				session.delete(appointment);  // Delete the Appointment object
				transaction.commit();         // Commit the transaction
				result = 1;                   // Successful deletion
			} else {
				result = 0;  // Appointment not found
			}
		} catch (Exception e) {
			transaction.rollback();  // Rollback if there is an error
			e.printStackTrace();
		} finally {
			session.close();         // Ensure session is closed
		}
		return result;
	}

	/*---- Method to fetch all appointments from the database -----*/
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointmentList = null;
		try (Session session = HibernateUtils.getSessionFactory().openSession()) {
			// Create a query to fetch all Appointment records
			Query<Appointment> query = session.createQuery("from Appointment", Appointment.class);
			// Execute the query and get the result list
			appointmentList = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appointmentList;
	}

	/*---- Method to fetch an appointment by appointmentId -----*/
	public Appointment getAppointmentById(String appointmentId) {
		Appointment appointment = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			// Fetch the appointment object by appointmentId
			appointment = session.get(Appointment.class, appointmentId);  // appointmentId is of type String
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();  // Ensure session is closed
		}
		return appointment;  // Return null if not found
	}

	/*---- Method to fetch all appointments for a specific citizen -----*/
	public List<Appointment> getAllAppointmentsByCitizen(String citizenId) {
		List<Appointment> appointmentList = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			// Create a query to fetch all appointments for the specific citizen
			String hql = "FROM Appointment a WHERE a.citizen.citizenId = :citizenId";
			Query query = session.createQuery(hql, Appointment.class);
			query.setParameter("citizenId", citizenId);  // Set the citizenId parameter
			appointmentList = query.list();  // Execute query to get list of appointments
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();  // Ensure session is closed
		}
		return appointmentList;
	}

	/*---- Method to fetch all appointments for a specific department -----*/
	public List<Appointment> getAllAppointmentsByDepartment(String departmentId) {
		List<Appointment> appointmentList = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			// Create a query to fetch all appointments for the specific department
			String hql = "FROM Appointment a WHERE a.department.departmentId = :departmentId";
			Query query = session.createQuery(hql, Appointment.class);
			query.setParameter("departmentId", departmentId);  // Set the departmentId parameter
			appointmentList = query.list();  // Execute query to get list of appointments
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();  // Ensure session is closed
		}
		return appointmentList;
	}

	/*---- Method to fetch all appointments for a specific employee -----*/
	public List<Appointment> getAllAppointmentsByEmployee(String employeeId) {
		List<Appointment> appointmentList = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			// Create a query to fetch all appointments for the specific employee
			String hql = "FROM Appointment a WHERE a.employee.employeeId = :employeeId";
			Query query = session.createQuery(hql, Appointment.class);
			query.setParameter("employeeId", employeeId);  // Set the employeeId parameter
			appointmentList = query.list();  // Execute query to get list of appointments
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();  // Ensure session is closed
		}
		return appointmentList;
	}
}
