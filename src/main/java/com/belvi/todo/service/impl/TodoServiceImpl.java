package com.belvi.todo.service.impl;

import com.belvi.todo.model.Todo;
import com.belvi.todo.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private List<Todo> todos = new ArrayList<>();

    @Override
    public List<Todo> getAllTodos() {
        return todos;
    }

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);

    }
}
