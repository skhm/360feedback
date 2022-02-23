package com.example.feedback.service;


import com.example.feedback.model.Feedback;
import com.example.feedback.model.FeedbackDetails;
import com.example.feedback.repository.FeedbackDetailsRepository;
import com.example.feedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class FeedbackService {

    private FeedbackRepository feedbackRepository;

    private FeedbackDetailsRepository feedbackDetailsRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository,
                           FeedbackDetailsRepository feedbackDetailsRepository) {
        this.feedbackDetailsRepository = feedbackDetailsRepository;
        this.feedbackRepository = feedbackRepository;
    }


    public FeedbackDetails submitFeedback(UUID feedbackId, UUID fromUserId, String feedback) {
        Feedback feedBack = feedbackRepository.findById(feedbackId).get();
        FeedbackDetails feedbackDetails = feedBack.getFeedbackDetails().
                stream().filter(details -> details.getFromUser().getId().
                        equals(fromUserId)).findFirst().get();
        feedbackDetails.setFeedbackData(feedback);
        feedbackDetails.setFeedbackStatus(true);
        feedbackDetailsRepository.save(feedbackDetails);
        return  feedbackDetails;
    }

    public List<Feedback> getFeedbackForUser(UUID uuid) {
        List<Feedback> feedback = feedbackRepository.findFeedbackById(uuid);
        return feedback;
    }

    public List<FeedbackDetails> getFeedbackTobeGivenByUser(UUID userId) {
        return feedbackDetailsRepository.findAllByUserId(userId);
    }
}
