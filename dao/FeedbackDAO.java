package com.egovernancesystem.dao;

import com.egovernancesystem.entities.Feedback;
import com.egovernancesystem.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FeedbackDAO {

    // Method to insert new feedback
    public int insertFeedback(Feedback feedback) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(feedback);
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

    // Method to update existing feedback
    public int updateFeedback(Feedback feedback) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(feedback);
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

    // Method to delete feedback by ID
    public int deleteFeedback(String feedbackId) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Feedback feedback = session.get(Feedback.class, feedbackId);
            if (feedback != null) {
                session.delete(feedback);
                transaction.commit();
                row = 1;  // Success
            } else {
                row = 0;  // Not found
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return row;
    }

    // Method to get feedback by ID
    public Feedback getFeedbackById(String feedbackId) {
        Session session = HibernateUtils.getsFactory().openSession();
        Feedback feedback = session.get(Feedback.class, feedbackId);
        session.close();
        return feedback;
    }

    // Method to get all feedbacks
    public List<Feedback> getAllFeedbacks() {
        Session session = HibernateUtils.getsFactory().openSession();
        Query<Feedback> query = session.createQuery("from Feedback", Feedback.class);
        List<Feedback> feedbacks = query.list();
        session.close();
        return feedbacks;
    }

    // Method to get feedbacks by Citizen ID
    public List<Feedback> getFeedbacksByCitizen(String citizenId) {
        Session session = HibernateUtils.getsFactory().openSession();
        Query<Feedback> query = session.createQuery("FROM Feedback WHERE citizen.citizenId = :citizenId", Feedback.class);
        query.setParameter("citizenId", citizenId);
        List<Feedback> feedbacks = query.list();
        session.close();
        return feedbacks;
    }

    // Method to get feedbacks by Service Request ID
    public List<Feedback> getFeedbacksByServiceRequest(String requestId) {
        Session session = HibernateUtils.getsFactory().openSession();
        Query<Feedback> query = session.createQuery("FROM Feedback WHERE serviceRequest.requestId = :requestId", Feedback.class);
        query.setParameter("requestId", requestId);
        List<Feedback> feedbacks = query.list();
        session.close();
        return feedbacks;
    }
}

