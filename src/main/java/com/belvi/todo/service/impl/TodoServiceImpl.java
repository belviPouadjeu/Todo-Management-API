package com.belvi.todo.service.impl;

import com.belvi.todo.model.Todo;
import com.belvi.todo.repository.TodoRepository;
import com.belvi.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    //private List<Todo> todos = new ArrayList<>();
    //private Long nextId = 1L;
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    @Override
    public void addTodo(Todo todo) {
        String status = todo.getStatus().toString();

        if (!status.equalsIgnoreCase("To do") &&
                !status.equalsIgnoreCase("In progress") &&
                !status.equalsIgnoreCase("Done")) {
            throw new IllegalArgumentException("Invalid status. Allowed values are: 'To do', 'In progress', 'Done'.");
        }

        todoRepository.save(todo);
    }


    /*@Override
    public void addTodo(Todo todo) {
        //todo.setTodId(nextId++);
        todoRepository.save(todo);

    }*/

    @Override
    public String deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        todoRepository.delete(todo);
        return "Todo with todoId : " + todoId + " deleted sucessfully";
    }

    @Override
    public Todo updateTodo(Todo todo, Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        todo.setTodId(todoId);
        savedTodo = todoRepository.save(todo);
        return savedTodo;

    }
}
