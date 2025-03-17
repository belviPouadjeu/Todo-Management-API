package com.belvi.todo.controller;

import com.belvi.todo.model.Todo;
import com.belvi.todo.payload.TodoDTO;
import com.belvi.todo.payload.TodoResponse;
import com.belvi.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/public/todos")
    public ResponseEntity<TodoResponse> getAllTodos(){
        TodoResponse todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }

    @PostMapping("/public/todos")
    public ResponseEntity<TodoDTO> addTodo(@Valid @RequestBody TodoDTO todoDTO){

        TodoDTO savedTodoDTo = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(savedTodoDTo, HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/todos/{todoId}")
    public ResponseEntity<TodoDTO> deletetodo(@PathVariable Long todoId){
        TodoDTO deleteTodo = todoService.deleteTodo(todoId);
        return new ResponseEntity<>(deleteTodo, HttpStatus.OK);

    }


    @PutMapping("/admin/todos/{todoId}")
    public ResponseEntity<TodoDTO> updateTodo(@Valid @RequestBody TodoDTO todoDTO,
                                             @PathVariable Long todoId){
        TodoDTO savedTodoDTO = todoService.updateTodo(todoDTO, todoId);
        return new ResponseEntity<>(savedTodoDTO, HttpStatus.OK);

    }
}
