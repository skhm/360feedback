package com.example.feedback.repository;

import com.example.feedback.model.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends CrudRepository<Feedback, UUID> {

    @Query(value = "select * from FEEDBACK where REQUESTED_FOR_USER_ID=:userId", nativeQuery = true)
    List<Feedback> findFeedbackById(@Param(value = "userId") UUID userId);

}
