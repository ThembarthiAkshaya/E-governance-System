package com.egovernancesystem.serviceImpl;

import com.egovernancesystem.dao.FeedbackDAO;
import com.egovernancesystem.entities.Feedback;
import com.egovernancesystem.service.FeedbackService;
import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {

    private FeedbackDAO feedbackDao;

    public FeedbackServiceImpl() {
        feedbackDao = new FeedbackDAO(); // Initialize the DAO object
    }

    @Override
    public boolean addFeedback(Feedback feedback) {
        // Call DAO method to insert feedback and return the result
        return feedbackDao.insertFeedback(feedback) > 0; // Assuming > 0 indicates success
    }

    @Override
    public boolean updateFeedback(Feedback feedback) {
        // Call DAO method to update feedback and return the result
        return feedbackDao.updateFeedback(feedback) > 0; // Assuming > 0 indicates success
    }

    @Override
    public boolean deleteFeedback(String feedbackId) {
        // Call DAO method to delete feedback and return the result
        return feedbackDao.deleteFeedback(feedbackId) > 0; // Assuming > 0 indicates success
    }

    @Override
    public Feedback getFeedbackById(String feedbackId) {
        // Fetch feedback by ID using the DAO method
        return feedbackDao.getFeedbackById(feedbackId);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        // Get all feedbacks from DAO
        return feedbackDao.getAllFeedbacks();
    }

    @Override
    public List<Feedback> getFeedbacksByCitizen(String citizenId) {
        // Get feedbacks associated with a citizen by their ID
        return feedbackDao.getFeedbacksByCitizen(citizenId);
    }

    @Override
    public List<Feedback> getFeedbacksByServiceRequest(String serviceRequestId) {
        // Get feedbacks associated with a specific service request
        return feedbackDao.getFeedbacksByServiceRequest(serviceRequestId);
    }

}
/*
 @Override
    public Citizen getCitizenById(String citizenId) {
        // Call the DAO to fetch citizen by ID
        return feedbackDao.getCitizenById(citizenId);
    }
 */
