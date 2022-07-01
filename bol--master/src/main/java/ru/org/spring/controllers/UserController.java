package ru.org.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.org.spring.model.User;
import ru.org.spring.services.UserDetailsServiceImpl;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserDetailsServiceImpl userService;

    public UserController(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/{name}")
    public String One(@PathVariable("name") String name, Model model) {
        User user = userService.findByName(name);
        model.addAttribute("user", user);
        return "user.html";

    }
}
