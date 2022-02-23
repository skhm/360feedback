package com.example.feedback.controller;

import com.example.feedback.model.Feedback;
import com.example.feedback.model.FeedbackDetails;
import com.example.feedback.model.User;
import com.example.feedback.service.FeedbackService;
import com.example.feedback.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private UserService userService;

    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(UserService userService, FeedbackService feedbackService) {
        this.userService = userService;
        this.feedbackService = feedbackService;
    }

    @PutMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Create user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateToken() {
        userService.createJWT();
        return ResponseEntity.ok("");
    }

    @Operation(description = "Request feedback for a user")
    @PostMapping(path = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Feedback> requestFeedback(@PathVariable(value = "userId") UUID userId, @RequestBody String description) {
       Feedback feedback = userService.createRequestFeedback(userId, description);
       return ResponseEntity.ok(feedback);
    }

    @Operation(description = "Provide feedback for user")
    @PostMapping(path = "/{feedbackId}/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FeedbackDetails> postFeedback(@PathVariable(value = "feedbackId") UUID feedbackId,
                                                        @PathVariable(value = "userId") UUID fromUserId,
                                                        @RequestBody String feedback) {
        FeedbackDetails details = feedbackService.submitFeedback(feedbackId,fromUserId, feedback);
        return ResponseEntity.ok(details);
    }

    @Operation(description = "Get all feedback requested by a user")
    @GetMapping(path = "/users/{userId}/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Feedback>> getFeedbackForUser(@PathVariable(value = "userId") UUID userId) {
        List<Feedback> feedback = feedbackService.getFeedbackForUser(userId);
        return ResponseEntity.ok(feedback);
    }

    @Operation(description = "Get all feedbacks to be provided by a user")
    @GetMapping(value = "/users/{userId}/others")
    public ResponseEntity<List<FeedbackDetails>> getToDoFeedback(@PathVariable(value = "userId") UUID userId) {
        return ResponseEntity.ok(feedbackService.getFeedbackTobeGivenByUser(userId));
    }
}
