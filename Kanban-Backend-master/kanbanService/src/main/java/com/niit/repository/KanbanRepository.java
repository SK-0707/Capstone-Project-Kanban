package com.niit.repository;

import com.niit.model.Kanban;
import com.niit.model.TaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


public interface KanbanRepository extends MongoRepository<Kanban,Integer> {

    Optional<Kanban> findByKanbanTitle(String title);
    Optional<Kanban> findByKanbanId(int kanbanId);
    @Query("{'email' : ?0}")
    List<Kanban> findByEmail(String email);

}
