package com.egovernancesystem.dao;

import com.egovernancesystem.entities.Notification;
import com.egovernancesystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class NotificationDAO {

    // Method to insert a new notification into the database
    public int insertNotification(Notification notification) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(notification);  // Save the Notification object
            transaction.commit();        // Commit the transaction
            row = 1;                     // Indicating success
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;  // In case of failure
        } finally {
            session.close();  // Ensure session is closed
        }
        return row;
    }

    // Method to update an existing notification in the database
    public int updateNotification(Notification notification) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(notification);  // Use update() method to update the notification
            transaction.commit();          // Commit the transaction
            row = 1;                       // Indicating success
            System.out.println("Notification updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;  // In case of failure
        } finally {
            session.close();  // Ensure session is closed
        }
        return row;
    }

    // Method to delete a notification by its ID
    public int deleteNotification(String notificationId) {
        int result = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Fetch the notification object first to ensure it exists
            Notification notification = session.get(Notification.class, notificationId);
            if (notification != null) {
                session.delete(notification);  // Delete the notification object
                transaction.commit();          // Commit the transaction
                result = 1;                    // Successful deletion
            } else {
                result = 0;                    // Notification not found
            }
        } catch (Exception e) {
            transaction.rollback();  // Rollback if there is an error
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed
        }
        return result;
    }

    // Method to fetch all notifications from the database
    public List<Notification> getAllNotifications() {
        List<Notification> notificationList = null;
        try (Session session = HibernateUtils.getsFactory().openSession()) {
            // Create a query to fetch all Notification records
            Query<Notification> query = session.createQuery("from Notification", Notification.class);
            // Execute the query and get the result list
            notificationList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notificationList;
    }

    // Method to fetch a notification by its ID
    public Notification getNotificationById(String notificationId) {
        Notification notification = null;
        Session session = HibernateUtils.getsFactory().openSession();
        try {
            // Fetch the notification object by notificationId
            notification = session.get(Notification.class, notificationId);  // notificationId is of type String
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed
        }
        return notification;  // Return null if not found
    }

    // Method to fetch notifications by a specific citizen's ID
    public List<Notification> getNotificationsByCitizen(String citizenId) {
        List<Notification> notifications = null;
        Session session = HibernateUtils.getsFactory().openSession();
        try {
            // Create a query to fetch notifications where citizenId matches
            Query<Notification> query = session.createQuery("FROM Notification WHERE citizen.citizenId = :citizenId", Notification.class);
            query.setParameter("citizenId", citizenId);  // Set the citizenId parameter
            notifications = query.list();  // Execute the query and fetch the results
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed
        }
        return notifications;  // Return null if no notifications found
    }
}
