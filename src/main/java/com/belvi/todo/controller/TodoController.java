package com.belvi.todo.controller;

import com.belvi.todo.model.Todo;
import com.belvi.todo.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }

    @Operation(summary = "Add todo",
            description = "Create a new todo item with the provided details. " +
                    "The request body should include the title, description, and status of the todo. " +
                    "The status must be To do, In progress or Done.")
    @PostMapping("/public/todos")
    public ResponseEntity<String> addTodo(@Valid @RequestBody Todo todo){
        todoService.addTodo(todo);
        return new ResponseEntity<>("Todo added successfully", HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete a todo item by ID",
            description = "Delete a specific todo item from the system using its unique identifier. " +
                    "This operation is restricted to admin users.")
    @DeleteMapping("/admin/todos/{todoId}")
    public ResponseEntity<String> deletetodo(@PathVariable Long todoId){
        try {
            String status = todoService.deleteTodo(todoId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @Operation(
            summary = "Update a todo item by ID",
            description = "Update an existing todo item using its unique identifier. " +
                    "The request body should include the updated title, description, and status of the todo.")
    @PutMapping("/admin/todos/{todoId}")
    public ResponseEntity<String> updateTodo(@Valid @RequestBody Todo todo,
                                             @PathVariable Long todoId){
        try {
            Todo savedTodo = todoService.updateTodo(todo, todoId);
            return new ResponseEntity<>("Todo with todoId : " + todoId, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
