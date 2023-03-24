package com.example.secure.controller;


import com.example.secure.entity.User;
import com.example.secure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    private final UserService serv;
    @Autowired
    public AdminController(UserService serv) {
        this.serv = serv;
    }

    @RequestMapping(value = "/admin")
    public String usersModels(Model model) {
        model.addAttribute("users", serv.getAll());
        return "admin";
    }
    @GetMapping("/new")
    public String createUserForm(Model model, User user) {
        model.addAttribute("user", user);
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        serv.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Long id) {
        serv.removeById(id);
        return "redirect:/admin";
    }
    @PostMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", serv.findById(id));
        return "update";
    }
    @PatchMapping("/update/{id}")
    public String updateUser(@ModelAttribute("person") User user, @PathVariable("id") Long id) {
        serv.update(user, id);
        return "redirect:/admin";
    }
}
