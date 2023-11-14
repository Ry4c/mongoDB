package com.mongo.service;

import com.mongo.config.exception.TodoCollectionException;
import com.mongo.dto.TodoDTO;
import com.mongo.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl {

    private final TodoRepo todoRepo;
    public void createTodo(TodoDTO todo) throws TodoCollectionException, ConstraintViolationException {
        Optional<TodoDTO> todoOtp = todoRepo.findByTodo(todo.getTodo());
        if (todoOtp.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.toDoAlreadyExists());
        }
        todo.setCreateAt(new Date(System.currentTimeMillis()));
        todoRepo.save(todo);
    }

    public List<TodoDTO> getAllTodos(){
        List<TodoDTO> todos = todoRepo.findAll();
        if (todos.isEmpty()) return new ArrayList<>();
        return todos;
    }

    public TodoDTO editById(String id, TodoDTO todo) throws TodoCollectionException{
        Optional<TodoDTO> todoOtp = todoRepo.findById(id);
        if (todoOtp.isPresent()) {
            TodoDTO todoSave = todoOtp.get();
            todoSave.setIsDone( todo.getIsDone() != null ? todo.getIsDone() : todoSave.getIsDone());
            todoSave.setUpdateAt(new Date(System.currentTimeMillis()));
            todoSave.setDescription(todo.getDescription() != null ? todo.getDescription() : todoSave.getDescription());
            todoSave.setTodo(todo.getTodo() != null ? todo.getTodo() : todoSave.getTodo());
            return todoRepo.save(todoSave);
        }
        throw new TodoCollectionException(TodoCollectionException.notFoundException(id));
    }

    public TodoDTO findById(String id) throws TodoCollectionException{
        Optional<TodoDTO> todoOtp = todoRepo.findById(id);
        if(todoOtp.isPresent()) return todoOtp.get();
        throw new TodoCollectionException(TodoCollectionException.notFoundException(id));
    }

    public List<TodoDTO> findByToDoContains(String todo){
        return todoRepo.findAllByTodoContains(todo);
    }
}
