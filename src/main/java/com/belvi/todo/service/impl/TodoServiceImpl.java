package com.belvi.todo.service.impl;

import com.belvi.todo.execeptions.APIException;
import com.belvi.todo.execeptions.ResourceNotFoundException;
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
        List<Todo> todos = todoRepository.findAll();
        if (todos.isEmpty()){
            throw new APIException("No todo added until now . ");
        }
        return  todos;

    }


    @Override
    public void addTodo(Todo todo) {
        String status = todo.getStatus().toString();
        Todo savedTodo= todoRepository.findByTitle(todo.getTitle());
        if (savedTodo != null){
            throw new APIException("Todo with the name " + todo.getTitle() + " Already exists !!");
        }

        if (!status.equalsIgnoreCase("To do") &&
                !status.equalsIgnoreCase("In progress") &&
                !status.equalsIgnoreCase("Done")) {
            throw new IllegalArgumentException("Invalid status. Allowed values are: 'To do', 'In progress', 'Done'.");
        }

        todoRepository.save(todo);
    }

    @Override
    public String deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "TodoId", todoId));

        todoRepository.delete(todo);
        return "Todo with todoId : " + todoId + " deleted sucessfully";
    }

    @Override
    public Todo updateTodo(Todo todo, Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "TodoId", todoId));

        todo.setTodId(todoId);
        savedTodo = todoRepository.save(todo);
        return savedTodo;

    }
}
