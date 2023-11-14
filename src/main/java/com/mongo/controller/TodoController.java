package com.mongo.controller;

import com.mongo.config.exception.TodoCollectionException;
import com.mongo.dto.TodoDTO;
import com.mongo.repository.TodoRepo;
import com.mongo.service.TodoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {
    private final TodoRepo todoRepo;
    private final TodoServiceImpl todoService;

    @GetMapping("/todo")
    public ResponseEntity<List<TodoDTO>> getAllTodos(){
        List<TodoDTO> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos,todos.isEmpty()? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
        try{
            todoService.createTodo(todo);
            return new ResponseEntity<>(todo, HttpStatus.CREATED);
        } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

     @GetMapping("/todo/")
    public ResponseEntity<?> findByTodo(String todo){
        List<TodoDTO> todos = todoService.findByToDoContains(todo);
        if(todos.isEmpty())
            return new ResponseEntity<>("no todo found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable String id){
        try{
        TodoDTO todo = todoService.findById(id);
            return new ResponseEntity<>(todo, HttpStatus.OK);
        } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/todo/{id}")
    public ResponseEntity<?> updateById(@PathVariable String id, @RequestBody TodoDTO todo){
            try {
                TodoDTO todoSaved = todoService.editById(id, todo);
                return new ResponseEntity<>(todoSaved, HttpStatus.ACCEPTED);
            } catch (TodoCollectionException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
            }
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        try{
            todoRepo.deleteById(id);
            return new ResponseEntity<>("deleted with id: " + id, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
