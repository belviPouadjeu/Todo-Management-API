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
        //todo.setTodId(nextId++);
        todoRepository.save(todo);

    }

    @Override
    public String deleteTodo(Long todoId) {
        List<Todo> todos = todoRepository.findAll();

        Todo todo = todos.stream()
                .filter(t -> t.getTodId().equals(todoId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        todos.remove(todo);
        return "Todo with todoId " + todoId + " deleted successfully";
    }

    @Override
    public Todo updateTodo(Todo todo, Long todoId) {
        List<Todo> todos = todoRepository.findAll();

        Optional<Todo> optionalTodo = todos.stream()
                .filter(t -> t.getTodId().equals(todoId))
                .findFirst();

        if (optionalTodo.isPresent()){
            Todo existingTodo = optionalTodo.get();
            existingTodo.setTitle(todo.getTitle());
            existingTodo.setDescription(todo.getDescription());
            existingTodo.setStatus(todo.getStatus());

            return todoRepository.save(existingTodo);

        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }

    }
}
