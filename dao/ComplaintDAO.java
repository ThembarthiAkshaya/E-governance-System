package com.egovernancesystem.dao;

import com.egovernancesystem.entities.Complaint;
import com.egovernancesystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ComplaintDAO {

	// Method to insert a new complaint into the database
	public int insertComplaint(Complaint complaint) {
		int row = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(complaint);
			transaction.commit();
			row = 1;  // Success
		} catch (Exception e) {
			e.printStackTrace();
			row = 0;  // Failure
		} finally {
			session.close();
		}
		return row;
	}

	// Method to update an existing complaint
	public int updateComplaint(Complaint complaint) {
		int row = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.update(complaint);
			transaction.commit();
			row = 1;  // Success
		} catch (Exception e) {
			e.printStackTrace();
			row = 0;  // Failure
		} finally {
			session.close();
		}
		return row;
	}

	// Method to delete a complaint by its ID
	public int deleteComplaint(String complaintId) {
		int result = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Complaint complaint = session.get(Complaint.class, complaintId);
			if (complaint != null) {
				session.delete(complaint);
				transaction.commit();
				result = 1;  // Success
			} else {
				result = 0;  // Complaint not found
			}
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	// Method to get a complaint by its ID
	public Complaint getComplaintById(String complaintId) {
		Session session = HibernateUtils.getsFactory().openSession();
		Complaint complaint = session.get(Complaint.class, complaintId);
		session.close();
		return complaint;
	}

	// Method to get all complaints
	public List<Complaint> getAllComplaints() {
		Session session = HibernateUtils.getsFactory().openSession();
		Query<Complaint> query = session.createQuery("from Complaint", Complaint.class);
		List<Complaint> complaints = query.list();
		session.close();
		return complaints;
	}
	public List<Complaint> getComplaintsByCitizen(String citizenId) {
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM Complaint c WHERE c.citizen.citizenId = :citizenId";
		Query<Complaint> query = session.createQuery(hql, Complaint.class);
		query.setParameter("citizenId", citizenId);
		List<Complaint> complaints = query.list();
		transaction.commit();
		return complaints;
	}
}
