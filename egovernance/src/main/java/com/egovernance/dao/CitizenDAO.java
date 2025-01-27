package com.egovernance.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.egovernance.entities.Citizen;
import com.egovernance.utils.HibernateUtils;

public class CitizenDAO {

    public void insertCitizen(Citizen citizen) {
    	
        Session session = HibernateUtils.getsFactory().openSession();
        
        Transaction transaction = session.beginTransaction();
        try 
        {
            // Save the Citizen object
            session.save(citizen);
            // Commit the transaction
            transaction.commit();
            System.out.println("Citizen saved successfully!");
        } 
        finally 
        {
            session.close();
        }
    }
    
   public void updateCitizen(Citizen citizen) {
    	
        Session session = HibernateUtils.getsFactory().openSession();
        
        Transaction transaction = session.beginTransaction();
        try 
        {
            // Save the Citizen object
            session.save(citizen);
            // Commit the transaction
            transaction.commit();
            System.out.println("Citizen updated successfully!");
        } 
        finally 
        {
            session.close();
        }
    }
}
