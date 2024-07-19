package com.example.todo_application.controllers;

import com.example.todo_application.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller

public class HomeController {
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/") // root controller handler
    public String root() {
        return "redirect:/today";
    }

    @GetMapping("/today")
    public ModelAndView getTodayPage() {
        ModelAndView modelAndView = new ModelAndView("today");
        modelAndView.addObject("todoItems", todoItemService.getAll());

        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd MMM"));

        modelAndView.addObject("currentDate", formattedDate);
        return modelAndView;
    }
}
