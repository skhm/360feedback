package com.example.feedback.repository;


import com.example.feedback.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    @Query(value = "select * from FEEDBACK_USER where manager_id=:uuid", nativeQuery = true)
    List<User> findPeers(@Param("uuid") UUID uuid);
}
