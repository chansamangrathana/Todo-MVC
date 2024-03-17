package org.example.todo.Service;

import org.example.todo.Model.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> getAllTodos();
    Todo getTodoById(Long id);
    void addTodo(Todo todo);
    void updateTodo(Long id, Todo updatedTodo);
    void deleteTodoById(Long id);
    List<Todo> searchTodos(String task, Boolean isDone);
}

