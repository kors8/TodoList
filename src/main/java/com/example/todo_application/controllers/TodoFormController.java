package com.example.todo_application.controllers;

import org.springframework.ui.Model;
import com.example.todo_application.models.TodoItem;
import com.example.todo_application.services.TodoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller

public class TodoFormController {
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/create-todo")
    public String showCreateFrom(TodoItem todoItem){
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model){
        if(result.hasErrors()){
            return "new-todo-item";
        }

        todoItem.setIsComplete(false);
        todoItemService.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("/delete-todo/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model){
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id " + id + " not fond"));
        todoItemService.delete(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit-todo/{id}")
    public String editTodoItem(@PathVariable("id") Long id, Model model){
        TodoItem todoItem = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id " + id + " not fond"));
        model.addAttribute("todoItem", todoItem);
        return "edit-todo";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid TodoItem todoItem, BindingResult result) {
        if (result.hasErrors()){
            return "edit-todo";
        }

        TodoItem item = todoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id " + id + " not found"));

        item.setDescription(todoItem.getDescription());
        item.setIsComplete(todoItem.getIsComplete());

        item.setUpdatedAt(Instant.now());

        todoItemService.save(item);

        return "redirect:/";
    }
}
