package com.belvi.todo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    private Long todId;
    private String title;
    private String description;
    private String status;
    // status : done, in progress, to do

}
