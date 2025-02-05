package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Application;
import com.egovernancesystem.utils.HibernateUtils;

public class ApplicationDAO {
	/*---- Method to insert data into citizen table -----*/
	public int insertApplication(Application application) 
	{
		int row = 0;
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		try 
		{
			session.save(application);  // Save the Citizen object
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
	// Method to fetch all applications from the database
    public List<Application> getAllApplication() 
    {
        List<Application> applicationList = null;
        // Create session reference
        try (Session session = HibernateUtils.getSessionFactory().openSession()) 
        {
            // Create a query to fetch all Citizen records
            Query<Application> query = session.createQuery("from Application", Application.class);
            // Execute the query and get the result list
            applicationList = query.list();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return applicationList;
    }
 // Method to fetch a citizen by citizenId (String type)
 	public Application getApplicationById(String applicaitonId)
 	{
 		Application application = null;
 		Session session = HibernateUtils.getSessionFactory().openSession();
 		try {
 			// Fetch the citizen object by citizenId
 			application = session.get(Application.class, applicaitonId);  // citizenId is of type String
 		} 
 		catch (Exception e)
 		{
 			e.printStackTrace();
 		} 
 		finally
 		{
 			session.close();  // Ensure session is closed
 		}
 		return application;  // Return null if not found
 	}
}
