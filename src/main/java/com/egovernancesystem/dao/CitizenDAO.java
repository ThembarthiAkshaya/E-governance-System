package com.egovernancesystem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.utils.HibernateUtils;

public class CitizenDAO {
	/*---- Method to insert data into citizen table -----*/
	public int insertCitizen(Citizen citizen) 
	{
		int row = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try 
		{
			session.save(citizen);  // Save the Citizen object
			transaction.commit();   // Commit the transaction
			row = 1;                // Indicating success
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			row = 0;  // In case of failure
		}
		finally
		{
			session.close();  // Ensure session is closed
		}
		return row;
	}
	public int updateCitizen(Citizen citizen)
	{
	    int row = 0;
	    Session session = HibernateUtils.getSessionFactory().openSession();
	    Transaction transaction = session.beginTransaction();
	    try {
	        // Reattach the detached Citizen entity
	        session.merge(citizen);  // This ensures the entity is reattached to the session
	        transaction.commit();
	        row = 1;  // Indicating success
	        System.out.println("Citizen updated successfully!");
	    } 
	    catch (Exception e)
	    {
	        e.printStackTrace();
	        row = 0;  // In case of failure
	    }
	    finally
	    {
	        session.close();  // Ensure session is closed
	    }
	    return row;
	}

	// Method to delete citizen by citizenId (String type)
	public int deleteCitizen(String citizenId) 
	{
		int result = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try {
			// Fetch the citizen object first to ensure it exists
			Citizen citizen = session.get(Citizen.class, citizenId);  // Use the citizenId (String)

			if (citizen != null)
			{
				session.delete(citizen);  // Delete the citizen object
				transaction.commit();  // Commit the transaction
				result = 1;  // Successful deletion
			}
			else
			{
				result = 0;  // Citizen not found
			}
		} 
		catch (Exception e) 
		{
			transaction.rollback();  // Rollback if there is an error
			e.printStackTrace();
		}
		finally
		{
			session.close();  // Ensure session is closed
		}
		return result;
	}
	
	// Method to fetch all citizens from the database
    public List<Citizen> getAllCitizens() 
    {
        List<Citizen> citizenList = null;
        // Create session reference
        try (Session session = HibernateUtils.getSessionFactory().openSession()) 
        {
            // Create a query to fetch all Citizen records
            Query<Citizen> query = session.createQuery("from Citizen", Citizen.class);
            // Execute the query and get the result list
            citizenList = query.list();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return citizenList;
    }

	// Method to fetch a citizen by citizenId (String type)
	public Citizen getCitizenById(String citizenId)
	{
		Citizen citizen = null;
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			// Fetch the citizen object by citizenId
			citizen = session.get(Citizen.class, citizenId);  // citizenId is of type String
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		finally
		{
			session.close();  // Ensure session is closed
		}
		return citizen;  // Return null if not found
	}
}




