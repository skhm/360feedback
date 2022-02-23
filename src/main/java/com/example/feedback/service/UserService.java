package com.example.feedback.service;

import com.example.feedback.model.Feedback;
import com.example.feedback.model.FeedbackDetails;
import com.example.feedback.model.User;
import com.example.feedback.repository.FeedbackRepository;
import com.example.feedback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    private FeedbackRepository feedbackRepository;


    @Autowired
    public UserService(UserRepository userRepository, FeedbackRepository feedbackRepository) {
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public void createJWT() {

    }

    public Feedback createRequestFeedback(UUID userUUID, String description) {
        User user = userRepository.findById(userUUID).get();
        UUID managerId = user.getManagerId();
        List<User> peers = userRepository.findPeers(managerId);
        peers.add(userRepository.findById(managerId).get());
        peers = peers.stream().filter(peer -> !peer.getId().equals(user.getId())).collect(Collectors.toList());
        UUID feedbackId = UUID.randomUUID();
        List<FeedbackDetails> feedbackDetails = peers.stream().map(peer ->
                 FeedbackDetails.builder().fromUser(peer).feedbackStatus(false).
                         feedbackDetailsId(UUID.randomUUID()).
                         feedback(Feedback.builder().feedbackId(feedbackId).
                                 build()).build()).
                collect(Collectors.toList());
        Feedback feedback = Feedback.builder().feedbackId(feedbackId
                ).feedbackDetails(feedbackDetails).creationDate(new Date()).requestedForUser(user).description(description).build();
        feedbackRepository.save(feedback);
        return feedback;
    }

}
