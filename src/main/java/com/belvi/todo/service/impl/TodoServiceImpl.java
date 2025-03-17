package com.belvi.todo.service.impl;

import com.belvi.todo.execeptions.APIException;
import com.belvi.todo.execeptions.ResourceNotFoundException;
import com.belvi.todo.model.Todo;
import com.belvi.todo.payload.TodoDTO;
import com.belvi.todo.payload.TodoResponse;
import com.belvi.todo.repository.TodoRepository;
import com.belvi.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    //private List<Todo> todos = new ArrayList<>();
    //private Long nextId = 1L;
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;

    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TodoResponse getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        if (todos.isEmpty()){
            throw new APIException("No todo added until now . ");
        }

        List<TodoDTO> todoDTOS = todos.stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .toList();

        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setContent(todoDTOS);
        return  todoResponse;

    }


    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        Todo todo = modelMapper.map(todoDTO, Todo.class);

        if (todo.getStatus() == null) {
            throw new IllegalArgumentException("Status cannot be null. Allowed values are: 'To do', 'In progress', 'Done'.");
        }

        String status = todo.getStatus().toString();

        Todo todoFromDb = todoRepository.findByTitle(todo.getTitle());

        if (!status.equalsIgnoreCase("To do") &&
                !status.equalsIgnoreCase("In progress") &&
                !status.equalsIgnoreCase("Done")) {
            throw new IllegalArgumentException("Invalid status. Allowed values are: 'To do', 'In progress', 'Done'.");
        }

        if (todoFromDb != null) {
            throw new APIException("Todo with the name " + todo.getTitle() + " Already exists !!");
        }

        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDTO.class);
    }


    @Override
    public TodoDTO deleteTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "TodoId", todoId));

        todoRepository.delete(todo);
        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO, Long todoId) {
        Todo savedTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "TodoId", todoId));

        Todo todo = modelMapper.map(todoDTO, Todo.class);

        todo.setTodId(todoId);
        savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDTO.class);

    }
}
