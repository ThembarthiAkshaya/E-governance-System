package com.egovernancesystem.serviceImpl;

import java.util.List;

import com.egovernancesystem.dao.NotificationDAO;  // Assume NotificationDAO is properly defined to interact with DB
import com.egovernancesystem.entities.Notification;
import com.egovernancesystem.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {

    private NotificationDAO notificationDao;  // DAO instance for interacting with the database

    // Constructor to initialize the DAO instance
    public NotificationServiceImpl() {
        notificationDao = new NotificationDAO();  // Initialize the DAO class
    }

    @Override
    public int insertNotification(Notification notification) {
        // Call the DAO method to insert the notification into the database
        return notificationDao.insertNotification(notification);
    }

    @Override
    public int updateNotification(Notification notification) {
        // Call the DAO method to update the notification in the database
        return notificationDao.updateNotification(notification);
    }

    @Override
    public int deleteNotification(String notificationId) {
        // Call the DAO method to delete the notification by its ID
        return notificationDao.deleteNotification(notificationId);
    }

    @Override
    public Notification getNotificationById(String notificationId) {
        // Call the DAO method to fetch the notification by its ID
        return notificationDao.getNotificationById(notificationId);
    }

    @Override
    public List<Notification> getAllNotifications() {
        // Call the DAO method to fetch all notifications
        return notificationDao.getAllNotifications();
    }

    @Override
    public List<Notification> getNotificationsByCitizen(String citizenId) {
        // Call the DAO method to fetch notifications for a specific citizen
        return notificationDao.getNotificationsByCitizen(citizenId);
    }
}
