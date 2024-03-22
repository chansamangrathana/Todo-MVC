package org.example.todo.repository;


import org.example.todo.Model.Todo;

import java.util.List;

public interface TodoRepository {
 List<Todo> getAllTodos();
 Todo getTodoById(Long id);
 void addTodo(Todo todo);
 void updateTodo(Todo todo);

 void deleteTodoById(int id);

 void deleteTodoById(Long id);

 List<Todo> searchTodosByTask(String task);

 List<Todo> searchTodosByIsDone(Boolean isDone);

 List<Todo> searchTodosByTaskContaining(String task);

 List<Todo> searchTodosByTaskContainingAndIsDone(String task, Boolean isDone);
}
