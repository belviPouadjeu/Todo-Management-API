package com.belvi.todo.controller;

import com.belvi.todo.model.Todo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {
    private List<Todo> todos = new ArrayList<>();

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot with Swagger!";
    }

    @Operation(summary = "Get all Todos", description = "Returns a list of all todo items")
    @GetMapping("/public/todos")
    public List<Todo> getAllTodos(){
        return todos;

    }
    
    @Operation(summary = "Add todo", description = "Returns a list of all todo items")
    @PostMapping("/public/todos")
    public String addTodo(@RequestBody Todo todo){
        todos.add(todo);
        return "Todo added successfully";
    }
}