package com.belvi.todo.repository;

import com.belvi.todo.model.Todo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByTitle(String title);

    List<Todo> findByStatus(String status);
}
