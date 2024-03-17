package org.example.todo.Controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import org.example.todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {
    @Autowired
    private List<Todo> todoList;

    @GetMapping("/todo")
    public String todoList(Model model) {
        model.addText("todos");
        return "todo-list";
    }

    @GetMapping("/todo/{id}")
    public String getTodoById(@PathVariable Long id, Model model) {
        Todo todo = todoList.stream()
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
        todoList.add(todo);
        return "redirect:/todo";
    }

    @GetMapping("/todo/edit/{id}")
    public String showEditTodoForm(@PathVariable Long id, Model model) {
        Todo todo = todoList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (todo == null) {
            return "redirect:/todo";
        }
        model.addAttribute("todo", todo);
        return "edit-todo";
    }

    @PostMapping("/todo/edit/{id}")
    public String editTodo(@PathVariable Long id, @ModelAttribute Todo updatedTodo) {
        Todo todo = todoList.stream()
                .filter(t -> t.getId().equals(id))
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
        todoList.removeIf(todo -> todo.getId().equals(id));
        return "redirect:/todo";
    }

    @GetMapping("/todo/search")
    public String searchTodo(@RequestParam(required = false) String task,
                             @RequestParam(required = false) Boolean isDone,
                             Model model) {
        List<Todo> searchResults = todoList.stream()
                .filter(todo -> (task == null || todo.getTask().contains(task))
                        && (isDone == null || todo.isDone() == isDone))
                .collect(Collectors.toList());
        model.addAttribute("todos", searchResults);
        return "todo-list";
    }
}
