package com.egovernancesystem.service;

import com.egovernancesystem.entities.Feedback;
import java.util.List;

public interface FeedbackService {

    boolean addFeedback(Feedback feedback);

    boolean updateFeedback(Feedback feedback);

    boolean deleteFeedback(String feedbackId);

    List<Feedback> getAllFeedbacks();

    Feedback getFeedbackById(String feedbackId);

    List<Feedback> getFeedbacksByCitizen(String citizenId);

    List<Feedback> getFeedbacksByServiceRequest(String serviceRequestId);

}
