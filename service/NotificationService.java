package com.egovernancesystem.service;

import com.egovernancesystem.entities.*;
import java.util.List;

public interface NotificationService {
    
    /*--- Method to insert a new notification ---*/
    int insertNotification(Notification notification);
    
    /*--- Method to update an existing notification ---*/
    int updateNotification(Notification notification);
    
    /*--- Method to delete a notification by its ID ---*/
    int deleteNotification(String notificationId);
    
    /*--- Method to get notification by its ID ---*/
    Notification getNotificationById(String notificationId);
    
    /*--- Method to get all notifications ---*/
    List<Notification> getAllNotifications();
    
    /*--- Method to get notifications by citizen ---*/
    List<Notification> getNotificationsByCitizen(String citizenId);
}

