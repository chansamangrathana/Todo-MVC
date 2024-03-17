package org.example.todo.Datasource;

import org.example.todo.Model.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TodoListDataSourceConfig {
    @Bean
    public List<Todo> todoList() {
        return new ArrayList<>(){{
            add(new Todo(1, "Task 1","Hello 1", false, LocalDateTime.now()));
            add(new Todo(2, "Task 2","Hi ", false, LocalDateTime.now()));
            add(new Todo(3, "Task 3","I'm fine", false, LocalDateTime.now()));
            add(new Todo(4, "Task 4","Are you ok", false, LocalDateTime.now()));

        }};
    }
}
