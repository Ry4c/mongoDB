package com.mongo.repository;

import com.mongo.dto.TodoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepo extends MongoRepository<TodoDTO, String> {
    List<TodoDTO> findAllByTodoContains(String todo);

    @Query("{'todo': ?0}")
    Optional<TodoDTO> findByTodo(String todo);
}
