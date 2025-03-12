package com.belvi.todo.service.impl;

import com.belvi.todo.model.Todo;
import com.belvi.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private List<Todo> todos = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }

    @Override
    public void addTodo(Todo todo) {
        todo.setTodId(nextId++);
        todos.add(todo);

    }

    @Override
    public String deleteTodo(Long todoId) {
        Todo todo = todos.stream()
                .filter(t -> t.getTodId().equals(todoId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        todos.remove(todo);
        return "Todo with todoId " + todoId + " deleted successfully";
    }
}
