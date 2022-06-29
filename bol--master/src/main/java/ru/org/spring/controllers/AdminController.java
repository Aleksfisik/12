package ru.org.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.org.spring.model.User;
import ru.org.spring.services.UserService;
import ru.org.spring.dao.UserDao;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserDao userDao;
    @Autowired
    public AdminController(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDao.index1());
        return "user-list.html";
    }


    @GetMapping("/user-create")
    public String newPerson(@ModelAttribute("user") User person) {
        return "user-create.html";
    }

    @PostMapping("/user-create")
    public String create(@ModelAttribute("user") User person) {
        userDao.save1(person);
        return "redirect:/admin";
    }

    @GetMapping("/user-update/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userDao.show1(id));
        return "user-update.html";
    }

    @PostMapping("/user-update/up/{id}")
    public String update(@ModelAttribute("user") User person,
                         @PathVariable("id") long id) {

        userDao.update1(id, person);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userDao.delete1(id);
        return "redirect:/admin";
    }
}
