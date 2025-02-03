package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.TaxRecord;
import com.egovernancesystem.utils.HibernateUtils;

public class TaxRecordDAO
{
	// Method to insert a new complaint into the database
		public int insertTax(TaxRecord taxrecord) {
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
		public TaxRecord getTaxRecordById(String taxRecordId) {
			Session session = HibernateUtils.getsFactory().openSession();
			TaxRecord taxrecord = session.get(TaxRecord.class, taxRecordId);
			session.close();
			return taxrecord;
		}
		public List<TaxRecord> getTaxRecordByCitizen(String citizenId) {
			Session session = HibernateUtils.getsFactory().openSession();
			Transaction transaction = session.beginTransaction();
			String hql = "FROM TaxRecord c WHERE c.citizen.citizenId = :citizenId";
			Query<TaxRecord> query = session.createQuery(hql,TaxRecord.class);
			query.setParameter("citizenId", citizenId);
			List<TaxRecord> taxrecords = query.list();
			transaction.commit();
			return taxrecords;
		}
}
