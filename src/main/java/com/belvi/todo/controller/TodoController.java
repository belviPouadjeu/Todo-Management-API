package com.belvi.todo.controller;

import com.belvi.todo.model.Todo;
import com.belvi.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/public/todos")
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> todos = todoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }

    @PostMapping("/public/todos")
    public ResponseEntity<String> addTodo(@Valid @RequestBody Todo todo){
        todoService.addTodo(todo);
        return new ResponseEntity<>("Todo added successfully", HttpStatus.CREATED);
    }


    @DeleteMapping("/admin/todos/{todoId}")
    public ResponseEntity<String> deletetodo(@PathVariable Long todoId){
        String status = todoService.deleteTodo(todoId);
        return new ResponseEntity<>(status, HttpStatus.OK);
        /*try {
            String status = todoService.deleteTodo(todoId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }*/
    }


    @PutMapping("/admin/todos/{todoId}")
    public ResponseEntity<String> updateTodo(@Valid @RequestBody Todo todo,
                                             @PathVariable Long todoId){
        Todo savedTodo = todoService.updateTodo(todo, todoId);
        return new ResponseEntity<>("Todo with todoId : " + todoId, HttpStatus.OK);
        /*try {
            Todo savedTodo = todoService.updateTodo(todo, todoId);
            return new ResponseEntity<>("Todo with todoId : " + todoId, HttpStatus.OK);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }*/
    }
}
