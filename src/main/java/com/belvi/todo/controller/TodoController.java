package com.belvi.todo.controller;

import com.belvi.todo.model.Todo;
import com.belvi.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(summary = "Get all Todos", description = "Returns a list of all todo items")
    @GetMapping("/public/todos")
    public List<Todo> getAllTodos(){
        return todoService.getAllTodos();

    }

    @Operation(summary = "Add todo",
            description = "Create a new todo item with the provided details. The request body should include the title, description, and status of the todo.")
    @PostMapping("/public/todos")
    public String addTodo(@RequestBody Todo todo){
        todoService.addTodo(todo);
        return "Todo added successfully";
    }

    @Operation(
            summary = "Delete a todo item by ID",
            description = "Delete a specific todo item from the system using its unique identifier. This operation is restricted to admin users.")
    @DeleteMapping("/admin/todos/{todoId}")
    public String deletetodo(@PathVariable Long todoId){
        return todoService.deleteTodo(todoId);
    }
}
