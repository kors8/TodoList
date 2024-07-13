package com.example.todo_application.controllers;

import com.example.todo_application.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HomeController {
    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/") // root controller handler
    public ModelAndView root(){
        ModelAndView modelAndView = new ModelAndView("root");
        modelAndView.addObject("todoItems", todoItemService.getAll());
        return modelAndView;
    }
}
