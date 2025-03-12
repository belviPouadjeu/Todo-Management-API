package com.belvi.todo.service;

import com.belvi.todo.model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    void addTodo(Todo todo);
    String deleteTodo(Long todoId);
    Todo updateTodo(Todo todo, Long todoId);
}
