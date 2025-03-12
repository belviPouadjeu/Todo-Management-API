package com.belvi.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todId;

    @Size(min = 5, max = 30, message = "Todo title must contain at least five characters and not exceed 30")
    @NotBlank
    private String title;

    @Size(min = 5, max = 100, message = "Todo description must contain at least five characters and not exceed 100")
    @NotBlank
    private String description;
    private String status;
    // status : done, in progress, to do

}
