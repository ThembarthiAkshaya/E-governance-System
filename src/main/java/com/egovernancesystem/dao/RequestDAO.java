package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.ServiceRequest;
import com.egovernancesystem.utils.HibernateUtils;

public class RequestDAO {
	// Method to insert a new complaint into the database
	public int insertRequest(ServiceRequest taxrecord) {
		int row = 0;
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(taxrecord);
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
	// Method to get a tax by its ID
	public ServiceRequest getServiceRequestById(String requestId) {
		Session session = HibernateUtils.getsFactory().openSession();
		ServiceRequest servicerequest = session.get(ServiceRequest.class, requestId);
		session.close();
		return servicerequest;
	}
	public List<ServiceRequest> getServiceRequestByCitizen(String citizenId) {
		Session session = HibernateUtils.getsFactory().openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "FROM ServiceRequest c WHERE c.citizen.citizenId = :citizenId";
		Query<ServiceRequest> query = session.createQuery(hql,ServiceRequest.class);
		query.setParameter("citizenId", citizenId);
		List<ServiceRequest> taxrecords = query.list();
		transaction.commit();
		return taxrecords;
	}
}
