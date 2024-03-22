package org.example.todo.Service;

import org.example.todo.Model.Todo;
import org.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.getAllTodos();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.getTodoById(id);
    }

    @Override
    public void addTodo(Todo todo) {
    todoRepository.addTodo(todo);
    }

    @Override
    public void updateTodo(Todo todo) {
    todoRepository.updateTodo(todo);
    }

    @Override
    public void deleteTodoById(int id) {
    todoRepository.deleteTodoById(id);
    }

    @Override
    public List<Todo> searchTodosByTask(String task) {
        return todoRepository.searchTodosByTask(task);
    }

    @Override
    public List<Todo> searchTodosByIsDone(Boolean isDone) {
        return todoRepository.searchTodosByIsDone(isDone);
    }

    @Override
    public List<Todo> searchTodosByTaskContaining(String task) {
        return todoRepository.searchTodosByTaskContaining(task);
    }

    @Override
    public List<Todo> searchTodosByTaskContainingAndIsDone(String task, Boolean isDone) {
        return todoRepository.searchTodosByTaskContainingAndIsDone(task,isDone);
    }
}
