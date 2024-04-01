package org.example.todo.repository;


import org.example.todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
    public static LocalDateTime formatLocalDateTime(LocalDateTime dateTime) {
        String pattern="dd-MM-yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTimeString = dateTime.format(formatter);
        return LocalDateTime.parse(formattedDateTimeString, formatter);
    }

    @Override
    public void addTodo(Todo todo) {
        todo.setId((long) idGenerator.getAndIncrement());
        todo.setCreatedAt(formatLocalDateTime(LocalDateTime.now()));
        todoList.add(todo);
    }
    @Override
    public void updateTodo(Todo todo) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId()== todo.getId()) {
                LocalDateTime createdAt = todoList.get(i).getCreatedAt();
                todo.setCreatedAt(createdAt);
                todoList.set(i, todo);
                break;
            }
        }
    }

    @Override
    public void deleteTodoById(int id) {
        todoList.removeIf(todo -> todo.getId()==(id));
    }


    @Override
    public void deleteTodoById(Long id) {
        todoList.removeIf(todo -> todo.getId()==(id));
    }


    @Override
    public List<Todo> searchTodosByTask(String task) {
        return todoList.stream()
                .filter(todo -> todo.getTask().contains(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByIsDone(Boolean isDone) {
        return todoList.stream()
                .filter(todo -> todo.getIsDone()
                        == isDone)
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByTaskContaining(String task) {
        return todoList.stream()
                .filter(todo -> todo.getTask().contains(task))
                .collect(Collectors.toList());
    }

    @Override
    public List<Todo> searchTodosByTaskContainingAndIsDone(String task, Boolean isDone) {
        return todoList.stream()
                .filter(todo -> todo.getTask().contains(task) && todo.getIsDone() == isDone)
                .collect(Collectors.toList());
    }
}