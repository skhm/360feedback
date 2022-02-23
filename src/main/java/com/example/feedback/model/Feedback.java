package com.example.feedback.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "FEEDBACK")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @Column
    private UUID feedbackId;

    @OneToOne
    private User requestedForUser;

    @Column
    private String description;

    @Column
    private Date creationDate;

    @OneToMany(mappedBy = "feedback", cascade = CascadeType.ALL)
    List<FeedbackDetails> feedbackDetails;

    public UUID getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(UUID feedbackId) {
        this.feedbackId = feedbackId;
    }

    public User getRequestedForUser() {
        return requestedForUser;
    }

    public void setRequestedForUser(User requestedForUser) {
        this.requestedForUser = requestedForUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<FeedbackDetails> getFeedbackDetails() {
        return feedbackDetails;
    }

    public void setFeedbackDetails(List<FeedbackDetails> feedbackDetails) {
        this.feedbackDetails = feedbackDetails;
    }
}
