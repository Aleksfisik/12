package ru.org.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.org.spring.model.User;
import ru.org.spring.dao.UserDao;
import ru.org.spring.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    public AdminController(UserService userService) {
        this.userService = userService;
    }




    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list.html";
    }


    @GetMapping("/user-create")
    public String newPerson(@ModelAttribute("user") User person) {
        return "user-create.html";
    }

    @PostMapping("/user-create")
    public String create(@ModelAttribute("user") User person) {
        userService.save(person);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getById(id));
        return "user-update.html";
    }

    @PutMapping("/user-update/up/{id}")
    public String update(@ModelAttribute("user") User person,
                         @PathVariable("id") long id) {

        userService.update(id, person);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
