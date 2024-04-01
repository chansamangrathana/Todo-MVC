package org.example.todo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Todo {
    private long id;
    private String task;
    private String description;
    private boolean IsDone;
    private LocalDateTime createdAt;


    public Boolean getIsDone() {
        return null;
    }
}
