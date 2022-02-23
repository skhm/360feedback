package com.example.feedback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "USER_FEEDBACK_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class FeedbackDetails {
    @Id
    @Column
    private UUID feedbackDetailsId;

    @Column
    private String feedbackData;

    @Column
    private Boolean feedbackStatus;

    @OneToOne
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "feedback_Id")
    private Feedback feedback;

    public UUID getFeedbackDetailsId() {
        return feedbackDetailsId;
    }

    public void setFeedbackDetailsId(UUID feedbackDetailsId) {
        this.feedbackDetailsId = feedbackDetailsId;
    }

    public String getFeedbackData() {
        return feedbackData;
    }

    public void setFeedbackData(String feedbackData) {
        this.feedbackData = feedbackData;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public Boolean getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(Boolean feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
