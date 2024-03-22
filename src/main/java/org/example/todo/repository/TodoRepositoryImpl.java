package org.example.todo.repository;


import org.example.todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final List<Todo> todoList;
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Autowired
    public TodoRepositoryImpl(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @Override
    public Todo getTodoById(Long id) {
        Optional<Todo> todoOptional = todoList.stream().filter(todo -> todo.getId() == id).findFirst();
        return todoOptional.orElse(null);
    }

    @Override
    public void addTodo(Todo todo) {

    }

    @Override
    public void updateTodo(Todo todo) {

    }

    @Override
    public void deleteTodoById(int id) {

    }

    @Override
    public void deleteTodoById(Long id) {

    }

    @Override
    public List<Todo> searchTodosByTask(String task) {
        return null;
    }

    @Override
    public List<Todo> searchTodosByIsDone(Boolean isDone) {
        return null;
    }

    @Override
    public List<Todo> searchTodosByTaskContaining(String task) {
        return null;
    }

    @Override
    public List<Todo> searchTodosByTaskContainingAndIsDone(String task, Boolean isDone) {
        return null;
    }

    public static LocalDateTime formatLocalDateTime(LocalDateTime dateTime) {
        String pattern = "dd-MM-yyyy hh:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTimeString = dateTime.format(formatter);
        return LocalDateTime.parse(formattedDateTimeString, formatter);
    }
}