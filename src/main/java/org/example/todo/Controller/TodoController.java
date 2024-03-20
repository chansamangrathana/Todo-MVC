package org.example.todo.Controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.example.todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TodoController {
    @Autowired
    private List<Todo> TodoService;

    @GetMapping("/todo")
    public String todoList(Model model) {
        model.addText("todos");
        return "todo-list";
    }

    @GetMapping("/todo/{id}")
    public String getTodoById(@PathVariable Long id, Model model) {
        Todo todo = TodoService.stream()
                .filter(t -> t.getId()==id)
                .findFirst()
                .orElse(null);
        if (todo == null) {
            return "redirect:/todo";
        }
        model.addText("todo");
        return "todo-details";
    }

    @GetMapping("/todo/new")
    public String showAddTodoForm(Model model) {
        model.addText("todo");
        return "add-todo";
    }

    @PostMapping("/todo/new")
    public String addTodo(@ModelAttribute Todo todo) {
        todo.setId(System.currentTimeMillis()); // Assign a unique ID
        todo.setCreatedAt(LocalDateTime.now()); // Set creation timestamp
        TodoService.add(todo);
        return "redirect:/todo";
    }

    @GetMapping("/todo/edit/{id}")
    public String showEditTodoForm(@PathVariable Long id, Model model) {
        Todo todo = TodoService.stream()
                .filter(t -> t.getId()==id)
                .findFirst()
                .orElse(null);
        if (todo == null) {
            return "redirect:/todo";
        }
        model.addText("todo");
        return "edit-todo";
    }

    @PostMapping("/todo/edit/{id}")
    public String editTodo(@PathVariable Long id, @ModelAttribute Todo updatedTodo) {
        Todo todo = TodoService.stream()
                .filter(t -> t.getId()==id)
                .findFirst()
                .orElse(null);
        if (todo == null) {
            return "redirect:/todo";
        }
        todo.setTask(updatedTodo.getTask());
        todo.setDescription(updatedTodo.getDescription());
        todo.setDone(updatedTodo.isDone());
        return "redirect:/todo";
    }

    @GetMapping("/todo/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        TodoService.removeIf(todo -> todo.getId()==id);
        return "redirect:/todo";
    }

    @GetMapping("/todo/search")
    public String searchTodo(@RequestParam(required = false) String task,
                             @RequestParam(required = false) Boolean isDone,
                             Model model) {
        List<Todo> searchResults = TodoService.stream()
                .filter(todo -> (task == null || todo.getTask().contains(task))
                        && (isDone == null || todo.isDone() == isDone))
                .collect(Collectors.toList());
        model.addText("todos");
        return "todo-list";
    }
}
