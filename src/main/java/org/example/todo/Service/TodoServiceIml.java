package org.example.todo.Service;

import org.example.todo.Model.Todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceIml implements TodoService{
    private List<Todo> todoList;

    public void TodoServiceImpl() {
        this.todoList = new ArrayList<>();
    }

    public TodoServiceIml(List<Todo> todoList) {
        this.todoList = todoList;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoList.stream()
                .filter(todo -> todo.getId()==id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addTodo(Todo todo) {
        todo.setId(System.currentTimeMillis());
        todo.setCreatedAt(LocalDateTime.now());
        todoList.add(todo);
    }

    @Override
    public void updateTodo(Long id, Todo updatedTodo) {
        Todo todo = getTodoById(id);
        if (todo != null) {
            todo.setTask(updatedTodo.getTask());
            todo.setDescription(updatedTodo.getDescription());
            todo.setDone(updatedTodo.isDone());
        }
    }

    @Override
    public void deleteTodoById(Long id) {
        todoList.removeIf(todo -> todo.getId()==id);
    }

    @Override
    public List<Todo> searchTodos(String task, Boolean isDone) {
            return todoList.stream().filter(todo-> todo.getTask().contains(task) && todo.isDone() == isDone)
                    .collect(Collectors.toList());
    }
}
