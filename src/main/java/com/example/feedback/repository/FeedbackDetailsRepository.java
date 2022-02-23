package com.example.feedback.repository;

import com.example.feedback.model.FeedbackDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FeedbackDetailsRepository extends CrudRepository<FeedbackDetails, UUID> {

    @Query(value = "Select * from USER_FEEDBACK_DETAILS where from_user_id=:userId", nativeQuery = true)
    public List<FeedbackDetails> findAllByUserId(@Param(value = "userId") UUID userId);
}
