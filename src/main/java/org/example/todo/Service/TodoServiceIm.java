package org.example.todo.Service;

import lombok.RequiredArgsConstructor;
import org.example.todo.Model.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final List<Todo> todoList;

    @Override
    public List<Todo> getAllTodos() {
        return todoList;
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoList.stream()
                .filter(todo -> todo.getId()==id)
                .findFirst().orElse(null);
    }

    @Override
    public void addTodo(Todo todo) {
        todo.setId(System.currentTimeMillis());
        todo.setCreatedAt(LocalDateTime.now());
        todoList.add(todo);
    }

    @Override
    public void updateTodo(Long id, Todo updatedTodo) {
        Optional<Todo> existingTodo = Optional.ofNullable(getTodoById(id));
        existingTodo.ifPresent(todo -> {
            todo.setTask(updatedTodo.getTask());
            todo.setDescription(updatedTodo.getDescription());
            todo.setDone(updatedTodo.isDone());
        });
    }

    @Override
    public void deleteTodoById(Long id) {
        todoList.removeIf(todo -> todo.getId()==id);
    }

    @Override
    public List<Todo> searchTodos(String task, Boolean isDone) {
        return todoList.stream()
                .filter(todo -> (task == null || todo.getTask().contains(task))
                        && (isDone == null || todo.isDone() == isDone))
                .collect(Collectors.toList());
    }
}
