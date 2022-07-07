package com.niit.repository;

import com.niit.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends MongoRepository<Task,Integer> {

    Optional<Task> findByTaskName(String taskName);
}
